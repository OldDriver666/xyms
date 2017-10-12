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
import com.fise.model.entity.AppChannelList;
import com.fise.service.app.IAppChannelListService;

@RestController
@RequestMapping("/app/channellist")
public class AppChannelListController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    IAppChannelListService appChannelListService;
    
    /*应用商城   频道应用查询*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Page<AppChannelList> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=appChannelListService.query(param);
        return resp;
    }
    
    /*应用商城    频道应用更新*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid AppChannelList param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            resp.setMsg("参数  id  不能为空！！");
            return resp;
        }
        
        resp = appChannelListService.update(param);
        return resp;
    }
    
    /*应用商城 新增频道应用*/
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insert(@RequestBody @Valid AppChannelList param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getChannelId()==null || param.getAppId()==null){
            return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        resp=appChannelListService.insert(param);
        return resp;
    }
}
