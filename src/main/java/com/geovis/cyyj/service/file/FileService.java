package com.geovis.cyyj.service.file;

import com.geovis.cyyj.common.core.domain.FileReturn;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileReturn uploadFile(MultipartFile multipartFile);

//    Boolean fileSave(String fileName, String operatePerson, int noticeCode);

}