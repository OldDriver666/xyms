package com.fise.controller.organization;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.model.entity.WiOrganization;
import com.fise.service.auth.IAuthService;
import com.fise.service.organization.IOrganizationService;
import com.fise.utils.JsonUtil;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/boss/organization")
public class OrganizationController {

    private Logger logger = Logger.getLogger(getClass());

    @Resource
    IOrganizationService organtSvr;

    @Resource
    IAuthService authService;

    /* 查询公司组织 */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response queryOrgan(@RequestBody @Valid WiOrganization param) {

        Response response = new Response();
        response = organtSvr.QueryOrganization(param.getName());
        logger.info("查询公司:"+JsonUtil.toJson(param)+" 结果:"+response.getMsg());
        return response;
    }

    /* 插入公司组织 */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response insertOrgan(@RequestBody @Valid WiOrganization param) {

        Response response = new Response();

        if (authService.inserAuth()) {
            if (!StringUtil.isEmpty(param.getName())) {
                response = organtSvr.InsertOrganization(param);
            } 
            else {
                response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            }
        } 
        else {
            response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }

        logger.info("新增公司:"+JsonUtil.toJson(param) + "结果:" + response.getMsg());
        return response;
    }

    /* 修改公司组织 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response updateOrgan(@RequestBody @Valid WiOrganization param) {

        Response response = new Response();

        if (authService.updateAuth()) {
            if(param.getId() != null){
                response = organtSvr.UpdateOrganization(param);
            }
            else{
                response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            }
        }
        else{
            response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }

        logger.info("更新公司:"+JsonUtil.toJson(param)+" 结果:"+response.getMsg());
        return response;
    }

    /* 删除公司组织 */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Response delOrgan(@RequestBody @Valid WiOrganization param) {

        Response response = new Response();

        if (!authService.updateAuth()) {
            response.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }

        if (param.getId() != null){
            response = organtSvr.delOrganization(param);
        }
        else{
            response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }

        logger.info("删除公司:"+JsonUtil.toJson(param)+" 结果:"+response.getMsg());
        return response;
    }
}
