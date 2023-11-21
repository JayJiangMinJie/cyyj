package com.geovis.cyyj.vo;

import com.geovis.cyyj.po.DistrictListPO;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 区县乡镇;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "区县乡镇",description = "")
public class DistrictListVO implements Serializable,Cloneable{
    private Integer code;
    private String name ;
    private Integer parentId ;
    private String address ;
    private String level ;
    private List<DistrictListVO> children = new ArrayList<>();

    public List<DistrictListVO> getChildren() {
        return children;
    }

    public void setChildren(List<DistrictListVO> children) {
        this.children = children;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}