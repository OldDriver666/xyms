package com.fise.service.suggest.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.IMSuggestMapper;
import com.fise.model.entity.IMSuggest;
import com.fise.model.entity.IMSuggestExample;
import com.fise.model.entity.IMSuggestExample.Criteria;
import com.fise.model.param.SuggestParam;
import com.fise.service.suggest.ISuggestService;
import com.fise.utils.StringUtil;

@Service
public class SuggestServiceImpl implements ISuggestService{

	private Logger logger=Logger.getLogger(getClass());
	
	@Autowired 
	IMSuggestMapper IMSuggestDao;
	
	@Override
	public Response insertSuggest(IMSuggest record) {
		
		Response response=new Response();	
		
		//更新数据
		long nowtime=System.currentTimeMillis()/1000;
		record.setCreated((int)nowtime);
		record.setUpdated((int)nowtime);
		
		IMSuggestDao.insertSelective(record);
		response.success();
		return response;
	}

	@Override
	public Response querySuggest(Page<SuggestParam> param) {
		
		Response response=new Response();
		
		IMSuggestExample example=new IMSuggestExample();
		Criteria criteria=example.createCriteria();
		
		if(param.getParam().getUname()!=null){
			criteria.andUnameEqualTo(param.getParam().getUname());
		}
		example.setOrderByClause("created desc");
		
		List<IMSuggest> list=IMSuggestDao.selectByExamplebypage(example, param);
		if(list.size()==0){
			return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
		}
		
		Page<IMSuggest> page=new Page<IMSuggest>();
		page.setPageNo(param.getCurrentPageNo());
		page.setPageSize(param.getPageSize());
		page.setTotalCount(param.getTotalCount());
		page.setTotalPageCount(param.getTotalPageCount());
		page.setResult(list);
		
		response.success(page);
		return response;
	}

	@Override
	public Response delSuggest(SuggestParam param) {
		
		Response response=new Response();
		
		IMSuggestDao.deleteByPrimaryKey(param.getId());
		
		return response.success();
	}

	@Override
	public Response updateSuggest(IMSuggest record) {
		
		Response response=new Response();
		
		if(record.getUserId()==null){
		    return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
		}
		if(StringUtil.isEmpty(record.getUname())){
		    return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
		}
		
		if(record.getUserId()!=null){
			IMSuggestExample example=new IMSuggestExample();
			Criteria criteria=example.createCriteria();
			criteria.andUserIdEqualTo(record.getUserId());
			List<IMSuggest> list=IMSuggestDao.selectByExample(example);
			
			if(list.size()!=0){
				if(record.getId().equals(list.get(0).getId())){
					
				}else{
					return response.failure(ErrorCode.ERROR_SUGGEST_USER_ID_EXISTED);
				}
			}	
		}
		
		//更新数据
		long updatetime=System.currentTimeMillis()/1000;
		record.setUpdated((int)updatetime);
		IMSuggestDao.updateByPrimaryKeySelective(record);
		return response.success();
	}

}
