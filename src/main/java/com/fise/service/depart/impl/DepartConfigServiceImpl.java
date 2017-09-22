package com.fise.service.depart.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMDepartConfigMapper;
import com.fise.model.entity.IMDepartConfig;
import com.fise.model.entity.IMDepartConfigExample;
import com.fise.model.entity.IMDepartConfigExample.Criteria;
import com.fise.model.param.DepartConfigParam;
import com.fise.service.depart.IDepartConfigService;


@Service
public class DepartConfigServiceImpl implements IDepartConfigService{
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Autowired
	IMDepartConfigMapper imDepartConfigDao;

	@Override
	public Response insertDepartConfig(IMDepartConfig record) {
		
		Response response=new Response();
		
		//判断添加的设备的clientType是否已经配置
		IMDepartConfigExample exampleAccount=new IMDepartConfigExample();
		Criteria criteriaAccount=exampleAccount.createCriteria();
		criteriaAccount.andClienttypeEqualTo(record.getClienttype());
		List<IMDepartConfig> list=imDepartConfigDao.selectByExample(exampleAccount);
		if(list.size()!=0){
			response.failure(ErrorCode.ERROR_CLIENT_TYPE_TYPE_EXISTED);
			return response;
		}
		
		//更新设备相关信息
		long nowtime=System.currentTimeMillis() / 1000;
		record.setCreated((int)nowtime);
		imDepartConfigDao.insertSelective(record);
		response.success();
		return response;		
	}

	@Override
	public Response queryDepartConfig(DepartConfigParam param) {
		
		Response response=new Response();
		
		IMDepartConfigExample example=new IMDepartConfigExample();
		Criteria criteria=example.createCriteria();
		
		if(param.getDepartid()!=null){
			criteria.andDepartidEqualTo(param.getDepartid());
		}
		
		if(param.getClienttype()!=null){
			criteria.andClienttypeEqualTo(param.getClienttype());
		}
			
		List<IMDepartConfig> fisedevicelist=imDepartConfigDao.selectByExample(example);
			
		if(fisedevicelist.size()!=0){
			response.success(fisedevicelist);
		}
			
		return response;
	}

	@Override
	public Response delDepartConfig(DepartConfigParam param) {
		
		Response response=new Response();
		
		imDepartConfigDao.deleteByPrimaryKey(param.getConfigid());
		
		return response.success();	
	}

	@Override
	public Response updateDepartConfig(IMDepartConfig record) {

		Response response=new Response();
		
		if(record.getClienttype()!=null){
			//判断修改的设备的clientType是否已经配置
			IMDepartConfigExample exampleAccount=new IMDepartConfigExample();
			Criteria criteriaAccount=exampleAccount.createCriteria();
			criteriaAccount.andClienttypeEqualTo(record.getClienttype());
			List<IMDepartConfig> list=imDepartConfigDao.selectByExample(exampleAccount);
			
			if(list.size()!=0){
				if(record.getId().equals(list.get(0).getId())){
					
				}else{
					response.failure(ErrorCode.ERROR_CLIENT_TYPE_TYPE_EXISTED);
					return response;
				}
			}
		}
		
		//更新设备相关信息	
		imDepartConfigDao.updateByPrimaryKeySelective(record);
		
		response.success();
		return response;		
	}
	
}
