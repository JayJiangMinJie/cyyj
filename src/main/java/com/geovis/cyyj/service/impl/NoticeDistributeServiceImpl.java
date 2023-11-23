package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeDistributeDTO;
import com.geovis.cyyj.mapper.NoticeDistributeMapper;
import com.geovis.cyyj.mapper.NoticeReceiveMapper;
import com.geovis.cyyj.po.NoticeDistributePO;
import com.geovis.cyyj.po.NoticeReceivePO;
import com.geovis.cyyj.po.PublicServerPO;
import com.geovis.cyyj.service.INoticeDistributeService;
import com.geovis.cyyj.service.INoticeReceiveService;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 通知下发 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeDistributeServiceImpl extends ServiceImpl<NoticeDistributeMapper, NoticeDistributePO> implements INoticeDistributeService {

    @Autowired
    private final NoticeDistributeMapper noticeDistributeMapper;

    @Autowired
    private final NoticeReceiveMapper noticeReceiveMapper;

    @Autowired
    private INoticeReceiveService iNoticeReceiveService;
    /**
     * 分页查询通知下发列表
     */
    @Override
    public TableDataInfo<NoticeDistributeVO> queryMainList(NoticeDistributeDTO noticeDistributeDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<NoticeDistributePO> lqw = buildQueryWrapper(noticeDistributeDTO);
        Page<NoticeDistributeVO> result = noticeDistributeMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<NoticeDistributePO> buildQueryWrapper(NoticeDistributeDTO noticeDistributeDTO) {
        LambdaQueryWrapper<NoticeDistributePO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(noticeDistributeDTO.getKeyWord()), NoticeDistributePO::getTitle, noticeDistributeDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(noticeDistributeDTO.getUserId()), NoticeDistributePO::getUserId, noticeDistributeDTO.getUserId());
        lqw.ge(noticeDistributeDTO.getStartTime() != null, NoticeDistributePO::getStartTime, noticeDistributeDTO.getStartTime());
        lqw.lt(noticeDistributeDTO.getEndTime() != null, NoticeDistributePO::getEndTime, noticeDistributeDTO.getEndTime());
        return lqw;
    }

    /**
     * 发布通知
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deliverNotice(DeliverNoticeDTO deliverNoticeDTO) {
        NoticeDistributePO noticeDistributePO = BeanCopyUtils.copy(deliverNoticeDTO, NoticeDistributePO.class);
        Boolean insertNoticeDistributeResult = noticeDistributeMapper.insertOrUpdate(noticeDistributePO);
        if(insertNoticeDistributeResult){
            return iNoticeReceiveService.deliverNotice(deliverNoticeDTO);
        }else {
            log.error("insertNoticeDistributeResult is false, user_id = " + deliverNoticeDTO.getUserId());
            return false;
        }
    }

    /**
     * 通知操作
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operateNotice(Integer noticeDistributeId, String operateType) {
        NoticeDistributePO bizDistributePO = noticeDistributeMapper.selectById(noticeDistributeId);
        LambdaUpdateWrapper<NoticeDistributePO> luw = Wrappers.lambdaUpdate();
        int resultWithdrawNum = 0;
        int resultEndNum = 0;
        if("withdraw".equals(operateType)){
            //撤回通知之后下发方消息还在，状态变成已撤回，接收方消息删除
            luw.eq(NoticeDistributePO::getId, noticeDistributeId);
            bizDistributePO.setStatus("已撤回");
            resultWithdrawNum = noticeDistributeMapper.update(bizDistributePO, luw);
            if(resultWithdrawNum == 0){
             log.warn("下发通知表撤回失败，noticeDistributeId： " + noticeDistributeId);
            }
            //删除通知接收方内容
            LambdaUpdateWrapper<NoticeReceivePO> receiveLuw = Wrappers.lambdaUpdate();
            receiveLuw.eq(noticeDistributeId != null, NoticeReceivePO::getNoticeDistributeId, noticeDistributeId);
            int deleteNum = noticeReceiveMapper.delete(receiveLuw);
            resultWithdrawNum = deleteNum + 1;
        } else if ("end".equals(operateType)) {
            luw.eq(NoticeDistributePO::getId, noticeDistributeId);
            bizDistributePO.setStatus("结束");
            resultEndNum = noticeDistributeMapper.update(bizDistributePO, luw);
        }
        if((resultEndNum == 1) || (resultWithdrawNum == 2)){
            return true;
        }else {
            return false;
        }
    }

}
