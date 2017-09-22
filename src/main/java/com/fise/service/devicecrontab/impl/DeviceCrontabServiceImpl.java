package com.fise.service.devicecrontab.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.DeviceCrontabMapper;
import com.fise.dao.IMUserMapper;
import com.fise.model.entity.DeviceCrontab;
import com.fise.model.entity.DeviceCrontabExample;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.IMUserExample;
import com.fise.model.param.DeviceCrontabParam;
import com.fise.service.devicecrontab.IDeviceCrontabService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class DeviceCrontabServiceImpl implements IDeviceCrontabService{
    
    @Autowired
    IMUserMapper userDao;
    
    @Autowired
    DeviceCrontabMapper deviceCrontabDao;
    
    @Override
    public Response addDeviceCrontab(DeviceCrontab record) {
        
        Response response=new Response();
        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        
        deviceCrontabDao.insertSelective(record);
        return response.success();
    }

    @Override
    public Response queryDeviceCrontab(DeviceCrontabParam param) {
        
        Response response=new Response();
        
        IMUserExample example=new IMUserExample();
        IMUserExample.Criteria criteria=example.createCriteria();
        
        if(!StringUtil.isEmpty(param.getName())){
            criteria.andNameEqualTo(param.getName());
        }
        
        List<IMUser> list=userDao.selectByExample(example);
        
        if(list.size()==0){
            return response.failure(ErrorCode.ERROR_CAMERA_CONF_DEVICE_EXISTED);
        }
        
        DeviceCrontabExample example1=new DeviceCrontabExample();
        DeviceCrontabExample.Criteria criteria1=example1.createCriteria();
        criteria1.andDeviceIdEqualTo(list.get(0).getId());
        
        List<DeviceCrontab> list1=deviceCrontabDao.selectByExample(example1);
        
        if(list1.size()==0){
            return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
        response.success(list1);
        return response;
    }

    @Override
    public Response updateDeviceCrontab(DeviceCrontab record) {
        
        Response response =new Response();
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        deviceCrontabDao.updateByPrimaryKeySelective(record);
        
        return response.success();
    }

    @Override
    public Response delDeviceCrobtab(DeviceCrontabParam param) {
        
        Response response=new Response();
        
        deviceCrontabDao.deleteByPrimaryKey(param.getTaskId());
        return response.success();
    }

}
