package com.fise.controller.adminstrator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.HttpContext;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.framework.annotation.IgnoreAuth;
import com.fise.model.entity.Search;
import com.fise.model.entity.WiAdmin;
import com.fise.model.param.AdminInsert;
import com.fise.model.param.AdminQuery;
import com.fise.model.param.AdminUpdate;
import com.fise.model.param.LoginParam;
import com.fise.model.param.LogoutParam;
import com.fise.service.administrator.IAdministratorService;
import com.fise.service.auth.IAuthService;
import com.fise.utils.StringUtil;
import com.fise.utils.httpclient.JsoupParserUtil;

@RestController
@RequestMapping("/boss/admin")
public class AdminstratorController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private IAdministratorService adminSvr;

    @Resource
    IAuthService authService;

    @IgnoreAuth
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response adminLogin(@RequestBody @Valid LoginParam param) {
        Response resp = new Response();
        try {
            logger.info(param.toString());
            resp = adminSvr.login(param);
        } catch (Exception e) {
            e.printStackTrace();
            resp.failure(ErrorCode.ERROR_SYSTEM);
        }
        return resp;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response logout(@RequestBody @Valid LogoutParam param, HttpServletRequest request) {
        Response resp = new Response();
        logger.info(param.toString());
        resp = adminSvr.logout(param, request);

        return resp;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response adminInsert(@RequestBody @Valid AdminInsert param) {
        Response resp = new Response();

        if (!authService.inserAuth()) {
            return resp.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }

        resp = adminSvr.insertAdmin(param);
        logger.info("新增管理员:"+param.toString());
        return resp;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response adminUpdate(@RequestBody @Valid AdminUpdate param) {
        Response resp = new Response();

        if (!authService.updateAuth()) {
            return resp.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }

        logger.info(param.toString());;
        resp = adminSvr.updateAdmin(param);
        return resp;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response adminDelete(@RequestBody @Valid AdminUpdate param) {
        Response resp = new Response();

        if (!authService.updateAuth()) {
            return resp.failure(ErrorCode.ERROR_REQUEST_AUTH_FAILED);
        }

        logger.info(param.toString());
        resp = adminSvr.deleteAdmin(param);
        return resp;
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response adminQuery(@RequestBody @Valid AdminQuery param) {
        Response resp = new Response();
        logger.info(param.toString());
        resp = adminSvr.queryAdmin(param);
        return resp;
    }

    @IgnoreAuth
    @RequestMapping(value = "/islogin", method = RequestMethod.POST)
    public Response isLogin(@RequestBody @Valid Map<String, String> map) {
        Response resp = new Response();
        logger.info(map.toString());
        resp = adminSvr.isLogin(map.get("accessToken"));
        return resp;
    }
    
    //发送邮箱验证码
    @IgnoreAuth
    @RequestMapping(value="/send_identity_code",method =RequestMethod.POST)
    public Response sendEmail(@RequestBody @Valid Map<String, String> map){
        Response resp = new Response();
        try {  
            HtmlEmail email = new HtmlEmail();//不用更改  
            email.setHostName("smtp.exmail.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com  
            email.setCharset("UTF-8");  
            email.setSSLOnConnect(true);
            
            email.addTo(map.get("emailaddress"));// 收件地址  
            //这是本人自己qq邮箱，测试使用，建议后期修改邮箱地址
            email.setFrom("chenzc@fise.com.cn", "fise智能");//此处填邮箱地址和用户名,用户名可以任意填写  
            //修改新的客户端授权码
            email.setAuthentication("chenzc@fise.com.cn", "Fise321");//此处填写邮箱地址和客户端授权码  
  
            email.setSubject("验证码");//此处填写邮件名，邮件名可任意填写  
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + StringUtil.makeCode(6,false));//此处填写邮件内容  
            System.out.println("----------"+map.get("emailaddress"));
            email.send();  
            return resp.success();  
        }  
        catch(Exception e){  
            e.printStackTrace();  
            return resp.failure(ErrorCode.ERROR_SEND_IDENTITY_CODE);  
        }  
    }
    
    //搜一搜（百度接口）
    @IgnoreAuth
    @RequestMapping(value="/search",method=RequestMethod.POST)
    public Response searchQuery(@RequestBody @Valid Map<String, String> map){
        Response response = new Response();
        
        logger.info(map.toString());
        
        List<Search> list = null;
        try {
            list = JsoupParserUtil.getElement(map.get("param"),Integer.valueOf(map.get("page")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
            response.setCode(400);
            response.setMsg("解析html出错");
            return response;
        }
        
        return response.success(list);
    }
    
    @RequestMapping(value = "/queryAdminByPage", method = RequestMethod.POST)
    public Response queryAdminByPage(@RequestBody @Valid Page<WiAdmin> page) {
        Response resp = new Response();
        logger.info(page.toString());
        resp = adminSvr.queryAdminByPage(page);
        return resp;
    }
}
