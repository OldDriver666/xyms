package com.fise.service.fisedevice.impl;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.FiseDeviceMapper;
import com.fise.model.entity.ExcelFiseDevice;
import com.fise.model.entity.FiseDevice;
import com.fise.model.entity.FiseDeviceExample;
import com.fise.model.entity.FiseDeviceExample.Criteria;
import com.fise.model.param.QueryFiseDeviceParam;
import com.fise.service.fisedevice.IFiseDeviceService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class FiseDeviceServiceImpl implements IFiseDeviceService{
	
	@Autowired
	FiseDeviceMapper fiseDevicedao;
	
	@Override
	public Response insertFiseDevice(FiseDevice record) {
		
		Response response=new Response();
		
		//判断添加的设备的IME是否已经存在
		FiseDeviceExample exampleIME=new FiseDeviceExample();
		Criteria criteriaIME=exampleIME.createCriteria();
		criteriaIME.andImeEqualTo(record.getIme());
		List<FiseDevice> fisedevicelist=fiseDevicedao.selectByExample(exampleIME);
		if(fisedevicelist.size()!=0){
			response.failure(ErrorCode.ERROR_FISE_DEVICE_IME_EXISTED);
			return response;
		}
		
		//判断添加的设备的账号是否已经注册
		FiseDeviceExample exampleAccount=new FiseDeviceExample();
		Criteria criteriaAccount=exampleAccount.createCriteria();
		criteriaAccount.andAccountEqualTo(record.getAccount());
		List<FiseDevice> list=fiseDevicedao.selectByExample(exampleAccount);
		if(list.size()!=0){
			response.failure(ErrorCode.ERROR_FISE_DEVICE_ACCOUNT_EXISTED);
			return response;
		}
		
		//更新设备相关信息
		long nowtime=System.currentTimeMillis() / 1000;
		record.setUpdated((int)nowtime);
		record.setCreated((int)nowtime);
		fiseDevicedao.insertSelective(record);
		response.success();
		return response;
	}

	@Override
	public Response queryFiseDevice(Page<QueryFiseDeviceParam> page) {
		
		Response response=new Response();
		
		FiseDeviceExample example=new FiseDeviceExample();
		Criteria criteria=example.createCriteria();
		criteria.andDepartidEqualTo(page.getParam().getDepartid());
		
		if(!StringUtil.isEmpty(page.getParam().getIme())){
			criteria.andImeEqualTo(page.getParam().getIme());
		}
		
		if(page.getParam().getStatus()!=null){
		    criteria.andStatusEqualTo(page.getParam().getStatus());
		}
		
		if(!StringUtil.isEmpty(page.getParam().getAccount())){
			criteria.andAccountEqualTo(page.getParam().getAccount());	
		} 
		
		List<FiseDevice> fisedevicelist=fiseDevicedao.selectByPage(example,page);
		
		Page<FiseDevice> page2=new Page<FiseDevice>();
		page2.setPageNo(page.getPageNo());
        page2.setPageSize(page.getPageSize());
        page2.setTotalCount(page.getTotalCount());
        page2.setTotalPageCount(page.getTotalPageCount());
        page2.setResult(fisedevicelist);
        
		if(fisedevicelist.size()!=0){
			response.success(page2);
			return response;
		}
		
		return response.failure(ErrorCode.ERROR_DB_RECORD_ALREADY_UNEXIST);
	}

	@Override
	public Response delFiseDevice(QueryFiseDeviceParam param) {
		
		Response response=new Response();
		
		fiseDevicedao.deleteByPrimaryKey(param.getFiseId());
		return response.success();
		
	}

	@Override
	public Response updateFiseDevice(FiseDevice param) {

		Response response=new Response();
		
		if(StringUtil.isEmpty(param.getIme())){
		    return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
		}
		if(StringUtil.isEmpty(param.getAccount())){
		    return response.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
		}
		
		if(!StringUtil.isEmpty(param.getIme())){
			//判断修改的设备的IME是否已经存在
			FiseDeviceExample exampleIME=new FiseDeviceExample();
			Criteria criteriaIME=exampleIME.createCriteria();
			criteriaIME.andImeEqualTo(param.getIme());
			List<FiseDevice> fisedevicelist=fiseDevicedao.selectByExample(exampleIME);
			
			if(fisedevicelist.size()!=0){
				if(param.getId().equals(fisedevicelist.get(0).getId())){
					
				}else{
					response.failure(ErrorCode.ERROR_FISE_DEVICE_IME_EXISTED);
					return response;
				}
			}
		}
		
		if(!StringUtil.isEmpty(param.getAccount())){
			//判断修改的设备的账号是否已经注册
			FiseDeviceExample exampleAccount=new FiseDeviceExample();
			Criteria criteriaAccount=exampleAccount.createCriteria();
			criteriaAccount.andAccountEqualTo(param.getAccount());
			List<FiseDevice> list=fiseDevicedao.selectByExample(exampleAccount);
			
			if(list.size()!=0){
				if(param.getId().equals(list.get(0).getId())){
					
				}else{
					response.failure(ErrorCode.ERROR_FISE_DEVICE_ACCOUNT_EXISTED);
					return response;
				}
			}
		}
		
		//更新设备相关信息
		long nowtime=System.currentTimeMillis() / 1000;
		param.setUpdated((int)nowtime);
		fiseDevicedao.updateByPrimaryKeySelective(param);
		response.success();
		return response;
		
	}

    @Override
    public Response insertExcel(List<ExcelFiseDevice> param) {
        Response resp = new Response();
        
        Iterator<ExcelFiseDevice> it=param.iterator();
        
        while(it.hasNext()){
            ExcelFiseDevice record=it.next();
            record.setUpdated(DateUtil.getLinuxTimeStamp());
            record.setCreated(DateUtil.getLinuxTimeStamp());
        }
        
        fiseDevicedao.excelImport(param);
        return resp.success();
    }

}
