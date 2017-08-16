package com.fise.service.agree.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Response;
import com.fise.dao.AgreeMapper;
import com.fise.dao.AnswerMapper;
import com.fise.model.entity.Agree;
import com.fise.model.entity.AgreeExample;
import com.fise.model.entity.Answer;
import com.fise.service.agree.IAgreeService;
import com.fise.utils.DateUtil;

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
        criteria.andNameEqualTo(agree.getName());
        criteria.andAnswerIdEqualTo(agree.getAnswerId());
        
        List<Agree> list=agreeDao.selectByExample(example);
        
        if(list.size()==0){
            agree.setCreated(DateUtil.getLinuxTimeStamp());
            agreeDao.insertSelective(agree);
            res.success();
            res.setMsg("已点赞");
            
            Answer answer=answerDao.selectByPrimaryKey(agree.getAnswerId());
            answer.setAgreeNum(answer.getAgreeNum()+1);
            answerDao.updateByPrimaryKey(answer);
            return res;
        }
        
        Agree agre=list.get(0);
        if(agre.getStatus()==1){
            agre.setStatus(0);
            agreeDao.updateByPrimaryKeySelective(agre);
            res.success();
            res.setMsg("已取消点赞");
            
            Answer answer=answerDao.selectByPrimaryKey(agree.getAnswerId());
            answer.setAgreeNum(answer.getAgreeNum()-1);
            answerDao.updateByPrimaryKey(answer);
            return res;
        }
        agre.setStatus(1);
        agreeDao.updateByPrimaryKeySelective(agre);
        res.success();
        res.setMsg("已点赞");
        
        Answer answer=answerDao.selectByPrimaryKey(agree.getAnswerId());
        answer.setAgreeNum(answer.getAgreeNum()+1);
        answerDao.updateByPrimaryKey(answer);
        return res;

    }

}
