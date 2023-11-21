package com.geovis.cyyj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通知报告
 *
 * @author jay
 * @version V1.0
 * @date 2023/10/23 22:23
 */
@ApiModel("通知报告DTO")
public class FileGenerateDTO {
    @ApiModelProperty(value = "汇报时间")
    private String reportTime;
    @ApiModelProperty(value = "工作人员姓名")
    private String staffName;
    @ApiModelProperty(value = "工作人员电话")
    private String staffTel;
    @ApiModelProperty(value = "网址")
    private String webSite;
    @ApiModelProperty(value = "附件1")
    private String annex1;
    @ApiModelProperty(value = "附件2")
    private String annex2;
    @ApiModelProperty(value = "附件3")
    private String annex3;
    @ApiModelProperty(value = "通知时间")
    private String noticeTime;

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffTel() {
        return staffTel;
    }

    public void setStaffTel(String staffTel) {
        this.staffTel = staffTel;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getAnnex1() {
        return annex1;
    }

    public void setAnnex1(String annex1) {
        this.annex1 = annex1;
    }

    public String getAnnex2() {
        return annex2;
    }

    public void setAnnex2(String annex2) {
        this.annex2 = annex2;
    }

    public String getAnnex3() {
        return annex3;
    }

    public void setAnnex3(String annex3) {
        this.annex3 = annex3;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }
}
