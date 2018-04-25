package com.fise.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 
 */
public class AppDownloadList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AppDownload> list ;

	public List<AppDownload> getList() {
		return list;
	}

	public void setList(List<AppDownload> list) {
		this.list = list;
	}
	
}