package com.fise.service.answer.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AgreeMapper;
import com.fise.dao.AnswerMapper;
import com.fise.dao.CommentMapper;
import com.fise.dao.IMMarkMapper;
import com.fise.dao.IMRelationShipMapper;
import com.fise.dao.IMUserMapper;
import com.fise.dao.MyAnswerMapper;
import com.fise.dao.MyConcernMapper;
import com.fise.dao.MyProblemMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.dao.SensitiveWordsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Agree;
import com.fise.model.entity.AgreeExample;
import com.fise.model.entity.Answer;
import com.fise.model.entity.AnswerExample;
import com.fise.model.entity.IMMark;
import com.fise.model.entity.IMMarkExample;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.MyAnswer;
import com.fise.model.entity.MyAnswerExample;
import com.fise.model.entity.MyConcern;
import com.fise.model.entity.MyConcernExample;
import com.fise.model.entity.MyProblem;
import com.fise.model.entity.MyProblemExample;
import com.fise.model.entity.AnswerExample.Criteria;
import com.fise.model.entity.Problems;
import com.fise.model.entity.SensitiveWords;
import com.fise.model.entity.SensitiveWordsExample;
import com.fise.model.result.AnswerResult;
import com.fise.service.answer.IAnswerService;
import com.fise.service.sensitiveword.ISensitivewordService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;
import redis.clients.jedis.Jedis;

@Service
public class AnswerServiceImpl implements IAnswerService{
    
    @Autowired
    AnswerMapper answerDao;
    
    @Autowired
    ProblemsMapper problemDao;
    
    @Autowired
    IMUserMapper userDao;
    
    @Autowired
    AgreeMapper agreeDao;
    
    @Autowired
    IMRelationShipMapper relationShipDao;
    
    @Autowired
    SensitiveWordsMapper sensitiveWordsDao;
    
    @Autowired
    CommentMapper commentDao;
    
    @Autowired
    MyAnswerMapper MyAnswerDao;
    
    @Autowired
    IMMarkMapper imMarkDao;
    
    @Autowired
    MyProblemMapper MyProblemDao;
    
    @Autowired
    MyConcernMapper myConcernDao;
    
    @Autowired
    ISensitivewordService sensitivewordService;
    
    @Override
    public Response insertAnswer(Answer record) {
        Response res = new Response();
        
        //检测敏感词
        List<String> listWords = sensitivewordService.checkSensitiveWord(record.getContent());
        if(listWords.size() != 0){
            res.failure(ErrorCode.ERROR_SENSITIVEWORDS_EXISTED);
            res.setMsg("语句中包含敏感词的个数为：" + listWords.size() + "。包含：" + listWords.toString());
            return res;
        }
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        record.setCreated(DateUtil.getLinuxTimeStamp());
        
        answerDao.insertSelective(record);
        
        Problems problems = problemDao.selectByPrimaryKey(record.getProblemId());
        problems.setAnswerNum(problems.getAnswerNum()+1);
        problems.setUpdated(DateUtil.getLinuxTimeStamp());
        problemDao.updateByPrimaryKey(problems);
        
        AnswerExample example = new AnswerExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(record.getUserId());
        example.setOrderByClause("created desc");
        
        List<Answer> list=answerDao.selectByExample(example);
        Answer answer=list.get(0);
        
        Jedis jedis=null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            String key=answer.getId()+"agree";
            String value=answer.getAgreeNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            key=answer.getId()+"comment";
            String value1=answer.getCommentNum()+"";
            jedis.set(key, value1);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            //存入数据库
            MyAnswer myAnswer = new MyAnswer();
            myAnswer.setAnswerId(answer.getId());
            myAnswer.setUserId(answer.getUserId());
            myAnswer.setAgreeNum(Integer.valueOf(value));
            myAnswer.setCommentNum(Integer.valueOf(value1));
            myAnswer.setUpdated(DateUtil.getLinuxTimeStamp());
            myAnswer.setCreated(DateUtil.getLinuxTimeStamp());
            MyAnswerDao.insertSelective(myAnswer);
            
        } catch (Exception e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        return res.success();
    }

