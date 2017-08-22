package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.framework.annotation.MaxLength;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class Problems implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 话题
     */
    private String title;

    /**
     * 提问内容
     */
    @MaxLength(value=1500)
    private String content;

    /**
     * 图片
     */
    private String picture;

    /**
     * 回答数量
     */
    @JsonProperty("answer_num")
    private Integer answerNum;

    /**
     * 浏览次数
     */
    @JsonProperty("browse_num")
    private Integer browseNum;

    /**
     * 1-可用   0-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    @Override
    public String toString() {        
        return JsonUtil.toJson(this);
    }
    
}