package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.StatisticTaskProgressFeedbackDTO;
import com.geovis.cyyj.po.StatisticTaskProgressFeedbackPO;
import com.geovis.cyyj.vo.StatisticTaskFeedbackListVO;

/**
 * <p>
 * 通知下发 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IStatisticTaskProgressFeedbackService extends IService<StatisticTaskProgressFeedbackPO> {

    /**
     * 获取统计任务进度反馈列表
     */
    TableDataInfo<StatisticTaskFeedbackListVO> getStatisticTaskFeedbackList(int statisticTaskId, PageQuery pageQuery);

    /**
     * 新增进度反馈
     */
    Boolean addOrUpdateProgressFeedback(StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO);

    /**
     * 新增进度反馈
     */
    Boolean updateProgressFeedback(StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO);

}
