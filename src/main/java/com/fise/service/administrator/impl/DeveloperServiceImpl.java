package com.fise.service.administrator.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.WiAdminMapper;
import com.fise.framework.config.ConfigProperties;
import com.fise.framework.redis.RedisManager;
import com.fise.model.entity.WiAdmin;
import com.fise.model.entity.WiAdminExample;
import com.fise.model.entity.WiAdminExample.Criteria;
import com.fise.model.param.DeveloperInsert;
import com.fise.model.param.DeveloperQuery;
import com.fise.model.param.DeveloperUpdate;
import com.fise.model.result.DeveloperResult;
import com.fise.service.administrator.IDeveloperService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

import redis.clients.jedis.Jedis;

@Service
public class DeveloperServiceImpl implements IDeveloperService {
	Logger logger = Logger.getLogger(DeveloperServiceImpl.class);

	@Autowired
	private WiAdminMapper adminDao;
	
	private String path = ConfigProperties.getValue("FILE_UPLOAD_PATH").trim();
	private String chown = ConfigProperties.getValue("AUTH_CHOWN_PATH").trim();
	private String url = ConfigProperties.getValue("FILE_UPLOAD_URL").trim();

	@Override
	public Response insert(DeveloperInsert param) {
		Response response = new Response();
		WiAdmin developer = new WiAdmin();

		WiAdminExample example = new WiAdminExample();
		Criteria con = example.createCriteria();
		con.andAccountEqualTo(param.getAccount());

		List<WiAdmin> queryAccount = adminDao.selectByExample(example);
		if (queryAccount.size() != 0) {
			response.failure(ErrorCode.ERROR_ACCOUNT_ALREADY_EXISTED);
			response.setMsg("该账户已注册");
			return response;
		}
		developer.setAccount(param.getAccount());

		developer.setPassword(param.getPassword());
		developer.setNickName(param.getNickName());
		developer.setPhone(param.getPhone());
		developer.setEmail(param.getEmail());
		// 默认不可用
		 Integer nNow = DateUtil.getLinuxTimeStamp();
		
		developer.setStatus(0);
		developer.setCreated(nNow);
		developer.setUpdated(nNow);
		developer.setSalt(nNow.toString().substring(5, 9));
		
		developer.setIdCard(param.getIdCard());
		// 三张身份证的照片，上传到服务器中，获取它们的地址值，用;隔开
		/*List<String> images = null;
		try {
			images = photoUpload(uploadfile);
		} catch (Exception e) {
			response.failure(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("上传图片失败");
			return response;
		}*/
		//String imagesUrl = StringUtil.combineStr(images);
		developer.setCardPhoto(param.getCardPhoto());
		developer.setDescription(param.getDescription());
		developer.setUserType(param.getUserType());
		developer.setCardPhoto(param.getCardPhoto());

		adminDao.insert(developer);
		response.success(developer);
		return response;
	}

	private List<String> photoUpload(List<MultipartFile> uploadfile) throws IllegalStateException, IOException {
		MultipartFile file = null;
		String pictureURL = "";
		List<String> result = new ArrayList<String>();
		// 上传图片文件
		if (uploadfile.size() != 0) {
			for (int i = 0; i < uploadfile.size(); i++) {
				file = uploadfile.get(i);

				String filename = file.getOriginalFilename().replace(".",
						new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".");
				File dir = new File(path, filename);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				file.transferTo(dir);
				Runtime.getRuntime().exec(chown + " " + path + "/" + filename);
				pictureURL = url + filename;
				result.add(pictureURL);
			}
		}
		return result;
	}

