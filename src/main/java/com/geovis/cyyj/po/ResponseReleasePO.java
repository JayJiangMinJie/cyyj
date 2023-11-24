package com.geovis.cyyj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 响应下发表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "响应下发表",description = "")
@TableName("response_release")
public class ResponseReleasePO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title ;
    private LocalDateTime releaseTime ;
    private LocalDateTime createTime ;
    private LocalDateTime updateTime ;
    private String type ;
    private String region ;
    private String status ;
    private String receiveUnit ;
    private String filePath;
    private String responseContent;
    private String userId;
    private String parentUserId;
    private String currentLevel;
    private String maxLevel;
    private LocalDateTime currentLevelAdjustTime ;
    private LocalDateTime maxLevelStartTime ;

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

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
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

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(String maxLevel) {
        this.maxLevel = maxLevel;
    }

    public LocalDateTime getCurrentLevelAdjustTime() {
        return currentLevelAdjustTime;
    }

    public void setCurrentLevelAdjustTime(LocalDateTime currentLevelAdjustTime) {
        this.currentLevelAdjustTime = currentLevelAdjustTime;
    }

    public LocalDateTime getMaxLevelStartTime() {
        return maxLevelStartTime;
    }

    public void setMaxLevelStartTime(LocalDateTime maxLevelStartTime) {
        this.maxLevelStartTime = maxLevelStartTime;
    }
}