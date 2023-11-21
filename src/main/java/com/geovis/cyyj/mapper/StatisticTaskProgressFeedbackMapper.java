package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.ProgressFeedbackPO;
import com.geovis.cyyj.po.StatisticTaskProgressFeedbackPO;
import com.geovis.cyyj.vo.ProgressFeedbackVO;
import com.geovis.cyyj.vo.StatisticTaskFeedbackListVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 统计任务进度反馈表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface StatisticTaskProgressFeedbackMapper extends BaseMapperPlus<StatisticTaskProgressFeedbackMapper, StatisticTaskProgressFeedbackPO, StatisticTaskFeedbackListVO> {

}
