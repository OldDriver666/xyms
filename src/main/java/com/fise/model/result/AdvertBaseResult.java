package com.fise.model.result;

import com.fise.model.entity.AppAdvert;
import com.fise.utils.JsonUtil;

public class AdvertBaseResult {
	private Integer id;

	private String advIndex;
	
	private String advName;

	private String innerType;

	private Integer innerId;

	private String image;

	private Integer delayTime;
	
	private String innerName;
	
	public String getAdvIndex() {
		return advIndex;
	}

	public void setAdvIndex(String advIndex) {
		this.advIndex = advIndex;
	}

	public String getInnerType() {
		return innerType;
	}

	public void setInnerType(String innerType) {
		this.innerType = innerType;
	}

	public Integer getInnerId() {
		return innerId;
	}

	public void setInnerId(Integer innerId) {
		this.innerId = innerId;
	}

	public String getInnerName() {
		return innerName;
	}

	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}

	public String getAdvName() {
		return advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}

	public void init(AppAdvert appAdvert) {
		this.id =appAdvert.getId();
	    this.advName =appAdvert.getAdvName();
	    this.advIndex=appAdvert.getAdvIndex();
		this.delayTime = appAdvert.getDelayTime();
		this.image = appAdvert.getAdvUrl();
		this.innerName=appAdvert.getInnerName();
		this.innerId=appAdvert.getInnerId();
		this.innerType=appAdvert.getInnerType();
	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
