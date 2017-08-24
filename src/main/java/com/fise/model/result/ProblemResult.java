package com.fise.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.framework.annotation.MaxLength;

public class ProblemResult implements Serializable{
    private static final long serialVersionUID=1L;
    
    private int addAnswerCount;
    private int addBrowseCount;
    
    private Integer id;
    private String name;
    private String title;
    
    @MaxLength(value=1500)
    private String content;
    
    private String picture;
    
    @JsonProperty("answer_num")
    private Integer answerNum;
    
    @JsonProperty("browse_num")
    private Integer browseNum;
    
    @JsonProperty("school_id")
    private Integer schoolId;
    
    private Integer updated;
    
    private Integer status;
    
    private Integer created;
    
    public int getAddAnswerCount() {
        return addAnswerCount;
    }
    public void setAddAnswerCount(int addAnswerCount) {
        this.addAnswerCount = addAnswerCount;
    }
    public int getAddBrowseCount() {
        return addBrowseCount;
    }
    public void setAddBrowseCount(int addBrowseCount) {
        this.addBrowseCount = addBrowseCount;
    }
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
    public Integer getSchoolId() {
        return schoolId;
    }
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
    public Integer getUpdated() {
        return updated;
    }
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }
    
    
    
}
