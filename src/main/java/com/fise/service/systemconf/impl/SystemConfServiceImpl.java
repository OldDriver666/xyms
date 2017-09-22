package com.fise.service.systemconf.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMSystemConfMapper;
import com.fise.model.entity.IMSystemConf;
import com.fise.model.entity.IMSystemConfExample;
import com.fise.model.entity.IMSystemConfExample.Criteria;
import com.fise.model.param.SystemConfParam;
import com.fise.service.systemconf.ISystemConfService;
import com.fise.utils.StringUtil;

@Service
public class SystemConfServiceImpl implements ISystemConfService{
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Autowired
	IMSystemConfMapper iMSystemConfDao;
	
	@Override
	public Response insertSystemConf(IMSystemConf record) {
		
		Response response=new Response();
		
		//更新设备相关信息
		long nowtime=System.currentTimeMillis() / 1000;
		record.setUpdated((int)nowtime);
		record.setCreated((int)nowtime);
		iMSystemConfDao.insertSelective(record);
		response.success();
		return response;		
	}

	@Override
	public Response querySystemConf(SystemConfParam param) {
		
		Response response=new Response();
		
		IMSystemConfExample example=new IMSystemConfExample();
		Criteria criteria=example.createCriteria();
		
		if(!StringUtil.isEmpty(param.getType())){
		    criteria.andTypeEqualTo(param.getType());
		}
		
		if(!StringUtil.isEmpty(param.getName())){
		    criteria.andNameEqualTo(param.getName());
		}
		
		List<IMSystemConf> list=iMSystemConfDao.selectByExample(example);
		
		if(list.size()==0){
			return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);	
		}
		
		response.success(list);
		return response;
	}

	@Override
	public Response delSystemConf(SystemConfParam param) {
		
		Response response=new Response();
		
		iMSystemConfDao.deleteByPrimaryKey(param.getConfigid());
		
		return response.success();
	}

	@Override
	public Response updateSystemConf(IMSystemConf record) {
		
		Response response=new Response();
		
		//更新设备相关信息
		long nowtime=System.currentTimeMillis() / 1000;
		record.setUpdated((int)nowtime);
		iMSystemConfDao.updateByPrimaryKeySelective(record);
		response.success();
		return response;		
	}

}
