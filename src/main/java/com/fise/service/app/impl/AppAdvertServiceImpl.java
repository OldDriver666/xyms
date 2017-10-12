package com.fise.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppAdvertMapper;
import com.fise.model.entity.AppAdvert;
import com.fise.model.entity.AppAdvertExample;
import com.fise.model.entity.AppAdvertExample.Criteria;
import com.fise.service.app.IAppAdvertService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AppAdvertServiceImpl implements IAppAdvertService{
    
    @Autowired
    AppAdvertMapper appAdvertDao;
    
    @Override
    public Response query(Page<AppAdvert> page) {
        Response resp = new Response();
        
        AppAdvertExample example = new AppAdvertExample();
        Criteria criteria = example.createCriteria();
        
        if(!StringUtil.isEmpty(page.getParam().getAdvName())){
            criteria.andAdvNameEqualTo(page.getParam().getAdvName());
        }
        
        List<AppAdvert> list = appAdvertDao.selectByPage(example, page);
        
        page.setParam(null);
        page.setResult(list);
        
        return resp.success(page);
    }

    @Override
    public Response update(AppAdvert record) {
        Response resp = new Response();
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        appAdvertDao.updateByPrimaryKeySelective(record);
        
        return resp.success();
    }

    @Override
    public Response insert(AppAdvert record) {
        Response resp = new Response();
        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        
        appAdvertDao.insertSelective(record);
        return resp.success();
    }

}
