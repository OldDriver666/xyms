package com.fise.service.complaints.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.ComplaintsMapper;
import com.fise.model.entity.Complaints;
import com.fise.model.entity.ComplaintsExample;
import com.fise.model.entity.Problems;
import com.fise.service.complaints.IComplaintsService;
import com.fise.utils.StringUtil;

@Service
public class ComplaintsServiceImpl implements IComplaintsService {

    @Autowired
    private ComplaintsMapper complaintsDao;
    
    @Override
    public Page<Complaints> queryComplaints(Page<Complaints> param) {

        ComplaintsExample example = new ComplaintsExample();
        ComplaintsExample.Criteria con = example.createCriteria();
        if (StringUtil.isNotEmpty(param.getParam().getPlaintiffName())) {
        	con.andPlaintiffNameEqualTo(param.getParam().getPlaintiffName());
		}
        if (StringUtil.isNotEmpty(param.getParam().getDefendantName())) {
        	con.andDefendantNameEqualTo(param.getParam().getDefendantName());
		}
        if (StringUtil.isNotEmpty(param.getParam().getComplaintType())) {
        	con.andComplaintTypeEqualTo(param.getParam().getComplaintType());
		}
        if (StringUtil.isNotEmpty(param.getParam().getDefendantType())) {
        	con.andDefendantTypeEqualTo(param.getParam().getDefendantType());
		}
        param.setResult(complaintsDao.selectByExampleByPage(example, param));
        return param;
    }

    @Override
    public Response insertComplaints(Complaints param) {
        Response resp = new Response();
        complaintsDao.insert(param);
        resp.success();
        return resp;
    }

    @Override
    public Response updateComplaints(Complaints param) {
        Response resp = new Response();
        complaintsDao.updateByPrimaryKeySelective(param);
        resp.success();
        return resp;
    }


    @Override
    public Response deleteComplaints(Integer Id) {
        Response resp = new Response();
        complaintsDao.deleteByPrimaryKey(Id);
        resp.success();
        return resp;
    }


}
