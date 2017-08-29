package com.fise.controller.school;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.service.school.ISchoolService;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    ISchoolService schoolService;
    
    @RequestMapping(value="/queryschool",method=RequestMethod.POST)
    public Response querySchool(@RequestBody @Valid Map<String, Integer[]> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("school_id").length==0){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=schoolService.querySchool(map.get("school_id"));
        return res;
    }
}
