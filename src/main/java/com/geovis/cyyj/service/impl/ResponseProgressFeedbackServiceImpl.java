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
import com.geovis.cyyj.po.ResponseProgressFeedbackPO;
import com.geovis.cyyj.service.IResponseProgressFeedbackService;
import com.geovis.cyyj.vo.ResponseProgressFeedbackVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public TableDataInfo<ResponseProgressFeedbackVO> queryResponseProgressFeedbackList(int responseDistributeId, PageQuery pageQuery) {
        LambdaQueryWrapper<ResponseProgressFeedbackPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(responseDistributeId != 0, ResponseProgressFeedbackPO::getResponseReleaseId, responseDistributeId);
        Page<ResponseProgressFeedbackVO> result = responseProgressFeedbackMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public Boolean addOrUpdateResponseProgressFeedback(ResponseProgressFeedbackDTO responseProgressFeedbackDTO) {
        ResponseProgressFeedbackPO responseProgressFeedbackPO = BeanCopyUtils.copy(responseProgressFeedbackDTO, ResponseProgressFeedbackPO.class);
        LocalDateTime now = LocalDateTime.now();
        String status;
        if(responseProgressFeedbackDTO.getIsRead()){

            if(now.isBefore(responseProgressFeedbackDTO.getEndTime())){
                status = "按时反馈";
            }else {
                status = "超时反馈";
            }
        }else {
            status = "未反馈";
        }
        responseProgressFeedbackDTO.setFeedbackStatus(status);
        return responseProgressFeedbackMapper.insertOrUpdate(responseProgressFeedbackPO);
    }

}
