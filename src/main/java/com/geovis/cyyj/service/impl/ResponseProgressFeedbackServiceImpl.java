package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.dto.ResponseProgressFeedbackDTO;
import com.geovis.cyyj.mapper.ResponseProgressFeedbackMapper;
import com.geovis.cyyj.po.ResponseChangeLogPO;
import com.geovis.cyyj.po.ResponseProgressFeedbackPO;
import com.geovis.cyyj.service.IResponseProgressFeedbackService;
import com.geovis.cyyj.vo.ResponseProgressFeedbackVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * 响应下发 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseProgressFeedbackServiceImpl extends ServiceImpl<ResponseProgressFeedbackMapper, ResponseProgressFeedbackPO> implements IResponseProgressFeedbackService {

    @Autowired
    private final ResponseProgressFeedbackMapper responseProgressFeedbackMapper;

    /**
     * 分页查询进度反馈列表
     */
    @Override
    public TableDataInfo<ResponseProgressFeedbackVO> queryResponseProgressFeedbackList(Integer responseDistributeId, String parentUserId, PageQuery pageQuery) {
        LambdaQueryWrapper<ResponseProgressFeedbackPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(responseDistributeId != 0, ResponseProgressFeedbackPO::getResponseReleaseId, responseDistributeId);
        lqw.eq(StringUtils.isNotBlank(parentUserId), ResponseProgressFeedbackPO::getParentUserId, parentUserId);
        lqw.orderByDesc(ResponseProgressFeedbackPO::getCreateTime);
        Page<ResponseProgressFeedbackVO> result = responseProgressFeedbackMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean addOrUpdateResponseProgressFeedback(ResponseProgressFeedbackDTO responseProgressFeedbackDTO) {
        ResponseProgressFeedbackPO responseProgressFeedbackPO = BeanCopyUtils.copy(responseProgressFeedbackDTO, ResponseProgressFeedbackPO.class);
        return responseProgressFeedbackMapper.insertOrUpdate(responseProgressFeedbackPO);
    }

}