    @Override
    public Response queryMyAnswer(Page<Answer> page) {
        Response res = new Response();
        
        AnswerExample example = new AnswerExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andUserIdEqualTo(page.getParam().getUserId());
        
        example.setOrderByClause("created desc");
        
        List<Answer> list=answerDao.selectBypage(example, page);
        
        //查询用户昵称和头像
        IMUser user=userDao.selectByPrimaryKey(page.getParam().getUserId());
        
        Page<AnswerResult> reslut = new Page<AnswerResult>();
        
        reslut.setPageNo(page.getPageNo());
        reslut.setPageSize(page.getPageSize());
        reslut.setTotalCount(page.getTotalCount());
        reslut.setTotalPageCount(page.getTotalPageCount());
        
        Jedis jedis=null;
        List<AnswerResult> listResult=new ArrayList<>();
        
        for(Answer answer:list){
            try {
                jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
                AnswerResult aResult = new AnswerResult();
                
                MyAnswerExample example2 = new MyAnswerExample();
                MyAnswerExample.Criteria criteria2 = example2.createCriteria();
                criteria2.andAnswerIdEqualTo(answer.getId());
                criteria2.andUserIdEqualTo(answer.getUserId());
                criteria2.andStatusEqualTo(1);
                MyAnswer myAnswer=MyAnswerDao.selectByExample(example2).get(0);
                
                //先在redis中查找，如果没有再从数据库找
                String key=answer.getId()+"agree";
                String value=jedis.get(key);
                if(!StringUtil.isEmpty(value)){
                    aResult.setAddAgreeCount(answer.getAgreeNum()-Integer.valueOf(value));
                }else {
                    aResult.setAddAgreeCount(answer.getAgreeNum()-myAnswer.getAgreeNum());
                }
                
                key=answer.getId()+"comment";
                value=jedis.get(key);
                if(!StringUtil.isEmpty(value)){
                    aResult.setAddCommentCount(answer.getCommentNum()-Integer.valueOf(value));
                }else {
                    aResult.setAddCommentCount(answer.getCommentNum()-myAnswer.getCommentNum());
                }
                               
                setResult(aResult,answer,user);
                
                listResult.add(aResult);
            } catch (Exception e) {
                //e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
            } 
        }
              
        reslut.setResult(listResult);
       
        return res.success(reslut);
     
    }

    @Override
    public Response queryAnswerById(Page<Answer> page,String order) {
        Response res = new Response();
        
        AnswerExample example = new AnswerExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        
        if(page.getParam().getProblemId()!=null){
            criteria.andProblemIdEqualTo(page.getParam().getProblemId());
        }
        
        example.setOrderByClause(order+" desc");
        
        //查询好友回答
        List<Integer> userlist=relationShipDao.findrelation(page.getParam().getUserId());
        //判断好友是否为空
        if(userlist.size()==0){
            userlist=new ArrayList<Integer>();
        }
        userlist.add(page.getParam().getUserId());
        criteria.andUserIdIn(userlist);
        
        List<Answer> list=answerDao.selectBypage(example, page);
        
        List<AnswerResult> list1=new ArrayList<>();
        
        for(Answer answer:list){
            AnswerResult result=new AnswerResult();
            
            //查询用户昵称和头像
            IMUser user=userDao.selectByPrimaryKey(answer.getUserId());
            
            //先在备注昵称表里查询备注信息
            IMMarkExample example1 = new IMMarkExample();
            IMMarkExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andFromUserEqualTo(page.getParam().getUserId());
            criteria1.andDestUserEqualTo(answer.getUserId());
            criteria1.andMarkTypeEqualTo(0);
            criteria1.andStatusEqualTo(1);
            List<IMMark> list2=imMarkDao.selectByExample(example1);
            if(list2.size()!=0){
                if(!StringUtil.isEmpty(list2.get(0).getMarkName())){
                    user.setNick(list2.get(0).getMarkName());
                }
            }
            setResult(result,answer,user);
            
            list1.add(result);
        }
        
        Page<AnswerResult> reslut = new Page<AnswerResult>();
        
        reslut.setPageNo(page.getPageNo());
        reslut.setPageSize(page.getPageSize());
        reslut.setTotalCount(page.getTotalCount());
        reslut.setTotalPageCount(page.getTotalPageCount());
        reslut.setResult(list1);
       
        return res.success(reslut);
    }

