package com.fise.service.problems.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AnswerMapper;
import com.fise.dao.CommentMapper;
import com.fise.dao.ConcernMapper;
import com.fise.dao.IMMarkMapper;
import com.fise.dao.IMRelationShipMapper;
import com.fise.dao.IMSchoolMapper;
import com.fise.dao.IMUserMapper;
import com.fise.dao.MyProblemMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.dao.SensitiveWordsMapper;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Answer;
import com.fise.model.entity.AnswerExample;
import com.fise.model.entity.Concern;
import com.fise.model.entity.IMMark;
import com.fise.model.entity.IMMarkExample;
import com.fise.model.entity.IMSchool;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.IMUserExample;
import com.fise.model.entity.MyProblem;
import com.fise.model.entity.MyProblemExample;
import com.fise.model.entity.Problems;
import com.fise.model.entity.ProblemsExample;
import com.fise.model.entity.ProblemsExample.Criteria;
import com.fise.model.entity.SensitiveWords;
import com.fise.model.entity.SensitiveWordsExample;
import com.fise.model.result.ProResult;
import com.fise.service.problems.IProblemService;
import com.fise.service.sensitiveword.ISensitivewordService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;
import redis.clients.jedis.Jedis;

@Service
public class ProblemServiceImpl implements IProblemService{

    @Autowired
    ProblemsMapper problemsDao;
    
    @Autowired
    IMUserMapper userDao;
    
    @Autowired
    IMSchoolMapper schoolDao;
    
    @Autowired IMRelationShipMapper relationShipDao;
        
    @Autowired SensitiveWordsMapper sensitiveWordsDao;    
    
    @Autowired AnswerMapper answerDao;
    
    @Autowired CommentMapper commentDao;    
    
    @Autowired MyProblemMapper MyProblemDao;
    
    @Autowired IMMarkMapper imMarkDao;
    
    @Autowired
    ISensitivewordService sensitivewordService;
    
