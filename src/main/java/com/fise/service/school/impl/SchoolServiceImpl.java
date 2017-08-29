package com.fise.service.school.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMSchoolMapper;
import com.fise.model.entity.IMSchool;
import com.fise.model.entity.IMSchoolExample;
import com.fise.service.school.ISchoolService;

@Service
public class SchoolServiceImpl implements ISchoolService{
    
    @Autowired
    IMSchoolMapper schoolDao;
    
    @Override
    public Response querySchool(Integer[] school_id) {
        
        Response res = new Response();
        List<Integer> id=Arrays.asList(school_id);
        
        IMSchoolExample example = new IMSchoolExample();
        IMSchoolExample.Criteria criteria = example.createCriteria();
        criteria.andSchoolidIn(id);
        
        List<IMSchool> list=schoolDao.selectByExample(example);
        
        if(list.size()==0){
            return res.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
                
        return res.success(list);
    }

}
