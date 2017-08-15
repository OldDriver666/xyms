package com.fise.service.answer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AnswerMapper;
import com.fise.model.entity.Answer;
import com.fise.model.entity.AnswerExample;
import com.fise.model.entity.AnswerExample.Criteria;
import com.fise.service.answer.IAnswerService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AnswerServiceImpl implements IAnswerService{
    
    @Autowired
    AnswerMapper answerDao;
    
    @Override
    public Response insertAnswer(Answer record) {
        Response res = new Response();
        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        
        answerDao.insertSelective(record);
        return res.success();
    }

    @Override
    public Response queryAnswer(Page<Answer> page) {
        Response res = new Response();
        
        AnswerExample example = new AnswerExample();
        Criteria criteria = example.createCriteria();
        
        if(!StringUtil.isEmpty(page.getParam().getName())){
            criteria.andNameEqualTo(page.getParam().getName());
        }else{
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        example.setOrderByClause("created desc");
        page.setPageSize(10);
        
        List<Answer> list=answerDao.selectBypage(example, page);
        
        Page<Answer> reslut = new Page<Answer>();
        
        reslut.setPageNo(page.getPageNo());
        reslut.setPageSize(page.getPageSize());
        reslut.setTotalCount(page.getTotalCount());
        reslut.setTotalPageCount(page.getTotalPageCount());
        reslut.setResult(list);
       
        return res.success(reslut);
     
    }

}
