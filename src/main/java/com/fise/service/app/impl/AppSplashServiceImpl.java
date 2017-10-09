package com.fise.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppSplashMapper;
import com.fise.model.entity.AppSplash;
import com.fise.model.entity.AppSplashExample;
import com.fise.service.app.IAppSplashService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AppSplashServiceImpl implements IAppSplashService{

    @Autowired
    AppSplashMapper appSplashDao;
    
    @Override
    public Response query(Page<AppSplash> page) {
        Response resp = new Response();
        
        AppSplashExample example = new AppSplashExample();
        AppSplashExample.Criteria criteria = example.createCriteria();
        
        if(!StringUtil.isEmpty(page.getParam().getName())){
            criteria.andNameEqualTo(page.getParam().getName());
        }
        
        List<AppSplash> list =appSplashDao.selectByPage(example, page);
        page.setParam(null);
        page.setResult(list);
        
        return resp.success(page);
    }

    @Override
    public Response update(AppSplash param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        appSplashDao.updateByPrimaryKeySelective(param);
        
        return resp.success();
    }
        
}
