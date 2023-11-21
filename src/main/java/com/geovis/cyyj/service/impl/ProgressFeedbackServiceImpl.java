package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;

import com.geovis.cyyj.dto.ProgressFeedbackDTO;
import com.geovis.cyyj.mapper.ProgressFeedbackMapper;
import com.geovis.cyyj.po.ProgressFeedbackPO;
import com.geovis.cyyj.service.IProgressFeedbackService;
import com.geovis.cyyj.vo.ProgressFeedbackVO;
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
public class ProgressFeedbackServiceImpl extends ServiceImpl<ProgressFeedbackMapper, ProgressFeedbackPO> implements IProgressFeedbackService {

    @Autowired
    private final ProgressFeedbackMapper progressFeedbackMapper;

    /**
     * 分页查询进度反馈列表
     */
    @Override
    public TableDataInfo<ProgressFeedbackVO> queryProgressFeedbackList(int noticeDistributeId, PageQuery pageQuery) {
        LambdaQueryWrapper<ProgressFeedbackPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(noticeDistributeId != 0, ProgressFeedbackPO::getNoticeDistributeId, noticeDistributeId);
        Page<ProgressFeedbackVO> result = progressFeedbackMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean addOrUpdateProgressFeedback(ProgressFeedbackDTO progressFeedbackDTO) {
        ProgressFeedbackPO progressFeedbackPO = BeanCopyUtils.copy(progressFeedbackDTO, ProgressFeedbackPO.class);
        LocalDateTime now = LocalDateTime.now();
        String status;
        if(progressFeedbackDTO.getIsRead()){

            if(now.isBefore(progressFeedbackDTO.getEndTime())){
                status = "按时反馈";
            }else {
                status = "超时反馈";
            }
        }else {
            status = "未反馈";
        }
        progressFeedbackDTO.setFeedbackStatus(status);
        return progressFeedbackMapper.insertOrUpdate(progressFeedbackPO);
    }

}