    @Override
    public Response query(Integer answer_id,Integer user_id) {
        Response res = new Response();
        AnswerResult result=new AnswerResult();
        
        Answer answer =answerDao.selectByPrimaryKey(answer_id);
        
        //判断该问题是否已经点赞
        Integer i=0;       //i=0 表示未点赞    i=1  表示已点赞
        AgreeExample example = new AgreeExample();
        AgreeExample.Criteria criteria=example.createCriteria();
        criteria.andAnswerIdEqualTo(answer_id);
        criteria.andUserIdEqualTo(user_id);
        List<Agree> list=agreeDao.selectByExample(example);
        
        if(list.size()!=0){
            i=list.get(0).getStatus()==1?1:0;
        }else {
            i=0;
        }
                
        if(answer.getUserId().intValue()!=user_id.intValue()){
            //查询用户昵称和头像
            IMUser user=userDao.selectByPrimaryKey(answer.getUserId());
            
            //先在备注昵称表里查询备注信息
            IMMarkExample example1 = new IMMarkExample();
            IMMarkExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andFromUserEqualTo(user_id);
            criteria1.andDestUserEqualTo(answer.getUserId());
            criteria1.andMarkTypeEqualTo(0);
            criteria1.andStatusEqualTo(1);
            List<IMMark> list2=imMarkDao.selectByExample(example1);
            if(list2.size()!=0){
                if(!StringUtil.isEmpty(list2.get(0).getMarkName())){
                    user.setNick(list2.get(0).getMarkName());
                }
            }
            
            setResult(result,answer,user);
            result.setStatus(i);
            return res.success(result);
        }
        
        Jedis jedis = null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            
            String key=answer.getId()+"agree";
            String value=answer.getAgreeNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            key=answer.getId()+"comment";
            String value1=answer.getCommentNum()+"";
            jedis.set(key, value1);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            //修改数据存入数据库
            MyAnswerExample example2 = new MyAnswerExample();
            MyAnswerExample.Criteria criteria2 = example2.createCriteria();
            criteria.andAnswerIdEqualTo(answer.getId());
            criteria.andUserIdEqualTo(answer.getUserId());
            MyAnswer myAnswer=MyAnswerDao.selectByExample(example2).get(0);
            
            myAnswer.setAgreeNum(Integer.valueOf(value));
            myAnswer.setCommentNum(Integer.valueOf(value1));
            myAnswer.setUpdated(DateUtil.getLinuxTimeStamp());
            MyAnswerDao.updateByPrimaryKeySelective(myAnswer);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        //查询用户昵称和头像
        IMUser user=userDao.selectByPrimaryKey(user_id);
        
        setResult(result,answer,user);
        result.setStatus(i);
        return res.success(result);
    }
    
