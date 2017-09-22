package com.fise.service.administrator;

import javax.servlet.http.HttpServletRequest;

import com.fise.base.Response;
import com.fise.model.param.AdminInsert;
import com.fise.model.param.AdminQuery;
import com.fise.model.param.AdminUpdate;
import com.fise.model.param.LoginParam;
import com.fise.model.param.LogoutParam;

public interface IAdministratorService {
    /* 用户登录 */
    public Response login(LoginParam param);

    /* 用户退出 */
    public Response logout(LogoutParam param, HttpServletRequest request);

    /* INSERT INTO wi_admin */
    public Response insertAdmin(AdminInsert param);

    /* UPDATE wi_admin */
    public Response updateAdmin(AdminUpdate param);
    
    /* DELETE wi_admin */
    public Response deleteAdmin(AdminUpdate param);

    /* 查询公司所有管理员 */
    public Response queryAdmin(AdminQuery param);

    /* 判断用户是否异地登录 */
    public Response isLogin(String accessToken);
}
