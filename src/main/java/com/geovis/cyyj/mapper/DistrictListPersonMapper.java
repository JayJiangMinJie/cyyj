package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.DistrictListPO;
import com.geovis.cyyj.po.DistrictListPersonPO;
import com.geovis.cyyj.vo.DistrictListPersonVO;
import com.geovis.cyyj.vo.DistrictListVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 区划人员表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface DistrictListPersonMapper extends BaseMapperPlus<DistrictListPersonMapper, DistrictListPersonPO, DistrictListPersonVO> {



}
