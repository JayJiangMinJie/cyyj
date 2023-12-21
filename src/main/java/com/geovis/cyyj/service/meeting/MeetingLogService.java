package com.geovis.cyyj.service.meeting;

import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.meeting.MeetingLogDTO;
import com.geovis.cyyj.vo.meeting.MeetingLogVO;

import java.util.List;

public interface MeetingLogService {

    Boolean createMeetingLog(MeetingLogDTO meetingLogDTO);

    Boolean updateMeetingLog(MeetingLogDTO meetingLogDTO);

    /**
     * 查询会议记录
     */
    TableDataInfo<MeetingLogVO> queryMeetingLog(String shouyingType, String shouyingId, PageQuery pageQuery);

}