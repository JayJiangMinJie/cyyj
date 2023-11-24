package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.GroupManagementPO;
import com.geovis.cyyj.vo.GroupManagementVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据上传表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface GroupManagementMapper extends BaseMapperPlus<GroupManagementMapper, GroupManagementPO, GroupManagementVO> {

}
