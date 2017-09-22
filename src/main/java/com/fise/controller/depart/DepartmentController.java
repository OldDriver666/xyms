package com.fise.controller.depart;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.param.DepartmentParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.depart.IDepartmentService;

@RestController
@RequestMapping("/boss/depart")
public class DepartmentController {
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Resource
	IDepartmentService iDepartmentSvr;
	
	@Resource
	IAuthService authService;
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Response insertDepartment(@RequestBody @Valid DepartmentParam param){
		
		Response response=new Response();
		
		if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getDepartName() == null || param.getParentId() == null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		response=iDepartmentSvr.insertOne(param);
		return response;
	}
	
	@RequestMapping(value="/query",method=RequestMethod.POST)
	public Response queryImdepartConfig(@RequestBody @Valid DepartmentParam param){
		
		Response response=new Response();
		logger.info(param.toString());
		response=iDepartmentSvr.queryList(param);
		return response;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Response delImdepartConfig(@RequestBody @Valid DepartmentParam param){
		
		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		response=iDepartmentSvr.update(param);
		return response;
	}

}
