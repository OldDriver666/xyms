package com.fise.service.smstemplate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.IMSmsTemplateMapper;
import com.fise.model.entity.IMSmsTemplate;
import com.fise.model.entity.IMSmsTemplateExample;
import com.fise.model.entity.IMSmsTemplateExample.Criteria;
import com.fise.model.param.SmsTemplateParam;
import com.fise.service.smstemplate.ISmsTemplateService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class SmsTemplateServiceImpl implements ISmsTemplateService{

    @Autowired
    IMSmsTemplateMapper IMSmsTemplateDao;
    
    @Override
    public Response addSmsTemplate(IMSmsTemplate record) {
        
        Response response=new Response();
        
        if(record.getAction()!=null){
            //判断是否已经存在action
            IMSmsTemplateExample example=new IMSmsTemplateExample();
            Criteria criteria=example.createCriteria();
            criteria.andActionEqualTo(record.getAction());
            List<IMSmsTemplate> list=IMSmsTemplateDao.selectByExample(example);
            if(list.size()!=0){
                response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
                response.setMsg("action已经存在！！");
                return response;
            }
        }
        
        if(!StringUtil.isEmpty(record.getActionName())){
            //判断是否已经存在actionName
            IMSmsTemplateExample example=new IMSmsTemplateExample();
            Criteria criteria=example.createCriteria();
            criteria.andActionNameEqualTo(record.getActionName());
            List<IMSmsTemplate> list=IMSmsTemplateDao.selectByExample(example);
            if(list.size()!=0){
                response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
                response.setMsg("actionName已经存在！！");
                return response;
            }
        }
        
        if(!StringUtil.isEmpty(record.getTemplateName())){
            //判断是否已经存在actionName
            IMSmsTemplateExample example=new IMSmsTemplateExample();
            Criteria criteria=example.createCriteria();
            criteria.andTemplateNameEqualTo(record.getTemplateName());
            List<IMSmsTemplate> list=IMSmsTemplateDao.selectByExample(example);
            if(list.size()!=0){
                response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
                response.setMsg("templateName已经存在！！");
                return response;
            }
        }
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        record.setCreated(DateUtil.getLinuxTimeStamp());
        
        IMSmsTemplateDao.insertSelective(record);
        return response.success();
    }

    @Override
    public Response querySmsTemplate(SmsTemplateParam param) {
        
        Response response=new Response();
        
        IMSmsTemplateExample example=new IMSmsTemplateExample();
        Criteria criteria=example.createCriteria();
        if(param.getAction()!=null){
            criteria.andActionEqualTo(param.getAction());
        }
        List<IMSmsTemplate> list=IMSmsTemplateDao.selectByExample(example);
        if(list.size()==0){
            return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
        }
        return response.success(list);
    }

    @Override
    public Response updateSmsTemplate(IMSmsTemplate record) {
        
        Response response=new Response();
        
        if(record.getAction()!=null){
            //判断是否已经存在action
            IMSmsTemplateExample example=new IMSmsTemplateExample();
            Criteria criteria=example.createCriteria();
            criteria.andActionEqualTo(record.getAction());
            List<IMSmsTemplate> list=IMSmsTemplateDao.selectByExample(example);
            if(list.size()!=0){
                if(list.get(0).getId()!=record.getId()){
                    response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
                    response.setMsg("action已经存在！！");
                    return response;
                }
            }
        }
        
        if(!StringUtil.isEmpty(record.getActionName())){
            //判断是否已经存在actionName
            IMSmsTemplateExample example=new IMSmsTemplateExample();
            Criteria criteria=example.createCriteria();
            criteria.andActionNameEqualTo(record.getActionName());
            List<IMSmsTemplate> list=IMSmsTemplateDao.selectByExample(example);
            if(list.size()!=0){
                if(list.get(0).getId()!=record.getId()){
                    response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
                    response.setMsg("actionName已经存在！！");
                    return response;
                }
            }
        }
        
        if(!StringUtil.isEmpty(record.getTemplateName())){
            //判断是否已经存在actionName
            IMSmsTemplateExample example=new IMSmsTemplateExample();
            Criteria criteria=example.createCriteria();
            criteria.andTemplateNameEqualTo(record.getTemplateName());
            List<IMSmsTemplate> list=IMSmsTemplateDao.selectByExample(example);
            if(list.size()!=0){
                if(list.get(0).getId()!=record.getId()){
                    response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
                    response.setMsg("templateName已经存在！！");
                    return response;
                }
            }
        }
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        IMSmsTemplateDao.updateByPrimaryKeySelective(record);
        return response.success();
    }

    @Override
    public Response delSmsTemplate(IMSmsTemplate record) {
        
        Response response=new Response();
        
        IMSmsTemplateDao.deleteByPrimaryKey(record.getId());
        return response.success();
    }

}
