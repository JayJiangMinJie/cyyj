package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知下发;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "通知下发",description = "")
public class StatisticTaskVO implements Serializable,Cloneable{
    private Integer id;
    private String title ;
    private LocalDateTime fillTime ;
    private String type ;
    private String status ;
    private LocalDateTime createTime;
    private String fillTemplate ;
    private String editor;
    private String issuer;
    private String reciveUnit;
    private String userId;
    private String parentUserId;

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public LocalDateTime getFillTime() {
        return fillTime;
    }

    public void setFillTime(LocalDateTime fillTime) {
        this.fillTime = fillTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFillTemplate() {
        return fillTemplate;
    }

    public void setFillTemplate(String fillTemplate) {
        this.fillTemplate = fillTemplate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getReciveUnit() {
        return reciveUnit;
    }

    public void setReciveUnit(String reciveUnit) {
        this.reciveUnit = reciveUnit;
    }
}