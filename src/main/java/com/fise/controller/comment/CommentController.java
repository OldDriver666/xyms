package com.fise.controller.comment;

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
import com.fise.framework.annotation.AuthValid;
import com.fise.framework.annotation.IgnoreAuth;
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
    @AuthValid
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response addComment(@RequestBody @Valid Comment record){
        Response res = new Response();
        logger.info(record.toString());
        
        if(record.getFromUserid()==null || record.getAnswerId()==null || record.getProblemId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(record.getContent())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("内容不能为空");
            return res;
        }
        
        res=commentService.addComment(record);
        return res;
    }
    
    /*查询评论*/
    @AuthValid
    @RequestMapping(value="/querycomment",method=RequestMethod.POST)
    public Response queryComment(@RequestBody @Valid Page<Comment> page){
        Response res = new Response();
        logger.info(page.toString());
        
        if(page.getParam().getAnswerId()==null || page.getParam().getId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=commentService.queryComment(page);
        return res;
    }
    
    /*查询我的评论*/
    @AuthValid
    @RequestMapping(value="/querymy",method=RequestMethod.POST)
    public Response querymy(@RequestBody @Valid Page<Comment> page){
        Response res = new Response();
        logger.info(page.toString());
        
        if(page.getParam().getFromUserid()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=commentService.queryMyComment(page);
        return res;
    }
    
    /*查询评论*/
    @AuthValid
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Map<String, Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("id")==null || map.get("page_no")==null || map.get("from_userid")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=commentService.query(map.get("id"), map.get("page_no"),map.get("from_userid"));
        return res;
    }
    
    /*根据评论id查询评论*/
    @AuthValid
    @RequestMapping(value="/querybyid",method=RequestMethod.POST)
    public Response queryById(@RequestBody @Valid Map<String , Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("id")==null || map.get("user_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=commentService.queryById(map.get("id"),map.get("user_id"));
        return res;
    }
    
    /*根据评论id，删除评论*/
    @AuthValid
    @RequestMapping(value="/delmycom",method=RequestMethod.POST)
    public Response delMyCom(@RequestBody @Valid Map<String, Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("comment_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=commentService.delMyCom(map.get("comment_id"));
        return res;
    }
    
    /*后台管理  评论查询*/
    @RequestMapping(value="/queryback",method=RequestMethod.POST)
    public Response queryBack(@RequestBody @Valid Page<Comment> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=commentService.queryBack(param);
        return resp;
    }
    
    /*后台管理  评论更新*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid Comment param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null) return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        
        resp=commentService.update(param);
        return resp;
    }
}
