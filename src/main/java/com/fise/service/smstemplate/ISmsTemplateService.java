package com.fise.service.smstemplate;

import com.fise.base.Response;
import com.fise.model.entity.IMSmsTemplate;
import com.fise.model.param.SmsTemplateParam;

public interface ISmsTemplateService {
    /*添加短信模板*/
    Response addSmsTemplate(IMSmsTemplate record);
    
    /*查询短信模板*/
    Response querySmsTemplate(SmsTemplateParam param);
    
    /*修改短信模板*/
    Response updateSmsTemplate(IMSmsTemplate record);
    
    /*删除短信模板*/
    Response delSmsTemplate(IMSmsTemplate record);
}
