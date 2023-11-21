package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeDistributeDTO;
import com.geovis.cyyj.dto.ProgressFeedbackDTO;
import com.geovis.cyyj.po.ProgressFeedbackPO;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import com.geovis.cyyj.vo.ProgressFeedbackVO;

/**
 * <p>
 * 通知下发 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IProgressFeedbackService extends IService<ProgressFeedbackPO> {

    /**
     * 分页查询进度反馈数据
     */
    TableDataInfo<ProgressFeedbackVO> queryProgressFeedbackList(int noticeDistributeId, PageQuery pageQuery);

    /**
     * 新增进度反馈
     */
    Boolean addOrUpdateProgressFeedback(ProgressFeedbackDTO progressFeedbackDTO);

}
