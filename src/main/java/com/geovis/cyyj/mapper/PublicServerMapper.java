package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.PublicServerPO;
import com.geovis.cyyj.vo.PublicServerVO;
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
public interface PublicServerMapper extends BaseMapperPlus<PublicServerMapper, PublicServerPO, PublicServerVO> {

//    /**
//     * 分页查询
//     *
//     * @param page    分页参数
//     * @param wrapper
//     * @return 返回用户分页
//     */
//    IPage<PublicServerVO> selectByPage(@Param("page") IPage<PublicServerDTO> page, @Param(Constants.WRAPPER) Wrapper<Object> wrapper);

}
