package com.fise.service.querydevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.DeviceConfMapper;
import com.fise.model.entity.DeviceConf;
import com.fise.model.entity.DeviceConfExample;
import com.fise.model.entity.DeviceConfExample.Criteria;
import com.fise.service.querydevice.IQueryDeviceService;

@Service
public class QueryDeviceServiceImpl implements IQueryDeviceService{

    @Autowired 
    DeviceConfMapper deviceConfDao;
    
    @Override
    public Response queryDevice(Page<DeviceConf> param) {
        Response resp = new Response();
        
        DeviceConfExample example = new DeviceConfExample();
        Criteria criteria = example.createCriteria();
        
        if(param.getParam().getDeviceId()!=null){
            criteria.andDeviceIdEqualTo(param.getParam().getDeviceId());
        }
        if(param.getParam().getMasterId()!=null){
            criteria.andMasterIdEqualTo(param.getParam().getMasterId());
        }
        
        List<DeviceConf> list=deviceConfDao.selectByPage(example, param);
        
        param.setParam(null);
        param.setResult(list);
        return resp.success(param);
    }

}
