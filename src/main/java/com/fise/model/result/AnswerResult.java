package com.fise.model.result;

import java.io.Serializable;

import com.fise.model.entity.Answer;

public class AnswerResult implements Serializable{
    private static final long serialVersionUID=1L;
    
    private int addAgreeCount;
    private int addCommentCount;
    private Answer answer;
    
    public int getAddAgreeCount() {
        return addAgreeCount;
    }
    public void setAddAgreeCount(int addAgreeCount) {
        this.addAgreeCount = addAgreeCount;
    }
    public int getAddCommentCount() {
        return addCommentCount;
    }
    public void setAddCommentCount(int addCommentCount) {
        this.addCommentCount = addCommentCount;
    }
    public Answer getAnswer() {
        return answer;
    }
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
    
    
}
