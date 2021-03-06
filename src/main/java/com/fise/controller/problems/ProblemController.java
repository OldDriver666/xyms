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
import com.fise.framework.annotation.AuthValid;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.Concern;
import com.fise.model.entity.Problems;
import com.fise.model.param.ProblemsParam;
import com.fise.model.result.ProResult;
import com.fise.service.auth.IAuthService;
import com.fise.service.concern.IConcernService;
import com.fise.service.problems.IProblemService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    private Logger logger=Logger.getLogger(getClass());
    
    @Resource
    IProblemService problemService;
    
    @Resource
    IConcernService concernService;
    
    @Resource
    IAuthService authService;
    
    /*提交问题*/
    @AuthValid
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insertProblem(@RequestBody @Valid Problems record) throws IOException{
        Response res = new Response();
        logger.info(record.toString());
        
        /*if(record.getSchoolId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }*/
        
        if(record.getUserId()==null || StringUtil.isEmpty(record.getTitle())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("话题不能为空");
            return res;
        }
        
        if(StringUtil.isEmpty(record.getContent())){
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
    @AuthValid
    @RequestMapping(value="/picture",method=RequestMethod.POST)
    public void filedown(@RequestBody Map<String, String> map,HttpServletResponse resp) throws IOException{
        
        logger.info(map.toString());
        BufferedOutputStream os = null;
        BufferedInputStream bis = null;
        try {
            if (map.get("picture") == null || "".equals(map.get("picture"))) {
                resp.getWriter().write("参数不能为空");
            }
            String fileName = map.get("picture");
            bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            String filename = URLEncoder.encode(fileName, "utf-8");
            resp.addHeader("Content-Disposition", "attachment;filename=" + filename);
            // resp.setContentType("multipart/form-data");
            os = new BufferedOutputStream(resp.getOutputStream());
            int len = 0;
            while ((len = bis.read()) != -1) {
                os.write(len);
                os.flush();
            }
        } catch (Exception e) {
            logger.info(e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
                logger.info(e);
                throw new RuntimeException(e);
            }
            try {
                if (null != bis) {
                    bis.close();
                }
            } catch (Exception e) {
                logger.info(e);
                throw new RuntimeException(e);
            }
        }
    }
    
    @AuthValid
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
        if(page==null){
            return res;
        }
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
    @AuthValid
    @RequestMapping(value="/titlequery",method=RequestMethod.POST)
    public Response titlequery(@RequestBody @Valid Page<Problems> param){
        Response res = new Response();
        Response res1 = new Response();
        Concern record=new Concern();
        logger.info(param.toString());
        
        if(StringUtil.isEmpty(param.getParam().getTitle()) || param.getParam().getUserId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        res=problemService.queryTitle(param);
        
        //添加判断用户是否关注该问题
        Page<ProResult> page=(Page<ProResult>)res.getData();
        if(page==null){
            return res;
        }
        List<ProResult> list=page.getResult();
        for(int i=0;i<list.size();i++){
            record.setProblemId(list.get(i).getId());
            record.setUserId(param.getParam().getUserId());
            res1=concernService.queryisConcern(record);
            list.get(i).setIsConcern(res1.getMsg());
        }
        return res;
    }
    
    /*查询我的问题     获取更新的回答数量信息*/
    @AuthValid
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
    @AuthValid
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
    
    /*根据问题id，删除我的问题    */
    @AuthValid
    @RequestMapping(value="/delmyproblem",method=RequestMethod.POST)
    public Response delMyProblem(@RequestBody @Valid Map<String, Integer> map){
        Response res = new Response();
        logger.info(map.toString());
        
        if(map.get("problem_id")==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        res = problemService.delMyPro(map.get("problem_id"));
        return res;
    }
    
    /*生成随机access_token*/
    @IgnoreAuth
    @RequestMapping(value="/get_token",method=RequestMethod.POST)
    public Response getToken(@RequestBody @Valid Map<String, Integer> map){
        Response resp = new Response();
        logger.info(map.toString());
        
        long now_time=DateUtil.getLinuxTimeStamp();
        String access_token = now_time+""+map.get("user_id");
        
        Jedis jedis=null;
        try {
            jedis=RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
            
            String key=Constants.REDIS_KEY_PREFIX_MEMBER_ACCESS_TOKEN+access_token;
            String value=map.get("user_id")+"";
            jedis.setex(key, Constants.XIAOYU_ACCESS_TOKEN_EXPIRE_SECONDS, value);
        } catch (Exception e) {
            e.printStackTrace();
            resp.failure(ErrorCode.ERROR_WECHAT_LOGIN_QUEST_TOKEN_ERROR);
            resp.setMsg("获取token失败");
            return resp;
        }finally {
            RedisManager.getInstance().returnResource(Constants.REDIS_POOL_NAME_MEMBER, jedis);
        }
        
        resp.success(access_token);
        return resp;
    }
    
    
    /*后台管理 查询问题*/
    @IgnoreAuth
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
    
    /*后台管理  管理员推送消息*/
    @RequestMapping(value="/insertback",method=RequestMethod.POST)
    public Response insertProblemBack(@RequestBody @Valid Problems record) throws IOException{
        Response res = new Response();
        
        if(!authService.inserAuth()){
            return res.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }
        
        logger.info(record.toString());
        
        /*if(record.getSchoolId()==null){
            return res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }*/
        
        if(record.getUserId()==null || StringUtil.isEmpty(record.getTitle())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("话题不能为空");
            return res;
        }
        
        if(StringUtil.isEmpty(record.getContent())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("内容不能为空");
            return res;
        }
        
        res=problemService.insert(record);
        //默认已经关注
        if(res.getData()!=null){
            res=concernService.addConcern((Concern)res.getData());
            return res.success();
        }
        return res;
    }
    
    
    /*后台管理  批量删除问题*/
    @RequestMapping(value="/bashDelete",method=RequestMethod.POST)
    public Response bashDelete(@RequestBody @Valid ProblemsParam param){
        Response res = new Response();
        if(null == param.getList() || param.getList().size() == 0){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("参数不能为空");
            return res;
        }
        logger.info(param.toString());
        res=problemService.bashDelete(param.getList());
        return res;
    }
}
