package com.fise.service.depart;

import java.util.List;

import com.fise.base.Response;
import com.fise.model.param.DepartmentParam;

public interface IDepartmentService {

	public Response insertOne(DepartmentParam record);
	
	public Response queryList(DepartmentParam param);
	
	public Response update(DepartmentParam record);
	
	List<Integer> getChildDepatId(Integer parentId);
}
