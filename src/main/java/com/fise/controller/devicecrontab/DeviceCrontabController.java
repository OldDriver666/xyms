package com.fise.controller.devicecrontab;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.entity.DeviceCrontab;
import com.fise.model.param.DeviceCrontabParam;
import com.fise.service.devicecrontab.IDeviceCrontabService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/boss/devicecrontab")
public class DeviceCrontabController {
    
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IDeviceCrontabService deviceCrontabService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response addDeviceCrontab(@RequestBody @Valid DeviceCrontab record){
        
        Response response=new Response();
        logger.info(record.toString());
        
        if(record.getDeviceId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=deviceCrontabService.addDeviceCrontab(record);
        return response;
    }
    
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response queryDeviceCrontab(@RequestBody @Valid DeviceCrontabParam param){
        
        Response response=new Response();
        logger.info(param.toString());
        
        if(StringUtil.isEmpty(param.getName())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=deviceCrontabService.queryDeviceCrontab(param);
        return response;
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response updateDeviceCrontab(@RequestBody @Valid DeviceCrontab record){
        
        Response response=new Response();
        logger.info(record.toString());
        
        if(record.getTaskId()==null || record.getDeviceId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=deviceCrontabService.updateDeviceCrontab(record);
        return response;
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response delDeviceCrontab(@RequestBody @Valid DeviceCrontabParam param){
        
        Response response=new Response();
        logger.info(param.toString());
        
        if(param.getTaskId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=deviceCrontabService.delDeviceCrobtab(param);
        return response;
    }
}
