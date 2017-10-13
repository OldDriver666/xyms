package com.fise.service.devicecontrol;

import com.fise.base.Response;
import com.fise.model.entity.DeviceControl;
import com.fise.model.param.DeviceControlParam;

public interface IDeviceControlService {
    /*新增儿童手表联系人*/
    Response addDeviceControl(DeviceControl record);
    
    /*查询儿童手表联系人*/
    Response queryDeviceControl(DeviceControlParam param);
    
    /*修改儿童手表联系人*/
    Response updateDeviceControl(DeviceControl record);
    
    /*删除儿童手表联系人*/
    Response delDeviceControl(DeviceControlParam param);
}
