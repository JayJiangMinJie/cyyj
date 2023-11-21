package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.*;
import com.geovis.cyyj.po.DataReportPO;
import com.geovis.cyyj.vo.DataReportVO;

/**
 * <p>
 * 数据上传 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IDataReportService extends IService<DataReportPO> {

    /**
     * 分页查询数据上传数据
     */
    TableDataInfo<DataReportVO> queryMainList(DataReportSearchDTO dataReportSearchDTO, PageQuery pageQuery);

    /**
     * 数据上传
     */
    Boolean dataReport(DataReportDTO dataReportDTO);

    /**
     * 状态变更
     */
    Boolean changeStatus(DataReportStatusDTO dataReportStatusDTO);

}
