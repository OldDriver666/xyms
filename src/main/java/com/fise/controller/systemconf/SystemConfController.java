package com.fise.controller.systemconf;

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
import com.fise.model.entity.IMSystemConf;
import com.fise.model.param.SystemConfParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.systemconf.ISystemConfService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/boss/systemconf")
public class SystemConfController {
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Resource
	ISystemConfService iSystemConfService;
	
	@Resource
	IAuthService authService;
	
	/*添加systemconf信息*/
	@RequestMapping(value="/addsystemconf",method=RequestMethod.POST)
	public Response addSystemConf(@RequestBody @Valid IMSystemConf record){
		
		Response response=new Response();
		
		if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(record.toString());
		
		if(StringUtil.isEmpty(record.getType())||StringUtil.isEmpty(record.getName())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iSystemConfService.insertSystemConf(record);
		
		
		return response;
	}
	
	/*查询systemconf信息*/
	@RequestMapping(value="/querysystemconf",method=RequestMethod.POST)
	public Response querySystemConf(@RequestBody @Valid SystemConfParam param){
		
		Response response=new Response();
		logger.info(param.toString());
		response=iSystemConfService.querySystemConf(param);

		return response;
	}
	
	/*删除systemconf信息*/
	@RequestMapping(value="/delsystemconf",method=RequestMethod.POST)
	public Response delSystemConf(@RequestBody @Valid SystemConfParam param){
		
		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getConfigid()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iSystemConfService.delSystemConf(param);
		
		
		return response;
	}
	
	/*修改systemconf信息*/
	@RequestMapping(value="/updatesystemconf",method=RequestMethod.POST)
	public Response updateSystemConf(@RequestBody @Valid IMSystemConf record){
		
		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(record.toString());
		
		if(record.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=iSystemConfService.updateSystemConf(record);
		
		
		return response;
	}
	
	@IgnoreAuth
    @RequestMapping(value = "/queryIMSystemConfByPage", method = RequestMethod.POST)
    public Response queryIMSystemConfByPage(@RequestBody @Valid Page<IMSystemConf> page) {
        Response resp = new Response();
        resp = iSystemConfService.queryIMSystemConfByPage(page);
        return resp;
    }
}
