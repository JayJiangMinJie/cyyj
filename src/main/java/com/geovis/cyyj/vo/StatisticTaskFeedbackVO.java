package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 统计任务进度反馈;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "统计任务进度反馈",description = "")
public class StatisticTaskFeedbackVO implements Serializable,Cloneable{
    @ApiModelProperty(name = "统计任务id",notes = "统计任务id")
    private List<StatisticTaskUnitVO> statisticTaskUnitVOS;
    @ApiModelProperty(name = "单位数量",notes = "单位数量")
    private Integer unitNum;
    @ApiModelProperty(name = "已反馈数量",notes = "已反馈数量")
    private Integer feedbackNum;
    @ApiModelProperty(name = "未反馈数量",notes = "未反馈数量")
    private Integer nonFeedbackNum;

    public List<StatisticTaskUnitVO> getStatisticTaskUnitVOS() {
        return statisticTaskUnitVOS;
    }

    public void setStatisticTaskUnitVOS(List<StatisticTaskUnitVO> statisticTaskUnitVOS) {
        this.statisticTaskUnitVOS = statisticTaskUnitVOS;
    }

    public Integer getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(Integer unitNum) {
        this.unitNum = unitNum;
    }

    public Integer getFeedbackNum() {
        return feedbackNum;
    }

    public void setFeedbackNum(Integer feedbackNum) {
        this.feedbackNum = feedbackNum;
    }

    public Integer getNonFeedbackNum() {
        return nonFeedbackNum;
    }

    public void setNonFeedbackNum(Integer nonFeedbackNum) {
        this.nonFeedbackNum = nonFeedbackNum;
    }
}