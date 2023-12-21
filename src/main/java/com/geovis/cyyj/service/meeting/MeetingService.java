package com.geovis.cyyj.service.meeting;

import com.geovis.cyyj.dto.meeting.MeetingPeopleDTO;
import com.geovis.cyyj.vo.meeting.MeetingVO;

public interface MeetingService {

    Boolean createMeeting(MeetingPeopleDTO meetingPeopleDTO);

    Boolean insertPeople(String memberNO);

    Boolean leavePeople(String memberNO);

    /**
     * 查询参会人
     */
    MeetingVO pollingQuery(MeetingPeopleDTO meetingPeopleDTO);

}