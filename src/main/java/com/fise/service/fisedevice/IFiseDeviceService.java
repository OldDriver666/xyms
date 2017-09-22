package com.fise.service.fisedevice;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.FiseDevice;
import com.fise.model.param.QueryFiseDeviceParam;

public interface IFiseDeviceService {
	/*添加新的fisedevice*/
	public Response insertFiseDevice(FiseDevice record);
	
	/*查询设备信息 分页*/
	public Response queryFiseDevice(Page<QueryFiseDeviceParam> page);
	
	/*删除fise设备*/
	public Response delFiseDevice(QueryFiseDeviceParam param);
	
	/*修改fise设备信息*/
	public Response updateFiseDevice(FiseDevice param);
}
