package com.fise.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppInformationMapper;
import com.fise.model.entity.AppInformation;
import com.fise.model.entity.AppInformationExample;
import com.fise.service.app.IAppInfoemationService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AppInformationServiceImpl implements IAppInfoemationService{
    
    @Autowired
    AppInformationMapper appInformationDao;

    @Override
    public Response query(Page<AppInformation> page) {
        Response resp = new Response();
        
        AppInformationExample example = new AppInformationExample();
        AppInformationExample.Criteria criteria = example.createCriteria();
        
        if(!StringUtil.isEmpty(page.getParam().getAppName())){
            criteria.andAppNameEqualTo(page.getParam().getAppName());
        }
        
        List<AppInformation> list=appInformationDao.selectByPage(example, page);
        page.setParam(null);
        page.setResult(list);
        
        return resp.success(page);
    }

    @Override
    public Response update(AppInformation param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        appInformationDao.updateByPrimaryKeySelective(param);
        
        return resp.success();
    }
    
    
}
