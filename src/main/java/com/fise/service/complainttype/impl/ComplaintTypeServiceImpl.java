package com.fise.service.complainttype.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.ComplaintTypeMapper;
import com.fise.model.entity.ComplaintType;
import com.fise.model.entity.ComplaintTypeExample;
import com.fise.model.entity.ComplaintsExample;
import com.fise.model.entity.WiModule;
import com.fise.model.entity.WiModuleExample;
import com.fise.service.complainttype.IComplaintTypeService;
import com.fise.utils.StringUtil;

@Service
public class ComplaintTypeServiceImpl implements IComplaintTypeService {

    @Autowired
    private ComplaintTypeMapper complaintTypeDao;
    
    @Override
    public Response queryComplaintType(ComplaintType param) {

    	Response resp = new Response();
    	ComplaintTypeExample example = new ComplaintTypeExample();
        ComplaintTypeExample.Criteria con = example.createCriteria();
        List<ComplaintType> complaintTypeList = complaintTypeDao.selectByExample(example);
        resp.success(complaintTypeList);
        return resp;
    }

    @Override
    public Response insertComplaintType(ComplaintType param) {
        Response resp = new Response();
        complaintTypeDao.insert(param);
        resp.success();
        return resp;
    }

    @Override
    public Response updateComplaintType(ComplaintType param) {
        Response resp = new Response();
        complaintTypeDao.updateByPrimaryKeySelective(param);
        resp.success();
        return resp;
    }


    @Override
    public Response deleteComplaintType(Integer Id) {
        Response resp = new Response();
        complaintTypeDao.deleteByPrimaryKey(Id);
        resp.success();
        return resp;
    }


}
