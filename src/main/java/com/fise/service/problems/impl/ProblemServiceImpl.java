package com.fise.service.problems.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.IMRelationShipMapper;
import com.fise.dao.IMSchoolMapper;
import com.fise.dao.IMUserMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.dao.SensitiveWordsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Concern;
import com.fise.model.entity.IMSchool;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.Problems;
import com.fise.model.entity.ProblemsExample;
import com.fise.model.entity.ProblemsExample.Criteria;
import com.fise.model.entity.SensitiveWords;
import com.fise.model.entity.SensitiveWordsExample;
import com.fise.model.result.ProResult;
import com.fise.service.problems.IProblemService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.sensitiveword.SensitivewordFilter;

import redis.clients.jedis.Jedis;

@Service
public class ProblemServiceImpl implements IProblemService{

    @Autowired
    ProblemsMapper problemsDao;
    
    @Autowired
    IMUserMapper userDao;
    
    @Autowired
    IMSchoolMapper schoolDao;
    
    @Autowired
    IMRelationShipMapper relationShipDao;
    
    @Autowired
    SensitiveWordsMapper sensitiveWordsDao;
    
    @Override
    public Response insert(Problems record) {
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
        
        //更新时间        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        problemsDao.insertSelective(record);
        
        ProblemsExample example = new ProblemsExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(record.getUserId());
        example.setOrderByClause("created desc");
        
        List<Problems> list=problemsDao.selectByExample(example);
        Problems problem=list.get(0);
        
        //获取problemid和user_id
        Concern concern = new Concern();
        concern.setProblemId(problem.getId());
        concern.setUserId(problem.getUserId());
        
        Jedis jedis=null;
        try {
            //在redis里存入该问题的浏览量
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            String key = problem.getId()+"browsermy";
            String value = problem.getBrowseNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            //在redis里存入该问题的回答量
            key = problem.getId()+"answermy";
            value=problem.getAnswerNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
               
        return res.success(concern);
    }

    @Override
    public Response queryAll(Page<Problems> param) {
        Response res = new Response();
        List<ProResult> list1=new ArrayList<>();
        
        ProblemsExample example = new ProblemsExample();
        Criteria criteria=example.createCriteria();        
        example.setOrderByClause("created desc");
        
        //根据好友关系查询
        criteria.andStatusEqualTo(1);
        List<Integer> userlist=relationShipDao.findrelation(param.getParam().getUserId());
        userlist.add(param.getParam().getUserId());
        criteria.andUserIdIn(userlist);
        
        List<Problems> list=problemsDao.selectBypage(example, param);
                       
        Page<ProResult> page = getResult(list1, list, param);
               
        return res.success(page);
    }

    @Override
    public Response queryTitle(Page<Problems> param) {
        Response res = new Response();
        List<ProResult> list1=new ArrayList<>();
        
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
        criteria.andUserIdEqualTo(param.getParam().getUserId());
        
        List<Problems> list=problemsDao.selectBypage(example, param);
        
        Page<ProResult> page = new Page<ProResult>();
        
        setPage(page,param);
        
        Jedis jedis=null;
        List<ProResult> listResult = new ArrayList<>();
        
        for(Problems problem:list){
            try {
                jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
                ProResult result=new ProResult();
                
                String key=problem.getId()+"answermy";
                String value=jedis.get(key);
                
                result.setAddAnswerCount(problem.getAnswerNum()-Integer.valueOf(value));
                
                
                key=problem.getId()+"browsermy";
                value=jedis.get(key);
                
                result.setAddBrowseCount(problem.getBrowseNum()-Integer.valueOf(value));
                
                //查询用户昵称和头像                
                IMUser user=userDao.selectByPrimaryKey(problem.getUserId());
                
                setResult(result, problem, user);

                listResult.add(result);
                
                //listResult集合排序，addAnswerCount，addBrowseCount降序排列
                /*Collections.sort(listResult, new Comparator<ProResult>() {

                    @Override
                    public int compare(ProResult o1, ProResult o2) {
                        int i=o1.getAnswerNum()-o2.getAnswerNum();
                        
                        if(i==0){
                            return o1.getAddBrowseCount()-o2.getAddBrowseCount();
                        }
                        return i;
                    }
                    
                });*/
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
    public Response query(Integer problem_id,Integer user_id) {
        Response res = new Response();
        ProResult result=new ProResult();
                
        Problems problem=problemsDao.selectByPrimaryKey(problem_id);
        
        //该问题的浏览数+1
        problem.setBrowseNum(problem.getBrowseNum()+1);
        problem.setUpdated(DateUtil.getLinuxTimeStamp());
        problemsDao.updateByPrimaryKeySelective(problem);
        
        if(problem.getUserId()!=user_id){
            //查询用户昵称和头像
            IMUser user=userDao.selectByPrimaryKey(problem.getUserId());
            
            setResult(result, problem, user);
            
            res.success(result);
            return res;
        }
        
        Jedis jedis = null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            String key=problem.getId()+"answermy";
            String value=problem.getAnswerNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            key=problem.getId()+"browsermy";
            value=problem.getBrowseNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        IMUser user=userDao.selectByPrimaryKey(user_id);
        
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
            
            //查询用户昵称和头像
            IMUser user=userDao.selectByPrimaryKey(problem.getUserId());
            
            //查询学校名字
            //IMSchool school=schoolDao.selectByPrimaryKey(param.getParam().getSchoolId());
            
            setResult(result, problem, user);
            result.setSchoolname(null);
            
            list1.add(result);
        }
               
        Page<ProResult> page = new Page<ProResult>();
        
        setPage(page,param);
        page.setResult(list1);
        
        return page;
    }
    
    private void setResult(ProResult result,Problems problem,IMUser user){
        result.setId(problem.getId());
        result.setUserId(problem.getUserId());
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
    
    private void setPage(Page<ProResult> page,Page<Problems> param){
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
    }

    @Override
    public Response queryBack(Page<Problems> param) {
        Response resp = new Response();
        
        ProblemsExample example = new ProblemsExample();
        ProblemsExample.Criteria criteria=example.createCriteria();
        
        if(param.getParam().getUserId()!=null){
            criteria.andUserIdEqualTo(param.getParam().getUserId());
        }
        
        example.setOrderByClause("created desc");
        List<Problems> list=problemsDao.selectBypage(example, param);
        
        param.setResult(list);
        param.setParam(null);
        resp.success(param);
        return resp;
    } 

    @Override
    public Response update(Problems param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        problemsDao.updateByPrimaryKeySelective(param);
        resp.success();
        return resp;
    }
}
