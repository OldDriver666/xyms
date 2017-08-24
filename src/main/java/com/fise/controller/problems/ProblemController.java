package com.fise.controller.problems;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.Problems;
import com.fise.service.problems.IProblemService;
import com.fise.utils.StringUtil;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IProblemService problemService;
    
    /*提交问题*/
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insertProblem(@RequestBody @Valid Problems record,MultipartFile[] uploadfile,HttpServletRequest req) throws IOException{
        Response res = new Response();
        logger.info(record.toString());
        
        if(record.getSchoolId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        MultipartFile file=null;
        String pictureURL = "";
        
        for(int i=0;i<uploadfile.length;i++){
            file=uploadfile[i];
            
            //上传图片文件
            if(file!=null){
                String path=req.getSession().getServletContext().getRealPath("upload");
                String filename=file.getOriginalFilename();
                File dir=new File(path,filename);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                
                file.transferTo(dir);
                if(i==0){
                    pictureURL=path+"/"+filename;
                }else {
                    pictureURL=pictureURL+","+path+"/"+filename;
                }
            }
        }
        
        record.setPicture(pictureURL);
        res=problemService.insert(record);
        return res;
    }
    
    /*查询问题    分页形式*/
    @RequestMapping(value="/queryall",method=RequestMethod.POST)
    public Response queryProblem(@RequestBody @Valid Page<Problems> param){
        Response res = new Response();
        logger.info(param.toString());
        
        if(param.getParam().getSchoolId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.queryAll(param);
        
        return res;
                   
    }
    
    /*查询图片*/
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
    
    /*根据话题  模糊查询  分页展示*/
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
    @RequestMapping(value="/myproblem",method=RequestMethod.POST)
    public Response myProblem(@RequestBody @Valid Page<Problems> param){
        Response res = new Response();
        logger.info(param.toString());
        
        if(StringUtil.isEmpty(param.getParam().getName())){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.queryMypro(param);
        return res;
    }
    
    /*根据问题id，查询问题详情    */
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Map<String, Object> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("problem_id")==null || StringUtil.isEmpty(map.get("name")+"")){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.query((Integer)map.get("problem_id"),(String)map.get("name")+"");
        return res;
    }
}
