package com.fise.service.report.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.KeyValueMap;
import com.fise.base.Response;
import com.fise.dao.ReportMapper;
import com.fise.model.entity.IMMessage0;
import com.fise.model.param.ReportActivateParam;
import com.fise.model.result.ActivateResult;
import com.fise.model.result.MessageTypeResult;
import com.fise.service.report.IReportService;

@Service
public class ReportServiceImpl implements IReportService {

    private Logger logger=Logger.getLogger(getClass());
    
    @Autowired
    ReportMapper reportDao;
    
    @Override
    public Response queryActivate(ReportActivateParam param) {
        Response resp = new Response();
        List<ActivateResult> data = reportDao.queryActivateCountByDate(param);
        Map<String, Integer> resultData = new HashMap<String, Integer>();
        for (ActivateResult tmp : data){
            resultData.put(tmp.getQueryDate(), tmp.getCount());
        }
        resp.success(resultData);
        return resp;
    }

    @Override
    public Response queryAboutPage(Integer companyId) {
        Response resp = new Response();
        
        List<KeyValueMap> sexCount = reportDao.queryUserSexCount(companyId);
        List<KeyValueMap> typeCount = reportDao.queryUserTypeCount(companyId);
        List<KeyValueMap> provinceCount = reportDao.queryUserProviceCount(companyId);
        
        Map<String, Object> resultData = new HashMap<String, Object>();
        
        Map<String, Integer> sexData = new HashMap<String, Integer>();
        for (KeyValueMap tmp : sexCount){
            sexData.put(tmp.getKeyName(), ((Long)tmp.getKeyValue()).intValue());
        }
        resultData.put("sex", sexData);

        Map<String, Integer> typeData = new HashMap<String, Integer>();
        for (KeyValueMap tmp : typeCount){
            typeData.put(tmp.getKeyName(), ((Long)tmp.getKeyValue()).intValue());
        }
        resultData.put("type", typeData);
        
        String splitStr = new String();
        int index = 0;
        Map<String, Integer> provinceData = new HashMap<String, Integer>();
        for (KeyValueMap tmp : provinceCount){
            //去掉"省"字
            splitStr = tmp.getKeyName();
            index = splitStr.indexOf("省");
            if (index == -1){
                index = splitStr.length();
            }
            provinceData.put(splitStr.substring(0, index), ((Long)tmp.getKeyValue()).intValue());
        }
        resultData.put("province", provinceData);
        resp.success(resultData);
        return resp;
    }

    @Override
    public Response queryMessages(String daytime) {
        Response response=new Response();
           
        int count = 0;
        for(int i=0;i<8;i++){
            String tablename="IMMessage_";
            tablename+=i;
            if(reportDao.querydaymessages(tablename, daytime)!=null){
                count+=reportDao.querydaymessages(tablename, daytime).getKeyValue();
            }

            tablename="IMGroupMessage_";
            tablename+=i;
            if(reportDao.querydaymessages(tablename, daytime)!=null){
                count+=reportDao.querydaymessages(tablename, daytime).getKeyValue();
            }
    
        }
        
        response.success(count);
        return response;
    }

    @Override
    public Response queryMessageType() {
        Response response=new Response();
        List<MessageTypeResult> list=null;
        List<MessageTypeResult> list1=new ArrayList<MessageTypeResult>();
        
        for(int i=0;i<8;i++){
            String tablename="IMMessage_";
            tablename+=i;
            if(list==null){
                list=reportDao.querytypemessages(tablename);
                
            }else{
                list1=reportDao.querytypemessages(tablename);
            }
            
            for(MessageTypeResult map1:list1){
                for(MessageTypeResult map:list){
                    if(map.getKeyName()==map1.getKeyName()){
                        map.setKeyValue(map.getKeyValue()+map1.getKeyValue());
                        //list1.remove(map1);
                        map1.setKeyName(0);
                        break;
                    }
                }
            }
            
            list.addAll(list1);
            
            tablename="IMGroupMessage_";
            tablename+=i;
            list1=reportDao.querytypemessages(tablename);
            
            for(MessageTypeResult map1:list1){
                for(MessageTypeResult map:list){
                    if(map.getKeyName()==map1.getKeyName()){
                        map.setKeyValue(map.getKeyValue()+map1.getKeyValue());
                        //list1.remove(map1);
                        map1.setKeyName(0);
                        break;
                    }
                }
            }
            list.addAll(list1);
        }
        
        Iterator<MessageTypeResult> it=list.iterator();
        while(it.hasNext()){
            MessageTypeResult result=it.next();
            if(result.getKeyName()==0){
                it.remove();
            }
        }
        
        return response.success(list);
    }
}
