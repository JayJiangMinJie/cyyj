package com.geovis.cyyj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据上报表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "数据上报表",description = "")
@TableName("data_report")
public class DataReportPO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title ;
    private LocalDateTime releaseTime ;
    private LocalDateTime lastFillTime ;
    private String status ;
    private Integer statisticTaskId;
    private String reciveUnit ;
    private LocalDateTime createTime ;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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
}