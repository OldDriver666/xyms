package com.fise.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppChannelListMapper;
import com.fise.dao.AppChannelMapper;
import com.fise.model.entity.AppChannel;
import com.fise.model.entity.AppChannelExample;
import com.fise.model.entity.AppChannelList;
import com.fise.model.entity.AppChannelListExample;
import com.fise.model.result.AppChannelResult;
import com.fise.service.app.IAppChannelService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AppChannelServiceImpl implements IAppChannelService{

    @Autowired
    AppChannelMapper appChannelDao;
    
    @Autowired
    AppChannelListMapper listDao;
    
    @Override
    public Response query(Page<AppChannel> page) {
        Response resp = new Response();
        
        AppChannelExample example = new AppChannelExample();
        AppChannelExample.Criteria criteria = example.createCriteria();
        
        if(!StringUtil.isEmpty(page.getParam().getChannelName())){
            criteria.andChannelNameEqualTo(page.getParam().getChannelName());
        }
        
        if(page.getParam().getStatus()!=null){
            criteria.andStatusEqualTo(page.getParam().getStatus());
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
    
    @Override
	public Response queryChannelAll() {
		Response response = new Response();
		AppChannelExample example = new AppChannelExample();
		AppChannelExample.Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(0);
		example.setOrderByClause("prority desc, id");
		List<AppChannel> datas = appChannelDao.selectByExample(example);
		List<AppChannelResult> channelData = new ArrayList<AppChannelResult>();
		for (int i = 0; i < datas.size(); i++) {
			AppChannelResult channelBase = new AppChannelResult();
			channelBase.init(datas.get(i));
			channelData.add(channelBase);
		}
		response.success(channelData);
		return response;
	}

    @Override
    public List<Integer> getChannelAppId(Integer channelId) {
        List<Integer> data = new ArrayList<Integer>();
        AppChannelListExample example = new AppChannelListExample();
        AppChannelListExample.Criteria con = example.createCriteria();
        con.andChannelIdEqualTo(channelId);
        con.andStatusEqualTo(1);
        List<AppChannelList> chanList = listDao.selectByExample(example);
        for(AppChannelList tmp : chanList){
            data.add(tmp.getAppId());
        }
        return data;
    }
    
    @Override
    public AppChannel getChannelInfo(Integer channelId) {
        AppChannelExample example = new AppChannelExample();
        AppChannelExample.Criteria con = example.createCriteria();
        con.andStatusNotEqualTo(0);
        con.andIdEqualTo(channelId);
        List<AppChannel> data = appChannelDao.selectByExample(example);
        if(data.isEmpty())
            return null;
        else
            return data.get(0);
    }
}
