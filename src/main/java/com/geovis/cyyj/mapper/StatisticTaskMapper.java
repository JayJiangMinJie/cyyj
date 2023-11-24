package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.StatisticTaskPO;
import com.geovis.cyyj.vo.StatisticTaskVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 统计任务表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface StatisticTaskMapper extends BaseMapperPlus<StatisticTaskMapper, StatisticTaskPO, StatisticTaskVO> {

    /**
     * 录入任务
     */
    int  insertStatisticTask(@Param("statisticTaskPO") StatisticTaskPO statisticTaskPO);

}
