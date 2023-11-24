package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 区县乡镇人员返回结果;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "区县乡镇人员返回结果",description = "")
public class DistrictListPersonResultVO implements Serializable,Cloneable{
    private String orgName ;
    private Map<String, String> userMap;
    private Map<String, List<Map<String, String>>> townMap;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Map<String, String> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, String> userMap) {
        this.userMap = userMap;
    }

    public Map<String, List<Map<String, String>>> getTownMap() {
        return townMap;
    }

    public void setTownMap(Map<String, List<Map<String, String>>> townMap) {
        this.townMap = townMap;
    }
}