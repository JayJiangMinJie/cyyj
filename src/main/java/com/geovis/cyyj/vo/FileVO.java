package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "文件",description = "")
public class FileVO implements Serializable,Cloneable{
    private Integer id;
    private String operatePerson ;
    private Integer noticeDistributeId;
    private LocalDateTime createTime;
    private String filePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }

    public Integer getNoticeDistributeId() {
        return noticeDistributeId;
    }

    public void setNoticeDistributeId(Integer noticeDistributeId) {
        this.noticeDistributeId = noticeDistributeId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}