package com.fise.service.deviceversion;

import com.fise.base.Response;
import com.fise.model.entity.IMDeviceVersion;
import com.fise.model.param.DeviceVersionParam;

public interface IDeviceVersionService {
	/*添加设备新版本*/
	public Response insertDeviceVersion(IMDeviceVersion record);
	
	/*查询设备版本信息*/
	public Response queryDeviceVersion(DeviceVersionParam param);
	
	/*删除设备版本信息*/
	public Response delDeviceVersion(DeviceVersionParam param);
	
	/*修改设备版本信息*/
	public Response updateDeviceVersion(IMDeviceVersion record);
}
