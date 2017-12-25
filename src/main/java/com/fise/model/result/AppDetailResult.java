package com.fise.model.result;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.model.entity.AppInformation;
import com.fise.utils.JsonUtil;
import com.fise.utils.StringUtil;

public class AppDetailResult {
	
	@JsonProperty("appId")
    private Integer id;

    private String appName;

    private Integer devId;
    
    private String devName;

    private String topCategory;

    private String category;

    private String description;

    private String version;
    
    private Integer versionCode;

    private String icon;

    private Integer iconType;
    
    private String download;

    private String size;

    private Integer updated;
    
    private Integer created;
    
    private String remarks;
    
    private String label;

    private String star;
    
    private Integer orientation;
    
    private String packageName;
    
    private List<String> images;
   
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getOrientation() {
		return orientation;
	}

	public void setOrientation(Integer orientation) {
		this.orientation = orientation;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	

	public Integer getIconType() {
		return iconType;
	}

	public void setIconType(Integer iconType) {
		this.iconType = iconType;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
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

	public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public void init(AppInformation data){
        this.id = data.getId();
        this.appName = data.getAppName();
        this.devId=data.getDevId();
        this.devName=data.getDevName();
        this.topCategory = data.getTopCategory();
        this.category = data.getCategory();
        this.description = data.getDescription();
        this.version = data.getVersion();
        this.icon = data.getIcon();
        this.download = data.getDownload();
        this.size = data.getSize();
        this.updated = data.getUpdated();
        this.created=data.getCreated();
        this.remarks=data.getRemarks();
        this.label=data.getLabel();
        this.versionCode=data.getVersioncode();
        this.iconType=data.getIconType();
        this.star=data.getStar();
        this.orientation=data.getOrientation();
        this.packageName=data.getPackageName();
        this.images= StringUtil.splitStr(data.getImages());
    }
    
    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
