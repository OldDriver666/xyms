package com.fise.service.app;

import java.util.List;

import com.fise.base.Response;
import com.fise.model.entity.AppDownload;

public interface IAppDownloadService {
    /*查询下载记录*/
	Response queryAppDownload(AppDownload param);
    
    /*新增下载记录*/
    Response insertAppDownload(AppDownload param);
    
    /*批量新增下载记录*/
    Response addListAppDownload(List<AppDownload> param);
    
    /*修改下载记录*/
    Response updateAppDownload(AppDownload param);
    
    /*删除下载记录*/
    Response deleteAppDownload(Integer id);
    
}
