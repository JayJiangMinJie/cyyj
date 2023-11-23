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
import com.geovis.cyyj.dto.DataReportSearchDTO;
import com.geovis.cyyj.dto.DataReportStatusDTO;
import com.geovis.cyyj.dto.NoticeReceiveStatusDTO;
import com.geovis.cyyj.mapper.DataReportMapper;
import com.geovis.cyyj.po.DataReportPO;
import com.geovis.cyyj.po.NoticeReceivePO;
import com.geovis.cyyj.po.PublicServerPO;
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
    public TableDataInfo<DataReportVO> queryMainList(DataReportSearchDTO dataReportSearchDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<DataReportPO> lqw = buildQueryWrapper(dataReportSearchDTO);
        Page<DataReportVO> result = dataReportMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean dataReport(DataReportDTO dataReportDTO) {
        DataReportPO dataReportPO = BeanCopyUtils.copy(dataReportDTO, DataReportPO.class);
        return dataReportMapper.insertOrUpdate(dataReportPO);
    }

    private LambdaQueryWrapper<DataReportPO> buildQueryWrapper(DataReportSearchDTO dataReportSearchDTO) {
        LambdaQueryWrapper<DataReportPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(dataReportSearchDTO.getKeyWord()), DataReportPO::getTitle, dataReportSearchDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(dataReportSearchDTO.getUserId()), DataReportPO::getUserId, dataReportSearchDTO.getUserId());
        lqw.eq(StringUtils.isNotBlank(dataReportSearchDTO.getStatus()), DataReportPO::getStatus, dataReportSearchDTO.getStatus());
        lqw.ge(dataReportSearchDTO.getStartReleaseTime() != null, DataReportPO::getReleaseTime, dataReportSearchDTO.getStartReleaseTime());
        lqw.lt(dataReportSearchDTO.getEndReleaseTime() != null, DataReportPO::getReleaseTime, dataReportSearchDTO.getEndReleaseTime());
        lqw.ge(dataReportSearchDTO.getStartLastFillTime() != null, DataReportPO::getLastFillTime, dataReportSearchDTO.getStartLastFillTime());
        lqw.lt(dataReportSearchDTO.getEndLastFillTime() != null, DataReportPO::getLastFillTime, dataReportSearchDTO.getEndLastFillTime());
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
        dataReportStatusDTO.setStatus(status);
        return dataReportMapper.insertOrUpdate(dataReportPO);
    }

}
