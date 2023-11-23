package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 区县乡镇人员;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "区县乡镇人员",description = "")
public class DistrictListPersonVO implements Serializable,Cloneable{
    private String orgName ;
    private String userName ;
    private String userId ;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}