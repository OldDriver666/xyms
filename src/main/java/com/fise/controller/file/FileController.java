package com.fise.controller.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fise.base.Response;

@RestController
@RequestMapping("/file")
public class FileController {
    
    //上传图片 
    @RequestMapping(value="/fileupload",method=RequestMethod.POST)
    public Response fileupload(@RequestBody MultipartFile[] uploadfile,HttpServletRequest req) throws IOException{
        Response response=new Response();
        
        MultipartFile file=null;
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
                    pictureURL=path+"/"+filename;
                }else {
                    pictureURL=pictureURL+","+path+"/"+filename;
                }
        
            }
        }
        response.success(pictureURL);
        return response;
    }
    
    @RequestMapping(value="/filedown",method=RequestMethod.POST)
    public void filedown(@RequestBody Map<String, String> map,HttpServletRequest req,HttpServletResponse resp) throws IOException{
        
        if(map.get("filedown")==null || "".equals(map.get("filedown"))){
            resp.getWriter().write("参数不能为空");
        }
        String fileName=map.get("filedown");
        fileName=req.getSession().getServletContext().getRealPath("upload")+"/"+fileName;
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(new File(fileName)));
        
        String filename=URLEncoder.encode(map.get("filedown"),"utf-8");
        resp.addHeader("Content-Disposition", "attachment;filename="+filename);
        resp.setContentType("multipart/form-data");
        
        BufferedOutputStream os=new BufferedOutputStream(resp.getOutputStream());
        
        int len=0;
        while((len=bis.read())!=-1){
            os.write(len);
            os.flush();
        }
        
        os.close();
        bis.close();
        
    }
}
