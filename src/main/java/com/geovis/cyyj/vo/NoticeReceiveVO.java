package com.geovis.cyyj.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知下发;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "通知下发",description = "")
@JsonIgnoreProperties
public class NoticeReceiveVO implements Serializable,Cloneable{
    private Integer id;
    private String title ;
    private LocalDateTime startTime ;
    private LocalDateTime endTime ;
    private String status ;
    private LocalDateTime createTime;
    private Integer noticeDistributeId;
    private String receiveUnit ;
    private String filePath;
    private String receiveType;
    private String parentUserId;

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public void setNoticeDistributeId(Integer noticeDistributeId) {
        this.noticeDistributeId = noticeDistributeId;
    }

    public String getReceiveUnit() {
        return receiveUnit;
    }

    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getNoticeDistributeId() {
        return noticeDistributeId;
    }
}