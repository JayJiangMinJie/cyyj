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
 * 发布预警
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "DeliverWarningDTO", description = "发布预警DTO")
public class DeliverWarningDTO implements Serializable {
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
    @ApiModelProperty(value = "预警下发id")
    private Integer disasterWarningId;
    @ApiModelProperty(value = "预警内容")
    private String warningContent;
    @ApiModelProperty(value = "用户标识")
    private String userId;
    @ApiModelProperty(value = "上级用户标识")
    private String parentUserId;
    @ApiModelProperty(value = "区域")
    private String region ;

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

    public Integer getDisasterWarningId() {
        return disasterWarningId;
    }

    public void setDisasterWarningId(Integer disasterWarningId) {
        this.disasterWarningId = disasterWarningId;
    }

    public String getWarningContent() {
        return warningContent;
    }

    public void setWarningContent(String warningContent) {
        this.warningContent = warningContent;
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
}
