package com.fise.controller.messagetype;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.Response;
import com.fise.service.messagetype.IMessageTypeService;

@RestController
@RequestMapping("/boss/messagetype")
public class MessageTypeController {
    
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IMessageTypeService messageTypeService;
    
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response queryMessageType(@RequestBody Map<String, Object> map){
        Response response=new Response();
        
        logger.info(map.toString());
        Integer type=(Integer) map.get("type");
        response=messageTypeService.queryMessageType(type);
        return response;
    }
}
