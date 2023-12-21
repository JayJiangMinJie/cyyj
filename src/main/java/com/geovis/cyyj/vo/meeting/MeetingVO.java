package com.geovis.cyyj.vo.meeting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 会议;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "会议",description = "会议")
public class MeetingVO implements Serializable,Cloneable{
    @ApiModelProperty(value = "id")
    private String meetingId;
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "channel")
    private String channel;
    @ApiModelProperty(value = "主持人")
    private String host;
    @ApiModelProperty(value = "被呼叫人")
    private String callee;

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
}