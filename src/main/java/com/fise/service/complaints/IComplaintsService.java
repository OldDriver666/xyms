package com.fise.service.complaints;

import java.util.List;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.Complaints;


public interface IComplaintsService {
    /*查询投诉*/
	Page<Complaints> queryComplaints(Page<Complaints> param);
    
    /*新增投诉*/
    Response insertComplaints(Complaints param);
    
    /*修改投诉*/
    Response updateComplaints(Complaints param);
    
    /*删除投诉*/
    Response deleteComplaints(Integer id);
}
