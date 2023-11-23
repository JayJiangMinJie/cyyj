package com.geovis.cyyj.common.utils;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 根据HTML生成world
 */
public class Html2WorldUtil {
 
    public static String writeWordFile(String htmlString, String dir, String pathName) {

        try {
            if (!"".equals(dir)) {
 
                // 检查目录是否存在
                File fileDir = new File(dir);
                if (fileDir.exists()) {
                    // 生成临时文件名称
                    StringBuilder sb=new StringBuilder();
                    sb.append("<html>");
                    sb.append("<head><title></title></head>");
                    sb.append("<body>");
                    sb.append(htmlString); // Append the existing HTML content
                    sb.append("</body>");
                    sb.append("</html>");
                    byte b[] = sb.toString().getBytes("GB2312");
                    ByteArrayInputStream bais = new ByteArrayInputStream(b);
                    POIFSFileSystem poifs = new POIFSFileSystem();
                    DirectoryEntry directory = poifs.getRoot();
                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                    FileOutputStream ostream = new FileOutputStream(pathName);
                    poifs.writeFilesystem(ostream);
                    bais.close();
                    ostream.close();
 
                }
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pathName;
    }

}