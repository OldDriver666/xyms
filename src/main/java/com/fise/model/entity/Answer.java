package com.fise.model.entity;
 
import java.io.Serializable;
 
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;
 
/**
 * @author 
 */
public class Answer implements Serializable {
    private Integer id;
 
    /**
     * 用户id
     */
    @JsonProperty("user_id")
    private Integer userId;
    
    /**
     * 用户昵称
     */
    private String nick;
 
    /**
     * 问题ID
     */
    @JsonProperty("problem_id")
    private Integer problemId;
    
    /**
     * 问题标题
     */
    private String title;
 
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
 
    /**
     * 更新时间
     */
    private Integer updated;
 
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}
