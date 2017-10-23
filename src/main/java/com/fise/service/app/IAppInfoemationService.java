package com.fise.service.app;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppInformation;

public interface IAppInfoemationService {
    /*应用商城   产品信息查询*/
    public Response query(Page<AppInformation> page);
    
    /*应用商城   产品信息更新*/
    public Response update(AppInformation param);
    
    /*应用商城   新增产品信息*/
    public Response insert(AppInformation param);
    
    /*应用市场 加载所有可用的App或是根据app_name做分页查询*/
    public Response queryAll(Page<AppInformation> page);
    
    /*应用市场 根据app_name查询出两条数据*/
    public Response queryByAppName(String param);
    
    //热门搜索，返回四个热门的appName即可
    public Response hotSearch();
    
  //根据索引查询单个App
    public Response queryByAppId(Integer param);
}
