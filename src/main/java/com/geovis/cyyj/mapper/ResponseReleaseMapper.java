package com.geovis.cyyj.mapper;


import com.geovis.cyyj.common.core.mapper.BaseMapperPlus;
import com.geovis.cyyj.po.ResponseReleasePO;
import com.geovis.cyyj.vo.ResponseReleaseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 响应录入表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface ResponseReleaseMapper extends BaseMapperPlus<ResponseReleaseMapper, ResponseReleasePO, ResponseReleaseVO> {

    /**
     * 录入响应
     */
    int  insertResponseRelease(@Param("responseReleasePO") ResponseReleasePO responseReleasePO);


}
