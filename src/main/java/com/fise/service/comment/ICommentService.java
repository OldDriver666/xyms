package com.fise.service.comment;

import com.fise.base.Response;
import com.fise.model.entity.Comment;

public interface ICommentService {
    /*发布评论*/
    public Response addComment(Comment record);
    
    
}
