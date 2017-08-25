package com.fise.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMUserMapper;
import com.fise.model.entity.IMUser;
import com.fise.model.entity.IMUserExample;
import com.fise.model.entity.IMUserExample.Criteria;
import com.fise.service.user.IUserService;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IMUserMapper userDao;
    
    @Override
    public Response queryUser(String name) {
        Response res = new Response();
        
        IMUserExample example = new IMUserExample();
        Criteria criteria = example.createCriteria();
        
        criteria.andNameEqualTo(name);
        
        List<IMUser> list = userDao.selectByExample(example);
        
        if(list.size()!=0){
            IMUser user=list.get(0);
            return res.success(user);
        }
        
        res.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
        res.setMsg("该用户不存在！！！");
        
        return res;
    }

}
