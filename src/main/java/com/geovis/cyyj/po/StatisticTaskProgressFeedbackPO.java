package com.geovis.cyyj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 统计任务进度反馈表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "统计任务进度反馈表",description = "")
@TableName("statistic_task_progress_feedback")
public class StatisticTaskProgressFeedbackPO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private LocalDateTime updateTime ;
    private LocalDateTime createTime ;
    private LocalDateTime feedbackTime ;
    private String dept ;
    private String feedbackStatus;
    private Integer StatisticTaskId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(LocalDateTime feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

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

    public Integer getStatisticTaskId() {
        return StatisticTaskId;
    }

    public void setStatisticTaskId(Integer statisticTaskId) {
        StatisticTaskId = statisticTaskId;
    }
}