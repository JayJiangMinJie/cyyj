package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.NoticeDistributePO;
import com.geovis.cyyj.po.ProgressFeedbackPO;
import com.geovis.cyyj.vo.NoticeDistributeVO;
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
public interface NoticeDistributeMapper extends BaseMapperPlus<NoticeDistributeMapper, NoticeDistributePO, NoticeDistributeVO> {

//    /**
//     * 分页查询
//     *
//     * @param page    分页参数
//     * @param wrapper
//     * @return 返回用户分页
//     */
//    IPage<NoticeDistributeVO> selectByPage(@Param("page") IPage<NoticeDistributeDTO> page, @Param(Constants.WRAPPER) Wrapper<Object> wrapper);

}
