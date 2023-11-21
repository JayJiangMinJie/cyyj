package com.geovis.cyyj.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ZipUtil;
import com.geovis.cyyj.common.core.domain.FileReturn;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.utils.file.FileUtils;
import com.geovis.cyyj.dto.FileGenerateDTO;
import com.geovis.cyyj.service.file.FileService;
import com.geovis.cyyj.common.utils.WordTmpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping(value="/file")
@Api(value = "FileController", tags = "文件接口")
@Slf4j
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Value("${file.taskPath}")
    String taskPath;

    @Value("${file.uploadPath}")
    String filePath;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public FileReturn uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile,
                                 @RequestParam("noticeCode") int noticeCode,
                                 @RequestParam("operatePerson") String operatePerson){
        return fileService.uploadFile(multipartFile, noticeCode, operatePerson);
    }

    @ApiOperation("生成通知word文件")
    @PostMapping("/createPackage")
    public ResponseEntity<String> createPackage(@Valid @RequestBody FileGenerateDTO fileGenerate, @RequestParam("noticeCode") int noticeCode) {
        // 生成word，返回相对（根）路径
        String rootDir = taskPath.replaceFirst("/? *", "/");
        String wordFile = generateFileName("fileGenerate/", noticeCode);
        WordTmpUtils.use.fillToDocx(rootDir + "template/通知.docx", rootDir + wordFile, fileGenerate);
        return ResponseEntity.ok(wordFile);
    }

    /**
     * 生成Word文件名
     *
     * @param relDir 相对路径
     */
    private String generateFileName(String relDir, Integer noticeCode) {
        return Optional.ofNullable(relDir)
                .map(s -> s.replaceFirst("^ */?", ""))
                .map(s -> s.replaceFirst("/? *$", "/")).orElse("") + noticeCode + "_" +
//                LocalDateTime.now().format(formatter) +
                UUID.randomUUID().toString().replace("-", "") + ".docx";
    }

    @PostMapping("/downloadZip")
    @ApiOperation("下载打包文件")
    public void exportWord(@RequestHeader Integer noticeCode, HttpServletResponse response) {
        try {
            // 压缩到的位置
            File zipFile = new File(taskPath + "zipPath\\" + noticeCode + ".zip");
            //总文件及附件读取
            List<File> reportFiles = getFiles(taskPath + "fileGenerate/");
            List<File> annexFiles = getFiles(filePath);
            // 压缩文件中包含的文件列表,此处为测试代码,实际为自己需要的文件列表
            List<File> fileList = CollUtil.newArrayList();
            for(File reportFile : reportFiles){
                if(reportFile.getName().contains(String.valueOf(noticeCode))){
                    fileList.add(reportFile);
                }
            }
            for(File annexFile : annexFiles){
                if(annexFile.getName().contains(String.valueOf(noticeCode))){
                    fileList.add(annexFile);
                }
            }
            // 压缩多个文件,压缩后会将压缩临时文件删除
            ZipUtil.zip(zipFile, false, fileList.toArray(new File[fileList.size()]));

            // 下载
            FileUtils.downloadZip(zipFile,response);

        } catch (Exception e) {
            logger.error("文件压缩异常",e);
        }
    }

    @PostMapping("/searchFiles")
    @ApiOperation("查询文件列表")
    public R exportWord() {
        Map<String, List<String>> fileMap = new HashMap<>();
        List<String> reportFileList = new ArrayList<>();
        List<String> annexFileList = new ArrayList<>();
        List<File> reportFiles = getFiles(taskPath + "fileGenerate/");
        List<File> annexFiles = getFiles(filePath);
        for(File reportFile : reportFiles){
            reportFileList.add(reportFile.getName());
        }
        for(File annexFile : annexFiles){
            annexFileList.add(annexFile.getName());
        }
        fileMap.put("reportFile", reportFileList);
        fileMap.put("annexFile", annexFileList);
        return R.ok(fileMap);
    }

    /**
     * 删除路径下文件名所覆盖的文件
     * @Param fileName 文件名
     */
    @ApiOperation("根据文件名删除文件")
    @PostMapping("/delete")
    private Boolean deleteFileByFileName(@RequestParam("fileName") String fileName,
                                         @RequestParam("fileType")String fileType){
        String deleteFilePath = "";
        if("通知".equals(fileType)){
            deleteFilePath = taskPath + "fileGenerate/" + fileName;
        } else if ("附件".equals(fileType)) {
            deleteFilePath = filePath + fileName;
        }

        try {
            // 实例化一个File对象
            File file = new File(deleteFilePath);
            if(file.exists()) {
                // 使用delete()方法删除文件
                if (file.delete()) {
                    log.info(file.getName() + " 已被删除！");
                } else {
                    log.warn(file.getName() + " 文件删除失败! ");
                    return false;
                }
            }else {
                log.warn(file.getName() + " 文件不存在! ");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public static List<File> getFiles(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    //System.out.println("目录：" + files[i].getPath());
                    getFiles(files[i].getPath());
                } else {
                    fileList.add(files[i]);
                }
            }
        } else {
            fileList.add(file);
        }
        return fileList;
    }

}
