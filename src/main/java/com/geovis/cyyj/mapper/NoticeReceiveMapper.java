package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.NoticeReceivePO;
import com.geovis.cyyj.vo.NoticeReceiveVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 通知接收表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface NoticeReceiveMapper extends BaseMapperPlus<NoticeReceiveMapper, NoticeReceivePO, NoticeReceiveVO> {

}
