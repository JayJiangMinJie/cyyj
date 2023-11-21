package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeReceiveDTO;
import com.geovis.cyyj.dto.NoticeReceiveStatusDTO;
import com.geovis.cyyj.mapper.NoticeReceiveMapper;
import com.geovis.cyyj.po.NoticeReceivePO;
import com.geovis.cyyj.po.ProgressFeedbackPO;
import com.geovis.cyyj.service.INoticeReceiveService;
import com.geovis.cyyj.vo.NoticeReceiveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


/**
 * <p>
 * 通知接收 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeReceiveServiceImpl extends ServiceImpl<NoticeReceiveMapper, NoticeReceivePO> implements INoticeReceiveService {

    @Autowired
    private final NoticeReceiveMapper noticeReceiveMapper;

    /**
     * 分页查询通知下发列表
     */
    @Override
    public TableDataInfo<NoticeReceiveVO> queryMainList(NoticeReceiveDTO noticeReceiveDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<NoticeReceivePO> lqw = buildQueryWrapper(noticeReceiveDTO);
        Page<NoticeReceiveVO> result = noticeReceiveMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<NoticeReceivePO> buildQueryWrapper(NoticeReceiveDTO noticeReceiveDTO) {
        LambdaQueryWrapper<NoticeReceivePO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(noticeReceiveDTO.getKeyWord()), NoticeReceivePO::getTitle, noticeReceiveDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(noticeReceiveDTO.getStatus()), NoticeReceivePO::getStatus, noticeReceiveDTO.getStatus());
        lqw.ge(noticeReceiveDTO.getStartTime() != null, NoticeReceivePO::getStartTime, noticeReceiveDTO.getStartTime());
        lqw.lt(noticeReceiveDTO.getEndTime() != null, NoticeReceivePO::getEndTime, noticeReceiveDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布任务
     */
    @Override
    public Boolean deliverNotice(DeliverNoticeDTO deliverNoticeDTO) {
        NoticeReceivePO noticeReceivePO = BeanCopyUtils.copy(deliverNoticeDTO, NoticeReceivePO.class);
        return noticeReceiveMapper.insertOrUpdate(noticeReceivePO);
    }

    @Override
    public Boolean changeStatus(NoticeReceiveStatusDTO noticeReceiveStatusDTO) {
        NoticeReceivePO noticeReceivePO = BeanCopyUtils.copy(noticeReceiveStatusDTO, NoticeReceivePO.class);
        LocalDateTime now = LocalDateTime.now();
        String status;
        if(noticeReceiveStatusDTO.getIsRead()){
            if(now.isBefore(noticeReceiveStatusDTO.getEndTime())){
                status = "按时反馈";
            }else {
                status = "超时反馈";
            }
        }else {
            status = "未反馈";
        }
        noticeReceiveStatusDTO.setStatus(status);
        return noticeReceiveMapper.insertOrUpdate(noticeReceivePO);
    }

}
