package com.fise.service.clienttype;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.IMClientType;
import com.fise.model.param.ClientTypeParam;

public interface IClientTypeService {
	
	/*添加clienttype*/
	public Response insertClientType(IMClientType record);
	
	/*查询clienttype信息*/
	public Response queryClientType(ClientTypeParam param);
	
	/*删除clienttype记录*/
	public Response delClientType(ClientTypeParam param);
	
	/*更改clienttype信息*/
	public Response updateClientType(IMClientType record);
	
	/*分页查询clienttype信息*/
	public Response queryClienTypePage(Page<ClientTypeParam> param);
}
