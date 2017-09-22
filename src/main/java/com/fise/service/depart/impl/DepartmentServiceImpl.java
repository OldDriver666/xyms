package com.fise.service.depart.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Response;
import com.fise.dao.DBFunctionMapper;
import com.fise.dao.WiDepartmentMapper;
import com.fise.model.entity.WiDepartment;
import com.fise.model.entity.WiDepartmentExample;
import com.fise.model.entity.WiDepartmentExample.Criteria;
import com.fise.model.param.DepartmentParam;
import com.fise.service.depart.IDepartmentService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;


@Service
public class DepartmentServiceImpl implements IDepartmentService{
	
	@Autowired
	WiDepartmentMapper departDao;

	@Autowired
    DBFunctionMapper dbDao;
	
    @Override
    public Response insertOne(DepartmentParam record) {
        Response resp = new Response();
        
        WiDepartment data = new WiDepartment();
        data.setCompanyId(record.getCompanyId());
        data.setDepartName(record.getDepartName());
        data.setParentId(record.getParentId());
        data.setStatus(1);
        data.setUpdated(DateUtil.getLinuxTimeStamp());
        departDao.insert(data);
        resp.success(record);
        return resp;
    }

    @Override
    public Response queryList(DepartmentParam param) {
        Response resp = new Response();
        WiDepartmentExample example = new WiDepartmentExample();
        Criteria con =  example.createCriteria();
        con.andCompanyIdEqualTo(param.getCompanyId());
        if(param.getDepartId() != null && param.getDepartId() != 0){
            con.andIdIn(getChildDepatId(param.getDepartId()));
        }
        con.andStatusEqualTo(1);
        List<WiDepartment> data = departDao.selectByExample(example);
        resp.success(data);
        return resp;
    }

    @Override
    public Response update(DepartmentParam param) {
        Response resp = new Response();
        WiDepartment record = new WiDepartment();
        record.setId(param.getDepartId());
        if(param.getStatus() != null){
            record.setStatus(param.getStatus());
        }
        if(!StringUtil.isEmpty(param.getDepartName())){
            record.setDepartName(param.getDepartName());
        }
        if(param.getParentId() != null){
            record.setParentId(param.getParentId());
        }
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        departDao.updateByPrimaryKeySelective(record);
        resp.success(record);
        return resp;
    }

    @Override
    public List<Integer> getChildDepatId(Integer parentId) {
        String childId = dbDao.getChildDepateId(parentId);
        String[] childList = childId.split(",");
        List<Integer> idList = new ArrayList<Integer>();
        for(int i=1;i < childList.length; i++){
            System.out.println(childList[i]);
            idList.add(Integer.valueOf(childList[i]));
        }
        return idList;
    }

}
