package com.fise.service.depart;

import com.fise.base.Response;
import com.fise.model.entity.IMDepartConfig;
import com.fise.model.param.DepartConfigParam;

public interface IDepartConfigService {
	/*添加新的departconfig*/
	public Response insertDepartConfig(IMDepartConfig record);
	
	/*查询departconfig信息*/
	public Response queryDepartConfig(DepartConfigParam param);
	
	/*删除departconfig*/
	public Response delDepartConfig(DepartConfigParam param);
	
	/*修改departconfig信息*/
	public Response updateDepartConfig(IMDepartConfig record);
}
