package com.geovis.cyyj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
    private String host;
    @ApiModelProperty(value = "被呼叫人")
    private String callee;
    @ApiModelProperty(value = "操作类别")
    private String type;

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCallee() {
        return callee;
    }

    public void setCallee(String callee) {
        this.callee = callee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
