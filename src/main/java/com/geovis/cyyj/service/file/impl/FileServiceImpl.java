package com.geovis.cyyj.service.file.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geovis.cyyj.common.core.domain.FileReturn;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.FileQueryDTO;
import com.geovis.cyyj.mapper.file.FileMapper;
import com.geovis.cyyj.po.WarningReceivePO;
import com.geovis.cyyj.po.file.FilePO;
import com.geovis.cyyj.service.file.FileService;
import com.geovis.cyyj.vo.FileVO;
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
            IOUtils.copy(inputStream, fileOutputStream);
            logger.info("===========file upload success=======: " + file.getName());
//            Boolean fileSave = fileSave(filePath + multipartFile.getOriginalFilename() + "-" + noticeCode, operatePerson, noticeCode);
//            if (fileSave == true) {
//                logger.info("===========file save to database success=======: " + file.getName());
//            } else {
//                logger.warn("===========file save to database failed=======: " + file.getName());
//            }
        } catch (IOException e) {
            e.printStackTrace();
//            logger.error("文件上传错误：" + noticeCode, e);
        } finally {
//          关闭
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
        }
        return new FileReturn<>(1, "文件上传成功", file);
    }

    @Override
    public Boolean fileSave(String filePath, Integer noticeCode, String operatePerson) {
        FilePO filePO = new FilePO();
        filePO.setFilePath(filePath);
        filePO.setNoticeDistributeId(noticeCode);
        filePO.setOperatePerson(operatePerson);
        int fileSaveResult = fileMapper.insert(filePO);
        if (fileSaveResult == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean deleteFile(Integer noticeCode, String operatePerson) {
        LambdaUpdateWrapper<FilePO> receiveLuw = Wrappers.lambdaUpdate();
        receiveLuw.eq(noticeCode != 0, FilePO::getNoticeDistributeId, noticeCode);
        receiveLuw.eq(StringUtils.isNotEmpty(operatePerson), FilePO::getOperatePerson, operatePerson);
        int deleteNum = fileMapper.delete(receiveLuw);
        if(deleteNum <= 0){
            return false;
        }
        return true;
    }

    @Override
    public TableDataInfo<FileVO> queryFileMainList(FileQueryDTO fileQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<FilePO> lqw = buildQueryWrapper(fileQueryDTO);
        Page<FileVO> result = fileMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }


    private LambdaQueryWrapper<FilePO> buildQueryWrapper(FileQueryDTO fileQueryDTO) {
        LambdaQueryWrapper<FilePO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(fileQueryDTO.getOperatePerson()), FilePO::getOperatePerson, fileQueryDTO.getOperatePerson());
        lqw.eq(fileQueryDTO.getNoticeDistributeId() != 0, FilePO::getNoticeDistributeId, fileQueryDTO.getNoticeDistributeId());
        return lqw;
    }
}

