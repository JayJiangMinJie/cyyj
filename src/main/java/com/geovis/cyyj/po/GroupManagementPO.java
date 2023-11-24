package com.geovis.cyyj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 群组管理表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "群组管理表",description = "")
@TableName("group_management")
public class GroupManagementPO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name ;
    private LocalDateTime createTime ;
    private Integer peopleNum ;
    private String peopleContent ;
    private String remark ;
    private String userId;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}