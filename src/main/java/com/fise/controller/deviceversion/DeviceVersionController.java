package com.fise.controller.deviceversion;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.entity.IMDeviceVersion;
import com.fise.model.param.DeviceVersionParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.deviceversion.IDeviceVersionService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/boss/deviceversion")
public class DeviceVersionController {
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Resource
	IDeviceVersionService iDeviceVersionService;
	
	@Resource
	IAuthService authService;
	
	/*添加设备版本信息*/
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Response addDeviceVersion(@RequestBody @Valid IMDeviceVersion record){
		
		Response response=new Response();
		
		if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(record.toString());
		
		if(record.getDepartid()==null || record.getDevType()==null || StringUtil.isEmpty(record.getDevVersion())||StringUtil.isEmpty(record.getUpdateUrl())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iDeviceVersionService.insertDeviceVersion(record);
		
		
		return response;
	}
	
	/*查询设备版本信息*/
	@RequestMapping(value="/query",method=RequestMethod.POST)
	public Response queryDeviceVersion(@RequestBody @Valid DeviceVersionParam param){
		
		Response response=new Response();
		logger.info(param.toString());
		
		if(param.getDepartid()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iDeviceVersionService.queryDeviceVersion(param);
		
		
		return response;
	}
	
	/*删除设备版本信息*/
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Response delDeviceVersion(@RequestBody @Valid DeviceVersionParam param){
		
		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iDeviceVersionService.delDeviceVersion(param);
		
		
		return response;
	}
	
	/*修改设备版本信息*/
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Response updateDeviceVersion(@RequestBody @Valid IMDeviceVersion record){
		
		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(record.toString());
		
		if(record.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iDeviceVersionService.updateDeviceVersion(record);
		
		
		return response;
	}
}
