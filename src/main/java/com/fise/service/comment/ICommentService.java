package com.fise.service.comment;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.Comment;

public interface ICommentService {
    /*发布评论*/
    public Response addComment(Comment record);
    
    /*查询回答评论 */
    public Response queryComment(Page<Comment> page);
    
    /*查询我的评论*/
    public Response queryMyComment(Page<Comment> page);
    
    /*查询评论*/
    public Response query(Integer comment_id,Integer page_no,Integer from_userid);
    
    /*根据评论id查询评论*/
    public Response queryById(Integer comment_id);
    
    /*后台管理  评论查询*/
    public Response queryBack(Page<Comment> page);
    
    /*后台管理  评论更新*/
    public Response update(Comment param);
}
