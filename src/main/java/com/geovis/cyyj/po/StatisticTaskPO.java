package com.geovis.cyyj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 统计任务表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "统计任务表",description = "")
@TableName("statistic_task")
public class StatisticTaskPO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title ;
    private LocalDateTime fillTime ;
    private String type ;
    private String status ;
    private String fillTemplate ;
    private String editor;
    private String issuer;
    private String reciveUnit;

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

    public LocalDateTime getFillTime() {
        return fillTime;
    }

    public void setFillTime(LocalDateTime fillTime) {
        this.fillTime = fillTime;
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

    public String getFillTemplate() {
        return fillTemplate;
    }

    public void setFillTemplate(String fillTemplate) {
        this.fillTemplate = fillTemplate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getReciveUnit() {
        return reciveUnit;
    }

    public void setReciveUnit(String reciveUnit) {
        this.reciveUnit = reciveUnit;
    }
}