package com.fise.controller.smsplatfrom;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.entity.IMSmsPlatfrom;
import com.fise.model.param.SmsPlatfromParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.smsplatfrom.ISmsPlatfromService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/boss/smsplatfrom")
public class SmsPlatfromController {
    
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    ISmsPlatfromService smsPlatfromService;
    
    @Resource
    IAuthService authService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response addSmsPlatfrom(@RequestBody @Valid IMSmsPlatfrom record){
        
        Response response=new Response();
        
        if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(record.toString());
        
        if(StringUtil.isEmpty(record.getPlatfromName())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=smsPlatfromService.addSmsPlatfrom(record);
        return response;
    }
    
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response querySmsPlatfrom(@RequestBody @Valid SmsPlatfromParam param){
        
        Response response=new Response();
        logger.info(param.toString());
        response=smsPlatfromService.querySmsPlatfrom(param);
        return response;
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response updateSmsPlatfrom(@RequestBody @Valid IMSmsPlatfrom record){
        
        Response response=new Response();
        
        if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(record.toString());
        
        if(record.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(record.getPlatfromName())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=smsPlatfromService.updateSmsPlatfrom(record);
        return response;
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response delSmsPlatfrom(@RequestBody @Valid SmsPlatfromParam param){
        
        Response response =new Response();
        
        if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(param.toString());
        
        if(param.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=smsPlatfromService.delSmsPlatfrom(param);
        return response;
    }
}
