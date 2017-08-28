package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class IMSchool implements Serializable {
    /**
     * 学校ID
     */
    @JsonProperty("school_id")
    private Integer schoolid;

    /**
     * 学校名
     */
    @JsonProperty("school_name")
    private String schoolname;

    /**
     * 所属地区/县id
     */
    @JsonProperty("district_id")
    private Integer districtid;

    /**
     * 学校地址
     */
    @JsonProperty("school_addr")
    private String schooladdr;

    private Integer updated;

    private Integer created;

    private static final long serialVersionUID = 1L;

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public String getSchooladdr() {
        return schooladdr;
    }

    public void setSchooladdr(String schooladdr) {
        this.schooladdr = schooladdr;
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