package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeDistributeQueryDTO;
import com.geovis.cyyj.mapper.DistrictListPersonMapper;
import com.geovis.cyyj.mapper.NoticeDistributeMapper;
import com.geovis.cyyj.mapper.NoticeReceiveMapper;
import com.geovis.cyyj.po.DistrictListPersonPO;
import com.geovis.cyyj.po.NoticeDistributePO;
import com.geovis.cyyj.po.NoticeReceivePO;
import com.geovis.cyyj.service.INoticeDistributeService;
import com.geovis.cyyj.service.INoticeReceiveService;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 通知下发 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeDistributeServiceImpl extends ServiceImpl<NoticeDistributeMapper, NoticeDistributePO> implements INoticeDistributeService {

    @Autowired
    private final NoticeDistributeMapper noticeDistributeMapper;

    @Autowired
    private final NoticeReceiveMapper noticeReceiveMapper;

    @Autowired
    private INoticeReceiveService iNoticeReceiveService;

    @Autowired
    private final DistrictListPersonMapper districtListPersonMapper;
    /**
     * 分页查询通知下发列表
     */
    @Override
    public TableDataInfo<NoticeDistributeVO> queryMainList(NoticeDistributeQueryDTO noticeDistributeQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<NoticeDistributePO> lqw = buildQueryWrapper(noticeDistributeQueryDTO);
        Page<NoticeDistributeVO> result = noticeDistributeMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<NoticeDistributePO> buildQueryWrapper(NoticeDistributeQueryDTO noticeDistributeQueryDTO) {
        LambdaQueryWrapper<NoticeDistributePO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(noticeDistributeQueryDTO.getKeyWord()), NoticeDistributePO::getTitle, noticeDistributeQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(noticeDistributeQueryDTO.getUserId()), NoticeDistributePO::getUserId, noticeDistributeQueryDTO.getUserId());
        lqw.ge(noticeDistributeQueryDTO.getStartTime() != null, NoticeDistributePO::getStartTime, noticeDistributeQueryDTO.getStartTime());
        lqw.le(noticeDistributeQueryDTO.getEndTime() != null, NoticeDistributePO::getEndTime, noticeDistributeQueryDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布通知
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deliverNotice(DeliverNoticeDTO deliverNoticeDTO) {
        if(StringUtils.isEmpty(deliverNoticeDTO.getReceiveUnit())){
            log.error("通知下发 receiveUnit is null, userid : " + deliverNoticeDTO.getUserId());
            return false;
        }
        NoticeDistributePO noticeDistributePO = BeanCopyUtils.copy(deliverNoticeDTO, NoticeDistributePO.class);
        int noticeDistribute = noticeDistributeMapper.insertNoticeDistribute(noticeDistributePO);
        if(noticeDistribute < 1){
            log.error("通知下发 insert into noticeDistribute failed, userid : " + deliverNoticeDTO.getUserId());
            return false;
        }
        NoticeDistributePO insertNoticeDistributeResult = noticeDistributeMapper.selectById(noticeDistributePO.getId());
        if(insertNoticeDistributeResult != null){
            //如果下发新增成功了，
            DeliverNoticeDTO deliverNotice2ReceiveDTO = new DeliverNoticeDTO();
            deliverNotice2ReceiveDTO.setTitle(insertNoticeDistributeResult.getTitle());
            deliverNotice2ReceiveDTO.setStartTime(insertNoticeDistributeResult.getStartTime());
            deliverNotice2ReceiveDTO.setEndTime(insertNoticeDistributeResult.getEndTime());
            deliverNotice2ReceiveDTO.setType(insertNoticeDistributeResult.getType());
            //根据通知类别更改初始状态
            if("通知".equals(insertNoticeDistributeResult.getType())){
                deliverNotice2ReceiveDTO.setStatus("待反馈");
            }
            deliverNotice2ReceiveDTO.setStatus("待上传");
            deliverNotice2ReceiveDTO.setReceiveUnit("");
            deliverNotice2ReceiveDTO.setFilePath("");
            deliverNotice2ReceiveDTO.setNoticeContent(insertNoticeDistributeResult.getNoticeContent());
            deliverNotice2ReceiveDTO.setParentUserId(insertNoticeDistributeResult.getUserId());
            deliverNotice2ReceiveDTO.setNoticeDistributeId(noticeDistributePO.getId());
            //这里暂时查询本地表人员
            //用查询到的接收单位list找出该给哪些单位发信息
            LambdaQueryWrapper<DistrictListPersonPO> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(DistrictListPersonPO::getOrgName, deliverNoticeDTO.getReceiveUnit());
            List<DistrictListPersonPO> districtListPersonPOList = districtListPersonMapper.selectList(lambdaQueryWrapper);
            Map<String, String> revceiveMap = new HashMap<>();
            for(DistrictListPersonPO districtListPersonPO : districtListPersonPOList){
                if(districtListPersonPO.getOrgName().equals(deliverNoticeDTO.getReceiveUnit())){
                    revceiveMap.put(districtListPersonPO.getUserName(), districtListPersonPO.getUserId());
                }
            }
            //给各个接收单位发通知
            for(Map.Entry<String, String> entry : revceiveMap.entrySet()){
                deliverNotice2ReceiveDTO.setUserId(entry.getValue());
                Boolean insertResult = iNoticeReceiveService.deliverNotice(deliverNotice2ReceiveDTO);
                if(!insertResult){
//                    log.error("insert into receive unit failed, userid is : " + deliverNotice2ReceiveDTO.getUserId() + " parentid is : " + deliverNotice2ReceiveDTO.getParentUserId());
                    throw new RuntimeException("insert into receive unit failed, userid is : " + deliverNotice2ReceiveDTO.getUserId() + " parentid is : " + deliverNotice2ReceiveDTO.getParentUserId());
                }
            }
            return true;
        }else {
            log.error("通知下发 insertNoticeDistributeResult is false, user_id = " + deliverNoticeDTO.getUserId());
            return false;
        }
    }

    /**
     * 通知操作
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operateNotice(Integer noticeDistributeId, String operateType) {
        NoticeDistributePO bizDistributePO = noticeDistributeMapper.selectById(noticeDistributeId);
        LambdaUpdateWrapper<NoticeDistributePO> luw = Wrappers.lambdaUpdate();
        int resultWithdrawNum = 0;
        int resultEndNum = 0;
        if("withdraw".equals(operateType)){
            //撤回通知之后下发方消息还在，状态变成已撤回，接收方消息删除
            luw.eq(NoticeDistributePO::getId, noticeDistributeId);
            bizDistributePO.setStatus("已撤回");
            resultWithdrawNum = noticeDistributeMapper.update(bizDistributePO, luw);
            if(resultWithdrawNum == 0){
             log.warn("下发通知表撤回失败，noticeDistributeId： " + noticeDistributeId);
            }
            //删除通知接收方内容
            LambdaUpdateWrapper<NoticeReceivePO> receiveLuw = Wrappers.lambdaUpdate();
            receiveLuw.eq(noticeDistributeId != null, NoticeReceivePO::getNoticeDistributeId, noticeDistributeId);
            int deleteNum = noticeReceiveMapper.delete(receiveLuw);
            resultWithdrawNum = deleteNum + 1;
        } else if ("end".equals(operateType)) {
            luw.eq(NoticeDistributePO::getId, noticeDistributeId);
            bizDistributePO.setStatus("结束");
            resultEndNum = noticeDistributeMapper.update(bizDistributePO, luw);
        }
        if((resultEndNum == 1) || (resultWithdrawNum == 2)){
            return true;
        }else {
            return false;
        }
    }

}
