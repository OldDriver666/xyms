package com.fise.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerResult implements Serializable{
    private static final long serialVersionUID=1L;
    
    private int addAgreeCount;
    private int addCommentCount;
    
    private Integer id;

    /**
     * 用户id
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

    /**
     * 问题ID
     */
    @JsonProperty("problem_id")
    private Integer problemId;

    /**
     * 回答内容
     */
    private String content;

    /**
     * 点赞数量
     */
    @JsonProperty("agree_num")
    private Integer agreeNum;

    /**
     * 评论数量
     */
    @JsonProperty("comment_num")
    private Integer commentNum;

    /**
     * 1-可用 0-删除
     */
    private Integer status;

    private Integer updated;
    /**
     * 创建时间
     */
    private Integer created;
    
    public int getAddAgreeCount() {
        return addAgreeCount;
    }
    public void setAddAgreeCount(int addAgreeCount) {
        this.addAgreeCount = addAgreeCount;
    }
    public int getAddCommentCount() {
        return addCommentCount;
    }
    public void setAddCommentCount(int addCommentCount) {
        this.addCommentCount = addCommentCount;
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
    public Integer getProblemId() {
        return problemId;
    }
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getAgreeNum() {
        return agreeNum;
    }
    public void setAgreeNum(Integer agreeNum) {
        this.agreeNum = agreeNum;
    }
    public Integer getCommentNum() {
        return commentNum;
    }
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
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
    public Integer getUpdated() {
        return updated;
    }
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }
    
    
    
}
