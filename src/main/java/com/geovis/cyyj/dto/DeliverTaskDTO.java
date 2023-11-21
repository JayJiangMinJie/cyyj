package com.geovis.cyyj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 发布任务
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "DeliverTaskDTO", description = "发布任务DTO")
public class DeliverTaskDTO implements Serializable {
    @ApiModelProperty(value = "标题",notes = "标题")
    private String title ;
    @ApiModelProperty(value = "填报模板",notes = "填报模板")
    private String fillTemplate ;
    @ApiModelProperty(value = "签发人",notes = "签发人")
    private String issuer ;
    @ApiModelProperty(value = "接收单位",notes = "接收单位")
    private String reciveUnit ;
    @ApiModelProperty(value = "编辑人",notes = "编辑人")
    private String editor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFillTemplate() {
        return fillTemplate;
    }

    public void setFillTemplate(String fillTemplate) {
        this.fillTemplate = fillTemplate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getReciveUnit() {
        return reciveUnit;
    }

    public void setReciveUnit(String reciveUnit) {
        this.reciveUnit = reciveUnit;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}
