package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.InsertResponseChangeLogDTO;
import com.geovis.cyyj.dto.ResponseChangeLogQueryDTO;
import com.geovis.cyyj.mapper.ResponseChangeLogMapper;
import com.geovis.cyyj.po.PublicServerPO;
import com.geovis.cyyj.po.ResponseChangeLogPO;
import com.geovis.cyyj.service.IResponseChangeLogService;
import com.geovis.cyyj.vo.ResponseChangeLogVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
public class ResponseChangeLogServiceImpl extends ServiceImpl<ResponseChangeLogMapper, ResponseChangeLogPO> implements IResponseChangeLogService {

    @Autowired
    private final ResponseChangeLogMapper responseChangeLogMapper;

    /**
     * 分页查询响应下发列表
     */
    @Override
    public TableDataInfo<ResponseChangeLogVO> queryMainList(ResponseChangeLogQueryDTO responseChangeLogQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<ResponseChangeLogPO> lqw = buildQueryWrapper(responseChangeLogQueryDTO);
        lqw.orderByDesc(ResponseChangeLogPO::getCreateTime);
        Page<ResponseChangeLogVO> result = responseChangeLogMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public List<ResponseChangeLogPO> queryLogList(ResponseChangeLogQueryDTO responseChangeLogQueryDTO) {
        LambdaQueryWrapper<ResponseChangeLogPO> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(ResponseChangeLogPO::getCreateTime);
        lqw.eq(StringUtils.isNotBlank(responseChangeLogQueryDTO.getUserId()), ResponseChangeLogPO::getUserId, responseChangeLogQueryDTO.getUserId());
        lqw.eq(responseChangeLogQueryDTO.getResponseReleaseId() != 0, ResponseChangeLogPO::getResponseReleaseId, responseChangeLogQueryDTO.getResponseReleaseId());
        List<ResponseChangeLogPO> result = responseChangeLogMapper.selectList(lqw);
        return result;
    }

    private LambdaQueryWrapper<ResponseChangeLogPO> buildQueryWrapper(ResponseChangeLogQueryDTO responseChangeLogQueryDTO) {
        LambdaQueryWrapper<ResponseChangeLogPO> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(ResponseChangeLogPO::getCreateTime);
        lqw.eq(responseChangeLogQueryDTO.getResponseReleaseId() != 0, ResponseChangeLogPO::getResponseReleaseId, responseChangeLogQueryDTO.getResponseReleaseId());
        lqw.eq(StringUtils.isNotBlank(responseChangeLogQueryDTO.getUserId()), ResponseChangeLogPO::getUserId, responseChangeLogQueryDTO.getUserId());
        return lqw;
    }

    /**
     * 发布任务
     */
    @Override
    public Boolean insertResponseLog(InsertResponseChangeLogDTO insertResponseChangeLogDTO) {
        ResponseChangeLogPO responseChangeLogPO = BeanCopyUtils.copy(insertResponseChangeLogDTO, ResponseChangeLogPO.class);
        return responseChangeLogMapper.insertOrUpdate(responseChangeLogPO);
    }

}
