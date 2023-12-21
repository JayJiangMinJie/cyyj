package com.geovis.cyyj.mapper.meeting;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.meeting.MeetingLogPO;
import com.geovis.cyyj.vo.meeting.MeetingLogVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会议记录信息表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface MeetingLogMapper extends BaseMapperPlus<MeetingLogMapper, MeetingLogPO, MeetingLogVO> {

}
