package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.ResponseChangeLogPO;
import com.geovis.cyyj.vo.ResponseChangeLogVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 响应历史表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface ResponseChangeLogMapper extends BaseMapperPlus<ResponseChangeLogMapper, ResponseChangeLogPO, ResponseChangeLogVO> {

}
