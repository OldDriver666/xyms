package com.fise.service.administrator;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.param.DeveloperInsert;
import com.fise.model.param.DeveloperQuery;
import com.fise.model.param.DeveloperUpdate;

public interface IDeveloperService {
	
	/*开发者注册*/
	public Response insert(DeveloperInsert developer,List<MultipartFile> uploadfile1);
	
	/*开发者审核*/
	public Response update(DeveloperUpdate developer);
	
	/*开发者查询*/
	public Response query(Page<DeveloperQuery> developer);
	
	/*开发者删除(只做逻辑删除)*/
	public Response delete(Integer id);
	
	/*开发者注册时检查account是否唯一*/
	public Response queryAccount(String account);
	
	/*根据邮箱获取该账户的account名*/
	public Response getAccountByEmail(String email);
	
	/*开发者注册时做邮箱验证发送验证码*/
	public Response sendCode(Map<String,String> map);
	
	/*开发者注册时做邮箱验证核对验证码*/
	public Response checkCode(Map<String,String> map);
	
	/*开发者注册时检查邮箱是否已注册*/
	public Response queryEmail(String email);
	
	/*开发者修改密码*/
	public Response modifyPassword(Map<String,Object> map);
}
