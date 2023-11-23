package com.geovis.cyyj.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class DataReportDTO implements Serializable {
    @ApiModelProperty(value = "标题")
    private String title ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime releaseTime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后填报时间")
    private LocalDateTime lastFillTime ;
    @ApiModelProperty(value = "状态")
    private String status ;
    @ApiModelProperty(value = "统计任务id")
    private Integer statisticTaskId;
    @ApiModelProperty(value = "接收单位")
    private String reciveUnit ;
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

    public LocalDateTime getLastFillTime() {
        return lastFillTime;
    }

    public void setLastFillTime(LocalDateTime lastFillTime) {
        this.lastFillTime = lastFillTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatisticTaskId() {
        return statisticTaskId;
    }

    public void setStatisticTaskId(Integer statisticTaskId) {
        this.statisticTaskId = statisticTaskId;
    }

    public String getReciveUnit() {
        return reciveUnit;
    }

    public void setReciveUnit(String reciveUnit) {
        this.reciveUnit = reciveUnit;
    }
}
