package com.geovis.cyyj.vo.meeting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 会议log;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "会议log",description = "会议log")
public class MeetingLogVO implements Serializable,Cloneable{
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "在线人")
    private String connected;
    @ApiModelProperty(value = "离线人")
    private String notConnectioned;
    @ApiModelProperty(value = "呼叫人")
    private String host;
    @ApiModelProperty(value = "状态")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }

    public String getNotConnectioned() {
        return notConnectioned;
    }

    public void setNotConnectioned(String notConnectioned) {
        this.notConnectioned = notConnectioned;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}