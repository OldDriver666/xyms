package com.fise.service.app;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppAdvert;

public interface IAppAdvertService {
    /*应用商城   查询广告*/
    public Response query(Page<AppAdvert> page);
    
    /*应用商城   更新广告*/
    public Response update(AppAdvert record);
    
    /*应用商城   新增广告*/
    public Response insert(AppAdvert record);
    
    /*查询出所有的Advert，并根据权重进行降序排序。*/
    public Response  queryAdvertAll();
}
