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
import com.geovis.cyyj.dto.NoticeProgressFeedbackDTO;
import com.geovis.cyyj.mapper.DistrictListPersonMapper;
import com.geovis.cyyj.mapper.NoticeDistributeMapper;
import com.geovis.cyyj.mapper.NoticeReceiveMapper;
import com.geovis.cyyj.po.DistrictListPersonPO;
import com.geovis.cyyj.po.NoticeDistributePO;
import com.geovis.cyyj.po.NoticeReceivePO;
import com.geovis.cyyj.po.ResponseReceivePO;
import com.geovis.cyyj.service.INoticeDistributeService;
import com.geovis.cyyj.service.INoticeProgressFeedbackService;
import com.geovis.cyyj.service.INoticeReceiveService;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    private INoticeProgressFeedbackService iNoticeProgressFeedbackService;

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
        lqw.like(StringUtils.isNotBlank(noticeDistributeQueryDTO.getKeyWord()), NoticeDistributePO::getTitle, noticeDistributeQueryDTO.getKeyWord());
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
            //如果下发新增成功了，先生成通知接收的
            DeliverNoticeDTO deliverNotice2ReceiveDTO = new DeliverNoticeDTO();
            deliverNotice2ReceiveDTO.setTitle(insertNoticeDistributeResult.getTitle());
            deliverNotice2ReceiveDTO.setStartTime(insertNoticeDistributeResult.getStartTime());
            deliverNotice2ReceiveDTO.setEndTime(insertNoticeDistributeResult.getEndTime());
            deliverNotice2ReceiveDTO.setType(insertNoticeDistributeResult.getType());
            deliverNotice2ReceiveDTO.setIsUpload(false);
            //根据通知类别更改初始状态: 0-通知 1-带附件通知
            if("0".equals(insertNoticeDistributeResult.getType())){
                deliverNotice2ReceiveDTO.setStatus("未反馈");
            }else {
                deliverNotice2ReceiveDTO.setStatus("待上传");
            }
            deliverNotice2ReceiveDTO.setType(insertNoticeDistributeResult.getType());
            deliverNotice2ReceiveDTO.setNoticeContent(insertNoticeDistributeResult.getNoticeContent());
            deliverNotice2ReceiveDTO.setParentUserId(insertNoticeDistributeResult.getUserId());
            deliverNotice2ReceiveDTO.setNoticeDistributeId(noticeDistributePO.getId());
            deliverNotice2ReceiveDTO.setWordPath(noticeDistributePO.getWordPath());
            deliverNotice2ReceiveDTO.setFilePath(noticeDistributePO.getFilePath());
            deliverNotice2ReceiveDTO.setTaskStatus(noticeDistributePO.getStatus());
            //再生成进度反馈的数据
            NoticeProgressFeedbackDTO noticeProgressFeedbackDTO = new NoticeProgressFeedbackDTO();
            noticeProgressFeedbackDTO.setNoticeDistributeId(noticeDistributePO.getId());
            noticeProgressFeedbackDTO.setEndTime(insertNoticeDistributeResult.getEndTime());
            noticeProgressFeedbackDTO.setCity("青岛市");
            noticeProgressFeedbackDTO.setCounty("城阳区应急管理局");
            noticeProgressFeedbackDTO.setOperatePerson(deliverNoticeDTO.getOperatePerson());
            noticeProgressFeedbackDTO.setReceiveStatus("未读");
            noticeProgressFeedbackDTO.setParentUserId(insertNoticeDistributeResult.getUserId());
            noticeProgressFeedbackDTO.setFilePath(insertNoticeDistributeResult.getFilePath());
            //这里暂时查询本地表人员
            //用查询到的接收单位list找出该给哪些单位发信息
            String[] unitArrays = deliverNoticeDTO.getReceiveUnit().split(",");
            List<String> unitList = Arrays.asList(unitArrays);
            LambdaQueryWrapper<DistrictListPersonPO> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.in(DistrictListPersonPO::getOrgName, unitList);
            List<DistrictListPersonPO> districtListPersonPOList = districtListPersonMapper.selectList(lambdaQueryWrapper);
            Map<String, String> revceiveMap = new HashMap<>();
            for(DistrictListPersonPO districtListPersonPO : districtListPersonPOList){
                revceiveMap.put(districtListPersonPO.getOrgName(), districtListPersonPO.getUserId());
            }
            //给各个接收单位发通知
            for(Map.Entry<String, String> entry : revceiveMap.entrySet()){
                deliverNotice2ReceiveDTO.setUserId(entry.getValue());
                Boolean insertReceiveResult = iNoticeReceiveService.deliverNotice(deliverNotice2ReceiveDTO);
                if(!insertReceiveResult){
                    throw new RuntimeException("insert into receive unit failed, userid is : " + deliverNotice2ReceiveDTO.getUserId() + " parentid is : " + deliverNotice2ReceiveDTO.getParentUserId());
                }
                //进度反馈也要给各个单位默认发一个
                noticeProgressFeedbackDTO.setUserId(entry.getValue());
                noticeProgressFeedbackDTO.setDept(entry.getKey());
                Boolean insertFeedbackResult = iNoticeProgressFeedbackService.addOrUpdateNoticeProgressFeedback(noticeProgressFeedbackDTO);
                if(!insertFeedbackResult){
                    throw new RuntimeException("insert into feedback failed, userid is : " + deliverNotice2ReceiveDTO.getUserId() + " parentid is : " + deliverNotice2ReceiveDTO.getParentUserId());
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
    public Boolean operateNotice(Integer noticeDistributeId, String userId, String operateType) {
        NoticeDistributePO bizDistributePO = noticeDistributeMapper.selectById(noticeDistributeId);
//        LambdaUpdateWrapper<NoticeReceivePO> lambdaUpdateWrap = new LambdaUpdateWrapper();
//        lambdaUpdateWrap.eq(NoticeReceivePO::getNoticeDistributeId, noticeDistributeId);
//
//
//        LambdaQueryWrapper<NoticeReceivePO> lambdaQueryWrap = new LambdaQueryWrapper();
//        lambdaUpdateWrap.eq(NoticeReceivePO::getNoticeDistributeId, noticeDistributeId);
//        NoticeReceivePO noticeReceivePO =
        //接收部分
        LambdaUpdateWrapper<NoticeReceivePO> luwReceive = Wrappers.lambdaUpdate();
        luwReceive.eq(NoticeReceivePO::getNoticeDistributeId, noticeDistributeId);



        LambdaUpdateWrapper<NoticeDistributePO> luw = Wrappers.lambdaUpdate();
        int resultWithdrawNum = 0;
        int resultReceiveEnd = 0;
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
//            receiveLuw.eq(StringUtils.isNotEmpty(userId), NoticeReceivePO::getUserId, userId);
            int deleteNum = noticeReceiveMapper.delete(receiveLuw);
            if(deleteNum == 0){
                log.warn("删除notice receive失败，noticeDistributeId： " + noticeDistributeId);
            }
            resultWithdrawNum = deleteNum + 1;
        } else if ("end".equals(operateType)) {
            luw.eq(NoticeDistributePO::getId, noticeDistributeId);
            bizDistributePO.setStatus("结束");
            resultEndNum = noticeDistributeMapper.update(bizDistributePO, luw);
            //更新接收表状态
            luwReceive.set(NoticeReceivePO::getTaskStatus, "结束");
            resultReceiveEnd = noticeReceiveMapper.update(null, luwReceive);
            if(resultReceiveEnd == 0){
                log.warn("下发通知表结束失败，noticeDistributeId： " + noticeDistributeId);
            }
        }
        if((resultEndNum == 1) || (resultWithdrawNum > 1)){
            return true;
        }else {
            return false;
        }
    }

}