    @Override
    public Response delMyAnswer(Integer answer_id){
        Response resp = new Response();
        //根据回答id，查询到该回答，修改回答的状态status为0
        Answer answer = answerDao.selectByPrimaryKey(answer_id);
        if(answer==null){
            return resp.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
        answer.setStatus(0);
        answer.setUpdated(DateUtil.getLinuxTimeStamp());
        answerDao.updateByPrimaryKey(answer);
        //回答的问题的回答数-1
        Problems problem =problemDao.selectByPrimaryKey(answer.getProblemId());
        problem.setAnswerNum(problem.getAnswerNum()-1);
        problem.setUpdated(DateUtil.getLinuxTimeStamp());
        problemDao.updateByPrimaryKey(problem);
        
        //根据回答的id，修改评论的状态status为0
        commentDao.updateList1(answer_id);
        
        //redis和数据库里myproblem的回答数-1
        Jedis jedis = null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER); 
            //修改myproblem的redis
            String key=problem.getId()+"answermy";
            String value=problem.getAnswerNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            key=problem.getId()+"browsermy";
            String value1=problem.getBrowseNum()+"";
            jedis.set(key, value1);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            //修改myconcern的redis
            key=problem.getId()+"answer"+problem.getUserId();
            String value3=problem.getAnswerNum()+"";
            jedis.set(key, value3);
            
            //修改myconcern的redis
            key=problem.getId()+"browser"+problem.getUserId();
            String value4=problem.getBrowseNum()+"";
            jedis.set(key, value4);
            
            //修改myconcern数据存入数据库
            MyConcernExample example1 = new MyConcernExample();
            MyConcernExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andProblemIdEqualTo(problem.getId());
            criteria1.andUserIdEqualTo(problem.getUserId());
            MyConcern myConcern = myConcernDao.selectByExample(example1).get(0);
            
            myConcern.setAnswerNum(Integer.valueOf(value3));
            myConcern.setBrowserNum(Integer.valueOf(value4));
            myConcern.setUpdated(DateUtil.getLinuxTimeStamp());
            myConcernDao.updateByPrimaryKeySelective(myConcern);
            
            //修改myproblem数据存入数据库
            MyProblemExample example = new MyProblemExample();
            MyProblemExample.Criteria criteria = example.createCriteria();
            criteria.andProblemIdEqualTo(problem.getId());
            criteria.andUserIdEqualTo(problem.getUserId());
            MyProblem myProblem=MyProblemDao.selectByExample(example).get(0);
            
            myProblem.setAnswerNum(Integer.valueOf(value));
            myProblem.setBrowserNum(Integer.valueOf(value1));
            myProblem.setUpdated(DateUtil.getLinuxTimeStamp());
            MyProblemDao.updateByPrimaryKeySelective(myProblem);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        return resp.success();
    }
    
    private void setResult(AnswerResult result,Answer answer,IMUser user){
        result.setId(answer.getId());
        result.setUserId(answer.getUserId());
        result.setProblemId(answer.getProblemId());
        result.setContent(answer.getContent());
        result.setAgreeNum(answer.getAgreeNum());
        result.setCommentNum(answer.getCommentNum());
        result.setStatus(answer.getStatus());
        result.setUpdated(answer.getUpdated());
        result.setCreated(answer.getCreated());
        result.setNick(user.getNick());
        result.setAvatar(user.getAvatar());
    }

    @Override
    public Response queryBack(Page<Answer> param) {
        Response resp = new Response();
        
        AnswerExample example = new AnswerExample();
        AnswerExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("created desc");
        
        if(param.getParam().getUserId()!=null){
            criteria.andUserIdEqualTo(param.getParam().getUserId());
        }
        if(param.getParam().getProblemId()!=null){
            criteria.andProblemIdEqualTo(param.getParam().getProblemId());
        }
        if(!StringUtil.isEmpty(param.getParam().getNick())){
        	criteria.andNickLike("%" + param.getParam().getNick()  + "%");
        }
        if(StringUtil.isNotEmpty(param.getParam().getTitle())){
            criteria.andTitleLike("%" + param.getParam().getTitle() + "%");
        }
        if(StringUtil.isNotEmpty(param.getParam().getContent())){
            criteria.andContentLike("%" + param.getParam().getContent() + "%");
        }
        if (StringUtil.isNotEmpty(param.getOrderby())) {
        	example.setOrderByClause(param.getOrderby());
		} else {
			example.setOrderByClause("created desc");
		}
        List<Answer> list=answerDao.selectBypage(example, param);
        param.setParam(null);
        param.setResult(list);
        resp.success(param);
        return resp;
    }

    @Override
    public Response update(Answer param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        answerDao.updateByPrimaryKeySelective(param);
        
        resp.success();
        return resp;
    }

}
