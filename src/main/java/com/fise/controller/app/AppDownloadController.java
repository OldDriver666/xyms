package com.fise.controller.app;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.Response;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.model.entity.AppDownload;
import com.fise.service.app.IAppDownloadService;
import com.fise.utils.JsonUtil;

@RestController
@RequestMapping("/boss/appdownload")
public class AppDownloadController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource 
    IAppDownloadService appDownloadSvr;
    
    @IgnoreAuth
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody @Valid AppDownload param){

        Response resp = new Response();
        resp.success(appDownloadSvr.queryAppDownload(param));

        logger.info("获取下载记录:"+JsonUtil.toJson(param));
        return resp;
    }
    @IgnoreAuth
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response insert(@RequestBody @Valid AppDownload param){
        Response resp = new Response();
        
        resp = appDownloadSvr.insertAppDownload(param);
        logger.info("新增下载记录:"+JsonUtil.toJson(param)+"结果:" + resp.getMsg());
        return resp;
    }
    @IgnoreAuth
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody @Valid AppDownload param){
        Response resp = new Response();
        
        resp = appDownloadSvr.updateAppDownload(param);
        
        logger.info("修改下载记录:"+param.toString()+" 结果:"+resp.getMsg());
        return resp;
    }
    @IgnoreAuth
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody @Valid AppDownload param){
        Response resp = new Response();
        
        resp = appDownloadSvr.deleteAppDownload(param.getId());

        return resp;
    }
}
