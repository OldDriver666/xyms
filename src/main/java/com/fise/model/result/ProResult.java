package com.fise.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.framework.annotation.MaxLength;
import com.fise.utils.JsonUtil;

public class ProResult implements Serializable{
    private int addAnswerCount;
    private int addBrowseCount;
    private String isConcern;
    
    private Integer id;

    /**
     * 用户名
     */
    @JsonProperty("user_id")
    private Integer userId;
    
    /*
     * 昵称
     */
    private String nick;
    
    /*
     * 头像
     */
    private String avatar;
    
    private String schoolname;

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
    
    @JsonProperty("school_id")
    private Integer schoolId;

    /**
     * 更新时间
     */
    private Integer updated;

    /**
     * 创建时间
     */
    private Integer created;

    private static final long serialVersionUID = 1L;

    public String getIsConcern() {
        return isConcern;
    }

    public void setIsConcern(String isConcern) {
        this.isConcern = isConcern;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

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

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
