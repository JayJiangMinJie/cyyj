package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeReceiveDTO;
import com.geovis.cyyj.dto.NoticeReceiveStatusDTO;
import com.geovis.cyyj.po.NoticeReceivePO;
import com.geovis.cyyj.vo.NoticeReceiveVO;

/**
 * <p>
 * 通知接收 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface INoticeReceiveService extends IService<NoticeReceivePO> {

    /**
     * 分页查询通知下发数据
     */
    TableDataInfo<NoticeReceiveVO> queryMainList(NoticeReceiveDTO noticeReceiveDTO, PageQuery pageQuery);

    /**
     * 发布通知
     */
    Boolean deliverNotice(DeliverNoticeDTO deliverNoticeDTO);

    /**
     * 状态变更
     */
    Boolean changeStatus(NoticeReceiveStatusDTO noticeReceiveStatusDTO);

}
