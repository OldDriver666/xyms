package com.fise.service.organization;

import com.fise.base.Response;
import com.fise.model.entity.WiOrganization;


public interface IOrganizationService {
    /*查询用户可见模块*/
    Response QueryOrganization(String name);
    
    /*新增用户可见模块*/
    Response InsertOrganization(WiOrganization param);
    
    /*删除用户可见模块*/
    Response delOrganization(WiOrganization param);
    
    /*修改用户可见模块*/
    Response UpdateOrganization(WiOrganization param);
}