	@Override
	public Response update(DeveloperUpdate developer) {

		Response response = new Response();
		WiAdmin wiadmin = new WiAdmin();

		WiAdminExample example = new WiAdminExample();
		Criteria updWhere = example.createCriteria();
		updWhere.andIdEqualTo(developer.getId());

		wiadmin.setStatus(developer.getStatus());
		wiadmin.setRoleId(31);
		wiadmin.setCompanyId(1);
		wiadmin.setUpdated(DateUtil.getLinuxTimeStamp());
		wiadmin.setRemarks(developer.getRemarks());
		int result = adminDao.updateByExampleSelective(wiadmin, example);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("开发者审核失败");
			return response;
		}
		WiAdmin dev = adminDao.selectByPrimaryKey(developer.getId());
		try {
			HtmlEmail email = new HtmlEmail();// 不用更改
			email.setHostName("smtp.exmail.qq.com");// 需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
			email.setCharset("UTF-8");
			email.setSSLOnConnect(true);

			email.addTo(dev.getEmail());// 收件地址
			//这是本人自己qq邮箱，测试使用，建议后期修改邮箱地址
			email.setFrom("chenzc@fise.com.cn", "fise智能");// 此处填邮箱地址和用户名,用户名可以任意填写
			//修改新的客户端授权码
			email.setAuthentication("chenzc@fise.com.cn", "Fise321");// 此处填写邮箱地址和客户端授权码

			email.setSubject("孙大大通讯");// 此处填写邮件名，邮件名可任意填写
			if (dev.getStatus() == 0) {
				email.setMsg("亲，您好,您在沸石开发者平台注册的信息审核未通过，原因是:" + dev.getRemarks() + "。");// 此处填写邮件内容
			}
			if (dev.getStatus() == 1) {
				email.setMsg("亲，恭喜您，你在沸石开发者平台注册的信息已审核通过，祝您生活愉快。");// 此处填写邮件内容
			}
			if (dev.getStatus() == 2) {
                email.setMsg("亲，您好，你在沸石开发者平台注册的信息已审核删除");// 此处填写邮件内容
            }
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
			return response.failure(ErrorCode.ERROR_SEND_IDENTITY_CODE);
		}
		response.setMsg("该开发者审核完成。");
		response.success();
		return response;
	}

	@Override
	public Response query(Page<DeveloperQuery> param) {
		Response response = new Response();
		WiAdminExample example = new WiAdminExample();
		Criteria con = example.createCriteria();
		con.andUserTypeNotEqualTo(0);
		if (param.getParam().getDevId() != null) {
			con.andIdEqualTo(param.getParam().getDevId());
		}
		if (StringUtil.isNotEmpty(param.getParam().getAccount())) {
			con.andAccountEqualTo(param.getParam().getAccount());
		}

		if (param.getParam().getStatus() != null) {
			int status = param.getParam().getStatus();
			con.andStatusEqualTo((byte) status);
		}
		if (param.getParam().getUserType() != null) {
			con.andUserTypeEqualTo(param.getParam().getUserType());
		}

		List<WiAdmin> adminList = adminDao.selectByPage(example, param);
		if (adminList.size() == 0) {
			response.setErrorCode(ErrorCode.ERROR_SEARCH_UNEXIST);
			response.setMsg("开发者资源不足");
			return response;
		}
		List<DeveloperResult> list = new ArrayList<DeveloperResult>();
		for (int i = 0; i < adminList.size(); i++) {
			DeveloperResult result = new DeveloperResult();
			result.init(adminList.get(i));
			list.add(result);
		}
		Page<DeveloperResult> page = new Page<DeveloperResult>();
		page.setPageNo(param.getPageNo());
		page.setPageSize(param.getPageSize());
		page.setTotalCount(param.getTotalCount());
		page.setTotalPageCount(param.getTotalPageCount());
		page.setResult(list);
		response.success(page);
		return response;
	}

	@Override
	public Response queryAccount(String account) {
		Response response = new Response();
		WiAdminExample example = new WiAdminExample();
		Criteria con = example.createCriteria();
		con.andAccountEqualTo(account);

		List<WiAdmin> queryAccount = adminDao.selectByExample(example);
		if (queryAccount.size() != 0) {
			response.failure(ErrorCode.ERROR_ACCOUNT_ALREADY_EXISTED);
			response.setMsg("该账户已注册");
			return response;
		}
		response.setCode(200);
		response.setMsg("该账户未注册");
		return response;
	}

	@Override
	public Response delete(Integer id) {
		Response response = new Response();
		WiAdmin wiadmin = new WiAdmin();
		WiAdmin queryDev = adminDao.selectByPrimaryKey(id);
		if (queryDev == null) {
			response.setErrorCode(ErrorCode.ERROR_MEMBER_INDB_IS_NULL);
			response.setMsg("开发者已不存在");
			return response;
		}

		WiAdminExample example = new WiAdminExample();
		Criteria updWhere = example.createCriteria();
		updWhere.andIdEqualTo(id);
		wiadmin.setStatus(4);
		int result = adminDao.updateByExampleSelective(wiadmin, example);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("开发者删除失败");
			return response;
		}
		return response;
	}

	@Override
	public Response sendCode(Map<String, String> map) {
		Response response = new Response();
		try {
			HtmlEmail email = new HtmlEmail();// 不用更改
			email.setHostName("smtp.exmail.qq.com");// 需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
			email.setCharset("UTF-8");
			email.setSSLOnConnect(true);

			email.addTo(map.get("emailaddress"));// 收件地址

			email.setFrom("chenzc@fise.com.cn", "fise智能");// 此处填邮箱地址和用户名,用户名可以任意填写

			email.setAuthentication("chenzc@fise.com.cn", "Fise321");// 此处填写邮箱地址和客户端授权码

			email.setSubject("孙大大通讯");// 此处填写邮件名，邮件名可任意填写

			String randomCode = StringUtil.makeCode(6, false);
			Jedis jedis = null;
			jedis = RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
			jedis.setex("randomCode", Constants.VALIDATION_CODE_EXPIRE_SECONDS, randomCode);
			email.setMsg("尊敬的用户:您好,您本次的验证码是:" + randomCode);
			email.send();
			response.success();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return response.failure(ErrorCode.ERROR_SEND_IDENTITY_CODE);
		}
	}

	@Override
	public Response checkCode(Map<String, String> map) {
		Response response = new Response();
		String code = map.get("checkCode");
		Jedis jedis = null;
		jedis = RedisManager.getInstance().getResource(Constants.REDIS_POOL_NAME_MEMBER);
		if (code.equalsIgnoreCase(jedis.get("randomCode"))) {
			response.setMsg("邮箱验证通过");
			response.setCode(200);
			return response;
		}
		response.setMsg("验证码有误，请重新输入！");
		response.setCode(ErrorCode.ERROR_PARAM_NOT_VALID_EXCEPTION.getCode());
		return response;
	}

	@Override
	public Response queryEmail(String email) {
		Response response = new Response();
		WiAdminExample example = new WiAdminExample();
		Criteria con = example.createCriteria();
		con.andEmailEqualTo(email);

		List<WiAdmin> queryAccount = adminDao.selectByExample(example);
		if (queryAccount.size() != 0) {
			response.failure(ErrorCode.ERROR_ACCOUNT_ALREADY_EXISTED);
			response.setMsg("该邮箱已注册");
			return response;
		}
		response.setCode(200);
		response.setMsg("该邮箱未注册");
		return response;
	}

	@Override
	public Response getAccountByEmail(String email) {
		Response response = new Response();
		WiAdminExample example = new WiAdminExample();
		Criteria con = example.createCriteria();
		con.andEmailEqualTo(email);

		List<WiAdmin> account = adminDao.selectByExample(example);
		if (account.size() == 0) {
			response.failure(ErrorCode.ERROR_SEARCH_UNEXIST);
			response.setMsg("该账户不存在");
			return response;
		}
		WiAdmin admin = null;
		for (int i = 0; i < account.size(); i++) {
			admin = account.get(0);
		}
		
		if(admin.getStatus()==0){
			response.failure(ErrorCode.ERROR_SEARCH_UNEXIST);
			response.setMsg("该账户不可用，请联系管理员。");
			return response;	
		}
		
		if(admin.getStatus()==2){
			response.failure(ErrorCode.ERROR_SEARCH_UNEXIST);
			response.setMsg("该账户已删除");
			return response;	
		}
		response.setCode(200);
		response.setData(admin.getAccount());
		return response;
	}

	@Override
	public Response modifyPassword(Map<String, Object> map) {
		Response response = new Response();
		String account = (String) map.get("account");
		String password = (String) map.get("password");
		WiAdmin wiadmin = new WiAdmin();
		WiAdminExample example = new WiAdminExample();
		Criteria updWhere = example.createCriteria();
		updWhere.andAccountEqualTo(account);
		updWhere.andStatusEqualTo((byte) 1);

		wiadmin.setPassword(password);
		wiadmin.setUpdated(DateUtil.getLinuxTimeStamp());
		int result = adminDao.updateByExampleSelective(wiadmin, example);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("修改密码失败");
			return response;
		}
		response.setCode(200);
		response.setMsg("修改密码成功");
		return response;
	}
}