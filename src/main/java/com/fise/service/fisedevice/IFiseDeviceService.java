package com.fise.service.fisedevice;

import java.util.List;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.ExcelFiseDevice;
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
	
	/*Excel批量插入fise设备*/
	public Response insertExcel(List<ExcelFiseDevice> param);
}
