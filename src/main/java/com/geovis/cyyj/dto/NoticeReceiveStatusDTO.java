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
 * 通知接收状态
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "NoticeReceiveStatusDTO", description = "通知接收状态DTO")
public class NoticeReceiveStatusDTO implements Serializable {
    @ApiModelProperty(value = "id(更新传)")
    private Integer id ;
    private Integer noticeDistributeId ;
    private String userId ;
    @ApiModelProperty(value = "状态(不传)")
    private String status ;
    @ApiModelProperty(value = "通知类型")
    private String type ;
    @ApiModelProperty(value = "已读状态")
    private Boolean isRead ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "截止时间")
    private LocalDateTime endTime ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNoticeDistributeId() {
        return noticeDistributeId;
    }

    public void setNoticeDistributeId(Integer noticeDistributeId) {
        this.noticeDistributeId = noticeDistributeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
