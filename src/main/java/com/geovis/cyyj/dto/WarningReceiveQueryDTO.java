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
 * 预警接收
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "WarningReceiveQueryDTO", description = "预警接收DTO")
public class WarningReceiveQueryDTO implements Serializable {

    @ApiModelProperty(value = "关键字")
    private String keyWord;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "开始日期",notes = "开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime ;

    @ApiModelProperty(value = "结束日期",notes = "结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "用户标识")
    private String userId;
    private String region;
    private String type;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
