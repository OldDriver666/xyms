package com.fise.service.module.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Response;
import com.fise.dao.WiModuleMapper;
import com.fise.dao.WiPermissionMapper;
import com.fise.model.entity.WiModule;
import com.fise.model.entity.WiModuleExample;
import com.fise.model.param.ModuleInsertParam;
import com.fise.model.param.ModuleQueryParam;
import com.fise.service.module.IModuleService;
import com.fise.utils.StringUtil;

@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private WiModuleMapper moduleDao;
    
    @Autowired
    private WiPermissionMapper permissionDao;
    
    @Override
    public List<WiModule> QueryModule(Integer companyId) {

        WiModuleExample example = new WiModuleExample();
        WiModuleExample.Criteria con = example.createCriteria();
        con.andBelongCompanyEqualTo(companyId);
        return moduleDao.selectByExample(example);
    }

    @Override
    public Response InsertModule(ModuleInsertParam param) {
        Response resp = new Response();
        //TODO 测试由数据库报错情况
        if(param.getModule_type() == null){
            param.setModule_type(0);
        }
        if(param.getStatus() == null){
            param.setStatus(1);
        }
        
//        //检测是否可以添加
//        WiModuleExample exm = new WiModuleExample();
//        WiModuleExample.Criteria con = exm.createCriteria();
//        con.andBelongCompanyEqualTo(param.getCompany_id());
//        con.andModuleTypeEqualTo(param.getModule_type());
//        con.andNameEqualTo(param.getName());
//        if(moduleDao.selectByExample(exm).isEmpty()){
//            
//        }
        
        WiModule module = new WiModule();
        module.setName(param.getName());
        module.setParentId(param.getParentId());
        module.setPriority(param.getPriority());
        module.setBelongCompany(param.getCompany_id());
        if(param.getDescription() != null){
            module.setDescription(param.getDescription());
        }
        if(param.getUrl() != null){
            module.setUrl(param.getUrl());
        }
        module.setModuleType(param.getModule_type());
        module.setSn(param.getSn());
        module.setStatus(param.getStatus());
        moduleDao.insert(module);
        resp.success();
        return resp;
    }

    @Override
    public Response UpdateModule(ModuleInsertParam param) {
        /*TODO 做角色权限判断*/
        Response resp = new Response();
        WiModule module = new WiModule();
        module.setId(param.getModule_id());
        
        if(!StringUtil.isEmpty(param.getName())){
            module.setName(param.getName());
        }
        if(param.getParentId() != null){
            module.setParentId(param.getParentId());
        }
        if(param.getPriority() != null){
            module.setPriority(param.getPriority());
        }
        if(param.getDescription() != null){
            module.setDescription(param.getDescription());
        }
        if(param.getSn() != null){
            module.setSn(param.getSn());
        }
        if(param.getStatus() != null){
            module.setStatus(param.getStatus());
        }
        if(param.getUrl() != null){
            module.setUrl(param.getUrl());
        }
        module.setBelongCompany(param.getCompany_id());
        moduleDao.updateByPrimaryKeySelective(module);
        resp.success();
        return resp;
    }

    @Override
    public Response QueryModuleAll(ModuleQueryParam param) {
        Response resp = new Response();
        // TODO  检测用户是否有权查询所有菜单用作管理
        WiModuleExample example = new WiModuleExample();
        List<WiModule> moduleList = moduleDao.selectByExample(example);
        resp.success(moduleList);
        return resp;
    }

    @Override
    public Response DeleteModule(Integer moduleId) {
        Response resp = new Response();
        moduleDao.deleteByPrimaryKey(moduleId);
        resp.success();
        return resp;
    }

}
