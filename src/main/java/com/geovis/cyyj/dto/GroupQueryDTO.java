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
 * 群组管理查询
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GroupQueryDTO", description = "群组管理查询DTO")
public class GroupQueryDTO implements Serializable {

    @ApiModelProperty(value = "关键字")
    private String keyWord;

    @ApiModelProperty(value = "用户标识")
    private String userId;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
