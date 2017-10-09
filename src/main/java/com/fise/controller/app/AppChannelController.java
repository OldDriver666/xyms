package com.fise.controller.app;

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
import com.fise.model.entity.AppChannel;
import com.fise.service.app.IAppChannelService;

@RestController
@RequestMapping("/app/channel")
public class AppChannelController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    IAppChannelService appChannelService;
    
    /*应用商城    频道查询*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Page<AppChannel> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp = appChannelService.query(param);
        return resp;
    }
    
    /*应用商城    频道更新*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid AppChannel param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            resp.setMsg("参数   id  不能为空！！");
            return resp;
        }
        
        resp = appChannelService.update(param);
        return resp;       
    }
}
