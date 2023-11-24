package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.DataReportDTO;
import com.geovis.cyyj.dto.DataReportQueryDTO;
import com.geovis.cyyj.dto.DataReportStatusDTO;
import com.geovis.cyyj.mapper.DataReportMapper;
import com.geovis.cyyj.po.DataReportPO;
import com.geovis.cyyj.service.IDataReportService;
import com.geovis.cyyj.vo.DataReportVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * 数据上传 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DataReportServiceImpl extends ServiceImpl<DataReportMapper, DataReportPO> implements IDataReportService {

    @Autowired
    private final DataReportMapper dataReportMapper;

    /**
     * 分页查询数据上传列表
     */
    @Override
    public TableDataInfo<DataReportVO> queryMainList(DataReportQueryDTO dataReportQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<DataReportPO> lqw = buildQueryWrapper(dataReportQueryDTO);
        Page<DataReportVO> result = dataReportMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean dataReport(DataReportDTO dataReportDTO) {
        DataReportPO dataReportPO = BeanCopyUtils.copy(dataReportDTO, DataReportPO.class);
        return dataReportMapper.insertOrUpdate(dataReportPO);
    }

    private LambdaQueryWrapper<DataReportPO> buildQueryWrapper(DataReportQueryDTO dataReportQueryDTO) {
        LambdaQueryWrapper<DataReportPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(dataReportQueryDTO.getKeyWord()), DataReportPO::getTitle, dataReportQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(dataReportQueryDTO.getUserId()), DataReportPO::getUserId, dataReportQueryDTO.getUserId());
        lqw.eq(StringUtils.isNotBlank(dataReportQueryDTO.getStatus()), DataReportPO::getStatus, dataReportQueryDTO.getStatus());
        lqw.ge(dataReportQueryDTO.getStartReleaseTime() != null, DataReportPO::getReleaseTime, dataReportQueryDTO.getStartReleaseTime());
        lqw.le(dataReportQueryDTO.getEndReleaseTime() != null, DataReportPO::getReleaseTime, dataReportQueryDTO.getEndReleaseTime());
        lqw.ge(dataReportQueryDTO.getStartLastFillTime() != null, DataReportPO::getLastFillTime, dataReportQueryDTO.getStartLastFillTime());
        lqw.le(dataReportQueryDTO.getEndLastFillTime() != null, DataReportPO::getLastFillTime, dataReportQueryDTO.getEndLastFillTime());
        return lqw;
    }

    @Override
    public Boolean changeStatus(DataReportStatusDTO dataReportStatusDTO) {
        DataReportPO dataReportPO = BeanCopyUtils.copy(dataReportStatusDTO, DataReportPO.class);
        LocalDateTime now = LocalDateTime.now();
        String status;
        if(dataReportStatusDTO.getIsRead()){
            if(now.isBefore(dataReportStatusDTO.getEndTime())){
                status = "按时反馈";
            }else {
                status = "超时反馈";
            }
        }else {
            status = "未反馈";
        }
        dataReportPO.setStatus(status);
        return dataReportMapper.insertOrUpdate(dataReportPO);
    }

}
