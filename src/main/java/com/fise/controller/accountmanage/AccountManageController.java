package com.fise.controller.accountmanage;

import java.util.Map;

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
import com.fise.model.entity.WiAccountManage;
import com.fise.service.accountmanage.IAccountManageService;
import com.fise.service.auth.IAuthService;

@RestController
@RequestMapping("/boss/accountmanage")
public class AccountManageController {
    
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IAccountManageService accountManageService;
    
    @Resource
    IAuthService authService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response insertAccount(@RequestBody @Valid WiAccountManage param){
        
        Response response=new Response();
        
        if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(param.toString());
        
        if(param.getDepartId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        response=accountManageService.insertAccount(param);
        return response;
    }
    
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response queryAccount(@RequestBody @Valid Map<String, Object> map){
        
        Response response=new Response();
        logger.info(map.toString());
        Integer id=(Integer) map.get("depart_id");
        response=accountManageService.queryAccount(id);
        return response;
    }
    
    @RequestMapping(value="/queryPage",method=RequestMethod.POST)
    public Response queryAccountPage(@RequestBody @Valid Page<WiAccountManage> param){
    	Response response=new Response();
    	logger.info(param.toString());
    	response=accountManageService.queryAccountPage(param);
    	return response;
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response delAccount(@RequestBody @Valid Map<String, Object> map){
        
        Response response=new Response();
        
        if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(map.toString());
        Integer id=(Integer) map.get("id");
        if(id==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        response=accountManageService.delAccount(id);
        return response;
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response updateAccount(@RequestBody @Valid WiAccountManage param){
        Response response=new Response();
        
        if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(param.toString());
        if(param.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        response=accountManageService.updateAccount(param);
        return response;
    }
}
