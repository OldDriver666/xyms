package com.fise.model.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fise.model.entity.Problems;
import com.fise.utils.JsonUtil;

/** 
 * @author 大表哥
 * @email xingyuanzuo@gmail.com
 * @date 2016-8-9
 * @desc 第三方登录参数对象
 */

public class ProblemsParam implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Problems> list = new ArrayList<Problems>();
	
	public List<Problems> getList() {
		return list;
	}

	public void setList(List<Problems> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
