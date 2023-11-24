package com.geovis.cyyj.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ZipUtil;
import com.geovis.cyyj.common.core.domain.FileReturn;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.Html2WorldUtil;
import com.geovis.cyyj.common.utils.file.FileUtils;
import com.geovis.cyyj.dto.FileQueryDTO;
import com.geovis.cyyj.service.file.FileService;
import com.geovis.cyyj.vo.FileVO;
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
    public FileReturn uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile){
        return fileService.uploadFile(multipartFile);
    }

    @PostMapping("/save")
    @ApiOperation("保存文件")
    public Boolean fileSave(@RequestParam("filePath") String filePath,
                                 @RequestParam("noticeCode") int noticeCode,
                                 @RequestParam("operatePerson") String operatePerson){
        return fileService.fileSave(filePath, noticeCode, operatePerson);
    }

    /**
     * 分页查询数据库文件列表
     */
    @ApiOperation(value = "分页查询数据库文件列表", notes = "分页查询数据库文件列表")
    @GetMapping("/queryFileMainList")
    public TableDataInfo<FileVO> queryFileMainList(FileQueryDTO fileQueryDTO, PageQuery pageQuery) {
        return fileService.queryFileMainList(fileQueryDTO, pageQuery);
    }


     /**
      * * 生成Word文件名
     * @param relDir 相对路径
     */
    private String generateFileName(String relDir) {
        return relDir +
//                LocalDateTime.now().format(formatter) +
                UUID.randomUUID().toString().replace("-", "") + ".doc";
    }

    @ApiOperation("根据html生成word文件")
    @PostMapping("/createByHtml")
    public ResponseEntity<String> reportCreate(@RequestParam("htmlString") String htmlString
                                               ) {
        String fileRoad = "";
        // 生成word，返回相对（根）路径
        long startTime = System.currentTimeMillis();
        try {
            String wordFile = generateFileName("fileGenerate\\");
            fileRoad = Html2WorldUtil.writeWordFile(htmlString, taskPath + "fileGenerate", taskPath + wordFile);
        } catch (Exception e) {
            log.warn("html to file failed and html is " + htmlString);
            throw new RuntimeException(e);
        }
        log.info("转换结束，耗时：{}ms",System.currentTimeMillis() - startTime);
        return ResponseEntity.ok(fileRoad);
    }



    @PostMapping("/downloadZip")
    @ApiOperation("下载打包文件")
    public void exportWord(HttpServletResponse response,
                           @RequestParam("wordPath") String wordFilesPath,
                           @RequestParam("annexFilePath") String annexFilesPath) {
        try {
            // 压缩文件中包含的文件列表
            List<File> fileList = CollUtil.newArrayList();
            // 压缩到的位置
            File zipFile = new File(taskPath + "zipPath\\" + UUID.randomUUID() + ".zip");
            //总文件及附件读取
            List<String> reportFilesList = Arrays.asList(wordFilesPath.split(";"));
            List<String> annexFilesList = Arrays.asList(annexFilesPath.split(";"));
            for(String report : reportFilesList){
                List<File> reportFiles = getFiles(report);
                fileList.addAll(reportFiles);
            }
            for(String annex : annexFilesList){
                List<File> annexFiles = getFiles(annex);
                fileList.addAll(annexFiles);
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
    @ApiOperation("查询本地文件列表")
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
    @ApiOperation("根据文件路径删除文件")
    @PostMapping("/delete")
    private Boolean deleteFileByFileName(@RequestParam("fileName") String deleteFilePath){
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
