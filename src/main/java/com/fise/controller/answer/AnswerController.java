package com.fise.controller.answer;

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
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.model.entity.Answer;
import com.fise.service.answer.IAnswerService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    IAnswerService answerService;
    
    /*提交回答*/
    @IgnoreAuth
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response addAnswer(@RequestBody @Valid Answer record){
        Response res = new Response();
        logger.info(record.toString());
        
        if(record.getUserId()==null || record.getProblemId()==null || StringUtil.isEmpty(record.getContent())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=answerService.insertAnswer(record);
        return res;
    }
    
    /*查询我的回答*/
    @IgnoreAuth
    @RequestMapping(value="/querymy",method=RequestMethod.POST)
    public Response queryMyAnswer(@RequestBody @Valid Page<Answer> page){
        Response res = new Response();
        logger.info(page.toString());
        
        if(page.getParam().getUserId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=answerService.queryMyAnswer(page);
        return res;
    }
    
    /*查询问题的回答   */
    @IgnoreAuth
    @RequestMapping(value="/querybyid",method=RequestMethod.POST)
    public Response queryById(@RequestBody @Valid Map<String, String> map){
        Response res = new Response();
        logger.info(map.toString());
        
        Page<Answer> page = new Page<Answer>();
        Answer answer = new Answer();
        page.setParam(answer);
        
        page.setPageNo(Integer.valueOf(map.get("page_no")));
        
        if(map.get("user_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        answer.setUserId(Integer.valueOf(map.get("user_id")));
        
        if(map.get("problem_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        page.getParam().setProblemId(Integer.valueOf(map.get("problem_id")));
        
        String order=map.get("order");
        res=answerService.queryAnswerById(page,order);
        return res;
    }
    
    /*根据回答ID，查询更新消息*/
    @IgnoreAuth
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Map<String, Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("answer_id")==null || map.get("user_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=answerService.query(map.get("answer_id"),map.get("user_id"));
        return res;
    }
    
    /*根据回答ID，删除我的回答*/
    @IgnoreAuth
    @RequestMapping(value="/delmyanswer",method=RequestMethod.POST)
    public Response delMyAnswer(@RequestBody @Valid Map<String, Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("answer_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        res=answerService.delMyAnswer(map.get("answer_id"));
        return res;
    }
    
    /*后台管理  查询回答信息*/
    @RequestMapping(value="/queryback",method=RequestMethod.POST)
    public Response queryBack(@RequestBody @Valid Page<Answer> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=answerService.queryBack(param);
        return resp;
    }
    
    /*后台管理  更新回答信息*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid Answer param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        resp=answerService.update(param);
        return resp;
    }
}
