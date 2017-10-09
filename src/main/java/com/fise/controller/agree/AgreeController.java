package com.fise.controller.agree;

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
import com.fise.model.entity.Agree;
import com.fise.service.agree.IAgreeService;

@RestController
public class AgreeController {
    private Logger logger =Logger.getLogger(getClass());
    
    @Resource
    IAgreeService agreeService;
    
    /*点击赞同  取消赞同*/
    @IgnoreAuth
    @RequestMapping(value="/agree",method=RequestMethod.POST)
    public Response bottonAgree(@RequestBody @Valid Agree agree){
        Response res = new Response();
        logger.info(agree.toString());
        
        if(agree.getUserId()==null && agree.getAnswerId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=agreeService.addAgree(agree);
        return res;
    }
    
    /*后台管理   点赞查询*/
    @RequestMapping(value="/agree/queryback",method=RequestMethod.POST)
    public Response queryBack(@RequestBody @Valid Page<Agree> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=agreeService.queryBack(param);
        return resp;
    }
    
    /*后台管理    点赞更新*/
    @RequestMapping(value="/agree/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid Agree agree){
        Response resp = new Response();
        logger.info(agree.toString());
        
        if(agree.getId()==null) return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        
        resp=agreeService.update(agree);
        return resp;
    }
}
