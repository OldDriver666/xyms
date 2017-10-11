package com.fise.controller.querydevice;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.DeviceConf;
import com.fise.service.querydevice.IQueryDeviceService;

@RestController
@RequestMapping("/boss")
public class QueryDeviceController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    IQueryDeviceService queryDeviceService;
    
    /*查询设备配置*/
    @RequestMapping(value="/querydevice",method=RequestMethod.POST)
    public Response queryDevice(@RequestBody @Valid Page<DeviceConf> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=queryDeviceService.queryDevice(param);
        return resp;
    }
}
