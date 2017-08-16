package com.fise.service.concern.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Response;
import com.fise.dao.ConcernMapper;
import com.fise.model.entity.Concern;
import com.fise.model.entity.ConcernExample;
import com.fise.model.entity.ConcernExample.Criteria;
import com.fise.service.concern.IConcernService;
import com.fise.utils.DateUtil;

@Service
public class ConcernServiceImpl implements IConcernService{

    @Autowired
    ConcernMapper concernDao;
    
    
    @Override
    public Response addConcern(Concern record) {
        Response res = new Response();
        
        ConcernExample example = new ConcernExample();
        Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(record.getName());
        criteria.andProblemIdEqualTo(record.getProblemId());
        
        List<Concern> list=concernDao.selectByExample(example);
        
        if(list.size()==0){
            record.setCreated(DateUtil.getLinuxTimeStamp());
            concernDao.insertSelective(record);
            res.success();
            res.setMsg("已关注");
            return res;            
        }
        
        Concern concern=list.get(0);
        if(concern.getStatus()==1){
            concern.setStatus(0);
            concernDao.updateByPrimaryKeySelective(concern);
            res.success();
            res.setMsg("已取消关注");
            return res;
        }
        
        concern.setStatus(1);
        concernDao.updateByPrimaryKeySelective(concern);
        res.success();
        res.setMsg("已关注");
        return res;        
    }


    @Override
    public Response queryConcern(Concern record) {
        Response res = new Response();
        
        ConcernExample example = new ConcernExample();
        Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(record.getName());
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

}
