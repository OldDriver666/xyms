package com.fise.controller.clienttype;

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
import com.fise.model.entity.IMClientType;
import com.fise.model.param.ClientTypeParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.clienttype.IClientTypeService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/boss/clienttype")
public class ClientTypeController {
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Resource
	IClientTypeService imClientTypeService;
	
	@Resource
	IAuthService authService;
	
	/*添加clienttype*/
	@RequestMapping(value="/addclienttype",method=RequestMethod.POST)
	public Response addClientType(@RequestBody @Valid IMClientType param){
		
		Response response=new Response();
		
		if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getClienttype()==null || StringUtil.isEmpty(param.getClientname())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=imClientTypeService.insertClientType(param);
		
		
		return response;
	}
	
	/*查询clienttype设备   */
	@RequestMapping(value="/queryclienttype",method=RequestMethod.POST)
	public Response queryclienttype(@RequestBody @Valid ClientTypeParam param){
		
		Response response=new Response();
		logger.info(param.toString());
		response=imClientTypeService.queryClientType(param);
		
		
		return response;
	}
	/*分页查询clienttype设备   */
	@RequestMapping(value="/queryClienTypePage",method=RequestMethod.POST)
	public Response queryClienTypePage(@RequestBody @Valid Page<ClientTypeParam> param) {
		Response response=new Response();
		logger.info(param.toString());
		response=imClientTypeService.queryClienTypePage(param);
		return response;
	}
	
	
	
	
	
	/*删除fise设备*/
	@RequestMapping(value="/delclienttype",method=RequestMethod.POST)
	public Response delFiseDevice(@RequestBody @Valid ClientTypeParam param){
		
		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getTypeid()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=imClientTypeService.delClientType(param);
		
		
		return response;
	}
	
	/*修改fise设备信息*/
	@RequestMapping(value="/updateclienttype",method=RequestMethod.POST)
	public Response updateFiseDevice(@RequestBody @Valid IMClientType record){

		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(record.toString());
		
		if(record.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=imClientTypeService.updateClientType(record);
		
		
		return response;
	}
}
