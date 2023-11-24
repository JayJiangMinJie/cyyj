package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.DisasterWarningPO;
import com.geovis.cyyj.vo.DisasterWarningVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 预警录入表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface DisasterWarningMapper extends BaseMapperPlus<DisasterWarningMapper, DisasterWarningPO, DisasterWarningVO> {

    /**
     * 录入通知
     */
    int  insertDisasterWarning(@Param("disasterWarningPO") DisasterWarningPO disasterWarningPO);


}
