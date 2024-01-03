package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.dto.WarningProgressFeedbackDTO;
import com.geovis.cyyj.mapper.WarningProgressFeedbackMapper;
import com.geovis.cyyj.po.StatisticTaskPO;
import com.geovis.cyyj.po.WarningProgressFeedbackPO;
import com.geovis.cyyj.service.IWarningProgressFeedbackService;
import com.geovis.cyyj.vo.WarningProgressFeedbackVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * 预警下发 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WarningProgressFeedbackServiceImpl extends ServiceImpl<WarningProgressFeedbackMapper, WarningProgressFeedbackPO> implements IWarningProgressFeedbackService {

    @Autowired
    private final WarningProgressFeedbackMapper warningProgressFeedbackMapper;

    /**
     * 分页查询进度反馈列表
     */
    @Override
    public TableDataInfo<WarningProgressFeedbackVO> queryWarningProgressFeedbackList(Integer warningDistributeId, String parentUserId, PageQuery pageQuery) {
        LambdaQueryWrapper<WarningProgressFeedbackPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(warningDistributeId != 0, WarningProgressFeedbackPO::getDisasterWarningId, warningDistributeId);
        lqw.eq(StringUtils.isNotBlank(parentUserId), WarningProgressFeedbackPO::getParentUserId, parentUserId);
        lqw.orderByDesc(WarningProgressFeedbackPO::getCreateTime);
        Page<WarningProgressFeedbackVO> result = warningProgressFeedbackMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean addOrUpdateWarningProgressFeedback(WarningProgressFeedbackDTO warningProgressFeedbackDTO) {
        WarningProgressFeedbackPO warningProgressFeedbackPO = BeanCopyUtils.copy(warningProgressFeedbackDTO, WarningProgressFeedbackPO.class);
        return warningProgressFeedbackMapper.insertOrUpdate(warningProgressFeedbackPO);
    }

}
