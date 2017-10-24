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
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insert(@RequestBody @Valid AppInformation param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(StringUtil.isEmpty(param.getAppIndex()) || StringUtil.isEmpty(param.getAppName()) || StringUtil.isEmpty(param.getAppSpell())){
            return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        resp=appInfoemationService.insert(param);
        return resp;
    }
    
    @IgnoreAuth
	@RequestMapping(value = "/appDelete", method = RequestMethod.POST)
	public Response appDelete(@RequestBody @Valid Map<String, Object> param) {
		Response response = new Response();
		Integer appId = (Integer) param.get("app_id");
		response = appInfoemationService.appDelete(appId);
		return response;
	}
    
    /**
     *当参数中，传app_name的时候，是做分页查询，查询出多条数据。
     *当参数中，不传app_name的时候，是做所有的查询，查询出所有的app。这时候该接口用于加载app栏
     * @param param
     * @return
     */
    /*应用市场 加载所有可用的App*/
    @IgnoreAuth
    @RequestMapping(value="/queryAll",method=RequestMethod.POST)
    public Response queryAll(@RequestBody @Valid Page<AppInformation> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=appInfoemationService.queryAll(param);
        return resp;
    }
    
    /*根据app_name返回两条数据*/
    @IgnoreAuth
    @RequestMapping(value = "/simpleSearch", method = RequestMethod.POST)
	public Response getsimpleSearch(@RequestBody @Valid Map<String, Object> param) {
		Response response = new Response();
		logger.info(param.toString());
		String appName = (String) param.get("app_name");
		response = appInfoemationService.queryByAppName(appName);
		return response;
	}
    
    /*热门搜索app,展示app_name*/
    @IgnoreAuth
    @RequestMapping(value = "/hotSearch", method = RequestMethod.POST)
	public Response getHotSearch(@RequestBody @Valid Map<String, Object> param) {
		Response response = new Response();
		logger.info(param.toString());
		response = appInfoemationService.hotSearch();
		return response;
	}
    
   /*获取单个的app的具体信息*/
    @IgnoreAuth
    @RequestMapping(value = "/appinfo", method = RequestMethod.POST)
	public Response getAppInfo(@RequestBody @Valid Map<String, Object> param) {
		Response response = new Response();
		Integer appId = (Integer) param.get("app_id");
		if(appId.equals("")||appId==null){
			return response.failure(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
		}
		response = appInfoemationService.queryByAppId(appId);
		return response;
	}

}
