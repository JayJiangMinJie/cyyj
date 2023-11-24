package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 进度反馈;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "进度反馈",description = "")
public class NoticeProgressFeedbackVO implements Serializable,Cloneable{
    private Integer id;
    private String city ;
    private LocalDateTime updateTime ;
    private LocalDateTime createTime ;
    private String county ;
    private String dept ;
    private String feedbackStatus;
    private String receiveStatus;
    private Integer noticeDistributeId;
    private String filePath;
    private String operatePerson;

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Integer getNoticeDistributeId() {
        return noticeDistributeId;
    }

    public void setNoticeDistributeId(Integer noticeDistributeId) {
        this.noticeDistributeId = noticeDistributeId;
    }
}