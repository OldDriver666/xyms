package com.fise.service.systemconf;

import com.fise.base.Response;
import com.fise.model.entity.IMSystemConf;
import com.fise.model.param.SystemConfParam;

public interface ISystemConfService {
	/*添加systemconf信息*/
	public Response insertSystemConf(IMSystemConf record);
	
	/*查询systemconf信息*/
	public Response querySystemConf(SystemConfParam param);
	
	/*删除systemconf信息*/
	public Response delSystemConf(SystemConfParam param);
	
	/*修改systemconf信息*/
	public Response updateSystemConf(IMSystemConf record);
}
