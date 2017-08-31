package com.fise.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentResult implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    private int addreply;
    
    private int count;
    
    
    private Integer id;

    /**
     * 用户名
     */
    @JsonProperty("from_userid")
    private Integer fromUserid;
    
    private String fromNick;
    
    private String fromAvatar;

    /**
     * 回复对方
     */
    @JsonProperty("to_userid")
    private Integer toUserid;
    
    private String toNick;

    private String toAvatar;
    /**
     * 回答问题ID
     */
    @JsonProperty("answer_id")
    private Integer answerId;

    /**
     * 评论ID
     */
    @JsonProperty("comment_id")
    private Integer commentId;

    /**
     * 问题ID
     */
    @JsonProperty("problem_id")
    private Integer problemId;

    /**
     * 对回答的评论
     */
    private String content;

    /**
     * 1-可用 0-删除
     */
    private Integer status;

    /**
     * 更新时间
     */
    private Integer updated;

    /**
     * 创建时间
     */
    private Integer created;
    
    
    public int getAddreply() {
        return addreply;
    }
    public void setAddreply(int addreply) {
        this.addreply = addreply;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getFromUserid() {
        return fromUserid;
    }
    public void setFromUserid(Integer fromUserid) {
        this.fromUserid = fromUserid;
    }
    public Integer getToUserid() {
        return toUserid;
    }
    public void setToUserid(Integer toUserid) {
        this.toUserid = toUserid;
    }
    public Integer getAnswerId() {
        return answerId;
    }
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
    public Integer getCommentId() {
        return commentId;
    }
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
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
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getFromNick() {
        return fromNick;
    }
    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }
    public String getToNick() {
        return toNick;
    }
    public void setToNick(String toNick) {
        this.toNick = toNick;
    }
    public String getFromAvatar() {
        return fromAvatar;
    }
    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }
    public String getToAvatar() {
        return toAvatar;
    }
    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }
    
    
    
}
