package com.fise.controller.module;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.param.ModuleInsertParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.module.IModuleService;
import com.fise.utils.JsonUtil;

@RestController
@RequestMapping("/boss/module")
public class ModuleController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource 
    IModuleService moduleSvr;
    
    @Resource
    IAuthService authService;
    
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody Map<String,Object> param){

        Response resp = new Response();
        Integer companyId = (Integer)param.get("company_id");
        if(companyId == null){
            resp.failure(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
            resp.setMsg("查询公司为空");
        } else {
            resp.success(moduleSvr.QueryModule(companyId));
        }

        logger.info("获取菜单:"+JsonUtil.toJson(param));
        return resp;
    }
    
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response insert(@RequestBody @Valid ModuleInsertParam param){
        Response resp = new Response();
        
        if(!authService.inserAuth()){
            return resp.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        resp = moduleSvr.InsertModule(param);
        logger.info("新增菜单:"+JsonUtil.toJson(param)+"结果:" + resp.getMsg());
        return resp;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody @Valid ModuleInsertParam param){
        Response resp = new Response();
        
        if(!authService.updateAuth()){
            return resp.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        if(param.getModule_id() == null){
            resp.failure(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
            resp.setMsg("修改菜单ID为空");
        } else {
            resp = moduleSvr.UpdateModule(param);
        }
        
        logger.info("修改菜单:"+param.toString()+" 结果:"+resp.getMsg());
        return resp;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response update(@RequestBody @Valid Map<String,String> param){
        Response resp = new Response();
        
        if(!authService.updateAuth()){
            return resp.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(param.toString());
        if(param.get("module_id") == null){
            resp.failure(ErrorCode.ERROR_PARAM_NOT_VALID_EXCEPTION);
        } else {
            resp = moduleSvr.DeleteModule(new Integer(param.get("module_id")));
        }
        return resp;
    }
}
