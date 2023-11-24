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
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeProgressFeedbackDTO;
import com.geovis.cyyj.dto.NoticeReceiveQueryDTO;
import com.geovis.cyyj.dto.NoticeReceiveStatusDTO;
import com.geovis.cyyj.mapper.NoticeProgressFeedbackMapper;
import com.geovis.cyyj.mapper.NoticeReceiveMapper;
import com.geovis.cyyj.po.DisasterWarningPO;
import com.geovis.cyyj.po.NoticeProgressFeedbackPO;
import com.geovis.cyyj.po.NoticeReceivePO;
import com.geovis.cyyj.po.PublicServerPO;
import com.geovis.cyyj.service.INoticeReceiveService;
import com.geovis.cyyj.vo.NoticeReceiveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * 通知接收 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeReceiveServiceImpl extends ServiceImpl<NoticeReceiveMapper, NoticeReceivePO> implements INoticeReceiveService {

    @Autowired
    private final NoticeReceiveMapper noticeReceiveMapper;

    @Autowired
    private final NoticeProgressFeedbackMapper noticeProgressFeedbackMapper;

    /**
     * 分页查询通知下发列表
     */
    @Override
    public TableDataInfo<NoticeReceiveVO> queryMainList(NoticeReceiveQueryDTO noticeReceiveQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<NoticeReceivePO> lqw = buildQueryWrapper(noticeReceiveQueryDTO);
        Page<NoticeReceiveVO> result = noticeReceiveMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<NoticeReceivePO> buildQueryWrapper(NoticeReceiveQueryDTO noticeReceiveQueryDTO) {
        LambdaQueryWrapper<NoticeReceivePO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(noticeReceiveQueryDTO.getKeyWord()), NoticeReceivePO::getTitle, noticeReceiveQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(noticeReceiveQueryDTO.getUserId()), NoticeReceivePO::getUserId, noticeReceiveQueryDTO.getUserId());
        lqw.eq(StringUtils.isNotBlank(noticeReceiveQueryDTO.getStatus()), NoticeReceivePO::getStatus, noticeReceiveQueryDTO.getStatus());
        lqw.ge(noticeReceiveQueryDTO.getStartTime() != null, NoticeReceivePO::getStartTime, noticeReceiveQueryDTO.getStartTime());
        lqw.le(noticeReceiveQueryDTO.getEndTime() != null, NoticeReceivePO::getEndTime, noticeReceiveQueryDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布任务
     */
    @Override
    public Boolean deliverNotice(DeliverNoticeDTO deliverNoticeDTO) {
        NoticeReceivePO noticeReceivePO = BeanCopyUtils.copy(deliverNoticeDTO, NoticeReceivePO.class);
        return noticeReceiveMapper.insertOrUpdate(noticeReceivePO);
    }

    @Override
    public Boolean changeStatus(NoticeReceiveStatusDTO noticeReceiveStatusDTO) {
        NoticeReceivePO noticeReceivePO = BeanCopyUtils.copy(noticeReceiveStatusDTO, NoticeReceivePO.class);
        LocalDateTime now = LocalDateTime.now();
        String receiveStatus = "未读";
        String status;
        if(noticeReceiveStatusDTO.getIsRead()){
            if(noticeReceiveStatusDTO.getType().equals("0")){
                if(now.isBefore(noticeReceiveStatusDTO.getEndTime())){
                    status = "按时反馈";
                }else {
                    status = "超时反馈";
                }
            }else {
                if(now.isBefore(noticeReceiveStatusDTO.getEndTime())){
                    status = "待上传";
                }else {
                    status = "未反馈";
                }
            }
            receiveStatus = "已读";
        }else {
            status = "未反馈";
        }
        noticeReceivePO.setStatus(status);
        Boolean insertOrUpdateNoticeReceiveResult = noticeReceiveMapper.insertOrUpdate(noticeReceivePO);
        if(!insertOrUpdateNoticeReceiveResult){
            throw new RuntimeException("insertOrUpdate NoticeReceive Result failed, userid is : " + noticeReceivePO.getUserId() + " parentid is : " + noticeReceivePO.getParentUserId());
        }
        //顺带也要更新反馈列表
        LambdaQueryWrapper<NoticeProgressFeedbackPO> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(NoticeProgressFeedbackPO::getNoticeDistributeId, noticeReceiveStatusDTO.getNoticeDistributeId());
        lambdaQueryWrapper.eq(NoticeProgressFeedbackPO::getUserId, noticeReceiveStatusDTO.getUserId());
        NoticeProgressFeedbackPO noticeProgressFeedbackPO = noticeProgressFeedbackMapper.selectOne(lambdaQueryWrapper);
        //先查询后更新
        noticeProgressFeedbackPO.setFeedbackStatus(status);
        noticeProgressFeedbackPO.setReceiveStatus(receiveStatus);
        int resultUpdateNoticeFeedback = noticeProgressFeedbackMapper.updateById(noticeProgressFeedbackPO);
        if(resultUpdateNoticeFeedback <= 0){
            throw new RuntimeException("resultUpdateNoticeFeedback Result failed, userid is : " + noticeReceivePO.getUserId() );
        }
        return true;
    }

}
