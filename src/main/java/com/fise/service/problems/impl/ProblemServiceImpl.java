package com.fise.service.problems.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.IMSchoolMapper;
import com.fise.dao.IMUserMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.IMSchool;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.IMUserExample;
import com.fise.model.entity.Problems;
import com.fise.model.entity.ProblemsExample;
import com.fise.model.entity.ProblemsExample.Criteria;
import com.fise.model.result.ProResult;
import com.fise.service.problems.IProblemService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import redis.clients.jedis.Jedis;

@Service
public class ProblemServiceImpl implements IProblemService{

    @Autowired
    ProblemsMapper problemsDao;
    
    @Autowired
    IMUserMapper userDao;
    
    @Autowired
    IMSchoolMapper schoolDao;
    
    @Override
    public Response insert(Problems record) {
        Response res = new Response();
                
        record.setCreated(DateUtil.getLinuxTimeStamp());
        record.setUpdated(DateUtil.getLinuxTimeStamp());
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
        List<ProResult> list1=new ArrayList<>();
        
        ProblemsExample example = new ProblemsExample();
        Criteria criteria=example.createCriteria();        
        example.setOrderByClause("created desc");
        /*param.setPageSize(10);*/
        
        criteria.andStatusEqualTo(1);
        criteria.andSchoolIdEqualTo(param.getParam().getSchoolId());
        
        List<Problems> list=problemsDao.selectBypage(example, param);
                       
        Page<ProResult> page = getResult(list1, list, param);
               
        return res.success(page);
    }

    @Override
    public Response queryTitle(Page<Problems> param) {
        Response res = new Response();
        List<ProResult> list1=new ArrayList<>();
        
        /*param.setPageSize(10);*/
        param.getParam().setTitle("%"+param.getParam().getTitle()+"%");
        List<Problems> list=problemsDao.querytitle(param,param.getParam().getTitle());
        
        if(list.size()==0){
            res.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
            res.setMsg("没有查询到相关记录");
            return res;
        }
        
        Page<ProResult> page=getResult(list1,list,param);
       
        return res.success(page);  
    }

    @Override
    public Response queryMypro(Page<Problems> param) {
        Response res = new Response();
        
        ProblemsExample example = new ProblemsExample();
        Criteria criteria=example.createCriteria();        
        example.setOrderByClause("created desc");
        criteria.andStatusEqualTo(1);
        criteria.andNameEqualTo(param.getParam().getName());
        
        List<Problems> list=problemsDao.selectBypage(example, param);
        
        Page<ProResult> page = new Page<ProResult>();
        
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        
        Jedis jedis=null;
        List<ProResult> listResult = new ArrayList<>();
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            for(Problems problem:list){
                ProResult result=new ProResult();
                
                String key=problem.getId()+"answermy";
                String value=jedis.get(key);
                
                result.setAddAnswerCount(problem.getAnswerNum()-Integer.valueOf(value));
                
                
                key=problem.getId()+"browsermy";
                value=jedis.get(key);
                
                result.setAddBrowseCount(problem.getBrowseNum()-Integer.valueOf(value));
                
                //查询用户昵称和头像
                IMUserExample userExample=new IMUserExample();
                IMUserExample.Criteria criteria2 =userExample.createCriteria();
                criteria2.andNameEqualTo(problem.getName());
                List<IMUser> list2=userDao.selectByExample(userExample);
                IMUser user=list2.get(0);
                
                setResult(result, problem, user);
                
                listResult.add(result);
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
    public Response query(Integer problem_id,String name) {
        Response res = new Response();
        ProResult result=new ProResult();
        
        //查询用户昵称和头像
        IMUserExample userExample=new IMUserExample();
        IMUserExample.Criteria criteria2 =userExample.createCriteria();
        
        
        Problems problem=problemsDao.selectByPrimaryKey(problem_id);
        
        if(!problem.getName().equals(name)){
            criteria2.andNameEqualTo(problem.getName());
            List<IMUser> list2=userDao.selectByExample(userExample);
            IMUser user=list2.get(0);
            
            setResult(result, problem, user);
            
            res.success(result);
            return res;
        }
        
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
        
        criteria2.andNameEqualTo(name);
        List<IMUser> list2=userDao.selectByExample(userExample);
        IMUser user=list2.get(0);
        
        setResult(result, problem, user);
        
        res.success(result);
        return res;
    }

    @Override
    public Response delMyPro(Integer problem_id) {
        Response res = new Response();
        
        
        return null;
    }
    
    private Page<ProResult> getResult(List<ProResult> list1,List<Problems> list,Page<Problems> param){
        for(Problems problem:list){
            ProResult result = new ProResult(); 
            
            problem.setBrowseNum(problem.getBrowseNum()+1);
            problem.setUpdated(DateUtil.getLinuxTimeStamp());
            problemsDao.updateByPrimaryKeySelective(problem);
            
            //查询用户昵称和头像
            IMUserExample userExample=new IMUserExample();
            IMUserExample.Criteria criteria2 =userExample.createCriteria();
            criteria2.andNameEqualTo(problem.getName());
            List<IMUser> list2=userDao.selectByExample(userExample);
            IMUser user=list2.get(0);
            
            //查询学校名字
            IMSchool school=schoolDao.selectByPrimaryKey(param.getParam().getSchoolId());
            
            setResult(result, problem, user);
            result.setSchoolname(school.getSchoolname());
            
            list1.add(result);
        }
               
        Page<ProResult> page = new Page<ProResult>();
        
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        page.setResult(list1);
        
        return page;
    }
    
    private void setResult(ProResult result,Problems problem,IMUser user){
        result.setId(problem.getId());
        result.setName(problem.getName());
        result.setTitle(problem.getTitle());
        result.setContent(problem.getContent());
        result.setPicture(problem.getPicture());
        result.setAnswerNum(problem.getAnswerNum());
        result.setBrowseNum(problem.getBrowseNum());
        result.setStatus(problem.getStatus());
        result.setSchoolId(problem.getSchoolId());
        result.setUpdated(problem.getUpdated());
        result.setCreated(problem.getCreated());
        result.setNick(user.getNick());
        result.setAvatar(user.getAvatar());
    }
}
