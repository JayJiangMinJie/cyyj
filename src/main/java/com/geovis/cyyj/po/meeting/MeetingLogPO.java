package com.geovis.cyyj.po.meeting;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 会议记录;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "会议记录",description = "")
@TableName("meeting_log")
public class MeetingLogPO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private int id;
    private String connected;
    private String notConnectioned;
    private String host;
    private String status;
    private String shouyingType;
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