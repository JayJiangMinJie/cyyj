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
@ApiModel(value = "统计任务进度反馈列表",description = "")
public class StatisticTaskFeedbackListVO implements Serializable,Cloneable{
    @ApiModelProperty(name = "部门",notes = "部门")
    private String dept;
    @ApiModelProperty(name = "反馈状态",notes = "反馈状态")
    private String feedbackStatus;
    @ApiModelProperty(name = "反馈时间",notes = "反馈时间")
    private LocalDateTime feedbackTime;
    @ApiModelProperty(name = "反馈状态",notes = "反馈状态")
    private Integer statisticTaskId;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public LocalDateTime getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(LocalDateTime feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public Integer getStatisticTaskId() {
        return statisticTaskId;
    }

    public void setStatisticTaskId(Integer statisticTaskId) {
        this.statisticTaskId = statisticTaskId;
    }
}