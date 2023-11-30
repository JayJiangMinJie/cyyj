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
import com.geovis.cyyj.dto.DeliverWarningDTO;
import com.geovis.cyyj.dto.DisasterWarningQueryDTO;
import com.geovis.cyyj.mapper.DistrictListPersonMapper;
import com.geovis.cyyj.mapper.DisasterWarningMapper;
import com.geovis.cyyj.mapper.WarningReceiveMapper;
import com.geovis.cyyj.po.DistrictListPersonPO;
import com.geovis.cyyj.po.DisasterWarningPO;
import com.geovis.cyyj.po.WarningReceivePO;
import com.geovis.cyyj.service.IDisasterWarningService;
import com.geovis.cyyj.service.IWarningReceiveService;
import com.geovis.cyyj.vo.DisasterWarningVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 预警下发 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DisasterWarningServiceImpl extends ServiceImpl<DisasterWarningMapper, DisasterWarningPO> implements IDisasterWarningService {

    @Autowired
    private final DisasterWarningMapper disasterWarningMapper;

    @Autowired
    private final WarningReceiveMapper warningReceiveMapper;

    @Autowired
    private IWarningReceiveService iWarningReceiveService;

    @Autowired
    private final DistrictListPersonMapper districtListPersonMapper;
    /**
     * 分页查询预警下发列表
     */
    @Override
    public TableDataInfo<DisasterWarningVO> queryMainList(DisasterWarningQueryDTO disasterWarningQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<DisasterWarningPO> lqw = buildQueryWrapper(disasterWarningQueryDTO);
        Page<DisasterWarningVO> result = disasterWarningMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<DisasterWarningPO> buildQueryWrapper(DisasterWarningQueryDTO disasterWarningDTO) {
        LambdaQueryWrapper<DisasterWarningPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(disasterWarningDTO.getKeyWord()), DisasterWarningPO::getTitle, disasterWarningDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(disasterWarningDTO.getUserId()), DisasterWarningPO::getUserId, disasterWarningDTO.getUserId());
        lqw.ge(disasterWarningDTO.getStartTime() != null, DisasterWarningPO::getCreateTime, disasterWarningDTO.getStartTime());
        lqw.le(disasterWarningDTO.getEndTime() != null, DisasterWarningPO::getCreateTime, disasterWarningDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布预警
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deliverWarning(DeliverWarningDTO deliverWarningDTO) {
        if(StringUtils.isEmpty(deliverWarningDTO.getReceiveUnit())){
            log.error("预警下发 receiveUnit is null, userid : " + deliverWarningDTO.getUserId());
            return false;
        }
        DisasterWarningPO disasterWarningPO = BeanCopyUtils.copy(deliverWarningDTO, DisasterWarningPO.class);
        disasterWarningPO.setStatus("预警中");
        disasterWarningPO.setReleaseTime(LocalDateTime.now());
        int disasterWarning = disasterWarningMapper.insertDisasterWarning(disasterWarningPO);
        if(disasterWarning < 1){
            log.error("预警下发 insert into disasterWarning failed, userid : " + deliverWarningDTO.getUserId());
            return false;
        }
        DisasterWarningPO insertDisasterWarningResult = disasterWarningMapper.selectById(disasterWarningPO.getId());
        if(insertDisasterWarningResult != null){
            //如果下发新增成功了，
            DeliverWarningDTO deliverWarning2ReceiveDTO = new DeliverWarningDTO();
            deliverWarning2ReceiveDTO.setTitle(insertDisasterWarningResult.getTitle());
            deliverWarning2ReceiveDTO.setContent(insertDisasterWarningResult.getContent());
            deliverWarning2ReceiveDTO.setReleaseTime(insertDisasterWarningResult.getReleaseTime());
            deliverWarning2ReceiveDTO.setType(insertDisasterWarningResult.getType());
            deliverWarning2ReceiveDTO.setStatus(insertDisasterWarningResult.getStatus());
            deliverWarning2ReceiveDTO.setReceiveUnit("");
            deliverWarning2ReceiveDTO.setFilePath("");
            deliverWarning2ReceiveDTO.setWarningContent(insertDisasterWarningResult.getWarningContent());
            deliverWarning2ReceiveDTO.setParentUserId(insertDisasterWarningResult.getUserId());
            deliverWarning2ReceiveDTO.setDisasterWarningId(disasterWarningPO.getId());
            //这里暂时查询本地表人员
            //用查询到的接收单位list找出该给哪些单位发信息
            String[] unitArrays = deliverWarningDTO.getReceiveUnit().split(",");
            List<String> unitList = Arrays.asList(unitArrays);
            LambdaQueryWrapper<DistrictListPersonPO> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.in(DistrictListPersonPO::getOrgName, unitList);
            List<DistrictListPersonPO> districtListPersonPOList = districtListPersonMapper.selectList(lambdaQueryWrapper);
            Map<String, String> revceiveMap = new HashMap<>();
            for(DistrictListPersonPO districtListPersonPO : districtListPersonPOList){
                revceiveMap.put(districtListPersonPO.getOrgName(), districtListPersonPO.getUserId());
            }
            //给各个接收单位发预警
            for(Map.Entry<String, String> entry : revceiveMap.entrySet()){
                deliverWarning2ReceiveDTO.setUserId(entry.getValue());
                Boolean insertResult = iWarningReceiveService.deliverWarning(deliverWarning2ReceiveDTO);
                if(!insertResult){
                    throw new RuntimeException("insert into receive unit failed, userid is : " + deliverWarning2ReceiveDTO.getUserId() + " parentid is : " + deliverWarning2ReceiveDTO.getParentUserId());
                }
            }
            return true;
        }else {
            log.error("预警下发 insertDisasterWarningResult is false, user_id = " + deliverWarningDTO.getUserId());
            return false;
        }
    }

    /**
     * 预警操作
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operateWarning(Integer disasterWarningId, String userId, String operateType) {
        DisasterWarningPO bizDistributePO = disasterWarningMapper.selectById(disasterWarningId);
        LambdaUpdateWrapper<DisasterWarningPO> luw = Wrappers.lambdaUpdate();
        int resultWithdrawNum = 0;
        int resultEndNum = 0;
        if("withdraw".equals(operateType)){
            //撤回预警之后下发方消息还在，状态变成已撤回，接收方消息删除
            luw.eq(DisasterWarningPO::getId, disasterWarningId);
            bizDistributePO.setStatus("已撤回");
            resultWithdrawNum = disasterWarningMapper.update(bizDistributePO, luw);
            if(resultWithdrawNum == 0){
             log.warn("下发预警表撤回失败，disasterWarningId： " + disasterWarningId);
            }
            //删除预警接收方内容
            LambdaUpdateWrapper<WarningReceivePO> receiveLuw = Wrappers.lambdaUpdate();
            receiveLuw.eq(disasterWarningId != null, WarningReceivePO::getDisasterWarningId, disasterWarningId);
//            receiveLuw.eq(StringUtils.isNotEmpty(userId), WarningReceivePO::getUserId, userId);
            int deleteNum = warningReceiveMapper.delete(receiveLuw);
            resultWithdrawNum = deleteNum + 1;
        } else if ("end".equals(operateType)) {
            luw.eq(DisasterWarningPO::getId, disasterWarningId);
            bizDistributePO.setStatus("结束");
            resultEndNum = disasterWarningMapper.update(bizDistributePO, luw);
        }
        if((resultEndNum == 1) || (resultWithdrawNum == 2)){
            return true;
        }else {
            return false;
        }
    }

}
