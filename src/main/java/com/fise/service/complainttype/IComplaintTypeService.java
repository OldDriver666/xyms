package com.fise.service.complainttype;

import com.fise.base.Response;
import com.fise.model.entity.ComplaintType;


public interface IComplaintTypeService {
    /*查询投诉类型*/
	Response queryComplaintType(ComplaintType param);
    
    /*新增投诉类型*/
    Response insertComplaintType(ComplaintType param);
    
    /*修改投诉类型*/
    Response updateComplaintType(ComplaintType param);
    
    /*删除投诉类型*/
    Response deleteComplaintType(Integer id);

}
