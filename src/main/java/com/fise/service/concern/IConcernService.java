package com.fise.service.concern;

import com.fise.base.Response;
import com.fise.model.entity.Concern;

public interface IConcernService {
    /*用户关注问题*/
    public Response addConcern(Concern record);
    
    /*查询用户是否关注*/
    public Response queryConcern(Concern record);
}
