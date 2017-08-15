package com.fise.service.problems.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.ProblemsMapper;
import com.fise.model.entity.Problems;
import com.fise.model.entity.ProblemsExample;
import com.fise.model.entity.ProblemsExample.Criteria;
import com.fise.service.problems.IProblemService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;



@Service
public class ProblemServiceImpl implements IProblemService{

    @Autowired
    ProblemsMapper problemsDao;
    
    @Override
    public Response insert(Problems record) {
        Response res = new Response();
        
        if(StringUtil.isEmpty(record.getName())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(record.getTitle())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(record.getContent()) && StringUtil.isEmpty(record.getPicture())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("内容不能为空");
            return res;
        }
        
        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        problemsDao.insertSelective(record);
        
        return res.success();
    }

    @Override
    public Response queryAll(Page<Problems> param) {
        Response res = new Response();
        
        ProblemsExample example = new ProblemsExample();
        Criteria criteria=example.createCriteria();        
        example.setOrderByClause("created desc");
        param.setPageSize(10);
        
        if(!StringUtil.isEmpty(param.getParam().getName())){
            criteria.andNameEqualTo(param.getParam().getName());
        }
        
        List<Problems> list=problemsDao.selectBypage(example, param);
        
        Page<Problems> page = new Page<Problems>();
        
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        page.setResult(list);
       
        return res.success(page);
    }

    @Override
    public Response queryTitle(Page<Problems> param) {
        Response res = new Response();
        
        param.setPageSize(10);
        
        List<Problems> list=problemsDao.querytitle(param);
        
        Page<Problems> page = new Page<Problems>();
        
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        page.setResult(list);
       
        return res.success(page);
  
    }

}
