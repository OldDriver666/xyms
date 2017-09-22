package com.fise.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fise.framework.annotation.NotEmpty;
import com.fise.utils.JsonUtil;

public class ReportActivateParam implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @JsonProperty("organ_id")
    private Integer organId;
    
    @NotEmpty
    @JsonProperty("begin_date")
    private String beginDate;
    
    @NotEmpty
    @JsonProperty("end_date")
    private String endDate;

    
    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
