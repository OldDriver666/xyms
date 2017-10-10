package com.fise.service.sensitiveword.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.SensitiveWordsMapper;
import com.fise.model.entity.SensitiveWords;
import com.fise.model.entity.SensitiveWordsExample;
import com.fise.service.sensitiveword.ISensitivewordService;
import com.fise.utils.StringUtil;

@Service
public class SensitivewordServiceImpl implements ISensitivewordService{

    @Autowired
    SensitiveWordsMapper sensitiveWordsDao;
    
    @Override
    public Response insert(SensitiveWords param) {
        Response resp = new Response();
        
        sensitiveWordsDao.insertSelective(param);
        return resp.success();
    }

    @Override
    public Response query(Page<SensitiveWords> param) {
        Response resp = new Response();
        
        SensitiveWordsExample example = new SensitiveWordsExample();
        SensitiveWordsExample.Criteria criteria = example.createCriteria();
        
        if(!StringUtil.isEmpty(param.getParam().getSensitiveWord())){
            criteria.andSensitiveWordEqualTo(param.getParam().getSensitiveWord());
        }
        
        List<SensitiveWords> list=sensitiveWordsDao.selectByPage(example, param);
        param.setParam(null);
        param.setResult(list);
        
        return resp.success(param);
    }

    @Override
    public Response delete(Integer id) {
        Response resp = new Response();
        
        sensitiveWordsDao.deleteByPrimaryKey(id);
        
        return resp.success();
    }
    
}
