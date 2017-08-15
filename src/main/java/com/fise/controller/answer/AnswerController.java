package com.fise.controller.answer;

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
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response addAnswer(@RequestBody @Valid Answer record){
        Response res = new Response();
        logger.info(record.toString());
        
        if(StringUtil.isEmpty(record.getName()) || record.getProblemId()==null || StringUtil.isEmpty(record.getContent())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=answerService.insertAnswer(record);
        return res;
    }
    
    /*查询我的回答*/
    @RequestMapping(value="/querymy",method=RequestMethod.POST)
    public Response queryMyAnswer(@RequestBody @Valid Page<Answer> page){
        Response res = new Response();
        logger.info(page.toString());
        
        res=answerService.queryAnswer(page);
        return res;
    }
}
