package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.NoticeProgressFeedbackDTO;
import com.geovis.cyyj.po.NoticeProgressFeedbackPO;
import com.geovis.cyyj.vo.NoticeProgressFeedbackVO;

/**
 * <p>
 * 通知下发 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface INoticeProgressFeedbackService extends IService<NoticeProgressFeedbackPO> {

    /**
     * 分页查询进度反馈数据
     */
    TableDataInfo<NoticeProgressFeedbackVO> queryNoticeProgressFeedbackList(Integer noticeDistributeId, PageQuery pageQuery);

    /**
     * 新增进度反馈
     */
    Boolean addOrUpdateNoticeProgressFeedback(NoticeProgressFeedbackDTO noticeProgressFeedbackDTO);

    Boolean updateNoticeProgressFeedback(NoticeProgressFeedbackDTO noticeProgressFeedbackDTO);

}
