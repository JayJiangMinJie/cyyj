package com.geovis.cyyj.mapper.meeting;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.meeting.MeetingPO;
import com.geovis.cyyj.vo.meeting.MeetingVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会议信息表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface MeetingMapper extends BaseMapperPlus<MeetingMapper, MeetingPO, MeetingVO> {

}
