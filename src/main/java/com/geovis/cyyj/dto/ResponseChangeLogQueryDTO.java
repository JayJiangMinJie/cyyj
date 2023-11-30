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
 * 响应接收
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ResponseReceiveQueryDTO", description = "响应接收DTO")
public class ResponseChangeLogQueryDTO implements Serializable {
    @ApiModelProperty(value = "响应下发id")
    private Integer responseReleaseId;

    @ApiModelProperty(value = "用户标识")
    private String userId;

    public Integer getResponseReleaseId() {
        return responseReleaseId;
    }

    public void setResponseReleaseId(Integer responseReleaseId) {
        this.responseReleaseId = responseReleaseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
