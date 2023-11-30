package com.geovis.cyyj.common.core.domain;

import java.io.Serializable;

/**
 * 文件操作返回前端实体类;
 * @author : jay
 * @date : 2022-10-12
 */
public class FileData<T> implements Serializable {
    private static final long serialVersionUID = -1959544190118740608L;
    private String url;
    private String alt;
    private String href;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
