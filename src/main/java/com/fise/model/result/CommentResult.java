package com.fise.model.result;

import com.fise.model.entity.Comment;

public class CommentResult {
    
    private int addreply;
    private Comment comment;
    
    public int getAddreply() {
        return addreply;
    }
    public void setAddreply(int addreply) {
        this.addreply = addreply;
    }
    public Comment getComment() {
        return comment;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }
    
    
}
