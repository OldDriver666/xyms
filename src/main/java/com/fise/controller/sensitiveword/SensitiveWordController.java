package com.fise.controller.sensitiveword;

import java.util.Map;

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
import com.fise.model.entity.SensitiveWords;
import com.fise.service.sensitiveword.ISensitivewordService;

@RestController
@RequestMapping("/sensitiveword")
public class SensitiveWordController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    ISensitivewordService sensitivewordService;
    
    /*插入敏感词*/
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insert(@RequestBody @Valid SensitiveWords param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=sensitivewordService.insert(param);
        return resp;
    }
    
    /*查询敏感词*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Page<SensitiveWords> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=sensitivewordService.query(param);
        return resp;
    }
    
    /*删除敏感词*/
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Response delete(@RequestBody @Valid Map<String, Integer> map){
        Response resp = new Response();
        logger.info(map.toString());
        
        if(map.get("id")==null){
            resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            resp.setMsg("参数  id 不能为空！！");
            return resp;
        }
        
        resp=sensitivewordService.delete(map.get("id"));
        return resp;
    }
}
