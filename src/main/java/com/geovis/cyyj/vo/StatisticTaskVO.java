package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 统计任务;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "统计任务",description = "")
public class StatisticTaskVO implements Serializable,Cloneable{
    private Integer id;
    @ApiModelProperty(value = "标题",notes = "标题")
    private String title ;
    @ApiModelProperty(value = "状态",notes = "状态")
    private String status ;
    @ApiModelProperty(value = "填报模板",notes = "填报模板")
    private String fillTemplate ;
    @ApiModelProperty(value = "签发人",notes = "签发人")
    private String issuer ;
    @ApiModelProperty(value = "接收单位",notes = "接收单位")
    private String receiveUnit ;
    @ApiModelProperty(value = "编辑人",notes = "编辑人")
    private String editor;
    @ApiModelProperty(value = "用户标识")
    private String userId;
    @ApiModelProperty(value = "父用户标识")
    private String parentUserId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最新填报时间")
    private LocalDateTime lastFillTime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime releaseTime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "截止时间")
    private LocalDateTime endTime ;

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFillTemplate() {
        return fillTemplate;
    }

    public void setFillTemplate(String fillTemplate) {
        this.fillTemplate = fillTemplate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getReceiveUnit() {
        return receiveUnit;
    }

    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
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

    public LocalDateTime getLastFillTime() {
        return lastFillTime;
    }

    public void setLastFillTime(LocalDateTime lastFillTime) {
        this.lastFillTime = lastFillTime;
    }
}