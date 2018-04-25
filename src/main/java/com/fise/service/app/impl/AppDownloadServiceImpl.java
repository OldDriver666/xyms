package com.fise.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Response;
import com.fise.dao.AppDownloadMapper;
import com.fise.model.entity.AppDownload;
import com.fise.model.entity.AppDownloadExample;
import com.fise.service.app.IAppDownloadService;

@Service
public class AppDownloadServiceImpl implements IAppDownloadService{

	   @Autowired
	    private AppDownloadMapper appDownloadDao;
	    
	    @Override
	    public Response queryAppDownload(AppDownload param) {

	    	Response resp = new Response();
	    	AppDownloadExample example = new AppDownloadExample();
	        AppDownloadExample.Criteria con = example.createCriteria();
	        List<AppDownload> appDownloadList = appDownloadDao.selectByExample(example);
	        resp.success(appDownloadList);
	        return resp;
	    }

	    @Override
	    public Response insertAppDownload(AppDownload param) {
	        Response resp = new Response();
	        appDownloadDao.insert(param);
	        resp.success();
	        return resp;
	    }

	    @Override
	    public Response updateAppDownload(AppDownload param) {
	        Response resp = new Response();
	    	AppDownloadExample example = new AppDownloadExample();
	        AppDownloadExample.Criteria con = example.createCriteria();
	        con.andIdEqualTo(param.getId());
	        appDownloadDao.updateByExampleSelective(param, example);
	        resp.success();
	        return resp;
	    }


	    @Override
	    public Response deleteAppDownload(Integer id) {
	        Response resp = new Response();
	        AppDownloadExample example = new AppDownloadExample();
	        AppDownloadExample.Criteria con = example.createCriteria();
	        con.andIdEqualTo(id);
	        appDownloadDao.deleteByExample(example);
	        resp.success();
	        return resp;
	    }

		@Override
		public Response addListAppDownload(List<AppDownload> param) {
			Response resp = new Response();
	        appDownloadDao.addListAppDownload(param);
	        resp.success();
	        return resp;
		}

}
