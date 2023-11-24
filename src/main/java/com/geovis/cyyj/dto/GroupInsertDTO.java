package com.geovis.cyyj.dto;

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
 * 新增群组
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GroupInsertDTO", description = "新增群组DTO")
public class GroupInsertDTO implements Serializable {
    @ApiModelProperty(value = "id")
    private Integer id ;
    @ApiModelProperty(value = "标题")
    private String name ;
    @ApiModelProperty(value = "群组人数")
    private Integer peopleNum ;
    @ApiModelProperty(value = "群组人员")
    private String peopleContent ;
    @ApiModelProperty(value = "备注")
    private String remark ;
    @ApiModelProperty(value = "用户标识")
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
