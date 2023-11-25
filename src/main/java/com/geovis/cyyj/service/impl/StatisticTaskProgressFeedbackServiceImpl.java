package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.dto.StatisticTaskProgressFeedbackDTO;
import com.geovis.cyyj.mapper.StatisticTaskProgressFeedbackMapper;
import com.geovis.cyyj.po.StatisticTaskProgressFeedbackPO;
import com.geovis.cyyj.po.StatisticTaskProgressFeedbackPO;
import com.geovis.cyyj.service.IStatisticTaskProgressFeedbackService;
import com.geovis.cyyj.vo.StatisticTaskFeedbackListVO;
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
public class StatisticTaskProgressFeedbackServiceImpl extends ServiceImpl<StatisticTaskProgressFeedbackMapper, StatisticTaskProgressFeedbackPO> implements IStatisticTaskProgressFeedbackService {

    @Autowired
    private final StatisticTaskProgressFeedbackMapper statisticTaskProgressFeedbackMapper;

    /**
     * 分页查询进度反馈列表
     */
    @Override
    public TableDataInfo<StatisticTaskFeedbackListVO> getStatisticTaskFeedbackList(int statisticTaskId, PageQuery pageQuery) {
        LambdaQueryWrapper<StatisticTaskProgressFeedbackPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(statisticTaskId != 0, StatisticTaskProgressFeedbackPO::getStatisticTaskId, statisticTaskId);
        Page<StatisticTaskFeedbackListVO> result = statisticTaskProgressFeedbackMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean addOrUpdateProgressFeedback(StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO) {
        StatisticTaskProgressFeedbackPO statisticTaskProgressFeedbackPO = BeanCopyUtils.copy(statisticTaskProgressFeedbackDTO, StatisticTaskProgressFeedbackPO.class);
        LocalDateTime now = LocalDateTime.now();
        String status;

        if(now.isBefore(statisticTaskProgressFeedbackDTO.getEndTime())){
            status = "按时反馈";
        }else {
            status = "超时反馈";
        }
        statisticTaskProgressFeedbackPO.setFeedbackStatus(status);
        return statisticTaskProgressFeedbackMapper.insertOrUpdate(statisticTaskProgressFeedbackPO);
    }

    @Override
    public Boolean updateProgressFeedback(StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO) {
        LocalDateTime now = LocalDateTime.now();
        String status;

        if(now.isBefore(statisticTaskProgressFeedbackDTO.getEndTime())){
            status = "按时反馈";
        }else {
            status = "超时反馈";
        }
        //先查出来再更新
        LambdaQueryWrapper<StatisticTaskProgressFeedbackPO> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(StatisticTaskProgressFeedbackPO::getStatisticTaskId, statisticTaskProgressFeedbackDTO.getStatisticTaskId());
        lambdaQueryWrapper.eq(StatisticTaskProgressFeedbackPO::getUserId, statisticTaskProgressFeedbackDTO.getUserId());
        StatisticTaskProgressFeedbackPO statisticTaskProgressFeedbackPO = statisticTaskProgressFeedbackMapper.selectOne(lambdaQueryWrapper);

        statisticTaskProgressFeedbackPO.setFeedbackStatus(status);
        int resultUpdateNoticeFeedback = statisticTaskProgressFeedbackMapper.updateById(statisticTaskProgressFeedbackPO);
        if(resultUpdateNoticeFeedback <= 0){
            throw new RuntimeException("feedback task update Result failed, userid is : " + statisticTaskProgressFeedbackPO.getUserId() );
        }
        return true;
    }

}
