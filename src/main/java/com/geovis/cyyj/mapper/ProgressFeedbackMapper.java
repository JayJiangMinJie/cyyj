package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.NoticeDistributePO;
import com.geovis.cyyj.po.ProgressFeedbackPO;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import com.geovis.cyyj.vo.ProgressFeedbackVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 通知下发表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface ProgressFeedbackMapper extends BaseMapperPlus<ProgressFeedbackMapper, ProgressFeedbackPO, ProgressFeedbackVO> {

}
