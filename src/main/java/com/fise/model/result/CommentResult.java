package com.fise.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentResult implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    private int addreply;
    
    private Integer id;

    /**
     * 用户名
     */
    @JsonProperty("from_name")
    private String fromName;

    /**
     * 回复对方
     */
    @JsonProperty("to_name")
    private String toName;

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
    public String getFromName() {
        return fromName;
    }
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    public String getToName() {
        return toName;
    }
    public void setToName(String toName) {
        this.toName = toName;
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
    
    
    
}
