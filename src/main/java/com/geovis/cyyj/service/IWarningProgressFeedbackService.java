package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.WarningProgressFeedbackDTO;
import com.geovis.cyyj.po.WarningProgressFeedbackPO;
import com.geovis.cyyj.vo.WarningProgressFeedbackVO;

/**
 * <p>
 * 预警下发 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IWarningProgressFeedbackService extends IService<WarningProgressFeedbackPO> {

    /**
     * 分页查询进度反馈数据
     */
    TableDataInfo<WarningProgressFeedbackVO> queryWarningProgressFeedbackList(int disasterWarningId, PageQuery pageQuery);

    /**
     * 新增进度反馈
     */
    Boolean addOrUpdateWarningProgressFeedback(WarningProgressFeedbackDTO warningProgressFeedbackDTO);

}
