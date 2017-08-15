package com.fise.service.answer;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.Answer;

public interface IAnswerService {
    /*提出回答*/
    public Response insertAnswer(Answer record);
    
    /*查询我的回答*/
    public Response queryAnswer(Page<Answer> page);
    
}
