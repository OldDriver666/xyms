package com.fise.service.organization.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.WiOrganizationMapper;
import com.fise.model.entity.WiOrganization;
import com.fise.model.entity.WiOrganizationExample;
import com.fise.model.entity.WiOrganizationExample.Criteria;
import com.fise.service.organization.IOrganizationService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private WiOrganizationMapper organDao;
    
    @Override
    public Response QueryOrganization(String name) {
        Response resp =new Response();
        WiOrganizationExample example = new WiOrganizationExample();
        Criteria criteria=example.createCriteria();
        
        if(!StringUtil.isEmpty(name)){
            criteria.andNameLike("%"+name+"%");
        }
  
        List<WiOrganization> organList = organDao.selectByExample(example);
        resp.success(organList);
        return resp;
    }

    @Override
    public Response InsertOrganization(WiOrganization param) {
        Response resp =new Response();
        
        param.setCreated(DateUtil.getLinuxTimeStamp());
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        organDao.insertSelective(param);
        return resp;
    }

    @Override
    public Response UpdateOrganization(WiOrganization param) {
        Response resp =new Response();
        
        if(StringUtil.isEmpty(param.getName())){
            return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        param.setUpdated(DateUtil.getLinuxTimeStamp());
        organDao.updateByPrimaryKeySelective(param);
        return resp;
    }

    @Override
    public Response delOrganization(WiOrganization param) {
        
        Response response=new Response();
        
        organDao.deleteByPrimaryKey(param.getId());
        
        return response.success();
    }

}
