
package com.fise.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.utils.JsonUtil;

/**
 * @author 
 */
public class IMDeviceVersion implements Serializable {
    @JsonProperty("version_id")
    private Integer id;

    /**
     * 公司ID
     */
    @JsonProperty("depart_id")
    private Integer departid;

    /**
     * 设备类型
     */
    @JsonProperty("dev_type")
    private Integer devType;

    /**
     * 最新设备固件版本号
     */
    @JsonProperty("dev_version")
    private String devVersion;

    /**
     * 版本信息
     */
    @JsonProperty("version_info")
    private String versionInfo;

    private Integer status;

    /**
     * 更新下载地址
     */
    @JsonProperty("update_url")
    private String updateUrl;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    public String getDevVersion() {
        return devVersion;
    }

    public void setDevVersion(String devVersion) {
        this.devVersion = devVersion;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
    
}