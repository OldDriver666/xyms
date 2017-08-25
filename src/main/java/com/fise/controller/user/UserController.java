package com.fise.controller.user;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.service.user.IUserService;
import com.fise.utils.StringUtil;



@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger =Logger.getLogger(getClass());
    
    @Resource
    IUserService userService;
    
    /*查询用户信息*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Map<String, String> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(StringUtil.isEmpty(map.get("name"))){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=userService.queryUser(map.get("name"));
        return res;
    }
}
