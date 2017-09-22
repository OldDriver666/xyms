package com.fise.service.clienttype.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMClientTypeMapper;
import com.fise.model.entity.IMClientType;
import com.fise.model.entity.IMClientTypeExample;
import com.fise.model.entity.IMClientTypeExample.Criteria;
import com.fise.model.param.ClientTypeParam;
import com.fise.service.clienttype.IClientTypeService;
import com.fise.utils.StringUtil;

@Service
public class ClientTypeServiceImpl implements IClientTypeService{
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Autowired
	IMClientTypeMapper imClientTypeDao;

	@Override
	public Response insertClientType(IMClientType record) {
		
		Response response=new Response();
		
		//判断添加的clienttype的设备类型是否已经存在
		IMClientTypeExample exampleType=new IMClientTypeExample();
		Criteria criteriaType=exampleType.createCriteria();
		criteriaType.andClienttypeEqualTo(record.getClienttype());
		List<IMClientType> fisedevicelist=imClientTypeDao.selectByExample(exampleType);
		
		if(fisedevicelist.size()!=0){
			response.failure(ErrorCode.ERROR_CLIENT_TYPE_TYPE_EXISTED);
			return response;
		}
		
		//判断添加的设备的名字是否已经存在
		IMClientTypeExample examplename=new IMClientTypeExample();
		Criteria criterianame=examplename.createCriteria();
		criterianame.andClientnameEqualTo(record.getClientname());
		List<IMClientType> list=imClientTypeDao.selectByExample(examplename);
		
		if(list.size()!=0){
			response.failure(ErrorCode.ERROR_CLIENT_TYPE_NAME_EXISTED);
			return response;
		}
		
		//更新设备相关信息
		long nowtime=System.currentTimeMillis() / 1000;
		record.setCreated((int)nowtime);
		imClientTypeDao.insertSelective(record);
		response.success();
		return response;
	}

	@Override
	public Response queryClientType(ClientTypeParam param) {
		
		Response response=new Response();
		
		IMClientTypeExample example=new IMClientTypeExample();
		Criteria criteria=example.createCriteria();
		
		if(!StringUtil.isEmpty(param.getClientname())){
			criteria.andClientnameEqualTo(param.getClientname());
		}
		
		if(param.getClienttype()!=null){
			criteria.andClienttypeEqualTo(param.getClienttype());	
		} 
		
		List<IMClientType> fisedevicelist=imClientTypeDao.selectByExample(example);
		
		if(fisedevicelist.size()!=0){
			response.success(fisedevicelist);
			return response;
		}
		
		return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
	}

	@Override
	public Response delClientType(ClientTypeParam param) {
		
		Response response=new Response();
		
		imClientTypeDao.deleteByPrimaryKey(param.getTypeid());
		return response.success();
	}

	@Override
	public Response updateClientType(IMClientType record) {

		Response response=new Response();
		
		if(StringUtil.isEmpty(record.getClientname())){
		    return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
		}
		if(record.getId()==null){
		    return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
		}
		
		if(!StringUtil.isEmpty(record.getClientname())){
			//判断修改的设备的名字是否已经存在
			IMClientTypeExample exampleIME=new IMClientTypeExample();
			Criteria criteriaIME=exampleIME.createCriteria();
			criteriaIME.andClientnameEqualTo(record.getClientname());
			List<IMClientType> fisedevicelist=imClientTypeDao.selectByExample(exampleIME);
			
			if(fisedevicelist.size()!=0){
				if(record.getId().equals(fisedevicelist.get(0).getId())){
					
				}else{
					response.failure(ErrorCode.ERROR_CLIENT_TYPE_NAME_EXISTED);
					return response;
				}
			}
		}
		
		if(record.getClienttype()!=0){
			//判断添加的设备的账号是否已经注册
			IMClientTypeExample exampleAccount=new IMClientTypeExample();
			Criteria criteriaAccount=exampleAccount.createCriteria();
			criteriaAccount.andClienttypeEqualTo(record.getClienttype());
			List<IMClientType> list=imClientTypeDao.selectByExample(exampleAccount);
			
			if(list.size()!=0){
				if(record.getId().equals(list.get(0).getId())){
					
				}else{
					response.failure(ErrorCode.ERROR_CLIENT_TYPE_TYPE_EXISTED);
					return response;
				}
			}
		}
		
		//更新设备相关信息
		imClientTypeDao.updateByPrimaryKeySelective(record);
		response.success();
		return response;	
	}
}
