package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 统计任务单位VO;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "统计任务单位VO",description = "")
public class StatisticTaskUnitVO implements Serializable,Cloneable{
    private Integer id;
    @ApiModelProperty(name = "接收单位",notes = "接收单位")
    private String reciveUnit ;
    @ApiModelProperty(name = "状态",notes = "状态")
    private String status ;
    @ApiModelProperty(name = "统计任务id",notes = "统计任务id")
    private Integer statisticTaskId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReciveUnit() {
        return reciveUnit;
    }

    public void setReciveUnit(String reciveUnit) {
        this.reciveUnit = reciveUnit;
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

}