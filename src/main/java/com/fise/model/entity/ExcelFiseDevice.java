package com.fise.model.entity;

import java.io.Serializable;

import com.fise.utils.JsonUtil;
import com.fise.utils.excel.ExcelCell;

public class ExcelFiseDevice implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    /**
     * 设备IME号
     */
    @ExcelCell("A")
    private String ime;
    
    /**
     * 状态 0-出厂 1-激活
     */
    @ExcelCell("B")
    private Integer status;
    
    /**
     * 小位号-账号
     */
    @ExcelCell("C")
    private String account;

    /**
     * 公司/团体ID
     */
    @ExcelCell("D")
    private Integer departid;

    /**
     * 设备类型
     */
    @ExcelCell("E")
    private Integer type;

    /**
     * 手机号
     */
    @ExcelCell("F")
    private String mobile;

    /**
     * 备注信息
     */
    @ExcelCell("G")
    private String mark;
    
    private Integer updated;

    private Integer created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
    
}
