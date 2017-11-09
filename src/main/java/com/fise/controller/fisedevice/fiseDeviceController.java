package com.fise.controller.fisedevice;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.ExcelFiseDevice;
import com.fise.model.entity.FiseDevice;
import com.fise.model.param.QueryFiseDeviceParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.fisedevice.IFiseDeviceService;
import com.fise.utils.StringUtil;
import com.fise.utils.excel.ExcelImporterUtils;


@RestController
@RequestMapping("/boss/fisedevice")
public class fiseDeviceController {
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Resource
	IFiseDeviceService fiseDeviceService;
	
	@Resource
	IAuthService authService;
	
	/*添加fise设备*/
	@RequestMapping(value="/addfisedevice",method=RequestMethod.POST)
	public Response addFiseDevice(@RequestBody @Valid FiseDevice param){
		
		Response response=new Response();
		
		if(!authService.inserAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(StringUtil.isEmpty(param.getIme())||StringUtil.isEmpty(param.getAccount())||param.getType()==null||param.getDepartid()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=fiseDeviceService.insertFiseDevice(param);
		
		
		return response;
	}
	
	/*查询fise设备  设备IME号    账号account*/
	@RequestMapping(value="/queryfisedevice",method=RequestMethod.POST)
	public Response queryFiseDevice(@RequestBody @Valid Page<QueryFiseDeviceParam> page){
		
		Response response=new Response();
		logger.info(page.toString());
		
		if(page.getParam().getDepartid()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=fiseDeviceService.queryFiseDevice(page);
		
		
		return response;
	}
	
	/*删除fise设备*/
	@RequestMapping(value="/delfisedevice",method=RequestMethod.POST)
	public Response delFiseDevice(@RequestBody @Valid QueryFiseDeviceParam param){
		
		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getFiseId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=fiseDeviceService.delFiseDevice(param);
		
		
		return response;
	}
	
	/*修改fise设备信息*/
	@RequestMapping(value="/updatefisedevice",method=RequestMethod.POST)
	public Response updateFiseDevice(@RequestBody @Valid FiseDevice param){

		Response response=new Response();
		
		if(!authService.updateAuth()){
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
		
		logger.info(param.toString());
		
		if(param.getId()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
		
		response=fiseDeviceService.updateFiseDevice(param);
		
		
		return response;
	}
	
	/*excel批量导入设备*/
	@RequestMapping(value="/excel_import",method=RequestMethod.POST)
	public Response excelImport(HttpServletRequest req) throws IOException,Exception{
	    Response resp = new Response();
	    
	    MultipartHttpServletRequest request=(MultipartHttpServletRequest) req;
	    MultipartFile file=request.getFile("file");
	    
        List<ExcelFiseDevice> unfrozenList = ExcelImporterUtils.importSheet(file.getInputStream(), ExcelFiseDevice.class,ExcelFiseDevice.class.getSimpleName());
        
        if (null == unfrozenList || unfrozenList.size() == 0) {
           resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
           resp.setMsg("Excel 数据为空！！");
           return resp;
        }
        
        resp=fiseDeviceService.insertExcel(unfrozenList);
        return resp;
	}
}
