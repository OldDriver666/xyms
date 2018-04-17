package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class Comment implements Serializable {
    private Integer id;

    /**
     * 用户id
     */
    @JsonProperty("from_userid")
    private Integer fromUserid;
    
    /**用户昵称*/ private String fnick   ;
	/**被回复用户昵称*/ private String tnick   ;
	/**回答内容*/ private String acontent;
	/**原评论内容*/ private String bcontent;
	/**问题标题*/ private String title   ;

    /**
     * 回复对方
     */
    @JsonProperty("to_userid")
    private Integer toUserid;

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

    private static final long serialVersionUID = 1L;

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

    public String getFnick() {
		return fnick;
	}

	public void setFnick(String fnick) {
		this.fnick = fnick;
	}

	public String getTnick() {
		return tnick;
	}

	public void setTnick(String tnick) {
		this.tnick = tnick;
	}

	public String getAcontent() {
		return acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
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