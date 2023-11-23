package com.geovis.cyyj.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 区县乡镇人员表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "区县乡镇人员表",description = "")
@TableName("district_user")
public class DistrictListPersonPO implements Serializable,Cloneable{
    private String orgName ;
    private String userName ;
    private String userId ;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}