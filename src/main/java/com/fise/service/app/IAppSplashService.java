package com.fise.service.app;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppSplash;

public interface IAppSplashService {
    /*应用商城    appsplash查询*/
    public Response query(Page<AppSplash> page);
    
    /*应用商城  appsplash更新*/
    public Response update(AppSplash param);
}
