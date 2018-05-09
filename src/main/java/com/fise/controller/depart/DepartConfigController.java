package com.fise.controller.depart;

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
import com.fise.model.entity.IMDepartConfig;
import com.fise.model.param.DepartConfigParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.depart.IDepartConfigService;

@RestController
@RequestMapping("/boss/departconf")
public class DepartConfigController {
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Resource
	IDepartConfigService iDepartConfigService;
	
	@Resource
	IAuthService authService;
	
	/*添加imdepartconfig*/
	@RequestMapping(value="/addimdepartconfig",method=RequestMethod.POST)
	public Response addImdepartConfig(@RequestBody @Valid IMDepartConfig param){
		
		Response response=new Response();
		
		if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getDepartid()==null || param.getClienttype()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
	
		response=iDepartConfigService.insertDepartConfig(param);
		
		
		return response;
	}
	
	/*查询imdepartconfig*/
	@RequestMapping(value="/queryimdepartconfig",method=RequestMethod.POST)
	public Response queryImdepartConfig(@RequestBody @Valid DepartConfigParam param){
		
		Response response=new Response();
		logger.info(param.toString());
		response=iDepartConfigService.queryDepartConfig(param);
		return response;
	}
	
	/*分页查询imdepartconfig*/
	@RequestMapping(value="/queryImdepCfgPage",method=RequestMethod.POST)
    public Response queryImdepCfgPage(@RequestBody @Valid Page<DepartConfigParam> param){
		Response response=new Response();
		logger.info(param.toString());
		response=iDepartConfigService.queryImdepCfgPage(param);
	    return response;
	}	
	
	/*删除imdepartconfig*/
	@RequestMapping(value="/delimdepartconfig",method=RequestMethod.POST)
	public Response delImdepartConfig(@RequestBody @Valid DepartConfigParam param){
		
		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getConfigid()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iDepartConfigService.delDepartConfig(param);
		
		
		return response;
	}
	
	/*修改imdepartconfig信息*/
	@RequestMapping(value="/updateimdepartconfig",method=RequestMethod.POST)
	public Response updateImdepartConfig(@RequestBody @Valid IMDepartConfig param){

		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iDepartConfigService.updateDepartConfig(param);
		
		
		return response;
	}
}
