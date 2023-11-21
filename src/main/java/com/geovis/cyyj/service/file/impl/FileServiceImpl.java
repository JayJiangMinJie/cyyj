package com.geovis.cyyj.service.file.impl;

import com.geovis.cyyj.common.core.domain.FileReturn;
import com.geovis.cyyj.mapper.file.FileMapper;
import com.geovis.cyyj.po.file.FilePO;
import com.geovis.cyyj.service.file.FileService;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file.uploadPath}")
    String filePath;

    @Autowired
    FileMapper fileMapper;

    @Override
    public FileReturn uploadFile(MultipartFile multipartFile, int noticeCode, String operatePerson) {
//        文件名
        File file = new File(filePath + noticeCode + "_" + multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(multipartFile.getInputStream(),fileOutputStream);
            logger.info("===========file upload success=======: " + file.getName());
            Boolean fileSave = fileSave(filePath + multipartFile.getOriginalFilename() + "-" + noticeCode, operatePerson, noticeCode);
            if(fileSave == true){
                logger.info("===========file save to database success=======: " + file.getName());
            }else {
                logger.warn("===========file save to database failed=======: " + file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件上传错误：" + noticeCode, e);
        } finally {
            try {
//                关闭
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文件关闭错误",e);
            }
        }

        return new FileReturn<>(1,"文件上传成功",file);
    }

    @Override
    public Boolean fileSave(String fileName, String operatePerson, int noticeCode) {
        FilePO filePO = new FilePO();
        filePO.setFileName(fileName);
        filePO.setNoticeDistributeId(noticeCode);
        filePO.setOperatePerson(operatePerson);
        int fileSaveResult = fileMapper.insert(filePO);
        if(fileSaveResult == 1){
            return true;
        }else {
            return false;
        }

    }

}

