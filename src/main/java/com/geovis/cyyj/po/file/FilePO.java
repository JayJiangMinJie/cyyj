package com.geovis.cyyj.po.file;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 上传文件信息表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "上传文件信息表",description = "")
@TableName("file_info")
public class FilePO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private int id;
    private String operatePerson ;
    private String filePath;
    private int noticeDistributeId ;
    private LocalDateTime createTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getNoticeDistributeId() {
        return noticeDistributeId;
    }

    public void setNoticeDistributeId(int noticeDistributeId) {
        this.noticeDistributeId = noticeDistributeId;
    }
}