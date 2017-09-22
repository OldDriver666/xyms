package com.fise.service.smsplatfrom;

import com.fise.base.Response;
import com.fise.model.entity.IMSmsPlatfrom;
import com.fise.model.param.SmsPlatfromParam;


public interface ISmsPlatfromService {
    /*添加短信平台*/
    Response addSmsPlatfrom(IMSmsPlatfrom record);
    
    /*查询短信平台*/
    Response querySmsPlatfrom(SmsPlatfromParam param);
    
    /*修改短信平台*/
    Response updateSmsPlatfrom(IMSmsPlatfrom record);
    
    /*删除短信平台*/
    Response delSmsPlatfrom(SmsPlatfromParam param);
}