    @Override
    public Response insert(Problems record) {
        Response res = new Response();
        //检测敏感词
        List<String> listWords = sensitivewordService.checkSensitiveWord(record.getContent());
        if(listWords.size() != 0){
            res.failure(ErrorCode.ERROR_SENSITIVEWORDS_EXISTED);
            res.setMsg("语句中包含敏感词的个数为：" + listWords.size() + "。包含：" + listWords.toString());
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
            String value1=problem.getAnswerNum()+"";
            jedis.set(key, value1);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            //存入数据库
            MyProblem myProblem = new MyProblem();
            myProblem.setProblemId(problem.getId());
            myProblem.setUserId(problem.getUserId());
            myProblem.setBrowserNum(Integer.valueOf(value));
            myProblem.setAnswerNum(Integer.valueOf(value1));
            myProblem.setCreated(DateUtil.getLinuxTimeStamp());
            myProblem.setUpdated(DateUtil.getLinuxTimeStamp());
            MyProblemDao.insertSelective(myProblem);
            
        } catch (Exception e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
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
        //判断好友是否为空
        if(userlist.size()==0){
            userlist=new ArrayList<Integer>();
        }
        userlist.add(param.getParam().getUserId());
        //添加官方账号ID，用于发送广告
        userlist.add(Constants.FISE_AD_ID);
        
        //System.out.println("-----------"+userlist.toString());
        criteria.andUserIdIn(userlist);
        
        List<Problems> list=problemsDao.selectBypage(example, param);
                               
        Page<ProResult> page = getResult(list1, list, param);       
        return res.success(page);
        
    }

    @Override
    public Response queryTitle(Page<Problems> param) {
        Response res = new Response();
        List<ProResult> list1=new ArrayList<>();
        ProblemsExample example = new ProblemsExample();
        Criteria criteria=example.createCriteria();
        
        //根据好友关系查询
        List<Integer> userlist=relationShipDao.findrelation(param.getParam().getUserId());
        //判断好友是否为空
        if(userlist.size()==0){
            userlist=new ArrayList<Integer>();
        }
        userlist.add(param.getParam().getUserId());
        criteria.andUserIdIn(userlist);
        
        param.getParam().setTitle("%"+param.getParam().getTitle()+"%");
        List<Problems> list=problemsDao.querytitle(example,param.getParam().getTitle());
        
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
                
                MyProblemExample example2 = new MyProblemExample();
                MyProblemExample.Criteria criteria2 = example2.createCriteria();
                criteria2.andProblemIdEqualTo(problem.getId());
                criteria2.andUserIdEqualTo(problem.getUserId());
                criteria2.andStatusEqualTo(1);
                List<MyProblem> list2=MyProblemDao.selectByExample(example2);
                MyProblem myProblem=list2.get(0);
                
                String key=problem.getId()+"answermy";
                String value=jedis.get(key);
                //先从redis里拿数据，如果没有就从数据库拿数据
                if(!StringUtil.isEmpty(value)){
                    result.setAddAnswerCount(problem.getAnswerNum()-Integer.valueOf(value));
                }else {
                    result.setAddAnswerCount(problem.getAnswerNum()-myProblem.getAnswerNum());
                }
                                
                key=problem.getId()+"browsermy";
                value=jedis.get(key);
                if(!StringUtil.isEmpty(value)){
                    result.setAddBrowseCount(problem.getBrowseNum()-Integer.valueOf(value));
                }else {
                    result.setAddBrowseCount(problem.getBrowseNum()-myProblem.getBrowserNum());
                }
                
                
                //查询用户昵称和头像
                IMUser user=userDao.selectByPrimaryKey(problem.getUserId());
                
                //先在备注昵称表里查询备注信息
                IMMarkExample example1 = new IMMarkExample();
                IMMarkExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andFromUserEqualTo(param.getParam().getUserId());
                criteria1.andDestUserEqualTo(problem.getUserId());
                criteria1.andMarkTypeEqualTo(0);
                criteria1.andStatusEqualTo(1);
                List<IMMark> list1=imMarkDao.selectByExample(example1);
                if(list1.size()!=0){
                    if(!StringUtil.isEmpty(list1.get(0).getMarkName())){
                        user.setNick(list1.get(0).getMarkName());
                    }
                }
                
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
            } catch (Exception e) {
                //e.printStackTrace();
                throw new RuntimeException(e);
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
        
        if(problem.getUserId().intValue()!=user_id.intValue()){
            //查询用户昵称和头像
            IMUser user=userDao.selectByPrimaryKey(problem.getUserId());
            
            //先在备注昵称表里查询备注信息
            IMMarkExample example = new IMMarkExample();
            IMMarkExample.Criteria criteria = example.createCriteria();
            criteria.andFromUserEqualTo(user_id);
            criteria.andDestUserEqualTo(problem.getUserId());
            criteria.andMarkTypeEqualTo(0);
            criteria.andStatusEqualTo(1);
            List<IMMark> list2=imMarkDao.selectByExample(example);
            if(list2.size()!=0){
                if(!StringUtil.isEmpty(list2.get(0).getMarkName())){
                    user.setNick(list2.get(0).getMarkName());
                }                
            }
            
            //查询用户可以看到的回答数（非好友或拉黑用户的回答无法看到）
            AnswerExample example1 = new AnswerExample();
            AnswerExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andStatusEqualTo(1);
            
            if(problem.getId()!=null){
                criteria1.andProblemIdEqualTo(problem.getId());
            }
            
            //查询好友回答
            List<Integer> userlist=relationShipDao.findrelation(user_id);
            //判断好友是否为空
            if(userlist.size()==0){
                userlist=new ArrayList<Integer>();
            }
            userlist.add(user_id);
            criteria1.andUserIdIn(userlist);
            
            
            List<Answer> list3=answerDao.selectByExample(example1);
            
            problem.setAnswerNum(list3.size());
            
            setResult(result, problem, user);
            
            res.success(result);
            return res;
        }
        
        //查询用户可以看到的回答数（非好友或拉黑用户的回答无法看到）
        AnswerExample example1 = new AnswerExample();
        AnswerExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andStatusEqualTo(1);
        
        if(problem.getId()!=null){
            criteria1.andProblemIdEqualTo(problem.getId());
        }
        
        //查询好友回答
        List<Integer> userlist=relationShipDao.findrelation(user_id);
        //判断好友是否为空
        if(userlist.size()==0){
            userlist=new ArrayList<Integer>();
        }
        userlist.add(user_id);
        criteria1.andUserIdIn(userlist);
        
        
        List<Answer> list3=answerDao.selectByExample(example1);
        
        problem.setAnswerNum(list3.size());
        
        Jedis jedis = null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);           
            String key=problem.getId()+"answermy";
            String value=problem.getAnswerNum()+"";
            jedis.set(key, value);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            key=problem.getId()+"browsermy";
            String value1=problem.getBrowseNum()+"";
            jedis.set(key, value1);
            
            /*jedis.setex(key, Constants.ACCESS_TOKEN_EXPIRE_SECONDS, value);*/
            
            //修改数据存入数据库
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
        
        IMUser user=userDao.selectByPrimaryKey(user_id);
        
        setResult(result, problem, user);
        
        res.success(result);
        return res;
    }

