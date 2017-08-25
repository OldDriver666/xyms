package com.fise.service.user;

import com.fise.base.Response;

public interface IUserService {
    /*根据用户名    查询用户信息*/
    public Response queryUser(String name);
}
