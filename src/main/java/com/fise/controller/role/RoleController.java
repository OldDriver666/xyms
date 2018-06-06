package com.fise.controller.role;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.HttpContext;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.model.entity.WiOrganizationRole;
import com.fise.model.param.InsertAuthParam;
import com.fise.model.param.InsertRoleParam;
import com.fise.model.param.QueryRoleParam;
import com.fise.model.param.RolePermissionParam;
import com.fise.model.result.ModulePermissResult;
import com.fise.service.auth.IAuthService;
import com.fise.service.role.IRoleService;

@RestController
@RequestMapping("/boss/role")
public class RoleController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource
    IRoleService roleSvr;

    @Resource
    IAuthService authService;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response queryRole(@RequestBody @Valid QueryRoleParam param) {
        Response resp = new Response();

        List<WiOrganizationRole> data = roleSvr.queryRole(param);
        if (data == null || data.isEmpty()) {
            resp.failure(ErrorCode.ERROR_DATABASE);
        } else {
            resp.success(data);
        }
        logger.info("查询角色:" + param.toString() + " 结果:" + resp.getMsg());
        return resp;
    }

    @RequestMapping(value = "/queryAuth", method = RequestMethod.POST)
    public Response queryAuth(@RequestBody @Valid QueryRoleParam param) {
        Response resp = new Response();

        List<ModulePermissResult> data = roleSvr.queryRoleAuth(param);
        resp.success(data);

        return resp;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody @Valid WiOrganizationRole param) {
        Response resp = new Response();
        
        if (!authService.updateAuth()) {
            return resp.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        if (param.getId() == null) {
            return resp.failure(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
        }
        resp = roleSvr.updateRole(param);
        return resp;
    }

    @RequestMapping(value = "/updateAuth", method = RequestMethod.POST)
    public Response updateAuth(@RequestBody @Valid RolePermissionParam param) {
        Response resp = new Response();

        if (!authService.updateAuth()) {
            return resp.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }

        resp = roleSvr.updateRoleAuth(param);
        return resp;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response insertRole(@RequestBody @Valid InsertRoleParam role) {
        Response response = new Response();

        if (!authService.inserAuth()) {
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        response = roleSvr.insertRole(role);
        logger.info("新增角色:" + role.toString() + " 结果:" + response.getMsg());
        return response;
    }

    @RequestMapping(value = "/insertAuth", method = RequestMethod.POST)
    public Response insertAuth(@RequestBody @Valid InsertAuthParam role) {
        Response response = new Response();

        if (!authService.inserAuth()) {
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        response = roleSvr.insertAuth(role);
        logger.info("新增角色:" + role.toString() + " 结果:" + response.getMsg());
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response deleteRole(@RequestBody @Valid WiOrganizationRole role) {
        Response response = new Response();

        if (!authService.inserAuth()) {
            return response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        logger.info(role.toString());

        if (role.getId() == null) {
            return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        response = roleSvr.delRole(role);
        return response;
    }
    
    @RequestMapping(value = "/queryAuthByName", method = RequestMethod.POST)
    public Response queryAuthByName(@RequestBody @Valid QueryRoleParam param) {
        Response resp = new Response();

        List<ModulePermissResult> data = roleSvr.queryAuthByName(param);
        resp.success(data);

        return resp;
    }
    
    @IgnoreAuth
    @RequestMapping(value = "/queryRoleByPage", method = RequestMethod.POST)
    public Response queryWiOrganizationRoleByPage(@RequestBody @Valid Page<WiOrganizationRole> page) {
        Response resp = new Response();
        resp = roleSvr.queryOrganizationRoleByPage(page);
        return resp;
    }
}
