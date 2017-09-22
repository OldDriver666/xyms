package com.fise.model.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class IMMessage0 implements Serializable {
    private Integer id;

    /**
     * 用户的关系id
     */
    private Integer relateid;

    /**
     * 发送用户的id
     */
    private Integer fromid;

    /**
     * 接收用户的id
     */
    private Integer toid;

    /**
     * 消息ID
     */
    private Integer msgid;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 音频格式类型 //0-mp3,1-spx
     */
    private Byte audiotype;

    /**
     * 消息类型
     */
    private Byte type;

    /**
     * 0正常 1被删除
     */
    private Boolean status;

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

    public Integer getRelateid() {
        return relateid;
    }

    public void setRelateid(Integer relateid) {
        this.relateid = relateid;
    }

    public Integer getFromid() {
        return fromid;
    }

    public void setFromid(Integer fromid) {
        this.fromid = fromid;
    }

    public Integer getToid() {
        return toid;
    }

    public void setToid(Integer toid) {
        this.toid = toid;
    }

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getAudiotype() {
        return audiotype;
    }

    public void setAudiotype(Byte audiotype) {
        this.audiotype = audiotype;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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