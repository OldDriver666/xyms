package com.fise.service.sensitiveword;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.SensitiveWords;

public interface ISensitivewordService {
    /*插入敏感词*/
    public Response insert(SensitiveWords param);
    
    /*查询敏感词*/
    public Response query(Page<SensitiveWords> param);
    
    /*删除敏感词*/
    public Response delete(Integer id);
    
    
}
