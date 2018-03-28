package com.fise.model.entity;

import java.io.Serializable;

import com.fise.utils.JsonUtil;

public class Search implements Serializable{
    
    private String title;
    private String url;
    private String photo;
    private String content;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    public Search(String title, String url, String photo, String content) {
        super();
        this.title = title;
        this.url = url;
        this.photo = photo;
        this.content = content;
    }
    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
    
}
