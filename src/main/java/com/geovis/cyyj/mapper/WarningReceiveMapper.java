package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.WarningReceivePO;
import com.geovis.cyyj.vo.WarningReceiveVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 预警接收表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface WarningReceiveMapper extends BaseMapperPlus<WarningReceiveMapper, WarningReceivePO, WarningReceiveVO> {

}
