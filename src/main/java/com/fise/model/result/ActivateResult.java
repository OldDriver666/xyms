package com.fise.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivateResult implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("query_date")
    private String queryDate;
    
    private Integer count;

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
