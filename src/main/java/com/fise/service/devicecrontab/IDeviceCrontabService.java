package com.fise.service.devicecrontab;

import com.fise.base.Response;
import com.fise.model.entity.DeviceCrontab;
import com.fise.model.param.DeviceCrontabParam;

public interface IDeviceCrontabService {
    /*添加设备响铃配置*/
    Response addDeviceCrontab(DeviceCrontab record);
    
    /*查询设备响铃配置*/
    Response queryDeviceCrontab(DeviceCrontabParam param);
    
    /*修改设备响铃配置*/
    Response updateDeviceCrontab(DeviceCrontab record);
    
    /*删除设备响铃配置*/
    Response delDeviceCrobtab(DeviceCrontabParam param);
}
