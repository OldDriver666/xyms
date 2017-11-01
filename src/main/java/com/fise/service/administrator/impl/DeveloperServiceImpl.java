package com.fise.service.administrator.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fise.base.ErrorCode;
import com.fise.base.Response;
import com.fise.dao.WiAdminMapper;
import com.fise.model.entity.WiAdmin;
import com.fise.model.entity.WiAdminExample;
import com.fise.model.entity.WiAdminExample.Criteria;
import com.fise.model.param.DeveloperInsert;
import com.fise.model.param.DeveloperUpdate;
import com.fise.model.result.DeveloperResult;
import com.fise.service.administrator.IDeveloperService;
import com.fise.utils.DateUtil;

@Service
public class DeveloperServiceImpl implements IDeveloperService {
	Logger logger = Logger.getLogger(DeveloperServiceImpl.class);

	@Autowired
	private WiAdminMapper adminDao;

	@Override
	public Response insert(DeveloperInsert param, MultipartFile[] uploadfile) {
		Response response = new Response();

		WiAdmin developer = new WiAdmin();
		developer.setAccount(param.getAccount());
		developer.setPassword(param.getPassword());
		developer.setNickName(param.getNickName());
		developer.setPhone(param.getPhone());
		developer.setEmail(param.getEmail());
		// 默认不可用
		developer.setStatus(0);
		developer.setCreated(DateUtil.getLinuxTimeStamp());
		developer.setCreatorId(param.getCreatorId());
		developer.setIdCard(param.getIdCard());
		// 三张身份证的照片，上传到服务器中，获取它们的地址值，用;隔开
		List<String> images = null;
		try {
			images = photoUpload(uploadfile);
		} catch (Exception e) {
			response.failure(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("上传图片失败");
			return response;
		}

		StringBuilder imagesUrl = new StringBuilder();
		for (int i = 1; i < images.size(); i++) {
			imagesUrl.append(images.get(i-1) + ";");
		}
		imagesUrl.append(images.get(images.size()-1));
		
		developer.setCardPhoto(param.getCardPhoto());
		developer.setDescription(param.getDescription());
		developer.setUserType(param.getUserType());
		developer.setCardPhoto(imagesUrl.toString());

		adminDao.insert(developer);
		response.success(developer);
		return response;
	}

	// @RequestBody @RequestParam("image")
	private List<String> photoUpload(MultipartFile[] uploadfile) throws IllegalStateException, IOException {
		MultipartFile file = null;
		String pictureURL = "";
		List<String> result = new ArrayList<String>();
		// 上传图片文件
		if (uploadfile.length != 0) {
			for (int i = 0; i < uploadfile.length; i++) {
				file = uploadfile[i];

				/* 内网上传图片路径 */
				String path = "/home/fise/bin/www/upload";
				/* 外网上传图片路径 */
				// String path="/home/fise/www/upload";

				String filename = file.getOriginalFilename().replace(".",
						new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".");
				File dir = new File(path, filename);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				file.transferTo(dir);

				if (i == 0) {
					/* 内网上传图片路径 */
					pictureURL = "http://192.168.2.250:8888/upload" + "/" + filename;
					/* 外网上传图片路径 */
					// pictureURL="http://120.78.145.162:8080/upload"+"/"+filename;
				} else {
					/* 内网上传图片路径 */
					pictureURL = pictureURL + "http://192.168.2.250:8888/upload/" + filename;
					/* 外网上传图片路径 */
					// pictureURL=pictureURL+"http://120.78.145.162:8080/upload/"+filename;
				}

				result.add(pictureURL);
			}
		}

		return result;
	}

	@Override
	public Response update(DeveloperUpdate developer) {
		
		Response response = new Response();
		WiAdmin wiadmin=new WiAdmin();
		
		WiAdminExample example = new WiAdminExample();
		Criteria updWhere = example.createCriteria();
		updWhere.andIdEqualTo(developer.getAdminId());
		
		wiadmin.setStatus(developer.getStatus());
		wiadmin.setUpdated(DateUtil.getLinuxTimeStamp());
		wiadmin.setRemarks(developer.getRemarks());
		int result=adminDao.updateByExampleSelective(wiadmin, example);
		if (result==0){
			response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("开发者审核失败");
			return response;
		}
		return response;
	}

	@Override
	public Response query(Integer id) {
		Response response = new Response();
		WiAdmin wiadmin=  adminDao.selectByPrimaryKey(id);
		if(wiadmin==null){
			response.setErrorCode(ErrorCode.ERROR_MEMBER_INDB_IS_NULL);
			response.setMsg("开发者不存在");
			return response;
		}
		DeveloperResult result=new DeveloperResult();
		result.init(wiadmin);
		response.success(wiadmin);
		return response;
	}

}
