package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.DeliverResponseDTO;
import com.geovis.cyyj.dto.ResponseReceiveQueryDTO;
import com.geovis.cyyj.dto.ResponseReceiveStatusDTO;
import com.geovis.cyyj.mapper.ResponseReceiveMapper;
import com.geovis.cyyj.po.ResponseReceivePO;
import com.geovis.cyyj.service.IResponseReceiveService;
import com.geovis.cyyj.vo.ResponseReceiveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * 响应接收 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseReceiveServiceImpl extends ServiceImpl<ResponseReceiveMapper, ResponseReceivePO> implements IResponseReceiveService {

    @Autowired
    private final ResponseReceiveMapper responseReceiveMapper;

    /**
     * 分页查询响应下发列表
     */
    @Override
    public TableDataInfo<ResponseReceiveVO> queryMainList(ResponseReceiveQueryDTO responseReceiveQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<ResponseReceivePO> lqw = buildQueryWrapper(responseReceiveQueryDTO);
        Page<ResponseReceiveVO> result = responseReceiveMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<ResponseReceivePO> buildQueryWrapper(ResponseReceiveQueryDTO responseReceiveQueryDTO) {
        LambdaQueryWrapper<ResponseReceivePO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(responseReceiveQueryDTO.getKeyWord()), ResponseReceivePO::getTitle, responseReceiveQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(responseReceiveQueryDTO.getUserId()), ResponseReceivePO::getUserId, responseReceiveQueryDTO.getUserId());
        lqw.eq(StringUtils.isNotBlank(responseReceiveQueryDTO.getStatus()), ResponseReceivePO::getStatus, responseReceiveQueryDTO.getStatus());
        lqw.ge(responseReceiveQueryDTO.getStartTime() != null, ResponseReceivePO::getCreateTime, responseReceiveQueryDTO.getStartTime());
        lqw.le(responseReceiveQueryDTO.getEndTime() != null, ResponseReceivePO::getCreateTime, responseReceiveQueryDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布任务
     */
    @Override
    public Boolean deliverResponse(DeliverResponseDTO deliverResponseDTO) {
        ResponseReceivePO responseReceivePO = BeanCopyUtils.copy(deliverResponseDTO, ResponseReceivePO.class);
        return responseReceiveMapper.insertOrUpdate(responseReceivePO);
    }

}
