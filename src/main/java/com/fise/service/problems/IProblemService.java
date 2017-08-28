package com.fise.service.problems;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.Problems;

public interface IProblemService {
    /*提出问题*/
    public Response insert(Problems record);
    
    /*查询问题    默认每次查最新10条记录*/
    public Response queryAll(Page<Problems> param);
    
    /*检索问题    模糊查询*/
    public Response queryTitle(Page<Problems> param);
    
    /*查询我的问题*/
    public Response queryMypro(Page<Problems> param);
    
    /*根据问题id，查询问题详情*/
    public Response query(Integer problem_id,String name);
    
    /*删除我的某个问题*/
    public Response delMyPro(Integer problem_id);
}
