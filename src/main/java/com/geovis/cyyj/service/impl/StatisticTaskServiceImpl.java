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
import com.geovis.cyyj.dto.DeliverTaskDTO;
import com.geovis.cyyj.dto.StatisticTaskDTO;
import com.geovis.cyyj.mapper.DataReportMapper;
import com.geovis.cyyj.mapper.StatisticTaskMapper;
import com.geovis.cyyj.mapper.NoticeReceiveMapper;
import com.geovis.cyyj.po.DataReportPO;
import com.geovis.cyyj.po.StatisticTaskPO;
import com.geovis.cyyj.service.IStatisticTaskService;
import com.geovis.cyyj.vo.StatisticTaskVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    /**
     * 分页查询统计任务列表
     */
    @Override
    public TableDataInfo<StatisticTaskVO> queryMainList(StatisticTaskDTO statisticTaskDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<StatisticTaskPO> lqw = buildQueryWrapper(statisticTaskDTO);
        Page<StatisticTaskVO> result = statisticTaskMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<StatisticTaskPO> buildQueryWrapper(StatisticTaskDTO statisticTaskDTO) {
        LambdaQueryWrapper<StatisticTaskPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(statisticTaskDTO.getKeyWord()), StatisticTaskPO::getTitle, statisticTaskDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(statisticTaskDTO.getStatus()), StatisticTaskPO::getStatus, statisticTaskDTO.getStatus());
        lqw.ge(statisticTaskDTO.getStartTime() != null, StatisticTaskPO::getFillTime, statisticTaskDTO.getStartTime());
        lqw.lt(statisticTaskDTO.getEndTime() != null, StatisticTaskPO::getFillTime, statisticTaskDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布任务
     */
    @Override
    public Boolean deliverTask(DeliverTaskDTO deliverTaskDTO) {
        StatisticTaskPO statisticTaskPO = BeanCopyUtils.copy(deliverTaskDTO, StatisticTaskPO.class);
        return statisticTaskMapper.insertOrUpdate(statisticTaskPO);
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
