package com.fise.service.app;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppInformation;

public interface IAppInfoemationService {
    /*应用商城   产品信息查询*/
    public Response query(Page<AppInformation> page);
    
    /*应用商城   产品信息更新*/
    public Response update(AppInformation param);
}