    @Override
    public Response delMyPro(Integer problem_id) {
        Response res = new Response();
        //根据问题id查询问题,更改问题状态status为0
        Problems problem = problemsDao.selectByPrimaryKey(problem_id);
        if(problem==null){
            return res.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
        problem.setStatus(0);
        problem.setUpdated(DateUtil.getLinuxTimeStamp());
        problemsDao.updateByPrimaryKey(problem);
        
        //根据问题id，更改问题下的回答状态status为0
        answerDao.updateList(problem_id);
        //根据问题id，更改问题下的评论状态status为0
        commentDao.updateList(problem_id);
        return res.success();
    }
    
    private Page<ProResult> getResult(List<ProResult> list1,List<Problems> list,Page<Problems> param){
        for(Problems problem:list){
            ProResult result = new ProResult(); 
            
            //查询用户昵称和头像
            IMUser user=userDao.selectByPrimaryKey(problem.getUserId());
            
            //先在备注昵称表里查询备注信息
            IMMarkExample example = new IMMarkExample();
            IMMarkExample.Criteria criteria = example.createCriteria();
            criteria.andFromUserEqualTo(param.getParam().getUserId());
            criteria.andDestUserEqualTo(problem.getUserId());
            criteria.andMarkTypeEqualTo(0);
            criteria.andStatusEqualTo(1);
            List<IMMark> list2=imMarkDao.selectByExample(example);
            if(list2.size()!=0){
                if(!StringUtil.isEmpty(list2.get(0).getMarkName())){
                    user.setNick(list2.get(0).getMarkName());
                }                
            }
            
            //查询学校名字
            //IMSchool school=schoolDao.selectByPrimaryKey(param.getParam().getSchoolId());
            
            //查询用户可以看到的回答数（非好友或拉黑用户的回答无法看到）
            AnswerExample example1 = new AnswerExample();
            AnswerExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andStatusEqualTo(1);
            
            if(problem.getId()!=null){
                criteria1.andProblemIdEqualTo(problem.getId());
            }
            
            //查询好友回答
            List<Integer> userlist=relationShipDao.findrelation(param.getParam().getUserId());
            //判断好友是否为空
            if(userlist.size()==0){
                userlist=new ArrayList<Integer>();
            }
            userlist.add(param.getParam().getUserId());
            criteria1.andUserIdIn(userlist);
            
            
            List<Answer> list3=answerDao.selectByExample(example1);
            
            problem.setAnswerNum(list3.size());
            
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
        result.setAddress(problem.getAddress());
        result.setAnswerNum(problem.getAnswerNum());
        result.setBrowseNum(problem.getBrowseNum());
        result.setStatus(problem.getStatus());
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
        if(param.getParam().getId()!=null){
            criteria.andIdEqualTo(param.getParam().getId());
        }
        
        if(!StringUtil.isEmpty(param.getParam().getTitle())){
            criteria.andTitleLike("%" + param.getParam().getTitle()  + "%");
        }
        if(!StringUtil.isEmpty(param.getParam().getNick())){
        	criteria.andNickLike("%" + param.getParam().getNick()  + "%");
        }
        if(!StringUtil.isEmpty(param.getParam().getContent())){
            criteria.andContentLike("%" + param.getParam().getContent()  + "%");
        }
        example.setOrderByClause("created desc");
        List<Problems> list=problemsDao.querytitlebypage(example, param);
        
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
