package com.geovis.cyyj.entity;

import io.swagger.annotations.ApiModel;

/**
 * 会议人员实体
 *
 * @author jay
 * @version V1.0
 * @date 2023/10/23 22:23
 */
@ApiModel("会议人员实体")
public class Member {
    private String userId;
    private String userName;
    private int membershipStatus;
    private int onlineStatus;
    private String dept;
    private String uid;
    private String phone;
    private String account;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(int membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
