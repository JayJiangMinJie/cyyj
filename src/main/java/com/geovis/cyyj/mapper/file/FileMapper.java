package com.geovis.cyyj.mapper.file;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geovis.cyyj.po.file.FilePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文件信息表 Mapper 接口
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Mapper
public interface FileMapper extends BaseMapper<FilePO> {

}
