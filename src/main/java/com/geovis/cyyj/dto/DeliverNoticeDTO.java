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
 * 发布通知
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "DeliverNoticeDTO", description = "发布通知DTO")
public class DeliverNoticeDTO implements Serializable {
    @ApiModelProperty(value = "标题")
    private String title ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime ;
    @ApiModelProperty(value = "类别")
    private String type ;
    @ApiModelProperty(value = "状态")
    private String status ;
    @ApiModelProperty(value = "接收单位")
    private String reciveUnit ;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "通知下发id")
    private Integer noticeDistributeId;
    @ApiModelProperty(value = "通知内容")
    private String noticeContent;
    @ApiModelProperty(value = "用户标识")
    private String userId;
    @ApiModelProperty(value = "上级用户标识")
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

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getNoticeDistributeId() {
        return noticeDistributeId;
    }

    public void setNoticeDistributeId(Integer noticeDistributeId) {
        this.noticeDistributeId = noticeDistributeId;
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

    public String getReciveUnit() {
        return reciveUnit;
    }

    public void setReciveUnit(String reciveUnit) {
        this.reciveUnit = reciveUnit;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
