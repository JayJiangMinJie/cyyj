package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.StatisticDataPO;
import com.geovis.cyyj.vo.StatisticDataVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 统计数据表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface StatisticDataMapper extends BaseMapperPlus<StatisticDataMapper, StatisticDataPO, StatisticDataVO> {

//    /**
//     * 分页查询
//     *
//     * @param page    分页参数
//     * @param wrapper
//     * @return 返回用户分页
//     */
//    IPage<StatisticDataVO> selectByPage(@Param("page") IPage<StatisticDataDTO> page, @Param(Constants.WRAPPER) Wrapper<Object> wrapper);

}
