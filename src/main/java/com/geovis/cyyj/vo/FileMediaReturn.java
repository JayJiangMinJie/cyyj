package com.geovis.cyyj.vo;

import com.geovis.cyyj.common.core.domain.FileData;

import java.io.Serializable;

/**
 * 文件操作返回前端实体类;
 * @author : jay
 * @date : 2022-10-12
 */
public class FileMediaReturn<T> implements Serializable {
    private static final long serialVersionUID = -1959544190118740608L;
    private int errno;
    private FileData data;

    public FileMediaReturn(int errno, FileData data) {
        this.errno = errno;
        this.data = data;
    }



    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public FileData getData() {
        return data;
    }

    public void setData(FileData data) {
        this.data = data;
    }
}
