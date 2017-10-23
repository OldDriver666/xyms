package com.fise.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppAdvertMapper;
import com.fise.model.entity.AppAdvert;
import com.fise.model.entity.AppAdvertExample;
import com.fise.model.entity.AppAdvertExample.Criteria;
import com.fise.model.result.AdvertBaseResult;
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
    
    /**
	 * 先检查该广告的状态。0-待审核 1-发布 2-下架
	 */
	@Override
	public Response queryAdvertAll() {
		Response response = new Response();
		AppAdvertExample example = new AppAdvertExample();
		AppAdvertExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		example.setOrderByClause("prority desc");
		List<AppAdvert> datas = appAdvertDao.selectByExample(example);
		List<AdvertBaseResult> advertData = new ArrayList<AdvertBaseResult>();
		for (int i = 0; i < datas.size(); i++) {
			AdvertBaseResult advertBase = new AdvertBaseResult();
			advertBase.init(datas.get(i));
			advertData.add(advertBase);
		}
		response.success(advertData);
		return response;
	}

}
