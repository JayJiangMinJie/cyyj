package com.geovis.cyyj.service.meeting.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.FileQueryDTO;
import com.geovis.cyyj.dto.MeetingPeopleDTO;
import com.geovis.cyyj.mapper.file.FileMapper;
import com.geovis.cyyj.mapper.meeting.MeetingMapper;
import com.geovis.cyyj.po.ResponseReleasePO;
import com.geovis.cyyj.po.file.FilePO;
import com.geovis.cyyj.po.meeting.MeetingPO;
import com.geovis.cyyj.service.meeting.MeetingService;
import com.geovis.cyyj.vo.FileVO;
import com.geovis.cyyj.vo.MeetingVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class MeetingServiceImpl implements MeetingService {

    private static final Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);

    @Autowired
    MeetingMapper meetingMapper;

    private LambdaQueryWrapper<MeetingPO> buildQueryWrapper(MeetingPeopleDTO meetingPeopleDTO) {
        LambdaQueryWrapper<MeetingPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(meetingPeopleDTO.getMeetingId()), MeetingPO::getMeetingId, meetingPeopleDTO.getMeetingId());
        lqw.eq(StringUtils.isNotBlank(meetingPeopleDTO.getToken()), MeetingPO::getToken, meetingPeopleDTO.getToken());
        lqw.eq(StringUtils.isNotBlank(meetingPeopleDTO.getChannel()), MeetingPO::getChannel, meetingPeopleDTO.getChannel());
        return lqw;
    }

    @Override
    public Boolean createOrDelete(MeetingPeopleDTO meetingPeopleDTO) {
        LambdaQueryWrapper<MeetingPO> lqw = buildQueryWrapper(meetingPeopleDTO);
        MeetingPO meetingPO = meetingMapper.selectOne(lqw);
//        MeetingPO meetingPO = BeanCopyUtils.copy(meetingPeopleDTO, MeetingPO.class);
        if(meetingPO == null){
            if("create".equals(meetingPeopleDTO.getType())){
                meetingPO = BeanCopyUtils.copy(meetingPeopleDTO, MeetingPO.class);
                int createMeetingResult = meetingMapper.insert(meetingPO);
                if(createMeetingResult > 0){
                    return true;
                }else {
                    logger.warn("新增失败");
                    return false;
                }
            } else if ("delete".equals(meetingPeopleDTO.getType())) {
                logger.warn("删除失败，会议不存在");
                return false;
            }
        }else {
            if("create".equals(meetingPeopleDTO.getType())){
                //如果数据存在，就更新参会人,直接覆盖
                meetingPO = BeanCopyUtils.copy(meetingPeopleDTO, MeetingPO.class);
                LambdaUpdateWrapper<MeetingPO> luw = Wrappers.lambdaUpdate();
                luw.eq(MeetingPO::getMeetingId, meetingPeopleDTO.getMeetingId());
                luw.eq(MeetingPO::getToken, meetingPeopleDTO.getToken());
                luw.eq(MeetingPO::getChannel, meetingPeopleDTO.getChannel());
                int updateMeeting = meetingMapper.update(meetingPO, luw);
                if(updateMeeting > 0){
                    return true;
                }else {
                    logger.warn("数据存在，但更新失败");
                    return false;
                }
            } else if ("delete".equals(meetingPeopleDTO.getType())) {
                //如果要删除某个用户，只能单个删除
                String[] calleeArrays = meetingPeopleDTO.getCallee().split(",");
                String deletePeople = calleeArrays[0];
                String[] newCallee = removeValueFromArray(meetingPO.getCallee().split(","), deletePeople);
                meetingPO.setCallee(String.join(",", newCallee));
                LambdaUpdateWrapper<MeetingPO> luw = Wrappers.lambdaUpdate();
                luw.eq(MeetingPO::getMeetingId, meetingPeopleDTO.getMeetingId());
                luw.eq(MeetingPO::getToken, meetingPeopleDTO.getToken());
                luw.eq(MeetingPO::getChannel, meetingPeopleDTO.getChannel());
                int updateMeeting = meetingMapper.update(meetingPO, luw);
                if(updateMeeting > 0){
                    return true;
                }else {
                    logger.warn("数据存在，但删除失败");
                    return false;
                }
            }
        }
        return false;
    }

    public String[] removeValueFromArray(String[] array, String valueToRemove) {
        List<String> newArray = new ArrayList<>();

        for (String value : array) {
            if (!value.equals(valueToRemove)) {
                newArray.add(value);
            }
        }
        return newArray.toArray(new String[0]);
    }

    @Override
    public MeetingVO pollingQuery(MeetingPeopleDTO meetingPeopleDTO) {
        if("search".equals(meetingPeopleDTO.getType())){
            LambdaQueryWrapper<MeetingPO> lqw = buildQueryWrapper(meetingPeopleDTO);
            MeetingVO meetingVO = meetingMapper.selectVoOne(lqw);
            return meetingVO;
        }else {
            logger.warn("type is error");
            return new MeetingVO();
        }
    }
}

