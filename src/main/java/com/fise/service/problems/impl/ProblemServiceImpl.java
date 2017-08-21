package com.fise.service.problems.impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.ProblemsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Problems;
import com.fise.model.entity.ProblemsExample;
import com.fise.model.entity.ProblemsExample.Criteria;
import com.fise.model.result.ProblemResult;
import com.fise.service.problems.IProblemService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

import redis.clients.jedis.Jedis;



@Service
public class ProblemServiceImpl implements IProblemService{

    @Autowired
    ProblemsMapper problemsDao;
    
    @Override
    public Response insert(Problems record) {
        Response res = new Response();
        
        if(StringUtil.isEmpty(record.getName())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(record.getTitle())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(record.getContent()) && StringUtil.isEmpty(record.getPicture())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("内容不能为空");
            return res;
        }
        
        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        problemsDao.insertSelective(record);
        
        ProblemsExample example = new ProblemsExample();
        Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(record.getName());
        example.setOrderByClause("created desc");
        
        List<Problems> list=problemsDao.selectByExample(example);
        Problems problem=list.get(0);
        
        Jedis jedis=null;
        try {
            //在redis里存入该问题的浏览量
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            String key = problem.getId()+"browsermy";
            String value = problem.getBrowseNum()+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
            
            //在redis里存入该问题的回答量
            key = problem.getId()+"answermy";
            value=problem.getAnswerNum()+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        
        return res.success();
    }

    @Override
    public Response queryAll(Page<Problems> param) {
        Response res = new Response();
        
        ProblemsExample example = new ProblemsExample();
        Criteria criteria=example.createCriteria();        
        example.setOrderByClause("created desc");
        /*param.setPageSize(10);*/
        
        criteria.andStatusEqualTo(1);
        
        List<Problems> list=problemsDao.selectBypage(example, param);
        
        for(Problems problem:list){
            problem.setBrowseNum(problem.getBrowseNum()+1);
            problemsDao.updateByPrimaryKeySelective(problem);
        }
        
        Page<Problems> page = new Page<Problems>();
        
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        page.setResult(list);
       
        return res.success(page);
    }

    @Override
    public Response queryTitle(Page<Problems> param) {
        Response res = new Response();
        
        /*param.setPageSize(10);*/
        
        List<Problems> list=problemsDao.querytitle(param,param.getParam().getTitle());
        
        if(list.size()==0){
            res.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
            res.setMsg("没有查询到相关记录");
            return res;
        }
        
        for(Problems problem:list){
            problem.setBrowseNum(problem.getBrowseNum()+1);
            problemsDao.updateByPrimaryKeySelective(problem);
        }
        
        Page<Problems> page = new Page<Problems>();
        
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        page.setResult(list);
       
        return res.success(page);
  
    }

    @Override
    public Response queryMypro(Page<Problems> param) {
        Response res = new Response();
        
        ProblemsExample example = new ProblemsExample();
        Criteria criteria=example.createCriteria();        
        example.setOrderByClause("created desc");
        /*param.setPageSize(10);*/
        
        if(!StringUtil.isEmpty(param.getParam().getName())){
            criteria.andNameEqualTo(param.getParam().getName());
        }
        
        criteria.andStatusEqualTo(1);
        
        List<Problems> list=problemsDao.selectBypage(example, param);
        
        Page<ProblemResult> page = new Page<ProblemResult>();
        
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        
        Jedis jedis=null;
        List<ProblemResult> listResult = new ArrayList<>();
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            for(Problems problem:list){
                ProblemResult pResult=new ProblemResult();
                
                String key=problem.getId()+"answermy";
                String value=jedis.get(key);
                
                pResult.setAddAnswerCount(problem.getAnswerNum()-Integer.valueOf(value));
                
                
                key=problem.getId()+"browsermy";
                value=jedis.get(key);
                
                pResult.setAddBrowseCount(problem.getBrowseNum()-Integer.valueOf(value));
                pResult.setProblems(problem);
                listResult.add(pResult);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        page.setResult(listResult);
        
        return res.success(page);
    }

    @Override
    public Response query(Integer problem_id) {
        Response res = new Response();
        
        Problems problem=problemsDao.selectByPrimaryKey(problem_id);
        
        Jedis jedis = null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            String key=problem.getId()+"answermy";
            String value=problem.getAnswerNum()+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
            
            key=problem.getId()+"browsermy";
            value=problem.getBrowseNum()+"";
            jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        res.success(problem);
        return res;
    }

}
