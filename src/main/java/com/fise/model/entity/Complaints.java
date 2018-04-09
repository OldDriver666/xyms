package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Complaints implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 投诉方id
     */
    private String plaintiffId;

    /**
     * 投诉方名称
     */
    private String plaintiffName;

    /**
     * 被投诉方id
     */
    private String defendantId;

    /**
     * 被投诉方名称
     */
    private String defendantName;

    /**
     * 被投诉方类型(群,个人)
     */
    private String defendantType;

    /**
     * 投诉类型
     */
    private String complaintType;

    /**
     * 投诉内容
     */
    private String content;

    /**
     * 图片
     */
    private String picture;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaintiffId() {
        return plaintiffId;
    }

    public void setPlaintiffId(String plaintiffId) {
        this.plaintiffId = plaintiffId;
    }

    public String getPlaintiffName() {
        return plaintiffName;
    }

    public void setPlaintiffName(String plaintiffName) {
        this.plaintiffName = plaintiffName;
    }

    public String getDefendantId() {
        return defendantId;
    }

    public void setDefendantId(String defendantId) {
        this.defendantId = defendantId;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getDefendantType() {
        return defendantType;
    }

    public void setDefendantType(String defendantType) {
        this.defendantType = defendantType;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
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
}