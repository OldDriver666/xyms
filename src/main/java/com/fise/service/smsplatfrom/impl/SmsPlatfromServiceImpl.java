package com.fise.service.smsplatfrom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMSmsPlatfromMapper;
import com.fise.model.entity.IMSmsPlatfrom;
import com.fise.model.entity.IMSmsPlatfromExample;
import com.fise.model.entity.IMSmsPlatfromExample.Criteria;
import com.fise.model.param.SmsPlatfromParam;
import com.fise.service.smsplatfrom.ISmsPlatfromService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class SmsPlatfromServiceImpl implements ISmsPlatfromService{

    @Autowired
    IMSmsPlatfromMapper smsPlatfromDao;

    @Override
    public Response addSmsPlatfrom(IMSmsPlatfrom record) {
        
        Response response=new Response();
        
        IMSmsPlatfromExample example=new IMSmsPlatfromExample();
        Criteria criteria=example.createCriteria();
        criteria.andPlatfromNameEqualTo(record.getPlatfromName());
        List<IMSmsPlatfrom> list=smsPlatfromDao.selectByExample(example);
        if(list.size()!=0){
            response.failure(ErrorCode.ERROR_SERVICE_CONF_NAME_EXISTED);
            response.setMsg("短信平台名称已经存在，请换个名称！！");
            return response;
        }
        
        record.setCreated(DateUtil.getLinuxTimeStamp());
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        
        smsPlatfromDao.insertSelective(record);
        response.success();
        return response;
    }

    @Override
    public Response querySmsPlatfrom(SmsPlatfromParam param) {
        
        Response response=new Response();
        
        IMSmsPlatfromExample example=new IMSmsPlatfromExample();
        Criteria criteria=example.createCriteria();
        
        if(!StringUtil.isEmpty(param.getPlatfromName())){
            criteria.andPlatfromNameEqualTo(param.getPlatfromName());
        }
        
        List<IMSmsPlatfrom> list=smsPlatfromDao.selectByExample(example);
        
        if(list.size()==0){
            return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
        
        response.success(list);
        return response;
    }

    @Override
    public Response updateSmsPlatfrom(IMSmsPlatfrom record) {
        
        Response response=new Response();
        
        IMSmsPlatfromExample example=new IMSmsPlatfromExample();
        Criteria criteria=example.createCriteria();
        if(!StringUtil.isEmpty(record.getPlatfromName())){
            criteria.andPlatfromNameEqualTo(record.getPlatfromName());
            List<IMSmsPlatfrom> list=smsPlatfromDao.selectByExample(example);
            if(list.size()!=0){
                if(list.get(0).getId()!=record.getId()){
                    response.failure(ErrorCode.ERROR_SERVICE_CONF_NAME_EXISTED);
                    response.setMsg("短信平台名称已经存在，请换个名称！！");
                    return response;
                }
            }
        }
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        
        smsPlatfromDao.updateByPrimaryKeySelective(record);
        return response.success();
    }

    @Override
    public Response delSmsPlatfrom(SmsPlatfromParam param) {
        
        Response response=new Response();
        
        smsPlatfromDao.deleteByPrimaryKey(param.getId());
        return response.success();
    }

    
   
}
