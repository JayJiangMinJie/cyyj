package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.ResponseProgressFeedbackDTO;
import com.geovis.cyyj.po.ResponseProgressFeedbackPO;
import com.geovis.cyyj.vo.ResponseProgressFeedbackVO;

/**
 * <p>
 * 响应下发 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IResponseProgressFeedbackService extends IService<ResponseProgressFeedbackPO> {

    /**
     * 分页查询进度反馈数据
     */
    TableDataInfo<ResponseProgressFeedbackVO> queryResponseProgressFeedbackList(Integer responseReleaseId, String parentUserId, PageQuery pageQuery);

    /**
     * 新增进度反馈
     */
    Boolean addOrUpdateResponseProgressFeedback(ResponseProgressFeedbackDTO responseProgressFeedbackDTO);

}
