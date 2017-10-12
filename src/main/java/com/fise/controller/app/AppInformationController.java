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
import com.fise.model.entity.AppInformation;
import com.fise.service.app.IAppInfoemationService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/appinformation")
public class AppInformationController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    IAppInfoemationService appInfoemationService;
    
    /*应用商城   产品信息查询*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Page<AppInformation> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=appInfoemationService.query(param);
        return resp;
    }
    
    /*应用商城   产品信息更新*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid AppInformation param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            resp.setMsg("参数   id 不能为空！！");
            return resp;
        }
        
        resp = appInfoemationService.update(param);
        return resp;
    }
    
    /*应用商城   新增产品信息*/
    @RequestMapping(value="insert",method=RequestMethod.POST)
    public Response insert(@RequestBody @Valid AppInformation param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(StringUtil.isEmpty(param.getAppIndex()) || StringUtil.isEmpty(param.getAppName()) || StringUtil.isEmpty(param.getAppSpell())){
            return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        resp=appInfoemationService.insert(param);
        return resp;
    }
}
