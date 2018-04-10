package com.fise.controller.complainttype;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.model.entity.ComplaintType;
import com.fise.service.auth.IAuthService;
import com.fise.service.complainttype.IComplaintTypeService;
import com.fise.utils.JsonUtil;

@RestController
@RequestMapping("/boss/complainttype")
public class ComplaintTypeController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource 
    IComplaintTypeService complaintTypeSvr;
    
    @Resource
    IAuthService authService;
    
    @IgnoreAuth
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody @Valid ComplaintType param){

        Response resp = new Response();
        resp.success(complaintTypeSvr.queryComplaintType(param));

        logger.info("获取投诉类型:"+JsonUtil.toJson(param));
        return resp;
    }
    
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response insert(@RequestBody @Valid ComplaintType param){
        Response resp = new Response();
        
        resp = complaintTypeSvr.insertComplaintType(param);
        logger.info("新增投诉类型:"+JsonUtil.toJson(param)+"结果:" + resp.getMsg());
        return resp;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody @Valid ComplaintType param){
        Response resp = new Response();
        
        resp = complaintTypeSvr.updateComplaintType(param);
        
        logger.info("修改投诉类型:"+param.toString()+" 结果:"+resp.getMsg());
        return resp;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody @Valid ComplaintType param){
        Response resp = new Response();
        
        resp = complaintTypeSvr.deleteComplaintType(param.getId());

        return resp;
    }
}
