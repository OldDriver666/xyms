package com.fise.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppChannelListMapper;
import com.fise.model.entity.AppChannelList;
import com.fise.model.entity.AppChannelListExample;
import com.fise.service.app.IAppChannelListService;
import com.fise.utils.DateUtil;

@Service
public class AppChannelListServiceImpl implements IAppChannelListService{

    @Autowired
    AppChannelListMapper appChannelListDao;
    
    @Override
    public Response query(Page<AppChannelList> param) {
        Response resp = new Response();
        
        AppChannelListExample example = new AppChannelListExample();
        AppChannelListExample.Criteria criteria = example.createCriteria();
        
        if(param.getParam().getAppId()!=null){
            criteria.andAppIdEqualTo(param.getParam().getAppId());
        }
        if(param.getParam().getChannelId()!=null){
            criteria.andChannelIdEqualTo(param.getParam().getChannelId());
        }
        
        List<AppChannelList> list =appChannelListDao.selectByPage(example, param);
        param.setParam(null);
        param.setResult(list);   
        
        return resp.success(param);
    }

    @Override
    public Response update(AppChannelList param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        appChannelListDao.updateByPrimaryKeySelective(param);
        
        return resp.success();
    }

    @Override
    public Response insert(AppChannelList param) {
        Response resp = new Response();
        
        appChannelListDao.insertSelective(param);
        return resp.success();
    }

}
