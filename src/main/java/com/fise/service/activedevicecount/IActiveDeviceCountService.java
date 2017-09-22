package com.fise.service.activedevicecount;

import com.fise.base.Response;
import com.fise.model.param.DeviceCountParam;

public interface IActiveDeviceCountService {
    //查询小位与设备的激活与在线统计
    public Response getActiveDeviceCount(DeviceCountParam param);
    
}
