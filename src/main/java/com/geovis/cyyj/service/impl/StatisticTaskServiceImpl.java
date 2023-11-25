package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.DataReportDTO;
import com.geovis.cyyj.dto.DeliverTaskDTO;
import com.geovis.cyyj.dto.StatisticTaskProgressFeedbackDTO;
import com.geovis.cyyj.dto.StatisticTaskQueryDTO;
import com.geovis.cyyj.mapper.DataReportMapper;
import com.geovis.cyyj.mapper.DistrictListPersonMapper;
import com.geovis.cyyj.mapper.StatisticTaskMapper;
import com.geovis.cyyj.po.*;
import com.geovis.cyyj.service.IDataReportService;
import com.geovis.cyyj.service.INoticeProgressFeedbackService;
import com.geovis.cyyj.service.IStatisticTaskProgressFeedbackService;
import com.geovis.cyyj.service.IStatisticTaskService;
import com.geovis.cyyj.vo.StatisticTaskVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 统计任务 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticTaskServiceImpl extends ServiceImpl<StatisticTaskMapper, StatisticTaskPO> implements IStatisticTaskService {

    @Autowired
    private final StatisticTaskMapper statisticTaskMapper;

    @Autowired
    private final DataReportMapper dataReportMapper;

    @Autowired
    private final DistrictListPersonMapper districtListPersonMapper;

    @Autowired
    IDataReportService iDataReportService;

    @Autowired
    private IStatisticTaskProgressFeedbackService iStatisticTaskProgressFeedbackService;
    /**
     * 分页查询统计任务列表
     */
    @Override
    public TableDataInfo<StatisticTaskVO> queryMainList(StatisticTaskQueryDTO statisticTaskQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<StatisticTaskPO> lqw = buildQueryWrapper(statisticTaskQueryDTO);
        Page<StatisticTaskVO> result = statisticTaskMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<StatisticTaskPO> buildQueryWrapper(StatisticTaskQueryDTO statisticTaskQueryDTO) {
        LambdaQueryWrapper<StatisticTaskPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(statisticTaskQueryDTO.getKeyWord()), StatisticTaskPO::getTitle, statisticTaskQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(statisticTaskQueryDTO.getUserId()), StatisticTaskPO::getUserId, statisticTaskQueryDTO.getUserId());
        lqw.eq(StringUtils.isNotBlank(statisticTaskQueryDTO.getStatus()), StatisticTaskPO::getStatus, statisticTaskQueryDTO.getStatus());
        lqw.ge(statisticTaskQueryDTO.getStartTime() != null, StatisticTaskPO::getLastFillTime, statisticTaskQueryDTO.getStartTime());
        lqw.le(statisticTaskQueryDTO.getEndTime() != null, StatisticTaskPO::getLastFillTime, statisticTaskQueryDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布任务
     */
    @Override
    public Boolean deliverTask(DeliverTaskDTO deliverTaskDTO) {
        if(StringUtils.isEmpty(deliverTaskDTO.getReceiveUnit())){
            log.error("发布任务 receiveUnit is null, userid : " + deliverTaskDTO.getUserId());
            return false;
        }
        StatisticTaskPO statisticTaskPO = BeanCopyUtils.copy(deliverTaskDTO, StatisticTaskPO.class);
        int statisticTask = statisticTaskMapper.insertStatisticTask(statisticTaskPO);
        if(statisticTask < 1){
            log.error("发布任务insert into statisticTask failed, userid : " + deliverTaskDTO.getUserId());
            return false;
        }
        StatisticTaskPO insertStatisticTaskResult = statisticTaskMapper.selectById(statisticTaskPO.getId());
        if(insertStatisticTaskResult != null){
            //如果下发新增成功了，
            DeliverTaskDTO deliverTask2ReportDTO = new DeliverTaskDTO();
            deliverTask2ReportDTO.setTitle(insertStatisticTaskResult.getTitle());
            deliverTask2ReportDTO.setStatus(insertStatisticTaskResult.getStatus());
            deliverTask2ReportDTO.setFillTemplate(insertStatisticTaskResult.getFillTemplate());
            deliverTask2ReportDTO.setEditor(insertStatisticTaskResult.getEditor());
            deliverTask2ReportDTO.setIssuer(insertStatisticTaskResult.getIssuer());
            deliverTask2ReportDTO.setReceiveUnit("");
            deliverTask2ReportDTO.setUserId("");
            deliverTask2ReportDTO.setParentUserId(insertStatisticTaskResult.getParentUserId());
            deliverTask2ReportDTO.setReleaseTime(insertStatisticTaskResult.getReleaseTime());
            deliverTask2ReportDTO.setLastFillTime(insertStatisticTaskResult.getLastFillTime());
            //生成进度反馈用数据
            StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO = new StatisticTaskProgressFeedbackDTO();
            statisticTaskProgressFeedbackDTO.setStatisticTaskId(statisticTaskPO.getId());
            statisticTaskProgressFeedbackDTO.setDept("城阳区应急局");
            statisticTaskProgressFeedbackDTO.setEndTime(insertStatisticTaskResult.getEndTime());
            statisticTaskProgressFeedbackDTO.setFeedbackStatus("未反馈");
            statisticTaskProgressFeedbackDTO.setParentUserId(insertStatisticTaskResult.getUserId());
            //这里暂时查询本地表人员02
            //用查询到的接收单位list找出该给哪些单位发信息
            LambdaQueryWrapper<DistrictListPersonPO> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(DistrictListPersonPO::getOrgName, deliverTaskDTO.getReceiveUnit());
            List<DistrictListPersonPO> districtListPersonPOList = districtListPersonMapper.selectList(lambdaQueryWrapper);
            Map<String, String> revceiveMap = new HashMap<>();
            for(DistrictListPersonPO districtListPersonPO : districtListPersonPOList){
                if(districtListPersonPO.getOrgName().equals(deliverTaskDTO.getReceiveUnit())){
                    revceiveMap.put(districtListPersonPO.getUserName(), districtListPersonPO.getUserId());
                }
            }
            //给各个接收单位发通知
            for(Map.Entry<String, String> entry : revceiveMap.entrySet()){
                DataReportDTO dataReportDTO = new DataReportDTO();
                dataReportDTO.setUserId(entry.getValue());
                dataReportDTO.setStatisticTaskId(statisticTaskPO.getId());
                dataReportDTO.setLastFillTime(deliverTask2ReportDTO.getLastFillTime());
                dataReportDTO.setStatus(deliverTask2ReportDTO.getStatus());
                dataReportDTO.setParentUserId(deliverTaskDTO.getUserId());
                dataReportDTO.setReleaseTime(deliverTask2ReportDTO.getReleaseTime());
                dataReportDTO.setTitle(deliverTask2ReportDTO.getTitle());
                Boolean insertResult = iDataReportService.dataReport(dataReportDTO);
                if(!insertResult){
//                    log.error("insert into receive unit failed, userid is : " + deliverNotice2ReceiveDTO.getUserId() + " parentid is : " + deliverNotice2ReceiveDTO.getParentUserId());
                    throw new RuntimeException("insert into receive unit failed, userid is : " + deliverTask2ReportDTO.getUserId() + " parentid is : " + deliverTask2ReportDTO.getParentUserId());
                }
                //进度反馈也要给各个单位默认发一个
                statisticTaskProgressFeedbackDTO.setUserId(entry.getValue());
                Boolean insertFeedbackResult = iStatisticTaskProgressFeedbackService.addOrUpdateProgressFeedback(statisticTaskProgressFeedbackDTO);
                if(!insertFeedbackResult){
                    throw new RuntimeException("insert into feedback failed, userid is : " + deliverTask2ReportDTO.getUserId() + " parentid is : " + deliverTask2ReportDTO.getParentUserId());
                }
            }
            return true;
        }else {
            log.error("发布任务 insertStatisticTaskResult is false, user_id = " + deliverTaskDTO.getUserId());
            return false;
        }
    }

    /**
     * 任务操作
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operateNotice(Integer statisticTaskId, String operateType) {
        StatisticTaskPO bizDistributePO = statisticTaskMapper.selectById(statisticTaskId);
        LambdaUpdateWrapper<StatisticTaskPO> luw = Wrappers.lambdaUpdate();
        int resultWithdrawNum = 0;
        int resultEndNum = 0;
        if("withdraw".equals(operateType)){
            //撤回任务之后下发方消息还在，状态变成已撤回，接收方消息删除
            luw.set(StatisticTaskPO::getStatus, "已撤回");
            resultWithdrawNum = statisticTaskMapper.update(bizDistributePO, luw);
            if(resultWithdrawNum == 0){
             log.warn("下发任务表撤回失败，statisticTaskId： " + statisticTaskId);
            }
            //删除任务接收方内容
            LambdaUpdateWrapper<DataReportPO> reportLuw = Wrappers.lambdaUpdate();
            reportLuw.eq(statisticTaskId != null, DataReportPO::getStatisticTaskId, statisticTaskId);
            resultWithdrawNum = dataReportMapper.delete(reportLuw) + 1;
        } else if ("end".equals(operateType)) {
            luw.set(StatisticTaskPO::getStatus, "结束");
            resultEndNum = statisticTaskMapper.update(bizDistributePO, luw);
        }
        if((resultEndNum == 1) || (resultWithdrawNum == 2)){
            return true;
        }else {
            return false;
        }
    }

}
