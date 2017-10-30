package com.fise.controller.problems;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.model.entity.Concern;
import com.fise.model.entity.Problems;
import com.fise.model.result.ProResult;
import com.fise.service.concern.IConcernService;
import com.fise.service.problems.IProblemService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IProblemService problemService;
    
    @Resource
    IConcernService concernService;
    
    /*提交问题*/
    @IgnoreAuth
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insertProblem(@RequestBody @Valid Problems record) throws IOException{
        Response res = new Response();
        logger.info(record.toString());
        
        /*if(record.getSchoolId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }*/
        
        if(record.getUserId()==null || StringUtil.isEmpty(record.getTitle())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(record.getContent()) && StringUtil.isEmpty(record.getPicture())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("内容不能为空");
            return res;
        }
        
        /*MultipartFile file=null;
        String pictureURL = "";
                   
        //上传图片文件
        if(uploadfile.length!=0){
            for(int i=0;i<uploadfile.length;i++){
                file=uploadfile[i];
                
                String path=req.getSession().getServletContext().getRealPath("upload");
                String filename=file.getOriginalFilename();
                File dir=new File(path,filename);
                if(!dir.exists()){
                    dir.mkdirs();
                }
            
                file.transferTo(dir);
                if(i==0){
                    pictureURL=path+"/"+filename+new Random().nextInt(16);
                }else {
                    pictureURL=pictureURL+","+path+"/"+filename+new Random().nextInt(16);
                }
        
            }
        }
                        
        record.setPicture(pictureURL);*/
        res=problemService.insert(record);
        //默认已经关注
        if(res.getData()!=null){
            res=concernService.addConcern((Concern)res.getData());
            return res.success();
        }
        return res;
    }
    
    /*查询问题    分页形式*/
    /*@IgnoreAuth
    @RequestMapping(value="/queryall",method=RequestMethod.POST)
    public Response queryProblem(@RequestBody @Valid Page<Problems> param){
        Response res = new Response();
        Response res1 = new Response();
        Concern record=new Concern();
        logger.info(param.toString());
        
        if(param.getParam().getSchoolId()==null || param.getParam().getUserId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.queryAll(param);
        
        //添加判断用户是否关注该问题
        Page<ProResult> page=(Page<ProResult>)res.getData();
        List<ProResult> list=page.getResult();
        for(int i=0;i<list.size();i++){
            record.setProblemId(list.get(i).getId());
            record.setUserId(param.getParam().getUserId());
            res1=concernService.queryisConcern(record);
            list.get(i).setIsConcern(res1.getMsg());
        }
        return res;
                   
    }*/
    
    /*查询图片*/
    @IgnoreAuth
    @RequestMapping(value="/picture",method=RequestMethod.POST)
    public void filedown(@RequestBody Map<String, String> map,HttpServletResponse resp) throws IOException{
        
        logger.info(map.toString());
        if(map.get("picture")==null || "".equals(map.get("picture"))){
            resp.getWriter().write("参数不能为空");
        }
        
        String fileName=map.get("picture");     
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(new File(fileName)));
        
        String filename=URLEncoder.encode(fileName,"utf-8");
        resp.addHeader("Content-Disposition", "attachment;filename="+filename);
        
        //resp.setContentType("multipart/form-data");
        
        BufferedOutputStream os=new BufferedOutputStream(resp.getOutputStream());
        
        int len=0;
        while((len=bis.read())!=-1){
            os.write(len);
            os.flush();
        }
        
        os.close();
        bis.close();
        
    }
    
    @IgnoreAuth
    @RequestMapping(value="/queryall",method=RequestMethod.POST)
    public Response queryProblem(@RequestBody @Valid Page<Problems> param){
        Response res = new Response();
        Response res1 = new Response();
        Concern record=new Concern();
        logger.info(param.toString());
        
        if(param.getParam().getUserId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.queryAll(param);
        
        //添加判断用户是否关注该问题
        Page<ProResult> page=(Page<ProResult>)res.getData();
        List<ProResult> list=page.getResult();
        for(int i=0;i<list.size();i++){
            record.setProblemId(list.get(i).getId());
            record.setUserId(param.getParam().getUserId());
            res1=concernService.queryisConcern(record);
            list.get(i).setIsConcern(res1.getMsg());
        }
        return res;
                   
    }
    
    /*根据话题  模糊查询  分页展示*/
    @IgnoreAuth
    @RequestMapping(value="/titlequery",method=RequestMethod.POST)
    public Response titlequery(@RequestBody @Valid Page<Problems> param){
        Response res = new Response();
        logger.info(param.toString());
        
        if(param.getParam().getSchoolId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(param.getParam().getTitle())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.queryTitle(param);
        return res;
    }
    
    /*查询我的问题     获取更新的回答数量信息*/
    @IgnoreAuth
    @RequestMapping(value="/myproblem",method=RequestMethod.POST)
    public Response myProblem(@RequestBody @Valid Page<Problems> param){
        Response res = new Response();
        logger.info(param.toString());
        
        if(param.getParam().getUserId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.queryMypro(param);
        return res;
    }
    
    /*根据问题id，查询问题详情    */
    @IgnoreAuth
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Map<String, Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("problem_id")==null || map.get("user_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.query(map.get("problem_id"),map.get("user_id"));
        return res;
    }
    
    /*后台管理 查询问题*/
    @RequestMapping(value="/queryback",method=RequestMethod.POST)
    public Response queryBack(@RequestBody @Valid Page<Problems> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=problemService.queryBack(param);
        return resp;
    }
    
    /*后台管理  修改问题*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid Problems param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        resp=problemService.update(param);
        return resp;
    }
    
}
