package com.fise.controller.user;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.param.QueryUserParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.user.IUserService;

@RestController
@RequestMapping("/boss/user")
public class UserController {
    
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IUserService IQueryUserService;
    
    @Resource
    IAuthService authService;
    
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response queryUserInfo(@RequestBody @Valid Page<QueryUserParam> param){
        
        Response response=new Response();

        logger.info(param.toString());
        response=IQueryUserService.queryUser(param);
       
        
        return response;
    }
}
