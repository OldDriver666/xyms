package com.fise.controller.comment;

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
import com.fise.model.entity.Comment;
import com.fise.service.comment.ICommentService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    ICommentService commentService;
    
    /*发布评论*/
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response addComment(@RequestBody @Valid Comment record){
        Response res = new Response();
        logger.info(record.toString());
        
        if(StringUtil.isEmpty(record.getFromName()) || record.getAnswerId()==null || record.getProblemId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=commentService.addComment(record);
        return res;
    }
    
    /*查询评论*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response queryComment(@RequestBody @Valid Page<Comment> page){
        Response res = new Response();
        logger.info(page.toString());
        
        if(page.getParam().getAnswerId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=commentService.queryComment(page);
        return res;
    }
}
