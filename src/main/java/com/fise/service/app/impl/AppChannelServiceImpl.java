package com.fise.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppChannelMapper;
import com.fise.model.entity.AppChannel;
import com.fise.model.entity.AppChannelExample;
import com.fise.service.app.IAppChannelService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AppChannelServiceImpl implements IAppChannelService{

    @Autowired
    AppChannelMapper appChannelDao;
    
    @Override
    public Response query(Page<AppChannel> page) {
        Response resp = new Response();
        
        AppChannelExample example = new AppChannelExample();
        AppChannelExample.Criteria criteria = example.createCriteria();
        
        if(!StringUtil.isEmpty(page.getParam().getChannelName())){
            criteria.andChannelNameEqualTo(page.getParam().getChannelName());
        }
        
        List<AppChannel> list = appChannelDao.selectByPage(example, page);
        
        page.setParam(null);
        page.setResult(list);
        
        return resp.success(page);
    }

    @Override
    public Response update(AppChannel param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        appChannelDao.updateByPrimaryKeySelective(param);
        
        return resp.success();
    }

    @Override
    public Response insert(AppChannel param) {
        Response resp = new Response();
        
        param.setCreated(DateUtil.getLinuxTimeStamp());
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        
        appChannelDao.insertSelective(param);
        return resp.success();
    }

}
