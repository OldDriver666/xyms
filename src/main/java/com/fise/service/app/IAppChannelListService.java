package com.fise.service.app;

import java.util.List;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppChannel;
import com.fise.model.entity.AppChannelList;

public interface IAppChannelListService {
    /*应用商城   频道应用查询*/
    public Response query(Page<AppChannelList> param);
    
    /*应用商城    频道应用更新*/
    public Response update(AppChannelList param);
    
    /*应用商城 新增频道应用*/
    public Response insert(AppChannelList param);
    
    public Response queryByIdList(Page<AppChannel> page,List<Integer> idList);
}
