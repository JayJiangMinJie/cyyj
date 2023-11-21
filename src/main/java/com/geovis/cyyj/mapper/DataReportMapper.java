package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.DataReportPO;
import com.geovis.cyyj.po.StatisticTaskPO;
import com.geovis.cyyj.vo.DataReportVO;
import com.geovis.cyyj.vo.StatisticTaskVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据上传表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface DataReportMapper extends BaseMapperPlus<DataReportMapper, DataReportPO, DataReportVO> {

}
