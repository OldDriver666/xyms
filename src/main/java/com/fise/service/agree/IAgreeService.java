package com.fise.service.agree;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.Agree;

public interface IAgreeService {
    /*用户点赞回答*/
    public Response addAgree(Agree agree);
    
    /*后台管理   点赞查询*/
    public Response queryBack(Page<Agree> page);
    
    /*后台管理    点赞更新*/
    public Response update(Agree agree);
}
