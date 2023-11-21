package com.geovis.cyyj.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.dto.StatisticTaskDTO;
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

//    /**
//     * 分页查询
//     *
//     * @param page    分页参数
//     * @param wrapper
//     * @return 返回用户分页
//     */
//    IPage<StatisticTaskVO> selectByPage(@Param("page") IPage<StatisticTaskDTO> page, @Param(Constants.WRAPPER) Wrapper<Object> wrapper);

}
