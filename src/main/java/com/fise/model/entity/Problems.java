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
     * 用户id
     */
    @JsonProperty("user_id")
    private Integer userId;

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
     * 用户昵称
     */
    private String nick;

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
     * 关注数量
     */
    private Integer concerns;
    
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
     * 1-可用   0-删除
     */
    private Integer status;

    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

	public Integer getConcerns() {
		return concerns;
	}

	public void setConcerns(Integer concerns) {
		this.concerns = concerns;
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

	@Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
    
}