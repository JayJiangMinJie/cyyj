package com.geovis.cyyj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 响应进度反馈
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ResponseProgressFeedbackDTO", description = "响应进度反馈DTO")
public class ResponseProgressFeedbackDTO implements Serializable {
    @ApiModelProperty(value = "id(更新传)")
    private Integer id ;
    @ApiModelProperty(value = "地市")
    private String city ;
    @ApiModelProperty(value = "区县")
    private String county ;
    @ApiModelProperty(value = "部门")
    private String dept ;
    @ApiModelProperty(value = "反馈状态")
    private String feedbackStatus ;
    @ApiModelProperty(value = "已读状态")
    private Boolean isRead ;
    @ApiModelProperty(value = "接收状态")
    private String receiveStatus;
    private Integer responseReleaseId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "截止时间")
    private LocalDateTime endTime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "反馈时间")
    private LocalDateTime feedbackTime ;
    private String filePath;
    private String operatePerson;
    private String userId;
    private String parentUserId;
    private String textContent;
    private String content;
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public LocalDateTime getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(LocalDateTime feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Integer getResponseReleaseId() {
        return responseReleaseId;
    }

    public void setResponseReleaseId(Integer responseReleaseId) {
        this.responseReleaseId = responseReleaseId;
    }
}
