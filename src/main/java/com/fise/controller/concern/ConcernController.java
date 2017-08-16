package com.fise.controller.concern;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.entity.Concern;
import com.fise.service.concern.IConcernService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/concern")
public class ConcernController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    IConcernService concernService;
    
    /*用户点击关注*/
    @RequestMapping(value="/change",method=RequestMethod.POST)
    public Response changeConcern(@RequestBody @Valid Concern record){
        Response res = new Response();
        logger.info(record.toString());
        
        if(StringUtil.isEmpty(record.getName()) || record.getProblemId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=concernService.addConcern(record);
        return res;
    }
    
    /*查询用户是否关注*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response queryConcern(@RequestBody @Valid Concern record){
        Response res = new Response();
        logger.info(record.toString());
        
        if(StringUtil.isEmpty(record.getName()) || record.getProblemId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=concernService.queryConcern(record);
        return res;
    }
}
