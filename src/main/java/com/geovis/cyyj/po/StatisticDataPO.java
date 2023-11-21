package com.geovis.cyyj.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 统计数据表;
 * @author : jay
 * @date : 2023-10-18
 */
@ApiModel(value = "统计数据表",description = "")
@TableName("statistic_data")
public class StatisticDataPO implements Serializable,Cloneable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String unit;
    private Integer alertNum;
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
    private Integer statisticTaskId;
    private Integer presetTeamNum;
    private Integer presetPersonNum;
    private Integer presetDeviceNum;
    private Integer fiveStopSituation;
    private Integer dangerousSituation;
    private Integer specialSituation;
    private String notes;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNotExcludedWaterloggingPointNum() {
        return notExcludedWaterloggingPointNum;
    }

    public void setNotExcludedWaterloggingPointNum(Integer notExcludedWaterloggingPointNum) {
        this.notExcludedWaterloggingPointNum = notExcludedWaterloggingPointNum;
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