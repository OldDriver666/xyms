package com.fise.service.module;

import java.util.List;

import com.fise.base.Response;
import com.fise.model.entity.WiModule;
import com.fise.model.param.ModuleInsertParam;
import com.fise.model.param.ModuleQueryParam;

public interface IModuleService {
    /*查询用户模块*/
    List<WiModule> QueryModule(Integer companyId);
    
    /*查询所有模块*/
    Response QueryModuleAll(ModuleQueryParam param);

    /*新增用户可见模块*/
    Response InsertModule(ModuleInsertParam param);
    
    /*修改用户可见模块*/
    Response UpdateModule(ModuleInsertParam param);
    
    /*删除记录*/
    Response DeleteModule(Integer moduleId);
}
