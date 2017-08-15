package com.fise.service.problems;

import java.util.Map;

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
}
