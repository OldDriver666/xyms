package com.fise.service.comment.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AnswerMapper;
import com.fise.dao.CommentMapper;
import com.fise.dao.IMMarkMapper;
import com.fise.dao.IMRelationShipMapper;
import com.fise.dao.IMUserMapper;
import com.fise.dao.MyCommentMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.dao.SensitiveWordsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Answer;
import com.fise.model.entity.Comment;
import com.fise.model.entity.CommentExample;
import com.fise.model.entity.IMMark;
import com.fise.model.entity.IMMarkExample;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.MyComment;
import com.fise.model.entity.MyCommentExample;
import com.fise.model.entity.MyProblem;
import com.fise.model.entity.MyProblemExample;
import com.fise.model.entity.Problems;
import com.fise.model.entity.SensitiveWords;
import com.fise.model.entity.SensitiveWordsExample;
import com.fise.model.entity.CommentExample.Criteria;
import com.fise.model.result.CommentResult;
import com.fise.service.comment.ICommentService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;
import com.fise.utils.sensitiveword.SensitivewordFilter;
import com.sun.org.apache.bcel.internal.generic.NEW;

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
    
    @Autowired
    IMRelationShipMapper relationShipDao;
    
    @Autowired
    SensitiveWordsMapper sensitiveWordsDao;
    
    @Autowired
    MyCommentMapper myCommentDao;
    
    @Autowired IMMarkMapper imMarkDao;
    
    @Override
    public Response addComment(Comment record) {
        Response res = new Response();
        
        //检测敏感词
        SensitiveWordsExample sensitiveWordsExample = new SensitiveWordsExample();
        SensitiveWordsExample.Criteria criteria1 = sensitiveWordsExample.createCriteria();
        List<SensitiveWords> sensitiveWordsList=sensitiveWordsDao.selectByExample(sensitiveWordsExample);
        SensitivewordFilter filter = new SensitivewordFilter(sensitiveWordsList);
        Set<String> set = filter.getSensitiveWord(record.getContent(), 1);
        if(set.size()!=0){
            res.failure(ErrorCode.ERROR_SENSITIVEWORDS_EXISTED);
            res.setMsg("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
            return res;
        }
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        record.setCreated(DateUtil.getLinuxTimeStamp());
        
        commentDao.insertSelective(record);
        
        Answer answer=answerDao.selectByPrimaryKey(record.getAnswerId());
        answer.setCommentNum(answer.getCommentNum()+1);
        answer.setUpdated(DateUtil.getLinuxTimeStamp());
        answerDao.updateByPrimaryKeySelective(answer);
        
        /*Problems problem=problemDao.selectByPrimaryKey(record.getProblemId());
        //问题的回答数=回答数+评论数
        problem.setAnswerNum(problem.getAnswerNum()+1);
        problem.setUpdated(DateUtil.getLinuxTimeStamp());
        problemDao.updateByPrimaryKeySelective(problem);*/
        
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
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            //存入数据库
            MyComment myComment = new MyComment();
            myComment.setCommentId(comment.getId());
            myComment.setUserId(comment.getFromUserid());
            myComment.setCommentNum(Integer.valueOf(value));
            myComment.setUpdated(DateUtil.getLinuxTimeStamp());
            myComment.setCreated(DateUtil.getLinuxTimeStamp());
            myCommentDao.insertSelective(myComment);
            
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
        example.setOrderByClause("created asc");
        //根据好友关系查询
        List<Integer> userlist = relationShipDao.findrelation(page.getParam().getId());
        //判断好友是否为空
        if(userlist.size()==0){
            userlist=new ArrayList<Integer>();
        }
        userlist.add(page.getParam().getId());
        criteria.andFromUseridIn(userlist);
        criteria.andToUseridIn(userlist);
        
        List<Comment> list=commentDao.selectByPage(example, page);
        
        Page<CommentResult> param=new Page<>();
        
        param.setPageNo(page.getPageNo());
        param.setPageSize(page.getPageSize());
        param.setTotalCount(page.getTotalCount());
        param.setTotalPageCount(page.getTotalPageCount());
        
        for(Comment comment:list){
            CommentResult result=new CommentResult();
            
            setNick(comment,result,page.getParam().getId());
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
        
        for(Comment c:list){
            try {
                jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
                CommentResult result = new CommentResult();
                
                example.clear();            
                Criteria criter = example.createCriteria();
                criter.andCommentIdEqualTo(c.getId());
                                
                long count=commentDao.countByExample(example);
                result.setCount((int)count);
                
                MyCommentExample example2=new MyCommentExample();
                MyCommentExample.Criteria criteria2=example2.createCriteria();
                criteria2.andCommentIdEqualTo(c.getId());
                criteria2.andStatusEqualTo(1);
                MyComment myComment=myCommentDao.selectByExample(example2).get(0);
                
                String key =c.getId()+"reply";
                String value=jedis.get(key);
                if(!StringUtil.isEmpty(value)){
                    result.setAddreply((int)count-Integer.valueOf(value));
                }else {
                    result.setAddreply((int)count-myComment.getCommentNum());
                }
                               
                setNick(c,result,page.getParam().getFromUserid());
                listresult.add(result);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
            }
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
            
            setNick(comment,result,from_userid);
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
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            //修改数据库信息
            MyCommentExample example2 = new MyCommentExample();
            MyCommentExample.Criteria criteria2 = example2.createCriteria();
            criteria.andProblemIdEqualTo(comment_id);
            MyComment myComment=myCommentDao.selectByExample(example2).get(0);
            
            myComment.setCommentNum(Integer.valueOf(value));            
            myComment.setUpdated(DateUtil.getLinuxTimeStamp());
            myCommentDao.updateByPrimaryKeySelective(myComment);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        
        return res.success(param);
    }

    @Override
    public Response queryById(Integer comment_id,Integer user_id) {
        Response res = new Response();
        
        Comment comment=commentDao.selectByPrimaryKey(comment_id);
        
        if(comment.getStatus()==0){
            res.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
            res.setMsg("该评论已经被删除了！！！");
        }
        
        CommentResult result=new CommentResult();
        
        setNick(comment,result,user_id);
        
        return res.success(result);        
    }

    private void setNick(Comment comment,CommentResult result,Integer id){
        //查询用户昵称和头像
        IMUser user=userDao.selectByPrimaryKey(comment.getFromUserid());
        
        //先在备注昵称表里查询备注信息
        IMMarkExample example1 = new IMMarkExample();
        IMMarkExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andFromUserEqualTo(id);
        criteria1.andDestUserEqualTo(comment.getFromUserid());
        criteria1.andMarkTypeEqualTo(0);
        criteria1.andStatusEqualTo(1);
        List<IMMark> list2=imMarkDao.selectByExample(example1);
        if(list2.size()!=0 || !StringUtil.isEmpty(list2.get(0).getMarkName())){
            user.setNick(list2.get(0).getMarkName());
        }
        result.setFromNick(user.getNick());
        result.setFromAvatar(user.getAvatar());
        
        IMUser user1=userDao.selectByPrimaryKey(comment.getToUserid());
        
        //先在备注昵称表里查询备注信息
        example1.clear();
        IMMarkExample.Criteria criteria2 = example1.createCriteria();
        criteria2.andFromUserEqualTo(id);
        criteria2.andDestUserEqualTo(comment.getToUserid());
        criteria2.andMarkTypeEqualTo(0);
        criteria1.andStatusEqualTo(1);
        List<IMMark> list3=imMarkDao.selectByExample(example1);
        if(list3.size()!=0 || !StringUtil.isEmpty(list3.get(0).getMarkName())){
            user1.setNick(list3.get(0).getMarkName());
        }
        
        //判断是否有回复对象
        if(user1!=null){
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

    @Override
    public Response queryBack(Page<Comment> page) {
        Response resp = new Response();
        
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("created desc");
        
        if(page.getParam().getAnswerId()!=null){
            criteria.andAnswerIdEqualTo(page.getParam().getAnswerId());
        }
        if(page.getParam().getProblemId()!=null){
            criteria.andProblemIdEqualTo(page.getParam().getProblemId());
        }
        if(page.getParam().getCommentId()!=null){
            criteria.andCommentIdEqualTo(page.getParam().getCommentId());
        }
        
        List<Comment> list=commentDao.selectByPage(example, page);
        
        page.setParam(null);
        page.setResult(list);
        return resp.success(page);
    }

    @Override
    public Response update(Comment param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        commentDao.updateByPrimaryKeySelective(param);
        
        return resp.success();
    }

    @Override
    public Response delMyCom(Integer comment_id) {
        Response resp = new Response();
        //根据评论id，查询到该评论，修改评论的状态status为0
        Comment comment = commentDao.selectByPrimaryKey(comment_id);
        if(comment==null){
            return resp.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
        comment.setStatus(0);
        comment.setUpdated(DateUtil.getLinuxTimeStamp());
        commentDao.updateByPrimaryKey(comment);
        //评论的回答的评论数-1
        Answer answer = answerDao.selectByPrimaryKey(comment.getAnswerId());
        answer.setCommentNum(answer.getCommentNum()-1);
        answer.setUpdated(DateUtil.getLinuxTimeStamp());
        answerDao.updateByPrimaryKey(answer);
        
        return resp.success();
    }
}
