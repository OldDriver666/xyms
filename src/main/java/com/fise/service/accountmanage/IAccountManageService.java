package com.fise.service.accountmanage;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.WiAccountManage;

public interface IAccountManageService {
    //新增
    Response insertAccount(WiAccountManage param);
    
    //查询
    Response queryAccount(Integer depart_id);
    
    //删除
    Response delAccount(Integer id);
    
    //修改
    Response updateAccount(WiAccountManage param);

    //分页查询
	Response queryAccountPage(Page<WiAccountManage> param);
}
