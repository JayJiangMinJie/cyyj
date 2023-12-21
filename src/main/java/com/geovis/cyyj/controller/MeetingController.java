package com.geovis.cyyj.controller;

import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.meeting.MeetingPeopleDTO;
import com.geovis.cyyj.po.meeting.MeetingLogPO;
import com.geovis.cyyj.service.meeting.MeetingLogService;
import com.geovis.cyyj.service.meeting.MeetingService;
import com.geovis.cyyj.vo.meeting.MeetingLogVO;
import com.geovis.cyyj.vo.meeting.MeetingVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/meeting")
@Api(value = "MeetingController", tags = "音视频会商接口")
@Slf4j
public class MeetingController {

    private static Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingLogService meetingLogService;

    @PostMapping("/createMeeting")
    @ApiOperation("创建会议")
    public Boolean createMeeting(@RequestBody MeetingPeopleDTO meetingPeopleDTO){
        return meetingService.createMeeting(meetingPeopleDTO);
    }

    @PostMapping("/insertPeople")
    @ApiOperation("入会")
    public Boolean insertPeople(@RequestParam("memberNO") String memberNO){
        return meetingService.insertPeople(memberNO);
    }

    @PostMapping("/leavePeople")
    @ApiOperation("离会")
    public Boolean leavePeople(@RequestParam("memberNO") String memberNO){
        return meetingService.leavePeople(memberNO);
    }

    /**
     * 查询参会人
     */
    @ApiOperation(value = "查询参会人", notes = "查询参会人")
    @GetMapping("/pollingQuery")
    public MeetingVO pollingQuery(MeetingPeopleDTO meetingPeopleDTO) {
        return meetingService.pollingQuery(meetingPeopleDTO);
    }

    /**
     * 查询参会记录
     */
    @ApiOperation(value = "查询参会记录", notes = "查询参会记录")
    @GetMapping("/queryMeetingLog")
    public TableDataInfo<MeetingLogVO> queryMeetingLog(@RequestParam("shouyingType") String shouyingType,
                                                       @RequestParam("shouyingId") String shouyingId,
                                                       PageQuery pageQuery) {
        return meetingLogService.queryMeetingLog(shouyingType, shouyingId, pageQuery);
    }

}
