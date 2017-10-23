package com.fise.model.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.model.entity.AppChannel;
import com.fise.utils.JsonUtil;

public class AppChannelResult {
	private Integer id;
	private String name;
	private String textColor;
	private String color;
	private String image;
	@JsonProperty("app_list")
	private List<AppBaseResult> appList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<AppBaseResult> getAppList() {
		return appList;
	}

	public void setAppList(List<AppBaseResult> appList) {
		this.appList = appList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void init(AppChannel data) {
		this.id=data.getId();
		this.name = data.getChannelName();
	    this.textColor = data.getTextcolor();
	    this.color =data.getBackground();
	    this.image =data.getImage();
	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
	
}
