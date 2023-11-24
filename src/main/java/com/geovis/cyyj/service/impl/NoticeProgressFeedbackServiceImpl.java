package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;

import com.geovis.cyyj.dto.NoticeProgressFeedbackDTO;
import com.geovis.cyyj.mapper.NoticeProgressFeedbackMapper;
import com.geovis.cyyj.po.NoticeProgressFeedbackPO;
import com.geovis.cyyj.service.INoticeProgressFeedbackService;
import com.geovis.cyyj.vo.NoticeProgressFeedbackVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


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
public class NoticeProgressFeedbackServiceImpl extends ServiceImpl<NoticeProgressFeedbackMapper, NoticeProgressFeedbackPO> implements INoticeProgressFeedbackService {

    @Autowired
    private final NoticeProgressFeedbackMapper noticeProgressFeedbackMapper;

    /**
     * 分页查询进度反馈列表
     */
    @Override
    public TableDataInfo<NoticeProgressFeedbackVO> queryNoticeProgressFeedbackList(int noticeDistributeId, PageQuery pageQuery) {
        LambdaQueryWrapper<NoticeProgressFeedbackPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(noticeDistributeId != 0, NoticeProgressFeedbackPO::getNoticeDistributeId, noticeDistributeId);
        Page<NoticeProgressFeedbackVO> result = noticeProgressFeedbackMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean addOrUpdateNoticeProgressFeedback(NoticeProgressFeedbackDTO noticeProgressFeedbackDTO) {
        NoticeProgressFeedbackPO noticeProgressFeedbackPO = BeanCopyUtils.copy(noticeProgressFeedbackDTO, NoticeProgressFeedbackPO.class);
        LocalDateTime now = LocalDateTime.now();
        String status;
        if(noticeProgressFeedbackDTO.getIsRead()){

            if(now.isBefore(noticeProgressFeedbackDTO.getEndTime())){
                status = "按时反馈";
            }else {
                status = "超时反馈";
            }
        }else {
            status = "未反馈";
        }
        noticeProgressFeedbackDTO.setFeedbackStatus(status);
        return noticeProgressFeedbackMapper.insertOrUpdate(noticeProgressFeedbackPO);
    }

}
