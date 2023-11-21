package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeDistributeDTO;
import com.geovis.cyyj.po.NoticeDistributePO;
import com.geovis.cyyj.po.ProgressFeedbackPO;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import com.geovis.cyyj.vo.ProgressFeedbackVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 通知下发 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface INoticeDistributeService extends IService<NoticeDistributePO> {

    /**
     * 分页查询通知下发数据
     */
    TableDataInfo<NoticeDistributeVO> queryMainList(NoticeDistributeDTO noticeDistributeDTO, PageQuery pageQuery);

    /**
     * 发布通知
     */
    Boolean deliverNotice(DeliverNoticeDTO deliverNoticeDTO);

    /**
     * 通知操作
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean operateNotice(Integer noticeDistributeId, String operateType);

}
