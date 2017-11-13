package com.fise.service.answer;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.Answer;

public interface IAnswerService {
    /*提出回答*/
    public Response insertAnswer(Answer record);
    
    /*查询我的回答*/
    public Response queryMyAnswer(Page<Answer> page);
    
    /*查询问题的回答 */
    public Response queryAnswerById(Page<Answer> page,String order);
    
    /*根据回答id，查询更新信息*/
    public Response query(Integer answer_id,Integer user_id);
    
    /*根据回答id，删除我的回答*/
    public Response delMyAnswer(Integer answer_id);
    
    /*后台管理  查询回答信息*/
    public Response queryBack(Page<Answer> page);
    
    /*后台管理  更新回答信息*/
    public Response update(Answer param);
}
