package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据上报;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "数据上报",description = "")
public class DataReportVO implements Serializable,Cloneable{
    private Integer id;
    private String title ;
    private LocalDateTime releaseTime ;
    private LocalDateTime lastFillTime ;
    private String status ;
    private Integer statisticTaskId;
    private String reciveUnit ;
    private LocalDateTime createTime;
    private String userId;
    private String parentUserId;

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}