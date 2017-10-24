package com.fise.service.app;

import java.util.List;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppChannel;

public interface IAppChannelService {
    /*应用商城    频道查询*/
    public Response query(Page<AppChannel> page);
    
    /*应用商城    频道更新*/
    public Response update(AppChannel param);
    
    /*应用商城   新增频道*/
    public Response insert(AppChannel param);
    
    /*查询出所有的channel，并根据权重进行降序排序。*/
    public Response queryChannelAll();
    
    List<Integer> getChannelAppId(Integer channelId);
    
    AppChannel getChannelInfo(Integer channelId);
}
