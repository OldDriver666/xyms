package com.fise.service.devicecontrol.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.DeviceControlMapper;
import com.fise.dao.IMUserMapper;
import com.fise.model.entity.DeviceControl;
import com.fise.model.entity.DeviceControlExample;
import com.fise.model.entity.DeviceControlExample.Criteria;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.IMUserExample;
import com.fise.model.param.DeviceControlParam;
import com.fise.service.devicecontrol.IDeviceControlService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class DeviceControlServiceImpl implements IDeviceControlService{

    @Autowired
    IMUserMapper userDao;
    
    @Autowired
    DeviceControlMapper deviceControlDao;
    
    @Override
    public Response addDeviceControl(DeviceControl record) {
        
        Response response=new Response();
        
        DeviceControlExample example =new DeviceControlExample();
        Criteria criteria =example.createCriteria();
        criteria.andDeviceIdEqualTo(record.getDeviceId());
        criteria.andMobileEqualTo(record.getMobile());
        List<DeviceControl> list=deviceControlDao.selectByExample(example);
        
        if(list.size()!=0){
            response.failure(ErrorCode.ERROR_REGISTER_MEMBER_MOBILE_EXISTED);
            response.setMsg("设备已经有这个号码");
            return response;
        }
        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        
        deviceControlDao.insertSelective(record);
        
        return response.success();
    }

    @Override
    public Response queryDeviceControl(DeviceControlParam param) {
        
        Response response=new Response();
        
        /*IMUserExample example=new IMUserExample();
        IMUserExample.Criteria criteria=example.createCriteria();
        
        if(!StringUtil.isEmpty(param.getName())){
            criteria.andNameEqualTo(param.getName());
        }
        
        List<IMUser> list=userDao.selectByExample(example);
        
        if(list.size()==0){
            return response.failure(ErrorCode.ERROR_CAMERA_CONF_DEVICE_EXISTED);
        }*/
        
        DeviceControlExample example1 =new DeviceControlExample();
        Criteria criteria1 =example1.createCriteria();
        criteria1.andDeviceIdEqualTo(param.getDeviceId());
        
        if(!StringUtil.isEmpty(param.getMobile())){
            criteria1.andMobileEqualTo(param.getMobile());
        }
        
        List<DeviceControl> list1=deviceControlDao.selectByExample(example1);
        if(list1.size()==0){
            return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
        response.success(list1);
        return response;
    }

    @Override
    public Response updateDeviceControl(DeviceControl record) {
        
        Response response=new Response();
        
        DeviceControlExample example =new DeviceControlExample();
        Criteria criteria =example.createCriteria();
        
        if(record.getDeviceId()!=null){
            criteria.andDeviceIdEqualTo(record.getDeviceId());
        }
        if(record.getAuthType()!=null){
            criteria.andAuthTypeEqualTo(record.getAuthType());
        }
        if(!StringUtil.isEmpty(record.getMobile())){
            criteria.andMobileEqualTo(record.getMobile());
        }
        List<DeviceControl> list=deviceControlDao.selectByExample(example);
        if(list.size()!=0){
            if(list.get(0).getId().intValue()!=record.getId().intValue()){
                return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
            }
        }
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        deviceControlDao.updateByPrimaryKeySelective(record);
        return response.success();
    }

    @Override
    public Response delDeviceControl(DeviceControlParam param) {
        
        Response response=new Response();
        
        deviceControlDao.deleteByPrimaryKey(param.getDeviceId());
        return response.success();
    }

}
