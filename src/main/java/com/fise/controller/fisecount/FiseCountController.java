package com.fise.controller.fisecount;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.Response;
import com.fise.model.param.DeviceCountParam;
import com.fise.service.activedevicecount.IActiveDeviceCountService;

@RestController
@RequestMapping("/boss")
public class FiseCountController {
    
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IActiveDeviceCountService iActiveDeviceCountService;
    
    //查询小位与设备的激活与在线统计
    @RequestMapping(value="/devicecount",method=RequestMethod.POST)
    public Response getDeviceCount(@RequestBody @Valid DeviceCountParam param){
        
        Response response=new Response();
        logger.info(param.toString());
        response=iActiveDeviceCountService.getActiveDeviceCount(param);
        
        return response;
    }
}
