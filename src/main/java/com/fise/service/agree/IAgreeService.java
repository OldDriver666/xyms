package com.fise.service.agree;

import com.fise.base.Response;
import com.fise.model.entity.Agree;

public interface IAgreeService {
    /*用户点赞回答*/
    public Response addAgree(Agree agree);
    
    
}
