package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 群组管理;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "群组管理",description = "")
public class GroupManagementVO implements Serializable,Cloneable{
    private Integer id;
    private String name ;
    private Integer peopleNum ;
    private String peopleContent ;
    private String remark ;
    private String userId;
    private LocalDateTime createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getPeopleContent() {
        return peopleContent;
    }

    public void setPeopleContent(String peopleContent) {
        this.peopleContent = peopleContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}