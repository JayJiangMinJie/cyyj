package com.geovis.cyyj.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ZipUtil;
import com.geovis.cyyj.common.core.domain.FileReturn;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.Html2WorldUtil;
import com.geovis.cyyj.common.utils.file.FileUtils;
import com.geovis.cyyj.dto.FileQueryDTO;
import com.geovis.cyyj.dto.MeetingPeopleDTO;
import com.geovis.cyyj.service.file.FileService;
import com.geovis.cyyj.service.meeting.MeetingService;
import com.geovis.cyyj.vo.FileMediaReturn;
import com.geovis.cyyj.vo.FileVO;
import com.geovis.cyyj.vo.MeetingVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping(value="/meeting")
@Api(value = "MeetingController", tags = "音视频会商接口")
@Slf4j
public class MeetingController {

    private static Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/createOrDelete")
    @ApiOperation("会议人员操作")
    public Boolean createOrDelete(MeetingPeopleDTO meetingPeopleDTOm){
        return meetingService.createOrDelete(meetingPeopleDTOm);
    }

    /**
     * 查询参会人
     */
    @ApiOperation(value = "查询参会人", notes = "查询参会人")
    @GetMapping("/pollingQuery")
    public MeetingVO pollingQuery(MeetingPeopleDTO meetingPeopleDTO) {
        return meetingService.pollingQuery(meetingPeopleDTO);
    }

}
