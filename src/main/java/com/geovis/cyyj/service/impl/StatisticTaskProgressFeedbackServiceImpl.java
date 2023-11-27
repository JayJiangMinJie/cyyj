package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.StatisticTaskProgressFeedbackDTO;
import com.geovis.cyyj.mapper.StatisticTaskMapper;
import com.geovis.cyyj.mapper.StatisticTaskProgressFeedbackMapper;
import com.geovis.cyyj.po.StatisticTaskPO;
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

    @Autowired
    private final StatisticTaskMapper statisticTaskMapper;

    /**
     * 分页查询进度反馈列表
     */
    @Override
    public TableDataInfo<StatisticTaskFeedbackListVO> getStatisticTaskFeedbackList(Integer statisticTaskId, String userId, PageQuery pageQuery) {
        LambdaQueryWrapper<StatisticTaskProgressFeedbackPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(statisticTaskId != 0, StatisticTaskProgressFeedbackPO::getStatisticTaskId, statisticTaskId);
        lqw.eq(StringUtils.isNotEmpty(userId), StatisticTaskProgressFeedbackPO::getUserId, userId);
        Page<StatisticTaskFeedbackListVO> result = statisticTaskProgressFeedbackMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean addOrUpdateProgressFeedback(StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO) {
        StatisticTaskProgressFeedbackPO statisticTaskProgressFeedbackPO = BeanCopyUtils.copy(statisticTaskProgressFeedbackDTO, StatisticTaskProgressFeedbackPO.class);
        String status = "未反馈";
        //第一次一定是未反馈状态，且只有已反馈未反馈，因为没有截止时间
        statisticTaskProgressFeedbackPO.setFeedbackStatus(status);
        return statisticTaskProgressFeedbackMapper.insertOrUpdate(statisticTaskProgressFeedbackPO);
    }

    @Override
    public Boolean updateProgressFeedback(StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO) {
        String status = "已反馈";
        //先查出来再更新
        LambdaQueryWrapper<StatisticTaskProgressFeedbackPO> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(StatisticTaskProgressFeedbackPO::getStatisticTaskId, statisticTaskProgressFeedbackDTO.getStatisticTaskId());
        lambdaQueryWrapper.eq(StatisticTaskProgressFeedbackPO::getUserId, statisticTaskProgressFeedbackDTO.getUserId());
        StatisticTaskProgressFeedbackPO statisticTaskProgressFeedbackPO = statisticTaskProgressFeedbackMapper.selectOne(lambdaQueryWrapper);

        statisticTaskProgressFeedbackPO.setFeedbackStatus(status);
        int resultUpdateTaskFeedback = statisticTaskProgressFeedbackMapper.updateById(statisticTaskProgressFeedbackPO);
        if(resultUpdateTaskFeedback <= 0){
            throw new RuntimeException("feedback task update Result failed, userid is : " + statisticTaskProgressFeedbackPO.getUserId() );
        }
        //更新完进度反馈要更新一下子统计任务数据的最新填报时间
        //先查出来再更新
        LambdaQueryWrapper<StatisticTaskPO> lqw = new LambdaQueryWrapper();
        lqw.eq(StatisticTaskPO::getId, statisticTaskProgressFeedbackDTO.getStatisticTaskId());
        StatisticTaskPO statisticTaskPO = statisticTaskMapper.selectOne(lqw);
        LocalDateTime now = LocalDateTime.now();
        statisticTaskPO.setLastFillTime(now);
        int resultUpdateTask = statisticTaskMapper.updateById(statisticTaskPO);
        if(resultUpdateTask <= 0){
            throw new RuntimeException("task update Result failed, userid is : " + statisticTaskProgressFeedbackPO.getUserId() );
        }
        return true;
    }

}
