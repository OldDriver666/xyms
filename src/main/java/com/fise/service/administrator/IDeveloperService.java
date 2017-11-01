package com.fise.service.administrator;

import org.springframework.web.multipart.MultipartFile;

import com.fise.base.Response;
import com.fise.model.param.DeveloperInsert;
import com.fise.model.param.DeveloperUpdate;

public interface IDeveloperService {
	
	/*开发者注册*/
	public Response insert(DeveloperInsert developer,MultipartFile[] uploadfile);
	
	/*开发者审核*/
	public Response update(DeveloperUpdate developer);
	
	/*开发者查询*/
	public Response query(Integer id);
	
	/*开发者删除，同时删除该开发者创建的资源*/

}
