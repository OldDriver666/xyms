package com.fise.controller.app;

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
import com.fise.framework.annotation.IgnoreAuth;
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
    
    /*应用商城    频道查询*/
    @IgnoreAuth
    @RequestMapping(value="/queryUsedChannel",method=RequestMethod.POST)
    public Response queryUsedChannel(){
    	Response resp = new Response();
    	resp = appChannelService.queryUsedChannel();
    	return resp;
    }
    
    /*应用商城    频道更新*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid AppChannel param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            resp.setMsg("参数id不能为空！！！");
            return resp;
        }
        
        resp = appChannelService.update(param);
        return resp;       
    }
    
    /*应用商城   新增频道*/
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insert(@RequestBody @Valid AppChannel param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=appChannelService.insert(param);
        return resp;
    }
    
    /*应用市场 根据权重展示不用的频道*/
    @IgnoreAuth
    @RequestMapping(value = "/channelAll", method = RequestMethod.POST)
	public Response getChannelAll(@RequestBody @Valid Map<String, String> param) {
		Response response = new Response();
		logger.info(param.toString());
		response = appChannelService.queryChannelAll();
		return response;
	}
}
