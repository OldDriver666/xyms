package com.fise.controller.smstemplate;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.entity.IMSmsTemplate;
import com.fise.model.param.SmsTemplateParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.smstemplate.ISmsTemplateService;

@RestController
@RequestMapping("/boss/sms")
public class SmsTemplateController {
    
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    ISmsTemplateService smsTemplateService;
    
    @Resource
    IAuthService authService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response addSmsTemplate(@RequestBody @Valid IMSmsTemplate record){
        
        Response response=new Response();
        
        if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(record.toString());
        
        if(record.getPlatfromId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=smsTemplateService.addSmsTemplate(record);
        return response;
    }
    
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response querySmsTemplate(@RequestBody @Valid SmsTemplateParam param){
        
        Response response=new Response();
        logger.info(param.toString());
        
        response=smsTemplateService.querySmsTemplate(param);
        
        return response;
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response updateSmsTemplate(@RequestBody @Valid IMSmsTemplate record){
        
        Response response=new Response();
        
        if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(record.toString());
        
        if(record.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        if(record.getPlatfromId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=smsTemplateService.updateSmsTemplate(record);
        return response;
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response delSmsTemplate(@RequestBody @Valid IMSmsTemplate record){
        
        Response response=new Response();
        
        if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(record.toString());
        
        if(record.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=smsTemplateService.delSmsTemplate(record);
        return response;
    }
}
