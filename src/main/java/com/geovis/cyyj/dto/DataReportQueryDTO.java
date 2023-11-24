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
 * 数据上传
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "DataReportDTO", description = "数据上传DTO")
public class DataReportQueryDTO implements Serializable {

    @ApiModelProperty(value = "关键字")
    private String keyWord;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "开始最新填报时间",notes = "开始最新填报时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startLastFillTime ;
    @ApiModelProperty(value = "结束最新填报时间",notes = "结束最新填报时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endLastFillTime ;
    @ApiModelProperty(value = "开始发布时间",notes = "开始发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startReleaseTime ;
    @ApiModelProperty(value = "结束发布时间",notes = "结束发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endReleaseTime;
    @ApiModelProperty(value = "用户标识")
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartLastFillTime() {
        return startLastFillTime;
    }

    public void setStartLastFillTime(LocalDateTime startLastFillTime) {
        this.startLastFillTime = startLastFillTime;
    }

    public LocalDateTime getEndLastFillTime() {
        return endLastFillTime;
    }

    public void setEndLastFillTime(LocalDateTime endLastFillTime) {
        this.endLastFillTime = endLastFillTime;
    }

    public LocalDateTime getStartReleaseTime() {
        return startReleaseTime;
    }

    public void setStartReleaseTime(LocalDateTime startReleaseTime) {
        this.startReleaseTime = startReleaseTime;
    }

    public LocalDateTime getEndReleaseTime() {
        return endReleaseTime;
    }

    public void setEndReleaseTime(LocalDateTime endReleaseTime) {
        this.endReleaseTime = endReleaseTime;
    }
}
