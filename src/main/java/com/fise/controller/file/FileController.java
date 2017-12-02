package com.fise.controller.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.framework.annotation.AuthValid;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.utils.Constants;

@RestController
@RequestMapping("/file")
public class FileController {
	
	private Logger logger = Logger.getLogger(this.getClass());
    
    //上传图片 
    @AuthValid
    @RequestMapping(value="/fileupload",method=RequestMethod.POST)
    public Response fileupload(@RequestBody @RequestParam("file") MultipartFile[] uploadfile,HttpServletRequest req) throws IOException{
        Response response=new Response();
        
        MultipartFile file=null;
        String pictureURL = "";
        
        //上传图片文件
        if(uploadfile.length!=0){
            for(int i=0;i<uploadfile.length;i++){
                file=uploadfile[i];
                
                /*内网上传图片路径*/
                //String path="/home/fise/bin/www/upload";
                /*外网上传图片路径*/
                String path="/home/fise/www/upload";
                
                String filename=file.getOriginalFilename().replace(".", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".");
                File dir=new File(path,filename);
                if(!dir.exists()){
                    dir.mkdirs();
                }
            
                file.transferTo(dir);
                System.out.println("=========="+path+"/"+filename);
                //在Linux服务器里将上传文件改为fise用户权限
                Runtime.getRuntime().exec("chown fise:fise "+path+"/"+filename);
                if(i==0){
                    /*内网上传图片路径*/
                    //pictureURL=Constants.IN_FILE_UPLOAD_URL+filename;
                    /*外网上传图片路径*/
                    pictureURL=Constants.OUT_FILE_UPLOAD_URL+filename;
                }else {
                    /*内网上传图片路径*/
                    //pictureURL=pictureURL+Constants.IN_FILE_UPLOAD_URL+filename;
                    /*外网上传图片路径*/
                    pictureURL=pictureURL+Constants.OUT_FILE_UPLOAD_URL+filename;
                }
        
            }
        }
        response.success(pictureURL);
        return response;
    }
    
    @IgnoreAuth
    @RequestMapping(value="/filedown",method=RequestMethod.POST)
    public Response filedown(@RequestBody Map<String, String> map,HttpServletRequest req,HttpServletResponse resp){
        Response res = new Response();
        BufferedOutputStream os = null;
        BufferedInputStream bis = null;
        try{
        	if(map.get("filedown")==null || "".equals(map.get("filedown"))){
                resp.getWriter().write("参数不能为空");
            }
            String fileName=map.get("filedown");
            fileName="/home/fise/bin/www/upload/"+fileName;
            bis=new BufferedInputStream(new FileInputStream(new File(fileName)));
            
            String filename=URLEncoder.encode(map.get("filedown"),"utf-8");
            resp.addHeader("Content-Disposition", "attachment;filename="+filename);
            resp.setContentType("multipart/form-data");
            
            os=new BufferedOutputStream(resp.getOutputStream());
            
            int len=0;
            while((len=bis.read())!=-1){
                os.write(len);
                os.flush();
            }
            return res.success();
        } catch (Exception e){
        	logger.info(e);
            res.failure(ErrorCode.ERROR_SYSTEM);
            return res;
        } finally {
        	try {
        		if (null != os) {
        			os.close();
        		}
			} catch (Exception e) {
				logger.info(e);
			}
        	try {
        		if (null != bis) {
        			bis.close();
        		}
			} catch (Exception e) {
				logger.info(e);
			}
        }
        
    }
}
