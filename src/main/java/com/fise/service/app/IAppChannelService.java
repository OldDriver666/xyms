package com.fise.service.app;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppChannel;

public interface IAppChannelService {
    /*应用商城    频道查询*/
    public Response query(Page<AppChannel> page);
    
    /*应用商城    频道更新*/
    public Response update(AppChannel param);
}
