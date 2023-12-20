package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.DeliverWarningDTO;
import com.geovis.cyyj.dto.WarningReceiveQueryDTO;
import com.geovis.cyyj.dto.WarningReceiveStatusDTO;
import com.geovis.cyyj.mapper.WarningReceiveMapper;
import com.geovis.cyyj.po.WarningReceivePO;
import com.geovis.cyyj.service.IWarningReceiveService;
import com.geovis.cyyj.vo.WarningReceiveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * 预警接收 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WarningReceiveServiceImpl extends ServiceImpl<WarningReceiveMapper, WarningReceivePO> implements IWarningReceiveService {

    @Autowired
    private final WarningReceiveMapper warningReceiveMapper;

    /**
     * 分页查询预警下发列表
     */
    @Override
    public TableDataInfo<WarningReceiveVO> queryMainList(WarningReceiveQueryDTO warningReceiveQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<WarningReceivePO> lqw = buildQueryWrapper(warningReceiveQueryDTO);
        Page<WarningReceiveVO> result = warningReceiveMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<WarningReceivePO> buildQueryWrapper(WarningReceiveQueryDTO warningReceiveQueryDTO) {
        LambdaQueryWrapper<WarningReceivePO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(warningReceiveQueryDTO.getKeyWord()), WarningReceivePO::getTitle, warningReceiveQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(warningReceiveQueryDTO.getUserId()), WarningReceivePO::getUserId, warningReceiveQueryDTO.getUserId());
        lqw.eq(StringUtils.isNotBlank(warningReceiveQueryDTO.getStatus()), WarningReceivePO::getStatus, warningReceiveQueryDTO.getStatus());
        lqw.eq(StringUtils.isNotBlank(warningReceiveQueryDTO.getType()), WarningReceivePO::getType, warningReceiveQueryDTO.getType());
        lqw.like(StringUtils.isNotBlank(warningReceiveQueryDTO.getRegion()), WarningReceivePO::getRegion, warningReceiveQueryDTO.getRegion());
        lqw.ge(warningReceiveQueryDTO.getStartTime() != null, WarningReceivePO::getCreateTime, warningReceiveQueryDTO.getStartTime());
        lqw.le(warningReceiveQueryDTO.getEndTime() != null, WarningReceivePO::getCreateTime, warningReceiveQueryDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布任务
     */
    @Override
    public Boolean deliverWarning(DeliverWarningDTO deliverWarningDTO) {
        WarningReceivePO warningReceivePO = BeanCopyUtils.copy(deliverWarningDTO, WarningReceivePO.class);
        return warningReceiveMapper.insertOrUpdate(warningReceivePO);
    }

    @Override
    public Boolean changeStatus(WarningReceiveStatusDTO warningReceiveStatusDTO) {
        WarningReceivePO warningReceivePO = BeanCopyUtils.copy(warningReceiveStatusDTO, WarningReceivePO.class);
        LocalDateTime now = LocalDateTime.now();
        String status;
        if(warningReceiveStatusDTO.getIsRead()){
            if(now.isBefore(warningReceiveStatusDTO.getEndTime())){
                status = "按时反馈";
            }else {
                status = "超时反馈";
            }
        }else {
            status = "未反馈";
        }
        warningReceivePO.setStatus(status);
        return warningReceiveMapper.insertOrUpdate(warningReceivePO);
    }

}
