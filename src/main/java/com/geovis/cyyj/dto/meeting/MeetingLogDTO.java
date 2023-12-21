package com.geovis.cyyj.dto.meeting;

import com.geovis.cyyj.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 会议人员DTO
 *
 * @author jay
 * @version V1.0
 * @date 2023/10/23 22:23
 */
@ApiModel("会议人员DTO")
public class MeetingLogDTO {
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "在线人")
    private String connected;
    @ApiModelProperty(value = "离线人")
    private String notConnectioned;
    @ApiModelProperty(value = "呼叫人")
    private String host;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "叫应类别")
    private String shouyingType;
    @ApiModelProperty(value = "叫应id")
    private String shouyingId;

    public String getShouyingType() {
        return shouyingType;
    }

    public void setShouyingType(String shouyingType) {
        this.shouyingType = shouyingType;
    }

    public String getShouyingId() {
        return shouyingId;
    }

    public void setShouyingId(String shouyingId) {
        this.shouyingId = shouyingId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }

    public String getNotConnectioned() {
        return notConnectioned;
    }

    public void setNotConnectioned(String notConnectioned) {
        this.notConnectioned = notConnectioned;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}
