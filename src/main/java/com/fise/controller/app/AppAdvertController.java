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
import com.fise.model.entity.AppAdvert;
import com.fise.service.app.IAppAdvertService;

@RestController
@RequestMapping("/app/advert")
public class AppAdvertController {
    private Logger logger =Logger.getLogger(getClass());
    
    @Resource
    IAppAdvertService appAdvertService;
    
    /*应用商城   查询广告*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Page<AppAdvert> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp = appAdvertService.query(param);
        return resp;
    }
    
    /*应用商城   更新广告*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid AppAdvert param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            resp.setMsg("参数  id 不能为空 ！！");
            return resp;
        }
        
        resp = appAdvertService.update(param);
        return resp;
    }
    
    /*应用商城   新增广告*/
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insert(@RequestBody @Valid AppAdvert param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=appAdvertService.insert(param);
        return resp;
    }
    
    /*应用市场   广告栏加载*/
    @IgnoreAuth
	@RequestMapping(value = "/advertAll", method = RequestMethod.POST)
	public Response getAdvertAll(@RequestBody @Valid Map<String, String> param) {
		Response response = new Response();
		response = appAdvertService.queryAdvertAll();
		return response;
	}
}
