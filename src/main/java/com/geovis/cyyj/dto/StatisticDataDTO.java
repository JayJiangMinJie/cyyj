package com.geovis.cyyj.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 统计数据
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "StatisticDataDTO", description = "统计数据DTO")
public class StatisticDataDTO implements Serializable {
    @ApiModelProperty(value = "单位", notes = "单位")
    private String unit;
    @ApiModelProperty(value = "台风预警数量", notes = "台风预警数量")
    private Integer alertNum;
    @ApiModelProperty(value = "一级响应", notes = "一级响应")
    private Integer firstLevelResp;
    @ApiModelProperty(value = "二级响应", notes = "二级响应")
    private Integer secondLevelResp;
    @ApiModelProperty(value = "三级响应", notes = "三级响应")
    private Integer thirdLevelResp;
    @ApiModelProperty(value = "四级响应", notes = "四级响应")
    private Integer fourthLevelResp;
    @ApiModelProperty(value = "渔船数量", notes = "渔船数量")
    private Integer fishBoatNum;
    @ApiModelProperty(value = "回港渔船数量", notes = "回港渔船数量")
    private Integer returningFishBoatNum;
    @ApiModelProperty(value = "景区数量", notes = "景区数量")
    private Integer scenicSpotNum;
    @ApiModelProperty(value = "关闭景区数量", notes = "关闭景区数量")
    private Integer closedScenicSpotNum;
    @ApiModelProperty(value = "已转移遇险人员数量", notes = "已转移遇险人员数量")
    private Integer transferredPersonNum;
    @ApiModelProperty(value = "返回人员数量", notes = "返回人员数量")
    private Integer returningPersonNum;
    @ApiModelProperty(value = "内涝点数量", notes = "内涝点数量")
    private Integer waterloggingPointNum;
    @ApiModelProperty(value = "未排除内涝点数量", notes = "未排除内涝点数量")
    private Integer notExcludedWaterloggingPointNum;
    @ApiModelProperty(value = "统计任务唯一值", notes = "统计任务唯一值")
    private Integer statisticTaskId;
    @ApiModelProperty(value = "累计预置队伍数量", notes = "累计预置队伍数量")
    private Integer presetTeamNum;
    @ApiModelProperty(value = "累计预置人员数量", notes = "累计预置人员数量")
    private Integer presetPersonNum;
    @ApiModelProperty(value = "累计预置设备数量", notes = "累计预置设备数量")
    private Integer presetDeviceNum;
    @ApiModelProperty(value = "五停情况", notes = "五停情况")
    private Integer fiveStopSituation;
    @ApiModelProperty(value = "险情情况", notes = "险情情况")
    private Integer dangerousSituation;
    @ApiModelProperty(value = "特殊情况", notes = "特殊情况")
    private Integer specialSituation;
    @ApiModelProperty(value = "备注", notes = "备注")
    private String notes;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getAlertNum() {
        return alertNum;
    }

    public void setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
    }

    public Integer getFirstLevelResp() {
        return firstLevelResp;
    }

    public void setFirstLevelResp(Integer firstLevelResp) {
        this.firstLevelResp = firstLevelResp;
    }

    public Integer getSecondLevelResp() {
        return secondLevelResp;
    }

    public void setSecondLevelResp(Integer secondLevelResp) {
        this.secondLevelResp = secondLevelResp;
    }

    public Integer getThirdLevelResp() {
        return thirdLevelResp;
    }

    public void setThirdLevelResp(Integer thirdLevelResp) {
        this.thirdLevelResp = thirdLevelResp;
    }

    public Integer getFourthLevelResp() {
        return fourthLevelResp;
    }

    public void setFourthLevelResp(Integer fourthLevelResp) {
        this.fourthLevelResp = fourthLevelResp;
    }

    public Integer getFishBoatNum() {
        return fishBoatNum;
    }

    public void setFishBoatNum(Integer fishBoatNum) {
        this.fishBoatNum = fishBoatNum;
    }

    public Integer getReturningFishBoatNum() {
        return returningFishBoatNum;
    }

    public void setReturningFishBoatNum(Integer returningFishBoatNum) {
        this.returningFishBoatNum = returningFishBoatNum;
    }

    public Integer getScenicSpotNum() {
        return scenicSpotNum;
    }

    public void setScenicSpotNum(Integer scenicSpotNum) {
        this.scenicSpotNum = scenicSpotNum;
    }

    public Integer getClosedScenicSpotNum() {
        return closedScenicSpotNum;
    }

    public void setClosedScenicSpotNum(Integer closedScenicSpotNum) {
        this.closedScenicSpotNum = closedScenicSpotNum;
    }

    public Integer getTransferredPersonNum() {
        return transferredPersonNum;
    }

    public void setTransferredPersonNum(Integer transferredPersonNum) {
        this.transferredPersonNum = transferredPersonNum;
    }

    public Integer getReturningPersonNum() {
        return returningPersonNum;
    }

    public void setReturningPersonNum(Integer returningPersonNum) {
        this.returningPersonNum = returningPersonNum;
    }

    public Integer getWaterloggingPointNum() {
        return waterloggingPointNum;
    }

    public void setWaterloggingPointNum(Integer waterloggingPointNum) {
        this.waterloggingPointNum = waterloggingPointNum;
    }

    public Integer getNotExcludedWaterloggingPointNum() {
        return notExcludedWaterloggingPointNum;
    }

    public void setNotExcludedWaterloggingPointNum(Integer notExcludedWaterloggingPointNum) {
        this.notExcludedWaterloggingPointNum = notExcludedWaterloggingPointNum;
    }

    public Integer getStatisticTaskId() {
        return statisticTaskId;
    }

    public void setStatisticTaskId(Integer statisticTaskId) {
        this.statisticTaskId = statisticTaskId;
    }

    public Integer getPresetTeamNum() {
        return presetTeamNum;
    }

    public void setPresetTeamNum(Integer presetTeamNum) {
        this.presetTeamNum = presetTeamNum;
    }

    public Integer getPresetPersonNum() {
        return presetPersonNum;
    }

    public void setPresetPersonNum(Integer presetPersonNum) {
        this.presetPersonNum = presetPersonNum;
    }

    public Integer getPresetDeviceNum() {
        return presetDeviceNum;
    }

    public void setPresetDeviceNum(Integer presetDeviceNum) {
        this.presetDeviceNum = presetDeviceNum;
    }

    public Integer getFiveStopSituation() {
        return fiveStopSituation;
    }

    public void setFiveStopSituation(Integer fiveStopSituation) {
        this.fiveStopSituation = fiveStopSituation;
    }

    public Integer getDangerousSituation() {
        return dangerousSituation;
    }

    public void setDangerousSituation(Integer dangerousSituation) {
        this.dangerousSituation = dangerousSituation;
    }

    public Integer getSpecialSituation() {
        return specialSituation;
    }

    public void setSpecialSituation(Integer specialSituation) {
        this.specialSituation = specialSituation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
