package com.fise.service.app.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppInformationMapper;
import com.fise.model.entity.AppInformation;
import com.fise.model.entity.AppInformationExample;
import com.fise.model.param.AppCheckUpParam;
import com.fise.model.result.AppBaseResult;
import com.fise.model.result.AppDetailResult;
import com.fise.service.app.IAppInfoemationService;
import com.fise.utils.Constants;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class AppInformationServiceImpl implements IAppInfoemationService {

	@Autowired
	AppInformationMapper appInformationDao;

	@Override
	public Response query(Page<AppInformation> page) {
		Response response = new Response();

		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();

		if (!StringUtil.isEmpty(page.getParam().getAppName())) {
			criteria.andAppNameEqualTo(page.getParam().getAppName());
		}
		List<AppInformation> list = appInformationDao.selectByPage(example, page);
		page.setParam(null);
		page.setResult(list);

		return response.success(page);
	}

	@Override
	public Response queryAll(Page<AppInformation> param) {
		Response response = new Response();

		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();

		criteria.andStatusEqualTo(1);
		example.setOrderByClause("prority desc");

		if (!StringUtil.isEmpty(param.getParam().getAppName())) {
			criteria.andAppNameEqualTo(param.getParam().getAppName());
			criteria.andAppNameLike("%" + param.getParam().getAppName() + "%");

		}

		List<AppInformation> list = appInformationDao.selectByPage(example, param);
		if (list.size() == 0) {
			response.setErrorCode(ErrorCode.ERROR_SEARCH_APP_UNEXIST);
			response.setMsg("亲，没有更多应用咯~");
			return response;
		}
		List<AppBaseResult> appData = new ArrayList<AppBaseResult>();
		for (int i = 0; i < list.size(); i++) {
			AppBaseResult appBase = new AppBaseResult();
			appBase.init(list.get(i));
			appData.add(appBase);
		}

		Page<AppBaseResult> page = new Page<AppBaseResult>();

		page.setPageNo(param.getPageNo());
		page.setPageSize(param.getPageSize());
		page.setTotalCount(param.getTotalCount());
		page.setTotalPageCount(param.getTotalPageCount());
		int haveMore = (int) (param.getTotalPageCount() - param.getPageNo());
		if (haveMore > 0) {
			page.setHasMore(true);
		} else {
			page.setHasMore(false);
		}

		page.setResult(appData);

		response.success(page);

		return response;
	}

	@Override
	public Response update(AppInformation param) {
		Response resp = new Response();

		param.setUpdated(DateUtil.getLinuxTimeStamp());
		appInformationDao.updateByPrimaryKeySelective(param);

		return resp.success();
	}

	@Override
	public Response insert(AppInformation param) {
		Response resp = new Response();

		param.setCreated(DateUtil.getLinuxTimeStamp());
		param.setUpdated(DateUtil.getLinuxTimeStamp());

		appInformationDao.insertSelective(param);
		return resp.success();
	}

	@Override
	public Response appDelete(Integer id) {
		Response response = new Response();
		AppInformation appInfo=new AppInformation();
		appInfo.setId(id);
		appInfo.setStatus(4);
		int result = appInformationDao.updateByPrimaryKeySelective(appInfo);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_SEARCH_APP_UNEXIST);
			response.setMsg("删除App失败");
			return response;
		}
		response.setData("删除应用成功");
		return response;
	}

	@Override
	public Response queryByAppName(String param) {

		Response response = new Response();
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();
		criteria.andAppNameLike("%" + param + "%");
		criteria.andStatusEqualTo(1);
		example.setOrderByClause("prority desc");

		List<AppBaseResult> appData = new ArrayList<AppBaseResult>();
		List<AppInformation> data = appInformationDao.selectByExample(example);

		int result = data.size();
		switch (result) {
		case 0:
			response.failure(ErrorCode.ERROR_SEARCH_APP_UNEXIST);
			response.setMsg("亲，找不到您要的APP~");
			break;
		case 1:
			AppBaseResult appResult = new AppBaseResult();
			appResult.init(data.get(0));
			appData.add(appResult);
			response.success(appData);
			break;
		default:
			for (int i = 0; i < 2; i++) {
				AppBaseResult appBase = new AppBaseResult();
				appBase.init(data.get(i));
				appData.add(appBase);
			}
			response.success(appData);
			break;
		}

		return response;
	}

	@Override
	public Response hotSearch() {
		Response response = new Response();
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		example.setOrderByClause("prority desc");

		List<String> nameList = new ArrayList<String>();
		List<AppInformation> data = appInformationDao.selectByExample(example);
		if (data.size() > 4) {
			for (int i = 0; i < 4; i++) {
				nameList.add(data.get(i).getAppName());
			}
		} else {
			for (int i = 0; i < data.size(); i++) {
				nameList.add(data.get(i).getAppName());
			}
		}
		response.success(nameList);
		return response;
	}

	@Override
	public Response queryByAppId(Integer param) {
		Response response = new Response();
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria con = example.createCriteria();
		// con.andAppIndexEqualTo(param);
		con.andIdEqualTo(param);
		List<AppInformation> data = appInformationDao.selectByExample(example);
		if (data.size() == 0) {
			response.setCode(200);
			response.setMsg("亲，找不到您要的APP~");
			return response;
		}
		AppDetailResult result = new AppDetailResult();
		// String creatorName=getCreatorName(data.get(0).getCreatorId());
		// data.get(0).setCreatorName(creatorName);
		result.init(data.get(0));
		response.success(result);
		return response;
	}

	@Override
	public Response appInsert(AppInformation param, List<MultipartFile> uploadPhoto, MultipartFile uploadApp,
			MultipartFile uploadIcon) {

		Response response = new Response();

		if (uploadApp.isEmpty()) {
			response.setCode(400);
			response.setMsg("请选择上传的App");
			return response;
		}

		if (uploadPhoto == null || uploadPhoto.size() == 0) {
			response.setCode(400);
			response.setMsg("请选择上传的图片");
			return response;
		}

		if (uploadIcon.isEmpty()) {
			response.setCode(400);
			response.setMsg("请选择上传的图标");
			return response;
		}

		AppInformation appInfo = new AppInformation();
		appInfo.setAppIndex(param.getAppIndex());
		appInfo.setAppName(param.getAppName());
		appInfo.setAppSpell(param.getAppSpell());
		appInfo.setPackageName(param.getPackageName());
		appInfo.setDevId(param.getDevId());
		appInfo.setDevName(param.getDevName());
		appInfo.setTopCategory(param.getTopCategory());
		appInfo.setCategory(param.getCategory());
		// 0-待审核 1-发布 2-拒绝 3-下架
		appInfo.setStatus(0);
		appInfo.setDescription(param.getDescription());
		appInfo.setVersion(param.getVersion());
		appInfo.setVersioncode(param.getVersioncode());
		// 图标
		String icon = null;
		try {
			icon = iconUpload(uploadIcon);
		} catch (Exception e) {
			response.setCode(400);
			response.setMsg("上传图标失败");
			return response;
		}
		appInfo.setIcon(icon);

		// images
		List<String> images = null;
		try {
			images = photoUpload(uploadPhoto);
		} catch (Exception e) {
			response.setCode(400);
			response.setMsg("上传图片失败");
			return response;
		}
		String imageurl=StringUtil.combineStr(images);
		appInfo.setImages(imageurl);

		// download
		String app = null;
		try {
			app = appUpload(uploadApp);
		} catch (Exception e) {
			response.setCode(400);
			response.setMsg("上传应用失败");
			return response;
		}
		appInfo.setDownload(app);

		appInfo.setIconType(param.getIconType());
		appInfo.setSize(getAppSize(uploadApp.getSize()));
		appInfo.setUpdated(0);
		appInfo.setCreated(DateUtil.getLinuxTimeStamp());
		appInfo.setPrority(param.getPrority());

		appInfo.setRemarks(param.getRemarks());
		appInfo.setLabel(param.getLabel());
		appInfo.setStar(param.getStar());
		appInfo.setOrientation(param.getOrientation());
		
	    int result=	appInformationDao.insertSelective(appInfo);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("新增应用失败");
			return response;
		}
		response.setMsg("新增应用成功");
		response.setCode(200);
		return response;
	}

	private String getAppSize(long size) {
		BigDecimal filesize = new BigDecimal(size);
		BigDecimal megabyte = new BigDecimal(1024 * 1000);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
		return returnValue + "M";
	}

	// 上传图片
	private List<String> photoUpload(List<MultipartFile> uploadfile) throws IllegalStateException, IOException {
		MultipartFile file = null;
		String pictureURL = "";
		List<String> result = new ArrayList<String>();
		// 上传图片文件
		if (uploadfile.size() != 0) {
			for (int i = 0; i < uploadfile.size(); i++) {
				file = uploadfile.get(i);

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

				pictureURL = Constants.FILE_UPLOAD_URL + "/" + filename;

				result.add(pictureURL);
			}
		}

		return result;
	}

	private String appUpload(MultipartFile uploadfile) throws IllegalStateException, IOException {

		/* 内网上传图片路径 */
		String path = "/home/fise/bin/www/upload";
		/* 外网上传图片路径 */
		// String path="/home/fise/www/upload";

		String filename = uploadfile.getOriginalFilename().replace(".",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".");
		File dir = new File(path, filename);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		uploadfile.transferTo(dir);

		/* 内网上传图片路径 */
		String downloadURL = Constants.FILE_UPLOAD_URL + "/" + filename;
		/* 外网上传图片路径 */
		// pictureURL="http://120.78.145.162:8080/upload"+"/"+filename;

		return downloadURL;
	}

	private String iconUpload(MultipartFile uploadfile) throws IllegalStateException, IOException {

		/* 内网上传图片路径 */
		String path = "/home/fise/bin/www/upload";
		/* 外网上传图片路径 */
		// String path="/home/fise/www/upload";

		String filename = uploadfile.getOriginalFilename().replace(".",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".");
		File dir = new File(path, filename);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		uploadfile.transferTo(dir);

		/* 内网上传图片路径 */
		String downloadURL = Constants.FILE_UPLOAD_URL + "/" + filename;
		/* 外网上传图片路径 */
		// pictureURL="http://120.78.145.162:8080/upload"+"/"+filename;

		return downloadURL;
	}

	@Override
	public Response appModify(AppInformation param, 
			                  List<MultipartFile> uploadPhoto, 
			                  MultipartFile uploadApp,
			                  MultipartFile uploadIcon) {
		Response response = new Response();
		AppInformation appinfo = appInformationDao.selectByPrimaryKey(param.getId());

		// 说明App的截图有被修改过:先全部删除截图，然后再全部上传截图。
		List<String> imageurl=null;
		if (uploadPhoto.size() != 0&&!uploadPhoto.isEmpty()) {
			String images=appinfo.getImages();
			List<String> str= StringUtil.splitStr(images);
			for (String string : str) {
				boolean photoResult=deleteFile(string);
				if (!photoResult) {
					response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
					response.setMsg("截图删除失败");
					return response;
				}
			}
			try {
				imageurl=photoUpload(uploadPhoto);
			} catch (Exception e) {
				response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
				response.setMsg("截图修改失败");
				return response;	
			}
			
		}

		// 说明app的下载地址有被修改:先删除原有的app;再上传新的app。
		String appurl = null;
		if (!uploadApp.isEmpty()) {
			String downLoad = appinfo.getDownload();
			boolean appResult = deleteFile(downLoad);
			if (!appResult) {
				response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
				response.setMsg("应用删除失败");
				return response;
			}
			try {
				appurl = appUpload(uploadApp);
			} catch (Exception e) {
				response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
				response.setMsg("应用修改失败");
				return response;
			}
		}
		// 说明图标的有被修改
		String iconurl=null;
		if (!uploadIcon.isEmpty()) {
			String icon = appinfo.getIcon();
			boolean appResult = deleteFile(icon);
			if (!appResult) {
				response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
				response.setMsg("图标删除失败");
				return response;
			}
			try {
				iconurl = iconUpload(uploadIcon);
			} catch (Exception e) {
				response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
				response.setMsg("图标修改失败");
				return response;
			}
		}

		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria con = example.createCriteria();
		con.andIdEqualTo(param.getId());

		AppInformation appInfo = new AppInformation();
		appInfo.setAppIndex(param.getAppIndex());
		appInfo.setAppName(param.getAppName());
		appInfo.setAppSpell(param.getAppSpell());
		appInfo.setPackageName(param.getPackageName());
		appInfo.setDevId(param.getDevId());
		appInfo.setDevName(param.getDevName());
		appInfo.setTopCategory(param.getTopCategory());
		if(imageurl.size()!=0||!imageurl.isEmpty()){
			appInfo.setImages(StringUtil.combineStr(imageurl)); 
		}
		
		appInfo.setCategory(param.getCategory());
		if(appurl!=null){
		      appInfo.setDownload(appurl);	
		}

		// 0-待审核 1-发布 2-拒绝 3-下架
		appInfo.setStatus(param.getStatus());
		appInfo.setIconType(param.getIconType());
		if(iconurl!=null){
			appInfo.setIcon(iconurl);
		}
		appInfo.setUpdated(DateUtil.getLinuxTimeStamp());
		appInfo.setDescription(param.getDescription());
		appInfo.setVersion(param.getVersion());
		appInfo.setVersioncode(param.getVersioncode());

		int result = appInformationDao.updateByExampleSelective(appInfo, example);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("应用修改失败");
			return response;
		}
		response.setData("应用修改成功");
		return response;
	}

	@Override
	public Response checkup(AppCheckUpParam developer) {
		Response response = new Response();
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria con = example.createCriteria();
		con.andIdEqualTo(developer.getAppId());

		AppInformation appInfo = new AppInformation();
		appInfo.setStatus(developer.getStatus());
		appInfo.setUpdated(DateUtil.getLinuxTimeStamp());
		appInfo.setRemarks(developer.getRemarks());

		int result = appInformationDao.updateByExampleSelective(appInfo, example);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("APP审核失败");
			return response;
		}
		return response;
	}

	@Override
	public Response queryByParam(Page<AppInformation> param) {
		Response response = new Response();

		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();
		// 先根据devdId查出对应得有哪些
		if(param.getParam().getDevId()!=null){
			criteria.andDevIdEqualTo(param.getParam().getDevId());	
		}
		if(StringUtil.isNotEmpty(param.getParam().getAppName())){
			criteria.andAppNameEqualTo(param.getParam().getAppName());
		}
		if(param.getParam().getStatus()!=null){
			criteria.andStatusEqualTo(param.getParam().getStatus());
		}
		List<AppInformation> list = appInformationDao.selectByPage(example, param);
		if (list.size() == 0) {
			response.setErrorCode(ErrorCode.ERROR_SEARCH_APP_UNEXIST);
			response.setMsg("App资源不足");
			return response;
		}
		param.setParam(null);
		param.setResult(list);
		response.success(param);

		return response;
	}

	private boolean deleteFile(String filename) {

		File file = new File(filename);
		if (!file.exists()) {
			// 表示要删除的原文件不存在
			return true;
		} else {
			file.delete();
		}
		return true;
	}
	
}
