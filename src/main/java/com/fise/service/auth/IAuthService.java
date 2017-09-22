package com.fise.service.auth;

public interface IAuthService {
    //查询用户是否有该功能模块的查询权限
    public Boolean queryAuth();
    
    //查询用户是否有该功能模块的添加权限
    public Boolean inserAuth();
    
    //查询用户是否有该功能模块的更新权限
    public Boolean updateAuth();
}
