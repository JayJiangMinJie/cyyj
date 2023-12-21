package com.geovis.cyyj.dto.meeting;

import com.geovis.cyyj.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 会议人员DTO
 *
 * @author jay
 * @version V1.0
 * @date 2023/10/23 22:23
 */
@ApiModel("会议人员DTO")
public class MeetingPeopleDTO {
    @ApiModelProperty(value = "meetingId")
    private String meetingId;
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "channel")
    private String channel;
    @ApiModelProperty(value = "主持人")
    private Member host;
    @ApiModelProperty(value = "被呼叫人")
    private List<Member> memberList;
    @ApiModelProperty(value = "叫应类别")
    private String shouyingType;
    @ApiModelProperty(value = "叫应id")
    private String shouyingId;

    public String getShouyingType() {
        return shouyingType;
    }

    public void setShouyingType(String shouyingType) {
        this.shouyingType = shouyingType;
    }

    public String getShouyingId() {
        return shouyingId;
    }

    public void setShouyingId(String shouyingId) {
        this.shouyingId = shouyingId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Member getHost() {
        return host;
    }

    public void setHost(Member host) {
        this.host = host;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

}
