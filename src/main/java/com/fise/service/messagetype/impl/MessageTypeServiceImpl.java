package com.fise.service.messagetype.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.WiMessageTypeMapper;
import com.fise.model.entity.WiMessageType;
import com.fise.model.entity.WiMessageTypeExample;
import com.fise.model.entity.WiMessageTypeExample.Criteria;
import com.fise.service.messagetype.IMessageTypeService;

@Service
public class MessageTypeServiceImpl implements IMessageTypeService{
    
    @Autowired
    WiMessageTypeMapper messageTypeDao;
    
    @Override
    public Response queryMessageType(Integer type) {
        Response response=new Response();
        
        WiMessageTypeExample example=new WiMessageTypeExample();
        Criteria criteria=example.createCriteria();
        
        if(type!=null){
            criteria.andMsgTypeEqualTo(type);
        }
        
        List<WiMessageType> list=messageTypeDao.selectByExample(example);
        if(list.size()==0){
            response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
            return response;
        }
        
        response.success(list);
        return response;
    }

}
