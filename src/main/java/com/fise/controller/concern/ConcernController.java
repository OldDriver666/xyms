package com.fise.controller.concern;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.model.entity.Concern;
import com.fise.service.concern.IConcernService;

@RestController
@RequestMapping("/concern")
public class ConcernController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    IConcernService concernService;
    
    /*用户点击关注*/
    @IgnoreAuth
    @RequestMapping(value="/change",method=RequestMethod.POST)
    public Response changeConcern(@RequestBody @Valid Concern record){
        Response res = new Response();
        logger.info(record.toString());
        
        if(record.getUserId()==null || record.getProblemId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=concernService.addConcern(record);
        return res;
    }
    
    /*查询用户是否关注*/
    @IgnoreAuth
    @RequestMapping(value="/isconcern",method=RequestMethod.POST)
    public Response queryisConcern(@RequestBody @Valid Concern record){
        Response res = new Response();
        logger.info(record.toString());
        
        if(record.getUserId()==null || record.getProblemId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=concernService.queryisConcern(record);
        return res;
    }
    
    /*查询用户关注的问题*/
    @IgnoreAuth
    @RequestMapping(value="/queryconcerns",method=RequestMethod.POST)
    public Response queryConcerns(@RequestBody @Valid Map<String, Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("user_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=concernService.queryConcerns(map.get("user_id"),map.get("page_no"));
        return res;
    }
    
    /*根据问题ID，查询关注问题详情*/
    @IgnoreAuth
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Map<String, Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("id")==null || map.get("user_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=concernService.query(map.get("user_id"),map.get("id"));
        return res;
    }
    
}
