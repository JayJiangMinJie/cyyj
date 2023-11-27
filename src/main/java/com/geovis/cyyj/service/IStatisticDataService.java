package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.dto.StatisticDataDTO;
import com.geovis.cyyj.po.StatisticDataPO;
import com.geovis.cyyj.vo.StatisticDataVO;
import com.geovis.cyyj.vo.StatisticTaskFeedbackVO;

import java.util.List;

/**
 * <p>
 * 统计数据 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IStatisticDataService extends IService<StatisticDataPO> {

    /**
     * 查询统计任务数据列表
     */
    List<StatisticDataVO> getStatisticDataList(Integer taskId, String userId);

    /**
     * 获取统计任务进度反馈
     */
    StatisticTaskFeedbackVO getStatisticTaskFeedback(Integer taskId, String userId);

    /**
     * 统计数据上传
     */
    Boolean statisticDataUpload(StatisticDataDTO statisticDataDTO);

}
