package com.fise.controller.complaints;

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
import com.fise.model.entity.Complaints;
import com.fise.model.entity.Problems;
import com.fise.service.auth.IAuthService;
import com.fise.service.complaints.IComplaintsService;
import com.fise.utils.JsonUtil;

@RestController
@RequestMapping("/boss/complaints")
public class ComplaintsController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource 
    IComplaintsService complaintsSvr;
    
    @Resource
    IAuthService authService;
    
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody @Valid Page<Complaints> param){

        Response resp = new Response();
        resp.success(complaintsSvr.queryComplaints(param));

        logger.info("获取投诉:"+JsonUtil.toJson(param));
        return resp;
    }
    
    @IgnoreAuth
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response insert(@RequestBody @Valid Complaints param){
        Response resp = new Response();
        
        resp = complaintsSvr.insertComplaints(param);
        logger.info("新增投诉:"+JsonUtil.toJson(param)+"结果:" + resp.getMsg());
        return resp;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody @Valid Complaints param){
        Response resp = new Response();
        
        resp = complaintsSvr.updateComplaints(param);
        
        logger.info("修改投诉:"+param.toString()+" 结果:"+resp.getMsg());
        return resp;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody @Valid Complaints param){
        Response resp = new Response();
        
        resp = complaintsSvr.deleteComplaints(param.getId());

        return resp;
    }
}
