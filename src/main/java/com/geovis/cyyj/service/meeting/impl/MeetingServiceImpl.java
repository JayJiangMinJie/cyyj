package com.geovis.cyyj.service.meeting.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.meeting.MeetingLogDTO;
import com.geovis.cyyj.dto.meeting.MeetingPeopleDTO;
import com.geovis.cyyj.entity.Member;
import com.geovis.cyyj.mapper.meeting.MeetingLogMapper;
import com.geovis.cyyj.mapper.meeting.MeetingMapper;
import com.geovis.cyyj.po.meeting.MeetingLogPO;
import com.geovis.cyyj.po.meeting.MeetingPO;
import com.geovis.cyyj.service.meeting.MeetingLogService;
import com.geovis.cyyj.service.meeting.MeetingService;
import com.geovis.cyyj.vo.meeting.MeetingVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class MeetingServiceImpl implements MeetingService {

    private static final Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);

    @Autowired
    MeetingMapper meetingMapper;

    @Autowired
    MeetingLogMapper meetingLogMapper;

    @Autowired
    MeetingLogService meetingLogService;

    private LambdaQueryWrapper<MeetingPO> buildQueryWrapper(MeetingPeopleDTO meetingPeopleDTO) {
        LambdaQueryWrapper<MeetingPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(meetingPeopleDTO.getMeetingId()), MeetingPO::getMeetingId, meetingPeopleDTO.getMeetingId());
        lqw.eq(StringUtils.isNotBlank(meetingPeopleDTO.getToken()), MeetingPO::getToken, meetingPeopleDTO.getToken());
        lqw.eq(StringUtils.isNotBlank(meetingPeopleDTO.getChannel()), MeetingPO::getChannel, meetingPeopleDTO.getChannel());
        return lqw;
    }

    @Override
    public Boolean createMeeting(MeetingPeopleDTO meetingPeopleDTO) {
        LambdaQueryWrapper<MeetingPO> lqw = buildQueryWrapper(meetingPeopleDTO);
        MeetingPO meetingPO = meetingMapper.selectOne(lqw);
        String memberJson = toJSON(meetingPeopleDTO.getMemberList());
        String hostJson = toJSON(meetingPeopleDTO.getHost());
        if (meetingPO == null) {
            meetingPO = BeanCopyUtils.copy(meetingPeopleDTO, MeetingPO.class);
            meetingPO.setCallee(memberJson);
            meetingPO.setHost(hostJson);
            int createMeetingResult = meetingMapper.insert(meetingPO);
            if (createMeetingResult > 0) {
                //创建会议室之后创建叫应记录
                MeetingLogDTO meetingLogDTO = new MeetingLogDTO();
                meetingLogDTO.setConnected("");
                meetingLogDTO.setNotConnectioned(memberJson);
                meetingLogDTO.setHost(hostJson);
                meetingLogDTO.setShouyingType(meetingPeopleDTO.getShouyingType());
                meetingLogDTO.setShouyingId(meetingPeopleDTO.getShouyingId());
                Boolean createMeetingLogResult = meetingLogService.createMeetingLog(meetingLogDTO);
                if(createMeetingLogResult){
                    return true;
                }
                logger.warn("创建会议记录失败");
                return false;
            } else {
                logger.warn("新增失败");
                return false;
            }
        } else {
            //如果数据存在，就更新参会人,直接覆盖
            meetingPO = BeanCopyUtils.copy(meetingPeopleDTO, MeetingPO.class);
            meetingPO.setCallee(memberJson);
            LambdaUpdateWrapper<MeetingPO> luw = Wrappers.lambdaUpdate();
            luw.eq(MeetingPO::getMeetingId, meetingPeopleDTO.getMeetingId());
            luw.eq(MeetingPO::getToken, meetingPeopleDTO.getToken());
            luw.eq(MeetingPO::getChannel, meetingPeopleDTO.getChannel());
            int updateMeeting = meetingMapper.update(meetingPO, luw);
            if (updateMeeting > 0) {
                //创建会议室之后创建叫应记录
                MeetingLogDTO meetingLogDTO = new MeetingLogDTO();
                meetingLogDTO.setConnected("");
                meetingLogDTO.setNotConnectioned(memberJson);
                meetingLogDTO.setHost(hostJson);
                meetingLogDTO.setShouyingId(meetingPeopleDTO.getShouyingId());
                meetingLogDTO.setShouyingType(meetingPeopleDTO.getShouyingType());
                Boolean createMeetingLogResult = meetingLogService.createMeetingLog(meetingLogDTO);
                if(createMeetingLogResult){
                    return true;
                }
                logger.warn("创建会议记录失败");
                return false;
            } else {
                logger.warn("数据存在，但更新失败");
                return false;
            }
        }
    }

    private static String toJSON(Member member) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(member);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 姓名、人员、是否参过会（1/0）、是否正在参会（1/0）
     * @param memberNO
     * @return
     */
    @Override
    public Boolean insertPeople(String memberNO) {
        int flag = 0;
        LambdaQueryWrapper<MeetingPO> lqw = new LambdaQueryWrapper<>();
        MeetingPO meetingPO = meetingMapper.selectOne(lqw);
        String memberJson = meetingPO.getCallee();
        // 解析JSON字符串为List<Member>对象
        List<Member> memberList = fromJSON(memberJson);
        for(Member member : memberList){
            if(memberNO.equals(member.getUserId())){
                member.setMembershipStatus(1);
                member.setOnlineStatus(1);
                flag = flag + 1;
            }
        }
        if(flag == 0){
            logger.info("会议没有此用户, memberNO ： " + memberNO);
            return false;
        }
        meetingPO.setCallee(toJSON(memberList));
        int updateResult = meetingMapper.updateById(meetingPO);
        if(updateResult == 0){
            logger.warn("更新入会会议表失败");
        }
        return true;
    }

    private static List<Member> fromJSON(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Member.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Boolean leavePeople(String memberNO) {
        int flag = 0;
        LambdaQueryWrapper<MeetingPO> lqw = new LambdaQueryWrapper<>();
        MeetingPO meetingPO = meetingMapper.selectOne(lqw);
        String memberJson = meetingPO.getCallee();
        String hostJSON = meetingPO.getHost();
        // 解析JSON字符串为List<Member>对象
        List<Member> memberList = fromJSON(memberJson);
        //离开会议人员改为1,0
        for(Member member : memberList){
            if(memberNO.equals(member.getUserId())){
                member.setMembershipStatus(1);
                member.setOnlineStatus(0);
            }
        }
        meetingPO.setCallee(toJSON(memberList));
        int updateResult = meetingMapper.updateById(meetingPO);
        if(updateResult == 0){
            logger.warn("更新会议表失败");
        }
        //检查会议中是否还有1，1的，如果有不做动作，如果没有啧记录会议并清空人员列表
        for(Member member : memberList){
            if(member.getMembershipStatus() == 1 && member.getOnlineStatus() == 1){
                flag = flag + 1;
            }
        }
        //flag代表会议中剩余1,1的数量
        if(flag == 0){
            List<Member> connList = new ArrayList<>();
            List<Member> unConnList = new ArrayList<>();
            LambdaQueryWrapper<MeetingLogPO> lambdaQueryWrap = new LambdaQueryWrapper<>();
            lambdaQueryWrap.eq(MeetingLogPO::getStatus, "进行中");
            MeetingLogPO meetingLog = meetingLogMapper.selectOne(lambdaQueryWrap);
            //找到进行中的会议记录，统计会议情况并更新
            for(Member member : memberList){
                if(member.getMembershipStatus() == 1){
                    connList.add(member);
                }else {
                    unConnList.add(member);
                }
            }
            MeetingLogDTO meetingLogDTO = BeanCopyUtils.copy(meetingLog, MeetingLogDTO.class);
            if(connList.size() > 0){
                meetingLogDTO.setConnected(toJSON(connList));
            }
            if(unConnList.size() > 0){
                meetingLogDTO.setNotConnectioned(toJSON(unConnList));
            }else {
                meetingLogDTO.setNotConnectioned("");
            }
            meetingLogDTO.setHost(hostJSON);
            meetingLogDTO.setStatus("已结束");
//            meetingLogDTO.setShouyingType(meetingLog.getShouyingType());
//            meetingLogDTO.setShouyingId(meetingLog.getShouyingId());
            Boolean updateMeetingLogResult = meetingLogService.updateMeetingLog(meetingLogDTO);
            if(updateMeetingLogResult){
                //更新叫应记录后清空会议人员列表
                meetingPO.setCallee("");
                meetingPO.setHost("");
                int cleanResult = meetingMapper.updateById(meetingPO);
                if(cleanResult == 0){
                    logger.warn("清理会议表失败");
                }
                return true;
            }
            logger.warn("更新会议记录失败");
            return false;
        }else {
            return true;
        }
    }

    private static String toJSON(List<Member> memberList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < memberList.size(); i++) {
            Member member = memberList.get(i);
            sb.append("{\"userId\":\"").append(member.getUserId()).append("\",")
            .append("\"userName\":\"").append(member.getUserName()).append("\",")
            .append("\"dept\":\"").append(member.getDept()).append("\",")
            .append("\"uid\":\"").append(member.getUid()).append("\",")
            .append("\"phone\":\"").append(member.getPhone()).append("\",")
            .append("\"account\":\"").append(member.getAccount()).append("\",")
            .append("\"membershipStatus\":").append(member.getMembershipStatus()).append(",")
            .append("\"onlineStatus\":").append(member.getOnlineStatus()).append("}");
            if (i < memberList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
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
        LambdaQueryWrapper<MeetingPO> lqw = buildQueryWrapper(meetingPeopleDTO);
        MeetingVO meetingVO = meetingMapper.selectVoOne(lqw);
        return meetingVO;
    }
}

