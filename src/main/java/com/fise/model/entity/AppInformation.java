package com.fise.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 
 */
public class AppInformation implements Serializable {
    private Integer id;

    /**
     * 应用名称
     */
    @JsonProperty("app_name")
    private String appName;
    
    @JsonProperty("app_spell")
    private String appSpell;

    @JsonProperty("package_name")
    private String packageName;

    /**
     * 开发者ID
     */
    @JsonProperty("dev_id")
    private Integer devId;

    /**
     * 开发者名称
     */
    @JsonProperty("dev_name")
    private String devName;
    
    @JsonProperty("channel_name")
    private String channelName;

    @JsonProperty("channel_id")
    private Integer channelId;

    /**
     * 应用大类型
     */
    @JsonProperty("top_category")
    private String topCategory;

    /**
     * 应用小类型
     */
    private String category;

    /**
     * 0-待审核 1-发布 2-拒绝 3-下架
     */
    private Integer status;

    /**
     * 应用简介
     */
    private String description;

    /**
     * 版本号
     */
    private String version;

    private Integer versioncode;

    /**
     * 应用图标
     */
    private String icon;

    @JsonProperty("icon_type")
    private Integer iconType;

    /**
     * 截图
     */
    private String images;

    /**
     * 下载地址
     */
    private String download;

    /**
     * 应用大小
     */
    private String size;

    /**
     * 更新时间
     */
    private Integer updated;

    /**
     * 创建时间
     */
    private Integer created;

    private Integer prority;
    
    /**
     * 下载次数
     */
    private Integer count;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 标签
     */
    private String label;

    /**
     * md5值
     */
    private String md5;

    private String star;

    /**
     * 0-竖 1-横着
     */
    private Integer orientation;
    
    @JsonProperty("channel_list")
    private List<AppChannelList> channelList = new ArrayList<AppChannelList>();
    
    private String channelIds;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSpell() {
        return appSpell;
    }

    public void setAppSpell(String appSpell) {
        this.appSpell = appSpell;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(String topCategory) {
        this.topCategory = topCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIconType() {
        return iconType;
    }

    public void setIconType(Integer iconType) {
        this.iconType = iconType;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public Integer getPrority() {
        return prority;
    }

    public void setPrority(Integer prority) {
        this.prority = prority;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<AppChannelList> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<AppChannelList> channelList) {
		this.channelList = channelList;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}


	
}