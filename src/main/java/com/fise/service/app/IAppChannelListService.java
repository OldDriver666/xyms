package com.fise.service.app;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppChannelList;

public interface IAppChannelListService {
    /*应用商城   频道应用查询*/
    public Response query(Page<AppChannelList> param);
    
    /*应用商城    频道应用更新*/
    public Response update(AppChannelList param);
}
