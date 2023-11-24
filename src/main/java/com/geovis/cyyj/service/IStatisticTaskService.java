package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverTaskDTO;
import com.geovis.cyyj.dto.StatisticTaskQueryDTO;
import com.geovis.cyyj.po.StatisticTaskPO;
import com.geovis.cyyj.vo.StatisticTaskVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 统计任务 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IStatisticTaskService extends IService<StatisticTaskPO> {

    /**
     * 分页查询统计任务数据
     */
    TableDataInfo<StatisticTaskVO> queryMainList(StatisticTaskQueryDTO statisticTaskQueryDTO, PageQuery pageQuery);

    /**
     * 发布任务
     */
    Boolean deliverTask(DeliverTaskDTO deliverTaskDTO);

    /**
     * 任务操作
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean operateNotice(Integer statisticTaskId, String operateType);

}
