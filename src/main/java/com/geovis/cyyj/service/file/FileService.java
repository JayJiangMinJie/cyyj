package com.geovis.cyyj.service.file;

import com.geovis.cyyj.common.core.domain.FileReturn;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.FileQueryDTO;
import com.geovis.cyyj.vo.FileVO;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileReturn uploadFile(MultipartFile multipartFile);

    Boolean fileSave(String filePath, int noticeCode, String operatePerson);

    /**
     * 分页查询文件列表数据
     */
    TableDataInfo<FileVO> queryFileMainList(FileQueryDTO fileQueryDTO, PageQuery pageQuery);

}