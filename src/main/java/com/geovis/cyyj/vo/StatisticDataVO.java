package com.geovis.cyyj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计数据;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "统计数据VO",description = "")
public class StatisticDataVO implements Serializable,Cloneable{
    @ApiModelProperty(name = "统计数据id",notes = "统计数据id")
    private Integer id;
    @ApiModelProperty(name = "统计任务id",notes = "统计任务id")
    private Integer statisticTaskId;
    @ApiModelProperty(name = "行政单位",notes = "行政单位")
    private String unit ;
    @ApiModelProperty(name = "台风预警数量",notes = "台风预警数量")
    private Integer alertNum;
    @ApiModelProperty(name = "五停数量",notes = "五停数量")
    private Integer fiveStopSituationNum;
    @ApiModelProperty(name = "险情情况",notes = "险情情况")
    private Integer dengrousSituationNum;
    @ApiModelProperty(name = "特殊情况",notes = "特殊情况")
    private Integer specialSituation;
    @ApiModelProperty(name = "备注",notes = "备注")
    private String  notes;
    @ApiModelProperty(name = "状态",notes = "状态")
    private String  status;
    private Integer firstLevelResp;
    private Integer secondLevelResp;
    private Integer thirdLevelResp;
    private Integer fourthLevelResp;
    private Integer fishBoatNum;
    private Integer returningFishBoatNum;
    private Integer scenicSpotNum;
    private Integer closedScenicSpotNum;
    private Integer transferredPersonNum;
    private Integer returningPersonNum;
    private Integer waterloggingPointNum;
    private Integer notExcludedWaterloggingPointNum;
    private Integer presetTeamNum;
    private Integer presetPersonNum;
    private Integer presetDeviceNum;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatisticTaskId() {
        return statisticTaskId;
    }

    public void setStatisticTaskId(Integer statisticTaskId) {
        this.statisticTaskId = statisticTaskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getFiveStopSituationNum() {
        return fiveStopSituationNum;
    }

    public void setFiveStopSituationNum(Integer fiveStopSituationNum) {
        this.fiveStopSituationNum = fiveStopSituationNum;
    }

    public Integer getDengrousSituationNum() {
        return dengrousSituationNum;
    }

    public void setDengrousSituationNum(Integer dengrousSituationNum) {
        this.dengrousSituationNum = dengrousSituationNum;
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