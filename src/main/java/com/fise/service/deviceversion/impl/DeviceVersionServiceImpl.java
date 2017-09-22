package com.fise.service.deviceversion.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMDeviceVersionMapper;
import com.fise.model.entity.IMDeviceVersion;
import com.fise.model.entity.IMDeviceVersionExample;
import com.fise.model.entity.IMDeviceVersionExample.Criteria;
import com.fise.model.param.DeviceVersionParam;
import com.fise.service.deviceversion.IDeviceVersionService;
import com.fise.utils.StringUtil;


@Service
public class DeviceVersionServiceImpl implements IDeviceVersionService{
	
	private Logger logger=Logger.getLogger(getClass());
	
	@Autowired
	IMDeviceVersionMapper deviceVersionDao;
	
	@Override
	public Response insertDeviceVersion(IMDeviceVersion record) {
		
		Response response=new Response();
		
		IMDeviceVersionExample example=new IMDeviceVersionExample();
		Criteria criteria=example.createCriteria();
		criteria.andDepartidEqualTo(record.getDepartid());
		criteria.andDevTypeEqualTo(record.getDevType());
		List<IMDeviceVersion> list=deviceVersionDao.selectByExample(example);
		if(list.size()!=0){
		    response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
		    response.setMsg("已经存在最新版本！！！");
		    return response;
		}
		
		deviceVersionDao.insertSelective(record);
		response.success();
		
		return response;
	}

	@Override
	public Response queryDeviceVersion(DeviceVersionParam param) {
		
		Response response=new Response();
		
		IMDeviceVersionExample example=new IMDeviceVersionExample();
		Criteria criteria=example.createCriteria();
		criteria.andDepartidEqualTo(param.getDepartid());
		
		if(param.getDevType()!=null){
			criteria.andDevTypeEqualTo(param.getDevType());
		}
		
		List<IMDeviceVersion> list=deviceVersionDao.selectByExample(example);
		
		if(list.size()==0){
			return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
		}
		
		response.success(list);
		
		return response;
	}

	@Override
	public Response delDeviceVersion(DeviceVersionParam param) {
		
		Response response=new Response();
		
		deviceVersionDao.deleteByPrimaryKey(param.getId());
		return response.success();
	}

	@Override
	public Response updateDeviceVersion(IMDeviceVersion record) {
		
		Response response=new Response();
		
		if(StringUtil.isEmpty(record.getDevVersion())){
		    return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
		}
        if(StringUtil.isEmpty(record.getUpdateUrl())){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        if(record.getStatus()==null){
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        deviceVersionDao.updateByPrimaryKeySelective(record);
        return response.success();

	}

}
