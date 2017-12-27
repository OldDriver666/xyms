package com.fise.controller.devicecontrol;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.entity.DeviceControl;
import com.fise.model.param.DeviceControlParam;
import com.fise.service.devicecontrol.IDeviceControlService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/boss/devicecontrol")
public class DeviceControlController {
    
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IDeviceControlService deviceControlService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response addDeviceControl(@RequestBody @Valid DeviceControl record){
        
        Response response=new Response();
        logger.info(record.toString());
        
        if(record.getDeviceId()==null || record.getAuthType()==null || StringUtil.isEmpty(record.getMobile())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=deviceControlService.addDeviceControl(record);
        return response;
    }
    
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response queryDeviceControl(@RequestBody @Valid DeviceControlParam param){
        
        Response response=new Response();
        logger.info(param.toString());
        
        response=deviceControlService.queryDeviceControl(param);
        return response;
    }
    
    /*@RequestMapping(value="/update",method=RequestMethod.POST)
    public Response updateDeviceControl(@RequestBody @Valid DeviceControl record){
        
        Response response=new Response();
        logger.info(record.toString());
        
        if(record.getDeviceId()==null || record.getAuthType()==null || StringUtil.isEmpty(record.getMobile())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        response=deviceControlService.updateDeviceControl(record);
        return response;
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response delDeviceControl(@RequestBody @Valid DeviceControlParam param){
        
        Response response=new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        response=deviceControlService.delDeviceControl(param);
        return response;
    }*/
     
}
