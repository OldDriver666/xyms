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
import com.fise.dao.IMUserMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Answer;
import com.fise.model.entity.Comment;
import com.fise.model.entity.CommentExample;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.IMUserExample;
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
    
    @Autowired
    IMUserMapper userDao;
    
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
        answer.setUpdated(DateUtil.getLinuxTimeStamp());
        answerDao.updateByPrimaryKeySelective(answer);
        
        Problems problem=problemDao.selectByPrimaryKey(record.getProblemId());
        problem.setAnswerNum(problem.getAnswerNum()+1);
        problem.setUpdated(DateUtil.getLinuxTimeStamp());
        problemDao.updateByPrimaryKeySelective(problem);
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        
        criteria.andFromUseridEqualTo(record.getFromUserid());
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
        List<CommentResult> list1=new ArrayList<>();
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        
        if(page.getParam().getAnswerId()!=null){
            criteria.andAnswerIdEqualTo(page.getParam().getAnswerId());
        }
        
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("created desc");
        
        List<Comment> list=commentDao.selectByPage(example, page);
        
        Page<CommentResult> param=new Page<>();
        
        param.setPageNo(page.getPageNo());
        param.setPageSize(page.getPageSize());
        param.setTotalCount(page.getTotalCount());
        param.setTotalPageCount(page.getTotalPageCount());
        
        for(Comment comment:list){
            CommentResult result=new CommentResult();
            
            setNick(comment,result);
            list1.add(result);
        }
        
        param.setResult(list1);
        res.success(param);
        return res;
    }

    @Override
    public Response queryMyComment(Page<Comment> page) {
        Response res = new Response();
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("created desc");
        
        if(page.getParam().getFromUserid()!=null){
            criteria.andFromUseridEqualTo(page.getParam().getFromUserid());
        }
        
        List<Comment> list=commentDao.selectByPage(example, page);
        
        Page<CommentResult> param = new Page<>();
        
        param.setPageNo(page.getPageNo());
        param.setPageSize(page.getPageSize());
        param.setTotalCount(page.getTotalCount());
        param.setTotalPageCount(page.getTotalPageCount());
        
        Jedis jedis=null;
        List<CommentResult> listresult = new ArrayList<>();
        
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            for(Comment c:list){
                CommentResult result = new CommentResult();
                
                example.clear();            
                Criteria criter = example.createCriteria();
                criter.andCommentIdEqualTo(c.getId());
                                
                long count=commentDao.countByExample(example);
                result.setCount((int)count);
                
                String key =c.getId()+"reply";
                String value=jedis.get(key);
                
                result.setAddreply((int)count-Integer.valueOf(value));
                
                setNick(c,result);
                listresult.add(result);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        param.setResult(listresult);
        return res.success(param);
    }

    @Override
    public Response query(Integer comment_id,Integer page_no,Integer from_userid) {
        Response res = new Response();
        List<CommentResult> list1=new ArrayList<>();
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        example.setOrderByClause("created desc");
        criteria.andCommentIdEqualTo(comment_id);
        criteria.andStatusEqualTo(1);
        
        Page<Comment> page = new Page<>();
        page.setPageNo(page_no);
        
        Comment c=commentDao.selectByPrimaryKey(comment_id);
        
        List<Comment> list=commentDao.selectByPage(example, page);
        
        Page<CommentResult> param = new Page<>();
        
        param.setPageNo(page.getPageNo());
        param.setPageSize(page.getPageSize());
        param.setTotalCount(page.getTotalCount());
        param.setTotalPageCount(page.getTotalPageCount());
        
        for(Comment comment:list){
            CommentResult result=new CommentResult();
            
            setNick(comment,result);
            list1.add(result);
        }
        
        param.setResult(list1);
        
        if(c.getFromUserid()!=from_userid){
            return res.success(param);
        }
        
        Jedis jedis=null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            
            long count=commentDao.countByExample(example);
            String key=comment_id+"reply";
            String value=count+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        
        return res.success(param);
    }

    @Override
    public Response queryById(Integer comment_id) {
        Response res = new Response();
        
        Comment comment=commentDao.selectByPrimaryKey(comment_id);
        
        if(comment.getStatus()==0){
            res.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
            res.setMsg("该评论已经被删除了！！！");
        }
        
        CommentResult result=new CommentResult();
        
        setNick(comment,result);
        
        return res.success(result);        
    }

    private void setNick(Comment comment,CommentResult result){
        //查询用户昵称
        IMUserExample userExample=new IMUserExample();
        IMUserExample.Criteria criteria2 =userExample.createCriteria();
        criteria2.andIdEqualTo(comment.getFromUserid());
        List<IMUser> list2=userDao.selectByExample(userExample);
        IMUser user=list2.get(0);
        
        result.setFromNick(user.getNick());
        result.setFromAvatar(user.getAvatar());
        
        criteria2.andIdEqualTo(comment.getToUserid());
        List<IMUser> list3=userDao.selectByExample(userExample);
        
        //判断是否有回复对象
        if(list3.size()!=0){
            IMUser user1=list3.get(0);
            
            result.setToNick(user1.getNick());
            result.setToAvatar(user1.getAvatar());
        }
        
        result.setId(comment.getId());
        result.setFromUserid(comment.getFromUserid());
        result.setToUserid(comment.getToUserid());
        result.setProblemId(comment.getProblemId());
        result.setAnswerId(comment.getAnswerId());
        result.setCommentId(comment.getCommentId());
        result.setContent(comment.getContent());
        result.setStatus(comment.getStatus());
        result.setUpdated(comment.getUpdated());
        result.setCreated(comment.getCreated());
        
    }
}
