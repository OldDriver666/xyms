package com.fise.service.comment.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AnswerMapper;
import com.fise.dao.CommentMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Answer;
import com.fise.model.entity.Comment;
import com.fise.model.entity.CommentExample;
import com.fise.model.entity.CommentExample.Criteria;
import com.fise.model.entity.Problems;
import com.fise.model.result.CommentResult;
import com.fise.service.comment.ICommentService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

import redis.clients.jedis.Jedis;

@Service
public class CommentServiceImpl implements ICommentService{

    @Autowired
    CommentMapper commentDao;
    
    @Autowired
    AnswerMapper answerDao;
    
    @Autowired
    ProblemsMapper problemDao;
    
    @Override
    public Response addComment(Comment record) {
        Response res = new Response();
        
        if(StringUtil.isEmpty(record.getContent())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("内容不能为空");
            return res;
        }
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        record.setCreated(DateUtil.getLinuxTimeStamp());
        
        commentDao.insertSelective(record);
        
        Answer answer=answerDao.selectByPrimaryKey(record.getAnswerId());
        answer.setCommentNum(answer.getCommentNum()+1);
        answerDao.updateByPrimaryKeySelective(answer);
        
        Problems problem=problemDao.selectByPrimaryKey(record.getProblemId());
        problem.setAnswerNum(problem.getAnswerNum()+1);
        problemDao.updateByPrimaryKeySelective(problem);
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        
        criteria.andFromNameEqualTo(record.getFromName());
        example.setOrderByClause("created desc");
        
        List<Comment> list=commentDao.selectByExample(example);
        Comment comment=list.get(0);
        
        Jedis jedis=null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            example.clear();            
            Criteria criter = example.createCriteria();
            criter.andCommentIdEqualTo(comment.getId());
            long count=commentDao.countByExample(example);
            
            String key =comment.getId()+"reply";
            String value=(int)count+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        return res.success();
    }

    @Override
    public Response queryComment(Page<Comment> page) {
        Response res = new Response();
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        
        if(page.getParam().getAnswerId()!=null){
            criteria.andAnswerIdEqualTo(page.getParam().getAnswerId());
        }
        
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("created desc");
        
        List<Comment> list=commentDao.selectByPage(example, page);
        
        page.setResult(list);
        page.setParam(null);
        res.success(page);
        return res;
    }

    @Override
    public Response queryMyComment(Page<Comment> page) {
        Response res = new Response();
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        /*page.setPageSize(10);*/
        example.setOrderByClause("created desc");
        
        if(!StringUtil.isEmpty(page.getParam().getFromName())){
            criteria.andFromNameEqualTo(page.getParam().getFromName());
        }
        
        List<Comment> list=commentDao.selectByPage(example, page);
        
        Page<CommentResult> result = new Page<>();
        
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setTotalCount(page.getTotalCount());
        result.setTotalPageCount(page.getTotalPageCount());
        
        Jedis jedis=null;
        List<CommentResult> listresult = new ArrayList<>();
        
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            for(Comment c:list){
                CommentResult re = new CommentResult();
                
                example.clear();            
                Criteria criter = example.createCriteria();
                criter.andCommentIdEqualTo(c.getId());
                                
                long count=commentDao.countByExample(example);
                
                String key =c.getId()+"reply";
                String value=jedis.get(key);
                
                re.setAddreply((int)count-Integer.valueOf(value));
                re.setComment(c);
                listresult.add(re);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        result.setResult(listresult);
        return res.success(result);
    }

    @Override
    public Response query(Integer comment_id,Integer page_no) {
        Response res = new Response();
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        example.setOrderByClause("created desc");
        
        Jedis jedis=null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            criteria.andCommentIdEqualTo(comment_id);
            
            long count=commentDao.countByExample(example);
            String key=comment_id+"reply";
            String value=count+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        Page<Comment> page = new Page<>();
        page.setPageNo(page_no);
        
        List<Comment> list=commentDao.selectByPage(example, page);
        page.setResult(list);
        
        return res.success(page);
    }

}
