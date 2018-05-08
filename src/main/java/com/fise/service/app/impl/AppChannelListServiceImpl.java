package com.fise.service.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppChannelListMapper;
import com.fise.dao.AppInformationMapper;
import com.fise.model.entity.AppChannel;
import com.fise.model.entity.AppChannelList;
import com.fise.model.entity.AppChannelListExample;
import com.fise.model.entity.AppInformation;
import com.fise.model.entity.AppInformationExample;
import com.fise.model.result.AppBaseResult;
import com.fise.service.app.IAppChannelListService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AppChannelListServiceImpl implements IAppChannelListService{

    @Autowired
    AppChannelListMapper appChannelListDao;
    
    @Autowired
	AppInformationMapper appInfoDao;
    
    @Override
    public Response query(Page<AppChannelList> param) {
        Response resp = new Response();
        
        AppChannelListExample example = new AppChannelListExample();
        AppChannelListExample.Criteria criteria = example.createCriteria();
        
        if(StringUtil.isNotEmpty(param.getParam().getAppName())){
            criteria.andAppNameLike("%" + param.getParam().getAppName() + "%");
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
        
        AppChannelListExample example = new AppChannelListExample();
        AppChannelListExample.Criteria criteria = example.createCriteria();
        
        criteria.andChannelIdEqualTo(param.getChannelId());
        criteria.andAppIdEqualTo(param.getAppId());
        
        List<AppChannelList> list = appChannelListDao.selectByExample(example);
        
        if(list.size()!=0){
            return resp.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
        }
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        
        appChannelListDao.insertSelective(param);
        return resp.success();
    }
    
    @Override
	public Response queryByIdList(Page<AppChannel> param, List<Integer> idList) {
		Response response = new Response();
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria con = example.createCriteria();
		con.andStatusEqualTo(1);
		example.setOrderByClause("prority desc, id");
		con.andIdIn(idList);
		param.setPageSize(10);
		List<AppInformation> appList = appInfoDao.selectByPage(example, param);
		if (appList.size() == 0) {
			response.setErrorCode(ErrorCode.ERROR_SEARCH_UNEXIST);
			response.setMsg("亲，没有更多应用咯~");
			return response;
		}
		List<AppBaseResult> appData = new ArrayList<AppBaseResult>();
		for (int i = 0; i < appList.size(); i++) {
			AppBaseResult appBase = new AppBaseResult();
			appBase.init(appList.get(i));
			appData.add(appBase);
		}

		Page<AppBaseResult> page = new Page<AppBaseResult>();
		Map<String ,Object> map=new HashMap<String ,Object>();
	    boolean hasMore=param.getCurrentPageNo()<param.getTotalPageCount()?true:false;
	    map.put("hasMore", hasMore);
		page.setExtraParam(map);
		page.setPageNo(param.getPageNo());
		page.setPageSize(param.getPageSize());
		page.setTotalCount(param.getTotalCount());
		page.setTotalPageCount(param.getTotalPageCount());
		page.setResult(appData);
		response.success(page);
		return response;
	}

}
