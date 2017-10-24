package com.fise.service.concern.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.ConcernMapper;
import com.fise.dao.IMUserMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Concern;
import com.fise.model.entity.ConcernExample;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.ConcernExample.Criteria;
import com.fise.model.result.ProResult;
import com.fise.model.entity.Problems;
import com.fise.model.entity.ProblemsExample;
import com.fise.service.concern.IConcernService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;

import redis.clients.jedis.Jedis;

@Service
public class ConcernServiceImpl implements IConcernService{

    @Autowired
    ConcernMapper concernDao;
    
    @Autowired
    ProblemsMapper problemDao;
    
    @Autowired
    IMUserMapper userDao;
    
    @Override
    public Response addConcern(Concern record) {
        Response res = new Response();
        
        ConcernExample example = new ConcernExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(record.getUserId());
        criteria.andProblemIdEqualTo(record.getProblemId());
        
        List<Concern> list=concernDao.selectByExample(example);
        
        if(list.size()==0){
            record.setUpdated(DateUtil.getLinuxTimeStamp());
            record.setCreated(DateUtil.getLinuxTimeStamp());
            concernDao.insertSelective(record);
            res.success();
            res.setMsg("已关注");
            
            Problems problem=problemDao.selectByPrimaryKey(record.getProblemId());
            
            addRedis(problem,record.getUserId());
            return res;            
        }
        
        Concern concern=list.get(0);
        if(concern.getStatus()==1){
            concern.setStatus(0);
            concern.setUpdated(DateUtil.getLinuxTimeStamp());
            concernDao.updateByPrimaryKeySelective(concern);
            res.success();
            res.setMsg("已取消关注");
            
            Problems problem=problemDao.selectByPrimaryKey(record.getProblemId());
            delRedis(problem,record.getUserId());
            return res;
        }
        
        concern.setStatus(1);
        concern.setUpdated(DateUtil.getLinuxTimeStamp());
        concernDao.updateByPrimaryKeySelective(concern);
        res.success();
        res.setMsg("已关注");
        
        Problems problem=problemDao.selectByPrimaryKey(record.getProblemId());
        addRedis(problem,record.getUserId());
        
        return res;        
    }


    @Override
    public Response queryisConcern(Concern record) {
        Response res = new Response();
        
        ConcernExample example = new ConcernExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(record.getUserId());
        criteria.andProblemIdEqualTo(record.getProblemId());
        
        List<Concern> list=concernDao.selectByExample(example);
        
        if(list.size()==0){
            res.success();
            res.setMsg("未关注");
            return res;       
        }
        
        Concern concern=list.get(0);
        
        if(concern.getStatus()==1){
            res.success();
            res.setMsg("已关注");
            return res;
        }
        
        res.success();
        res.setMsg("未关注");
        return res;
     
    }
    
    private void addRedis(Problems problem,Integer user_id){
        Jedis jedis=null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            
            String key = problem.getId()+"browser"+user_id;
            String value = problem.getBrowseNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            key = problem.getId()+"answer"+user_id;
            value=problem.getAnswerNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
        } catch (Exception e) {               
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
    }
    
    private void delRedis(Problems problem,Integer user_id){
        Jedis jedis=null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            
            String key = problem.getId()+"browser"+user_id;
            jedis.del(key);
            
            key = problem.getId()+"answer"+user_id;
            jedis.del(key);
        } catch (Exception e) {           
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
    }


    @Override
    public Response queryConcerns(Integer user_id,Integer page_no) {
        Response res = new Response();
        
        ConcernExample example = new ConcernExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(user_id);
        criteria.andStatusEqualTo(1);
        
        List<Concern> list=concernDao.selectByExample(example);
        
        List<Integer> listint= new ArrayList<>();
        
        if(listint.size()==0){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("你没有关注任何问题！！");
            return res;
        }
        
        for(Concern c:list){
            listint.add(c.getProblemId());
        }
        
        ProblemsExample proExample = new ProblemsExample();
        ProblemsExample.Criteria cri=proExample.createCriteria();
        cri.andIdIn(listint);
        proExample.setOrderByClause("created desc");
        
        Page<Problems> param = new Page<Problems>();
        param.setPageNo(page_no);
        
        List<Problems> lProblems=problemDao.selectBypage(proExample, param);
        
        Page<ProResult> page = new Page<ProResult>();
        
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        
        Jedis jedis=null;
        List<ProResult> listResult = new ArrayList<>();
        
        for(Problems problem:lProblems){
            try {
                jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
                ProResult pResult=new ProResult();
                
                String key=problem.getId()+"answer"+user_id;
                String value=jedis.get(key);
                
                pResult.setAddAnswerCount(problem.getAnswerNum()-Integer.valueOf(value));
                                
                key=problem.getId()+"browser"+user_id;
                value=jedis.get(key);
                
                pResult.setAddBrowseCount(problem.getBrowseNum()-Integer.valueOf(value));
                
                setResult(pResult,problem);
                
                listResult.add(pResult);
                                    
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }finally {
                RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
            }
        }
               
        page.setResult(listResult);
        
        return res.success(page);
    }


    @Override
    public Response query(Integer user_id,Integer problem_id) {
        Response res = new Response();
        
        Problems problem=problemDao.selectByPrimaryKey(problem_id);
        
        ProResult pResult=new ProResult();
        
        setResult(pResult,problem);
        
        Jedis jedis = null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            String key=problem.getId()+"answer"+user_id;
            String value=problem.getAnswerNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            key=problem.getId()+"browser"+user_id;
            value=problem.getBrowseNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        res.success(pResult);
        return res;
    }
    
    private void setResult(ProResult pResult,Problems problem){
        //查询用户昵称和头像
        IMUser user=userDao.selectByPrimaryKey(problem.getUserId());
        
        pResult.setNick(user.getNick());
        pResult.setAvatar(user.getAvatar());
        
        pResult.setId(problem.getId());
        pResult.setUserId(problem.getUserId());
        pResult.setTitle(problem.getTitle());
        pResult.setContent(problem.getContent());
        pResult.setPicture(problem.getPicture());
        pResult.setStatus(problem.getStatus());
        pResult.setAnswerNum(problem.getAnswerNum());
        pResult.setBrowseNum(problem.getBrowseNum());
        pResult.setUpdated(problem.getUpdated());
        pResult.setCreated(problem.getCreated());
    }


    @Override
    public Response queryBack(Page<Concern> page) {
        Response resp = new Response();
        
        ConcernExample example = new ConcernExample();
        ConcernExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("created desc");
        
        if(page.getParam().getUserId()!=null){
            criteria.andUserIdEqualTo(page.getParam().getUserId());
        }
        if(page.getParam().getProblemId()!=null){
            criteria.andProblemIdEqualTo(page.getParam().getProblemId());
        }
        
        List<Concern> list=concernDao.selectByPage(example, page);
        
        page.setResult(list);
        page.setParam(null);
        resp.success(page);
        return resp;
    }


    @Override
    public Response update(Concern param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        concernDao.updateByPrimaryKeySelective(param);
        
        return resp.success();
    }
}
