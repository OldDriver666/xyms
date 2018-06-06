package com.fise.service.role.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.WiAdminMapper;
import com.fise.dao.WiOrganizationRoleMapper;
import com.fise.dao.WiPermissionMapper;
import com.fise.model.entity.WiOrganizationRole;
import com.fise.model.entity.WiOrganizationRoleExample;
import com.fise.model.entity.WiOrganizationRoleExample.Criteria;
import com.fise.model.entity.WiPermission;
import com.fise.model.param.InsertAuthParam;
import com.fise.model.param.InsertRoleParam;
import com.fise.model.param.QueryRoleParam;
import com.fise.model.param.RolePermissionParam;
import com.fise.model.result.ModulePermissResult;
import com.fise.service.depart.IDepartmentService;
import com.fise.service.module.IModuleService;
import com.fise.service.role.IRoleService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    WiOrganizationRoleMapper roleDao;

    @Autowired
    private WiPermissionMapper permissionDao;

    @Autowired
    private WiAdminMapper adminDao;

    @Resource
    IDepartmentService departSvr;

    @Resource
    IModuleService moduleSvr;

    @Override
    public List<WiOrganizationRole> queryRole(QueryRoleParam param) {

        // 预先查询管理者权限
        WiOrganizationRole admin = roleDao.selectByPrimaryKey(param.getRole_id());
        if (admin == null) {
            return null;
        }
        WiOrganizationRoleExample example = new WiOrganizationRoleExample();
        WiOrganizationRoleExample.Criteria criterion = example.createCriteria();
        criterion.andAuthLevelLessThan(admin.getAuthLevel());
        criterion.andOrganizationIdEqualTo(param.getCompany_id());
        if(param.getStatus()!=null){
            criterion.andStatusEqualTo(param.getStatus());
        }
        
        return roleDao.selectByExample(example);
    }

    @Override
    public List<ModulePermissResult> queryRoleAuth(QueryRoleParam param) {
        // 需要返回的真实JSON格式数据
        List<ModulePermissResult> data = new ArrayList<ModulePermissResult>();
        List<ModulePermissResult> tmpData = new ArrayList<ModulePermissResult>();
        List<ModulePermissResult> result = new ArrayList<ModulePermissResult>();
        //先查询顶级目录权限 查出来的结果需要遍历一次
        Integer needAll = 1;
        if (param.getInclude_all() == null || param.getInclude_all() == 0)
            needAll = 0;
        data = permissionDao.selectAuthByRole(param.getCompany_id(), param.getRole_id(),0,needAll);
        for (ModulePermissResult tmp : data) {
            result.add(tmp);
            tmpData.clear();
            tmpData = permissionDao.selectAuthByRole(param.getCompany_id(), param.getRole_id(),tmp.getModule_id(),needAll);
            result.addAll(tmpData);
        }

        return result;
    }
    
    @Override
    public List<ModulePermissResult> queryAuthByName(QueryRoleParam param) {
    	
    	return permissionDao.selectAuthByName(param.getCompany_id(), param.getRole_id(),param.getName());
    }

    @Override
    public Response updateRoleAuth(RolePermissionParam param) {

        Response resp = new Response();

        WiPermission dbValue = new WiPermission();
        dbValue.setId(param.getPermissionId());
        if (param.getInsertAuth() != null) {
            dbValue.setInsertAuth(param.getInsertAuth());
        }
        if (param.getQueryAuth() != null) {
            dbValue.setQueryAuth(param.getQueryAuth());
        }
        if (param.getUpdateAuth() != null) {
            dbValue.setUpdateAuth(param.getUpdateAuth());
        }
        if (param.getStatus() != null) {
            dbValue.setStatus(param.getStatus());
        }
        dbValue.setUpdated(DateUtil.getLinuxTimeStamp());
        permissionDao.updateByPrimaryKeySelective(dbValue);

        resp.success();
        return resp;
    }

    @Override
    public Response insertRole(InsertRoleParam role) {

        Response response = new Response();
//
//        // 判断用户权限
//        WiAdminExample example1 = new WiAdminExample();
//        WiAdminExample.Criteria criteria1 = example1.createCriteria();
//        criteria1.andIdEqualTo(role.getAdminId());
//        List<WiAdmin> list1 = adminDao.selectByExample(example1);
//        if (list1.isEmpty()) {
//            response.failure(ErrorCode.ERROR_DATABASE);
//            response.setMsg("用户不存在");
//            return response;
//        }
//
//        Integer roleid = list1.get(0).getRoleId();
//        
//        Criteria criteria = example.createCriteria();
//        criteria.andIdEqualTo(roleid);
//        List<WiOrganizationRole> list2 = roleDao.selectByExample(example);
//        if (list2.isEmpty() || list2.get(0).getAuthLevel() <= role.getRoleLevel()) {
//            response.failure(ErrorCode.ERROR_PARAM_VIOLATION_EXCEPTION);
//            response.setMsg("角色权限值错误");
//            return response;
//        }

        // 判断角色name是否已经存在
        WiOrganizationRoleExample example = new WiOrganizationRoleExample();
        Criteria criteri = example.createCriteria();
        criteri.andNameEqualTo(role.getRoleName());
        criteri.andOrganizationIdEqualTo(role.getCompanyId());
        List<WiOrganizationRole> list = roleDao.selectByExample(example);

        if (list.size() != 0) {
            response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_EXIST);
            response.setMsg("新增的角色已经存在！！！");
            return response;
        }

        WiOrganizationRole data = new WiOrganizationRole();
        data.setAuthLevel(role.getRoleLevel());
        data.setDescription(StringUtil.isEmpty(role.getDesc()) ? "" : role.getDesc());
        data.setDepartId(role.getDepartId() == null ? 0 : role.getDepartId());
        data.setName(role.getRoleName());
        data.setOrganizationId(role.getCompanyId());
        roleDao.insertSelective(data);

        return response.success();
    }

    @Override
    public Response delRole(WiOrganizationRole role) {
        Response resp = new Response();
        roleDao.deleteByPrimaryKey(role.getId());
        return resp.success();
    }

    @Override
    public Response updateRole(WiOrganizationRole param) {
        Response resp = new Response();
        roleDao.updateByPrimaryKeySelective(param);
        return resp;
    }

    @Override
    public Response insertAuth(InsertAuthParam param) {
        Response resp = new Response();
        WiPermission data = new WiPermission();
        data.setCompanyId(param.getCompanyId());
        data.setModuleId(param.getModuleId());
        data.setRoleId(param.getRoleId());
        data.setInsertAuth(param.getInsertAuth());
        data.setUpdateAuth(param.getUpdateAuth());
        data.setQueryAuth(param.getQueryAuth());
        data.setStatus(param.getStatus());
        Integer tNow = DateUtil.getLinuxTimeStamp();
        data.setUpdated(tNow);
        data.setCreated(tNow);
        
        permissionDao.insert(data);
        return resp;
    }

    @Override
	public Response queryOrganizationRoleByPage(Page<WiOrganizationRole> page) {
		
		Response response=new Response();
		
		WiOrganizationRoleExample example=new WiOrganizationRoleExample();
		WiOrganizationRoleExample.Criteria criteria=example.createCriteria();
		WiOrganizationRole param = page.getParam();
		if(null != param.getOrganizationId()){
        	criteria.andOrganizationIdEqualTo(param.getOrganizationId());
        }
        if(StringUtil.isNotEmpty(param.getName())){
        	criteria.andNameLike("%" + param.getName() + "%");
        }

        page.setResult(roleDao.selectByExampleByPage(example, page));
		return response.success(page);
	}
}
