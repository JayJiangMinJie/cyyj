package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.StatisticDataDTO;
import com.geovis.cyyj.mapper.StatisticDataMapper;
import com.geovis.cyyj.po.StatisticDataPO;
import com.geovis.cyyj.service.IStatisticDataService;
import com.geovis.cyyj.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 统计数据 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticDataServiceImpl extends ServiceImpl<StatisticDataMapper, StatisticDataPO> implements IStatisticDataService {

    @Autowired
    private final StatisticDataMapper statisticDataMapper;

    @Override
    public List<StatisticDataVO> getStatisticDataList(Integer taskId, String userId) {
        List<StatisticDataVO> statisticDataVOList = new ArrayList<>();
        StatisticDataVO statistic;
        //应急响应情况
        Map<String, Integer> emergencyResponseSituationMap;
        //渔船回港情况
        Map<String, Integer> fishBoatReturnSituationMap;
        //景区关停情况
        Map<String, Integer> scenicSpotsCloseSituationMap;
        //人员转移避险情况
        Map<String, Integer> personnelTransferHedgingSituationMap;
        //内涝点情况
        Map<String, Integer> waterloggingPointsSituationMap;
        //预置投入救援情况
        Map<String, Integer> presetForRescueSituationMap;
        LambdaQueryWrapper<StatisticDataPO> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(taskId != 0, StatisticDataPO::getStatisticTaskId, taskId);
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(userId), StatisticDataPO::getUserId, userId);
        List<StatisticDataPO> statisticDataPOS = statisticDataMapper.selectList(lambdaQueryWrapper);
        for(StatisticDataPO statisticDataPO : statisticDataPOS){
            emergencyResponseSituationMap = new HashMap<>();
            fishBoatReturnSituationMap = new HashMap<>();
            scenicSpotsCloseSituationMap = new HashMap<>();
            personnelTransferHedgingSituationMap = new HashMap<>();
            waterloggingPointsSituationMap = new HashMap<>();
            presetForRescueSituationMap = new HashMap<>();

            emergencyResponseSituationMap.put("firstLevelResp", statisticDataPO.getFirstLevelResp());
            emergencyResponseSituationMap.put("secondLevelResp", statisticDataPO.getSecondLevelResp());
            emergencyResponseSituationMap.put("thirdLevelResp", statisticDataPO.getThirdLevelResp());
            emergencyResponseSituationMap.put("fourthLevelResp", statisticDataPO.getFourthLevelResp());

            fishBoatReturnSituationMap.put("fishBoatNum", statisticDataPO.getFishBoatNum());
            fishBoatReturnSituationMap.put("returningFishBoatNum", statisticDataPO.getReturningFishBoatNum());

            scenicSpotsCloseSituationMap.put("scenicSpotNum", statisticDataPO.getScenicSpotNum());
            scenicSpotsCloseSituationMap.put("closedScenicSpotNum", statisticDataPO.getClosedScenicSpotNum());

            personnelTransferHedgingSituationMap.put("transferredPersonNum", statisticDataPO.getTransferredPersonNum());
            personnelTransferHedgingSituationMap.put("returningPersonNum", statisticDataPO.getReturningPersonNum());

            waterloggingPointsSituationMap.put("waterloggingPointNum", statisticDataPO.getWaterloggingPointNum());
            waterloggingPointsSituationMap.put("notExcludedWaterloggingPointNum", statisticDataPO.getNotExcludedWaterloggingPointNum());

            presetForRescueSituationMap.put("presetTeamNum", statisticDataPO.getPresetTeamNum());
            presetForRescueSituationMap.put("presetPersonNum", statisticDataPO.getPresetPersonNum());
            presetForRescueSituationMap.put("presetDeviceNum", statisticDataPO.getPresetDeviceNum());

            statistic = new StatisticDataVO();
            statistic.setStatisticTaskId(statisticDataPO.getStatisticTaskId());
            statistic.setAlertNum(statisticDataPO.getAlertNum());
            statistic.setDengrousSituationNum(statisticDataPO.getDangerousSituation());
            statistic.setEmergencyResponseSituationMap(emergencyResponseSituationMap);
            statistic.setId(statisticDataPO.getId());
            statistic.setNotes(statisticDataPO.getNotes());
            statistic.setFishBoatReturnSituationMap(fishBoatReturnSituationMap);
            statistic.setFiveStopSituationNum(statisticDataPO.getFiveStopSituation());
            statistic.setPersonnelTransferHedgingSituationMap(personnelTransferHedgingSituationMap);
            statistic.setPresetForRescueSituationMap(presetForRescueSituationMap);
            statistic.setScenicSpotsCloseSituationMap(scenicSpotsCloseSituationMap);
            statistic.setSpecialSituation(statisticDataPO.getSpecialSituation());
            statistic.setUnit(statisticDataPO.getUnit());
            statistic.setWaterloggingPointsSituationMap(waterloggingPointsSituationMap);
            statistic.setStatus(statisticDataPO.getStatus());
            statisticDataVOList.add(statistic);
        }
        return statisticDataVOList;
    }

    @Override
    public StatisticTaskFeedbackVO getStatisticTaskFeedback(Integer taskId, String userId) {
        StatisticTaskFeedbackVO statisticTaskFeedbackVO= new StatisticTaskFeedbackVO();
        int unitNum = 0;
        int feedbackNum = 0;
        int nonFeedbackNum = 0;
        List<StatisticTaskUnitVO> statisticTaskUnitVOS = new ArrayList<>();
        StatisticTaskUnitVO statisticTaskUnitVO;
        LambdaQueryWrapper<StatisticDataPO> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(taskId != 0, StatisticDataPO::getStatisticTaskId, taskId);
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(userId), StatisticDataPO::getUserId, userId);
        List<StatisticDataPO> statisticDataPOS = statisticDataMapper.selectList(lambdaQueryWrapper);
        for(StatisticDataPO statistic : statisticDataPOS){
            statisticTaskUnitVO = new StatisticTaskUnitVO();
            statisticTaskUnitVO.setStatisticTaskId(statistic.getStatisticTaskId());
            statisticTaskUnitVO.setReciveUnit(statistic.getUnit());
            statisticTaskUnitVO.setId(statistic.getId());
            statisticTaskUnitVO.setStatus(statistic.getStatus());
            if("已反馈".equals(statistic.getStatus())){
                feedbackNum = feedbackNum + 1;
            }else if ("未反馈".equals(statistic.getStatus())){
                nonFeedbackNum = nonFeedbackNum + 1;
            }
            unitNum = unitNum + 1;
            statisticTaskUnitVOS.add(statisticTaskUnitVO);
        }
        statisticTaskFeedbackVO.setUnitNum(unitNum);
        statisticTaskFeedbackVO.setFeedbackNum(feedbackNum);
        statisticTaskFeedbackVO.setNonFeedbackNum(nonFeedbackNum);
        statisticTaskFeedbackVO.setStatisticTaskUnitVOS(statisticTaskUnitVOS);
        return statisticTaskFeedbackVO;
    }

    @Override
    public Boolean statisticDataUpload(StatisticDataDTO statisticDataDTO) {
        StatisticDataPO statisticDataPO = BeanCopyUtils.copy(statisticDataDTO, StatisticDataPO.class);
        return statisticDataMapper.insertOrUpdate(statisticDataPO);
    }

}
