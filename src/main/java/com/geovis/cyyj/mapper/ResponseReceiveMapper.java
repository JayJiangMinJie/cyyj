package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.ResponseReceivePO;
import com.geovis.cyyj.vo.ResponseReceiveVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 响应接收表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface ResponseReceiveMapper extends BaseMapperPlus<ResponseReceiveMapper, ResponseReceivePO, ResponseReceiveVO> {

}
