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

import java.io.*;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file.uploadPath}")
    String filePath;

    @Autowired
    FileMapper fileMapper;

    @Override
    public FileReturn uploadFile(MultipartFile multipartFile) {
//        文件名
        File file = new File(filePath + multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = null;
       InputStream inputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            inputStream = multipartFile.getInputStream();
            IOUtils.copy(inputStream,fileOutputStream);
            logger.info("===========file upload success=======: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//          关闭
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
        }
        return new FileReturn<>(1,"文件上传成功",file);
    }

//    @Override
//    public Boolean fileSave(String fileName) {
//        FilePO filePO = new FilePO();
//        filePO.setFileName(fileName);
//        filePO.setNoticeDistributeId(noticeCode);
//        filePO.setOperatePerson(operatePerson);
//        int fileSaveResult = fileMapper.insert(filePO);
//        if(fileSaveResult == 1){
//            return true;
//        }else {
//            return false;
//        }

    }


