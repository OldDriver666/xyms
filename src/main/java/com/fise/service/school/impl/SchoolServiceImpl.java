package com.fise.service.school.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMSchoolMapper;
import com.fise.model.entity.IMSchool;
import com.fise.service.school.ISchoolService;

@Service
public class SchoolServiceImpl implements ISchoolService{
    
    @Autowired
    IMSchoolMapper schoolDao;
    
    @Override
    public Response querySchool(Integer school_id) {
        
        Response res = new Response();
        
        IMSchool school=schoolDao.selectByPrimaryKey(school_id);
        
        if(school==null){
            return res.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
        
        return res.success(school);
    }

}
