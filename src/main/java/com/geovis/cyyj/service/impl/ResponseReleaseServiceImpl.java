package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.DeliverResponseDTO;
import com.geovis.cyyj.dto.ResponseReleaseQueryDTO;
import com.geovis.cyyj.mapper.DistrictListPersonMapper;
import com.geovis.cyyj.mapper.ResponseReleaseMapper;
import com.geovis.cyyj.mapper.ResponseReceiveMapper;
import com.geovis.cyyj.po.DistrictListPersonPO;
import com.geovis.cyyj.po.ResponseReleasePO;
import com.geovis.cyyj.po.ResponseReceivePO;
import com.geovis.cyyj.service.IResponseReleaseService;
import com.geovis.cyyj.service.IResponseReceiveService;
import com.geovis.cyyj.vo.ResponseReleaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 响应下发 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseReleaseServiceImpl extends ServiceImpl<ResponseReleaseMapper, ResponseReleasePO> implements IResponseReleaseService {

    @Autowired
    private final ResponseReleaseMapper responseReleaseMapper;

    @Autowired
    private final ResponseReceiveMapper responseReceiveMapper;

    @Autowired
    private IResponseReceiveService iResponseReceiveService;

    @Autowired
    private final DistrictListPersonMapper districtListPersonMapper;
    /**
     * 分页查询响应下发列表
     */
    @Override
    public TableDataInfo<ResponseReleaseVO> queryMainList(ResponseReleaseQueryDTO responseReleaseQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<ResponseReleasePO> lqw = buildQueryWrapper(responseReleaseQueryDTO);
        Page<ResponseReleaseVO> result = responseReleaseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<ResponseReleasePO> buildQueryWrapper(ResponseReleaseQueryDTO responseReleaseQueryDTO) {
        LambdaQueryWrapper<ResponseReleasePO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(responseReleaseQueryDTO.getKeyWord()), ResponseReleasePO::getTitle, responseReleaseQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(responseReleaseQueryDTO.getUserId()), ResponseReleasePO::getUserId, responseReleaseQueryDTO.getUserId());
        lqw.ge(responseReleaseQueryDTO.getStartTime() != null, ResponseReleasePO::getCreateTime, responseReleaseQueryDTO.getStartTime());
        lqw.le(responseReleaseQueryDTO.getEndTime() != null, ResponseReleasePO::getCreateTime, responseReleaseQueryDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布响应
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deliverResponse(DeliverResponseDTO deliverResponseDTO) {
        if(StringUtils.isEmpty(deliverResponseDTO.getReceiveUnit())){
            log.error("响应下发 receiveUnit is null, userid : " + deliverResponseDTO.getUserId());
            return false;
        }
        ResponseReleasePO responseReleasePO = BeanCopyUtils.copy(deliverResponseDTO, ResponseReleasePO.class);
        int responseRelease = responseReleaseMapper.insertResponseRelease(responseReleasePO);
        if(responseRelease < 1){
            log.error("响应下发 insert into responseRelease failed, userid : " + deliverResponseDTO.getUserId());
            return false;
        }
        ResponseReleasePO insertResponseReleaseResult = responseReleaseMapper.selectById(responseReleasePO.getId());
        if(insertResponseReleaseResult != null){
            //如果下发新增成功了，
            DeliverResponseDTO deliverResponse2ReceiveDTO = new DeliverResponseDTO();
            deliverResponse2ReceiveDTO.setTitle(insertResponseReleaseResult.getTitle());
            deliverResponse2ReceiveDTO.setReleaseTime(insertResponseReleaseResult.getReleaseTime());
            deliverResponse2ReceiveDTO.setType(insertResponseReleaseResult.getType());
            deliverResponse2ReceiveDTO.setStatus(insertResponseReleaseResult.getStatus());
            deliverResponse2ReceiveDTO.setReceiveUnit("");
            deliverResponse2ReceiveDTO.setFilePath("");
            deliverResponse2ReceiveDTO.setResponseContent(insertResponseReleaseResult.getResponseContent());
            deliverResponse2ReceiveDTO.setParentUserId(insertResponseReleaseResult.getUserId());
            deliverResponse2ReceiveDTO.setResponseReleaseId(responseReleasePO.getId());
            //这里暂时查询本地表人员
            //用查询到的接收单位list找出该给哪些单位发信息
            LambdaQueryWrapper<DistrictListPersonPO> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(DistrictListPersonPO::getOrgName, deliverResponseDTO.getReceiveUnit());
            List<DistrictListPersonPO> districtListPersonPOList = districtListPersonMapper.selectList(lambdaQueryWrapper);
            Map<String, String> revceiveMap = new HashMap<>();
            for(DistrictListPersonPO districtListPersonPO : districtListPersonPOList){
                if(districtListPersonPO.getOrgName().equals(deliverResponseDTO.getReceiveUnit())){
                    revceiveMap.put(districtListPersonPO.getUserName(), districtListPersonPO.getUserId());
                }
            }
            //给各个接收单位发响应
            for(Map.Entry<String, String> entry : revceiveMap.entrySet()){
                deliverResponse2ReceiveDTO.setUserId(entry.getValue());
                Boolean insertResult = iResponseReceiveService.deliverResponse(deliverResponse2ReceiveDTO);
                if(!insertResult){
//                    log.error("insert into receive unit failed, userid is : " + deliverResponse2ReceiveDTO.getUserId() + " parentid is : " + deliverResponse2ReceiveDTO.getParentUserId());
                    throw new RuntimeException("insert into receive unit failed, userid is : " + deliverResponse2ReceiveDTO.getUserId() + " parentid is : " + deliverResponse2ReceiveDTO.getParentUserId());
                }
            }
            return true;
        }else {
            log.error("响应下发 insertResponseReleaseResult is false, user_id = " + deliverResponseDTO.getUserId());
            return false;
        }
    }

    /**
     * 响应操作
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operateResponse(Integer responseReleaseId, String operateType) {
        ResponseReleasePO bizDistributePO = responseReleaseMapper.selectById(responseReleaseId);
        LambdaUpdateWrapper<ResponseReleasePO> luw = Wrappers.lambdaUpdate();
        int resultWithdrawNum = 0;
        int resultEndNum = 0;
        if("withdraw".equals(operateType)){
            //撤回响应之后下发方消息还在，状态变成已撤回，接收方消息删除
            luw.eq(ResponseReleasePO::getId, responseReleaseId);
            bizDistributePO.setStatus("已撤回");
            resultWithdrawNum = responseReleaseMapper.update(bizDistributePO, luw);
            if(resultWithdrawNum == 0){
             log.warn("下发响应表撤回失败，responseReleaseId： " + responseReleaseId);
            }
            //删除响应接收方内容
            LambdaUpdateWrapper<ResponseReceivePO> receiveLuw = Wrappers.lambdaUpdate();
            receiveLuw.eq(responseReleaseId != null, ResponseReceivePO::getResponseReleaseId, responseReleaseId);
            int deleteNum = responseReceiveMapper.delete(receiveLuw);
            resultWithdrawNum = deleteNum + 1;
        } else if ("end".equals(operateType)) {
            luw.eq(ResponseReleasePO::getId, responseReleaseId);
            bizDistributePO.setStatus("结束");
            resultEndNum = responseReleaseMapper.update(bizDistributePO, luw);
        }
        if((resultEndNum == 1) || (resultWithdrawNum == 2)){
            return true;
        }else {
            return false;
        }
    }

}
