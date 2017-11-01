package com.fise.controller.adminstrator;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fise.base.Response;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.model.param.DeveloperInsert;
import com.fise.model.param.DeveloperUpdate;
import com.fise.service.administrator.IDeveloperService;

@RestController
@RequestMapping("/boss/developer")
public class DeveloperController {
	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	IDeveloperService devservice;

	@IgnoreAuth
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response register(@RequestBody @Valid DeveloperInsert developer,
			                 @RequestParam("images") MultipartFile[] uploadfile) {
		Response response=new Response();
		logger.info(developer.toString());
		response=devservice.insert(developer, uploadfile);
		return response;
	}
	
	@IgnoreAuth
	@RequestMapping(value = "/checkup", method = RequestMethod.POST)
	public Response checkup(@RequestBody @Valid DeveloperUpdate developer){
		Response response=new Response();
		logger.info(developer.toString());
		response=devservice.update(developer);
		return response;
	}
	
	@IgnoreAuth
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Response query(@RequestBody @Valid Map<String , Object> map){
		Response response=new Response();
		logger.info(map.toString());
		Integer id=(Integer) map.get("developer_id");
		response=devservice.query(id);
		return response;
	}

}
