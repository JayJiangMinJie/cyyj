package com.geovis.cyyj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 统计任务表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "统计任务表",description = "")
@TableName("statistic_task")
public class StatisticTaskPO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title ;
    private String status ;
    private String fillTemplate ;
    private String issuer ;
    private String receiveUnit ;
    private String editor;
    private String userId;
    private String parentUserId;
    private LocalDateTime lastFillTime ;
    private LocalDateTime releaseTime ;
    private LocalDateTime createTime ;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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