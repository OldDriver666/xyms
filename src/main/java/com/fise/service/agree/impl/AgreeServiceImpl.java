package com.fise.service.agree.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AgreeMapper;
import com.fise.dao.AnswerMapper;
import com.fise.model.entity.Agree;
import com.fise.model.entity.AgreeExample;
import com.fise.model.entity.Answer;
import com.fise.service.agree.IAgreeService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AgreeServiceImpl implements IAgreeService{
    
    @Autowired
    AgreeMapper agreeDao;
    
    @Autowired
    AnswerMapper answerDao;
    
    @Override
    public Response addAgree(Agree agree) {
        Response res = new Response();
        
        AgreeExample example = new AgreeExample();
        AgreeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(agree.getUserId());
        criteria.andAnswerIdEqualTo(agree.getAnswerId());
        
        List<Agree> list=agreeDao.selectByExample(example);
        
        if(list.size()==0){
            agree.setUpdated(DateUtil.getLinuxTimeStamp());
            agree.setCreated(DateUtil.getLinuxTimeStamp());
            agreeDao.insertSelective(agree);
            res.success();
            res.setMsg("已点赞");
            
            addAnswerNum(agree,1);
            return res;
        }
        
        Agree agre=list.get(0);
        if(agre.getStatus()==1){
            agre.setStatus(0);
            agre.setUpdated(DateUtil.getLinuxTimeStamp());
            agreeDao.updateByPrimaryKeySelective(agre);
            res.success();
            res.setMsg("已取消点赞");
            
            addAnswerNum(agree,-1);
            return res;
        }
        
        agre.setStatus(1);
        agre.setUpdated(DateUtil.getLinuxTimeStamp());
        agreeDao.updateByPrimaryKeySelective(agre);
        res.success();
        res.setMsg("已点赞");
        
        addAnswerNum(agree,1);
        return res;

    }
    
    private void addAnswerNum(Agree agree,Integer num){
        Answer answer=answerDao.selectByPrimaryKey(agree.getAnswerId());
        answer.setAgreeNum(answer.getAgreeNum()+num);
        answer.setUpdated(DateUtil.getLinuxTimeStamp());
        answerDao.updateByPrimaryKey(answer);
    }

    @Override
    public Response queryBack(Page<Agree> page) {
        Response resp = new Response();
        
        AgreeExample example = new AgreeExample();
        AgreeExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("created desc");
        
        if(page.getParam().getAnswerId()!=null){
            criteria.andAnswerIdEqualTo(page.getParam().getAnswerId());
        }
        if(page.getParam().getUserId()!=null){
            criteria.andUserIdEqualTo(page.getParam().getUserId());
        }
        if(StringUtil.isNotEmpty(page.getParam().getContent())){
            criteria.andContentLike("%" + page.getParam().getContent() + "%");
        }
        if(!StringUtil.isEmpty(page.getParam().getNick())){
            criteria.andNickLike("%" + page.getParam().getNick()  + "%");
        }
        
        List<Agree> list=agreeDao.selectBypage(example, page);
        
        page.setParam(null);
        page.setResult(list);
        return resp.success(page);
    }

    @Override
    public Response update(Agree agree) {
        Response resp = new Response();
        
        agree.setUpdated(DateUtil.getLinuxTimeStamp());
        agreeDao.updateByPrimaryKeySelective(agree);
        return resp.success();
    }

    

}
