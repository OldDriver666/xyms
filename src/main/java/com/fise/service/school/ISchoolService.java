package com.fise.service.school;

import com.fise.base.Response;

public interface ISchoolService {
    /*根据shcool_id查询学校*/
    public Response querySchool(Integer[] school_id);
}
