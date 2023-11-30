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
 * 响应变更
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ResponseChangeDTO", description = "响应变更DTO")
public class ResponseChangeDTO implements Serializable {

    @ApiModelProperty(value = "标题")
    private String title ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime releaseTime ;
    @ApiModelProperty(value = "类别")
    private String type ;
    @ApiModelProperty(value = "状态")
    private String status ;
    @ApiModelProperty(value = "接收单位")
    private String receiveUnit ;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "响应下发id")
    private Integer responseReleaseId;
    @ApiModelProperty(value = "响应内容")
    private String responseContent;
    @ApiModelProperty(value = "用户标识")
    private String userId;
    @ApiModelProperty(value = "上级用户标识")
    private String parentUserId;
    @ApiModelProperty(value = "区域")
    private String region ;
    @ApiModelProperty(value = "当前等级")
    private String currentLevel;
    @ApiModelProperty(value = "最大等级")
    private String maxLevel;
    @ApiModelProperty(value = "当前级别调整时间")
    private LocalDateTime currentLevelAdjustTime ;
    @ApiModelProperty(value = "最高级别启动时间")
    private LocalDateTime maxLevelStartTime ;
    @ApiModelProperty(value = "操作类型")
    private String operateType;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
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

    public Integer getResponseReleaseId() {
        return responseReleaseId;
    }

    public void setResponseReleaseId(Integer responseReleaseId) {
        this.responseReleaseId = responseReleaseId;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
