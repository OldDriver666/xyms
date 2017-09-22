package com.fise.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.IMUserMapper;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.IMUserExample;
import com.fise.model.entity.IMUserExample.Criteria;
import com.fise.model.param.QueryUserParam;
import com.fise.service.user.IUserService;
import com.fise.utils.StringUtil;

@Service
public class UserServiceImpl implements IUserService{
    
    @Autowired
    IMUserMapper IMUserDao;
    
    @Override
    public Response queryUser(Page<QueryUserParam> param) {
        
        Response response=new Response();
        
        IMUserExample example=new IMUserExample();
        Criteria criteria=example.createCriteria();
        
        if(!StringUtil.isEmpty(param.getParam().getDomain())){
            criteria.andDomainEqualTo(param.getParam().getDomain());
        }
        
        if(!StringUtil.isEmpty(param.getParam().getPhone())){
            criteria.andPhoneEqualTo(param.getParam().getPhone());
        }
        
        if(param.getParam().getUserId() != null){
            criteria.andIdEqualTo(param.getParam().getUserId());
        }
        
        if(param.getParam().getOnlineStatus()!=null){
            criteria.andOnlineStatusEqualTo(param.getParam().getOnlineStatus());
        }
        
        List<IMUser> list=IMUserDao.selectByPage(example,param);
              
        if(list.size()==0){
            return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        }
        
        Page<IMUser> page=new Page<IMUser>();
        page.setPageNo(param.getPageNo());
        page.setPageSize(param.getPageSize());
        page.setTotalCount(param.getTotalCount());
        page.setTotalPageCount(param.getTotalPageCount());
        page.setResult(list);
        
        response.success(page);
        return response;
    }

}
