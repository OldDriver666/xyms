package com.fise.model.result;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.model.entity.AppInformation;
import com.fise.utils.JsonUtil;

public class AppBaseResult {
	@JsonProperty("appId")
	private Integer id;
	private String appName;
	private String download;
	private String description;
	private String version;
	private Integer versionCode;
	private String category;
	private String star;
	private String icon;
	private Integer iconType;
	private String size;
	private String packageName;
	private String md5;
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

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

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void init(AppInformation data) {
		this.id = data.getId();
		this.appName = data.getAppName();
		this.download = data.getDownload();
		this.description = data.getDescription();
		this.version = data.getVersion();
		this.versionCode = data.getVersioncode();
		this.category = data.getCategory();
		this.star = data.getStar();
		this.icon = data.getIcon();
		this.iconType = data.getIconType();
		this.size = data.getSize();
		this.packageName=data.getPackageName();
		this.md5=data.getMd5();
	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
