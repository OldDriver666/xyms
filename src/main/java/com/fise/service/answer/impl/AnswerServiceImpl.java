package com.fise.service.answer.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AnswerMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Answer;
import com.fise.model.entity.AnswerExample;
import com.fise.model.entity.AnswerExample.Criteria;
import com.fise.model.entity.Problems;
import com.fise.model.result.AnswerResult;
import com.fise.service.answer.IAnswerService;
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
    
    @Override
    public Response insertAnswer(Answer record) {
        Response res = new Response();
        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        
        answerDao.insertSelective(record);
        
        Problems problems = problemDao.selectByPrimaryKey(record.getProblemId());
        problems.setAnswerNum(problems.getAnswerNum()+1);
        problemDao.updateByPrimaryKey(problems);
        
        AnswerExample example = new AnswerExample();
        Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(record.getName());
        example.setOrderByClause("created desc");
        
        List<Answer> list=answerDao.selectByExample(example);
        Answer answer=list.get(0);
        
        Jedis jedis=null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            String key=answer.getId()+"agree";
            String value=answer.getAgreeNum()+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
            
            key=answer.getId()+"comment";
            value=answer.getCommentNum()+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
        } catch (Exception e) {
            e.printStackTrace();
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
        
        if(!StringUtil.isEmpty(page.getParam().getName())){
            criteria.andNameEqualTo(page.getParam().getName());
        }else{
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        example.setOrderByClause("created desc");
        /*page.setPageSize(10);*/
        
        List<Answer> list=answerDao.selectBypage(example, page);
        
        Page<AnswerResult> reslut = new Page<AnswerResult>();
        
        reslut.setPageNo(page.getPageNo());
        reslut.setPageSize(page.getPageSize());
        reslut.setTotalCount(page.getTotalCount());
        reslut.setTotalPageCount(page.getTotalPageCount());
        
        Jedis jedis=null;
        List<AnswerResult> listResult=new ArrayList<>();
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            for(Answer answer:list){
                AnswerResult aResult = new AnswerResult();
                
                String key=answer.getId()+"agree";
                String value=jedis.get(key);            
                aResult.setAddAgreeCount(answer.getAgreeNum()-Integer.valueOf(value));
                
                key=answer.getId()+"comment";
                value=jedis.get(key);
                aResult.setAddCommentCount(answer.getCommentNum()-Integer.valueOf(value));
                
                aResult.setAnswer(answer);
                listResult.add(aResult);            
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
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
        /*page.setPageSize(10);*/
        
        List<Answer> list=answerDao.selectBypage(example, page);
        
        Page<Answer> reslut = new Page<Answer>();
        
        reslut.setPageNo(page.getPageNo());
        reslut.setPageSize(page.getPageSize());
        reslut.setTotalCount(page.getTotalCount());
        reslut.setTotalPageCount(page.getTotalPageCount());
        reslut.setResult(list);
       
        return res.success(reslut);
    }

    @Override
    public Response query(Integer answer_id) {
        Response res = new Response();
        
        Answer answer =answerDao.selectByPrimaryKey(answer_id);
        
        Jedis jedis = null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            
            String key=answer.getId()+"agree";
            String value=answer.getAgreeNum()+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
            
            key=answer.getId()+"comment";
            value=answer.getCommentNum()+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        return res.success(answer);
    }

}
