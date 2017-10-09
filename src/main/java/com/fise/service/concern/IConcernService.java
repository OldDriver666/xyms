package com.fise.service.concern;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.Concern;

public interface IConcernService {
    /*用户关注问题*/
    public Response addConcern(Concern record);
    
    /*查询用户是否关注*/
    public Response queryisConcern(Concern record);
    
    /*查询用户关注的问题*/
    public Response queryConcerns(Integer user_id,Integer page_no);
    
    /*根据问题ID，查询关注问题详情*/
    public Response query(Integer user_id,Integer problem_id);
    
    /*后台管理 查询关注*/
    public Response queryBack(Page<Concern> page);
    
    /*后台管理  关注更新*/
    public Response update(Concern param);
}
