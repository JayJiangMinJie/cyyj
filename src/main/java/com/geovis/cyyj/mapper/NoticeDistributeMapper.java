package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.NoticeDistributePO;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 通知录入表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface NoticeDistributeMapper extends BaseMapperPlus<NoticeDistributeMapper, NoticeDistributePO, NoticeDistributeVO> {

    /**
     * 录入通知
     */
    int  insertNoticeDistribute(@Param("noticeDistributePO") NoticeDistributePO noticeDistributePO);


}
