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
    @ApiModelProperty(name = "应急响应情况",notes = "应急响应情况")
    private Map<String, Integer> emergencyResponseSituationMap = new HashMap<>();
    @ApiModelProperty(name = "渔船回港情况",notes = "渔船回港情况")
    private Map<String, Integer> fishBoatReturnSituationMap = new HashMap<>();
    @ApiModelProperty(name = "景区关停情况",notes = "景区关停情况")
    private Map<String, Integer> scenicSpotsCloseSituationMap = new HashMap<>();
    @ApiModelProperty(name = "人员转移避险情况",notes = "人员转移避险情况")
    private Map<String, Integer> personnelTransferHedgingSituationMap = new HashMap<>();
    @ApiModelProperty(name = "内涝点情况",notes = "内涝点情况")
    private Map<String, Integer> waterloggingPointsSituationMap = new HashMap<>();
    @ApiModelProperty(name = "预置投入救援情况",notes = "预置投入救援情况")
    private Map<String, Integer> presetForRescueSituationMap = new HashMap<>();
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

    public Map<String, Integer> getEmergencyResponseSituationMap() {
        return emergencyResponseSituationMap;
    }

    public void setEmergencyResponseSituationMap(Map<String, Integer> emergencyResponseSituationMap) {
        this.emergencyResponseSituationMap = emergencyResponseSituationMap;
    }

    public Map<String, Integer> getFishBoatReturnSituationMap() {
        return fishBoatReturnSituationMap;
    }

    public void setFishBoatReturnSituationMap(Map<String, Integer> fishBoatReturnSituationMap) {
        this.fishBoatReturnSituationMap = fishBoatReturnSituationMap;
    }

    public Map<String, Integer> getScenicSpotsCloseSituationMap() {
        return scenicSpotsCloseSituationMap;
    }

    public void setScenicSpotsCloseSituationMap(Map<String, Integer> scenicSpotsCloseSituationMap) {
        this.scenicSpotsCloseSituationMap = scenicSpotsCloseSituationMap;
    }

    public Map<String, Integer> getPersonnelTransferHedgingSituationMap() {
        return personnelTransferHedgingSituationMap;
    }

    public void setPersonnelTransferHedgingSituationMap(Map<String, Integer> personnelTransferHedgingSituationMap) {
        this.personnelTransferHedgingSituationMap = personnelTransferHedgingSituationMap;
    }

    public Map<String, Integer> getWaterloggingPointsSituationMap() {
        return waterloggingPointsSituationMap;
    }

    public void setWaterloggingPointsSituationMap(Map<String, Integer> waterloggingPointsSituationMap) {
        this.waterloggingPointsSituationMap = waterloggingPointsSituationMap;
    }

    public Map<String, Integer> getPresetForRescueSituationMap() {
        return presetForRescueSituationMap;
    }

    public void setPresetForRescueSituationMap(Map<String, Integer> presetForRescueSituationMap) {
        this.presetForRescueSituationMap = presetForRescueSituationMap;
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