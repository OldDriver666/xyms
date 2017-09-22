package com.fise.controller.event;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.param.EventQueryParam;
import com.fise.service.auth.IAuthService;
import com.fise.service.event.IEventService;

@RestController
@RequestMapping("/boss")
public class EventController {
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Resource
	IEventService eventSvr;
	
	@Resource
	IAuthService authService;
	
	/*查询设备事件*/
	@RequestMapping(value="/event/query",method=RequestMethod.POST)
	public Response queryEvent(@RequestBody @Valid Page<EventQueryParam> param){
		
		Response response=new Response();
		logger.info(param.toString());
		response=eventSvr.query(param);
		
		return response;
	}

}
