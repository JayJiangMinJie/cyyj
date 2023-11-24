package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.WarningProgressFeedbackPO;
import com.geovis.cyyj.vo.WarningProgressFeedbackVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 预警下发表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface WarningProgressFeedbackMapper extends BaseMapperPlus<WarningProgressFeedbackMapper, WarningProgressFeedbackPO, WarningProgressFeedbackVO> {

}
