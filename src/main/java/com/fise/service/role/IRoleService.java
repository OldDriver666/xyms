package com.fise.service.role;

import java.util.List;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.WiOrganizationRole;
import com.fise.model.param.InsertAuthParam;
import com.fise.model.param.InsertRoleParam;
import com.fise.model.param.QueryRoleParam;
import com.fise.model.param.RolePermissionParam;
import com.fise.model.result.ModulePermissResult;

public interface IRoleService {
    /* 查询所有的角色记录 */
    public List<WiOrganizationRole> queryRole(QueryRoleParam param);

    /* 查询角色并返回对应权限-不包括不可见的 */
    public List<ModulePermissResult> queryRoleAuth(QueryRoleParam param);
    
    /* 根据模块名称模糊查询并返回对应权限-不包括不可见的 */
    public List<ModulePermissResult> queryAuthByName(QueryRoleParam param);

    //更新角色信息
    public Response updateRole(WiOrganizationRole param);
    /* 修改角色权限配置 */
    public Response updateRoleAuth(RolePermissionParam param);

    /* 新增角色 */
    public Response insertRole(InsertRoleParam role);
    public Response insertAuth(InsertAuthParam role);

    public Response delRole(WiOrganizationRole role);
    
    /* 分页查询角色 */
    public Response queryOrganizationRoleByPage(Page<WiOrganizationRole> page);
}
