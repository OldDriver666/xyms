package com.fise.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppInformationMapper;
import com.fise.model.entity.AppInformation;
import com.fise.model.entity.AppInformationExample;
import com.fise.model.result.AppBaseResult;
import com.fise.model.result.AppDetailResult;
import com.fise.service.app.IAppInfoemationService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AppInformationServiceImpl implements IAppInfoemationService{
    
    @Autowired
    AppInformationMapper appInformationDao;

    @Override
    public Response query(Page<AppInformation> page) {
        Response response = new Response();
        
        AppInformationExample example = new AppInformationExample();
        AppInformationExample.Criteria criteria = example.createCriteria();
        
        if(!StringUtil.isEmpty(page.getParam().getAppName())){
            criteria.andAppNameEqualTo(page.getParam().getAppName());
        }
        List<AppInformation> list=appInformationDao.selectByPage(example, page);
        page.setParam(null);
        page.setResult(list);
        
        return response.success(page);
    }

    @Override
    public Response queryAll(Page<AppInformation> param) {
        Response response = new Response();
        
        AppInformationExample example = new AppInformationExample();
        AppInformationExample.Criteria criteria = example.createCriteria();
        
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("prority desc");
        
        if(!StringUtil.isEmpty(param.getParam().getAppName())){
        	criteria.andAppNameLike("%" + param.getParam().getAppName() + "%");
        }
        
        List<AppInformation> list=appInformationDao.selectByPage(example, param);
        if(list.size()==0){
        	response.setErrorCode(ErrorCode.ERROR_SEARCH_APP_UNEXIST);
			response.setMsg("亲，没有更多应用咯~");
			return response;	
        }
        List<AppBaseResult> appData = new ArrayList<AppBaseResult>();
		for (int i = 0; i < list.size(); i++) {
			AppBaseResult appBase = new AppBaseResult();
			appBase.init(list.get(i));
			appData.add(appBase);
		}
        
		Page<AppBaseResult> page = new Page<AppBaseResult>();

		page.setPageNo(param.getPageNo());
		page.setPageSize(param.getPageSize());
		page.setTotalCount(param.getTotalCount());
		page.setTotalPageCount(param.getTotalPageCount());
		int haveMore = (int) (param.getTotalPageCount() - param.getPageNo());
		if (haveMore > 0) {
			page.setHasMore(true);
		} else {
			page.setHasMore(false);
		}

		page.setResult(appData);

		response.success(page);

		return response;
    }
    
    
    @Override
    public Response update(AppInformation param) {
        Response resp = new Response();
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        appInformationDao.updateByPrimaryKeySelective(param);
        
        return resp.success();
    }

    @Override
    public Response insert(AppInformation param) {
        Response resp = new Response();
        
        param.setCreated(DateUtil.getLinuxTimeStamp());
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        
        appInformationDao.insertSelective(param);
        return resp.success();
    }
    
    @Override
	public Response appDelete(Integer param) {
		Response response = new Response();
		int result=appInformationDao.deleteByPrimaryKey(param);
		if(result==0){
			response.setErrorCode(ErrorCode.ERROR_SEARCH_APP_UNEXIST);
			response.setMsg("删除App失败");
			return response;
		}
		return response;
	}
    
    @Override
	public Response queryByAppName(String param) {

		Response response = new Response();
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();
		criteria.andAppNameLike("%" + param + "%");
		criteria.andStatusEqualTo(1);
		example.setOrderByClause("prority desc");

		List<AppBaseResult> appData = new ArrayList<AppBaseResult>();
		List<AppInformation> data = appInformationDao.selectByExample(example);

		int result = data.size();
		switch (result) {
		case 0:
			response.failure(ErrorCode.ERROR_SEARCH_APP_UNEXIST);
			response.setMsg("亲，找不到您要的APP~");
			break;
		case 1:
			AppBaseResult appResult = new AppBaseResult();
			appResult.init(data.get(0));
			appData.add(appResult);
			response.success(appData);
			break;
		default:
			for (int i = 0; i < 2; i++) {
				AppBaseResult appBase = new AppBaseResult();
				appBase.init(data.get(i));
				appData.add(appBase);
			}
			response.success(appData);
			break;
		}
		
		return response;
	} 
    
    
    @Override
	public Response hotSearch() {
		Response response = new Response();
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		example.setOrderByClause("prority desc");

		List<String> nameList = new ArrayList<String>();
		List<AppInformation> data = appInformationDao.selectByExample(example);
		if (data.size() > 4) {
			for (int i = 0; i < 4; i++) {
				nameList.add(data.get(i).getAppName());
			}
		} else {
			for (int i = 0; i < data.size(); i++) {
				nameList.add(data.get(i).getAppName());
			}
		}
		response.success(nameList);
		return response;
	}
    
    @Override
	public Response queryByAppId(Integer param) {
		Response response = new Response();
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria con = example.createCriteria();
		//con.andAppIndexEqualTo(param);
		con.andIdEqualTo(param);
		List<AppInformation> data = appInformationDao.selectByExample(example);
		if (data.size() == 0) {
			response.setCode(200);
			response.setMsg("亲，找不到您要的APP~");
			return response;
		}
		AppDetailResult result = new AppDetailResult();
//		String creatorName=getCreatorName(data.get(0).getCreatorId());
//		data.get(0).setCreatorName(creatorName);
		result.init(data.get(0));
		response.success(result);
		return response;
    }
}
