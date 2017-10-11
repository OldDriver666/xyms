package com.fise.service.querydevice;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.DeviceConf;

public interface IQueryDeviceService {
    /*查询设备配置*/
    public Response queryDevice(Page<DeviceConf> param);
}
