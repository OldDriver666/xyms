package com.fise.service.accountmanage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.WiAccountManageMapper;
import com.fise.model.entity.WiAccountManage;
import com.fise.model.entity.WiAccountManageExample;
import com.fise.service.accountmanage.IAccountManageService;
import com.fise.utils.DateUtil;

@Service
public class AccountManageServiceImpl implements IAccountManageService{

    @Autowired
    WiAccountManageMapper accountDao;
    
    @Override
    public Response insertAccount(WiAccountManage param) {
        
        Response response=new Response();
        
        //TODO 没有比较begin_account 和 end_account的大小
        
        param.setCreated(DateUtil.getLinuxTimeStamp());
        accountDao.insertSelective(param);
        
        response.success();
        return response;
    }

    @Override
    public Response queryAccount(Integer depart_id) {
        
        Response response=new Response();
        
        WiAccountManageExample example=new WiAccountManageExample();
        WiAccountManageExample.Criteria criteria=example.createCriteria();
        
        if(depart_id!=null){
            criteria.andDepartIdEqualTo(depart_id);
        }
        
        List<WiAccountManage> list=accountDao.selectByExample(example);
        response.success(list);
        return response;
    }
    
    @Override
	public Response queryAccountPage(Page<WiAccountManage> param) {
        Response response=new Response();
        
        WiAccountManageExample example=new WiAccountManageExample();
        WiAccountManageExample.Criteria criteria=example.createCriteria();
        if(param.getParam().getDepartId()!=null) {
        	criteria.andDepartIdEqualTo(param.getParam().getDepartId());
        }
        List<WiAccountManage> list=accountDao.selectByPage(example, param);
        param.setParam(null);
        param.setResult(list); 
		response.success(param);
		return response;
	}

    @Override
    public Response delAccount(Integer id) {
        
        Response response=new Response();
        
        accountDao.deleteByPrimaryKey(id);
        response.success();
        return response;
    }

    @Override
    public Response updateAccount(WiAccountManage param) {
        
        Response response=new Response();
        
        accountDao.updateByPrimaryKeySelective(param);
        
        return response.success();
    }


}
