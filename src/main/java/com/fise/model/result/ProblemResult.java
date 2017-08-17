package com.fise.model.result;

import java.io.Serializable;

import com.fise.model.entity.Problems;

public class ProblemResult implements Serializable{
    private static final long serialVersionUID=1L;
    
    private int addAnswerCount;
    private int addBrowseCount;
    private Problems problems;
    
    public int getAddAnswerCount() {
        return addAnswerCount;
    }
    public void setAddAnswerCount(int addAnswerCount) {
        this.addAnswerCount = addAnswerCount;
    }
    public int getAddBrowseCount() {
        return addBrowseCount;
    }
    public void setAddBrowseCount(int addBrowseCount) {
        this.addBrowseCount = addBrowseCount;
    }
    public Problems getProblems() {
        return problems;
    }
    public void setProblems(Problems problems) {
        this.problems = problems;
    }
    
    
}
