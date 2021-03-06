package com.fise.service.app.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fise.base.ErrorCode;
import com.fise.base.HttpContext;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AppChannelListMapper;
import com.fise.dao.AppInformationMapper;
import com.fise.dao.WiAdminMapper;
import com.fise.framework.config.ConfigProperties;
import com.fise.model.dto.appmarket.ApkInfo;
import com.fise.model.dto.appmarket.ApkUtil;
import com.fise.model.dto.utils.IconUtil;
import com.fise.model.entity.AppChannel;
import com.fise.model.entity.AppChannelList;
import com.fise.model.entity.AppChannelListExample;
import com.fise.model.entity.AppInformation;
import com.fise.model.entity.AppInformationExample;
import com.fise.model.entity.WiAdmin;
import com.fise.model.entity.WiAdminExample;
import com.fise.model.entity.WiAdminExample.Criteria;
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
	private AppInformationMapper appInformationDao;
	
	@Autowired
	private WiAdminMapper adminDao;
	
	@Autowired
	private AppChannelListMapper appChannelListDao;
	
	private String path = ConfigProperties.getValue("FILE_UPLOAD_PATH").trim();
	private String chown = ConfigProperties.getValue("AUTH_CHOWN_PATH").trim();
	private String url = ConfigProperties.getValue("FILE_UPLOAD_URL").trim();
	

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
		response.success(page);
		return response;
	}

	@Override
	public Response queryAll(Page<AppInformation> param) {
		Response response = new Response();

		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();

		criteria.andStatusEqualTo(1);
		example.setOrderByClause("prority desc, id");

		if (!StringUtil.isEmpty(param.getParam().getAppName())) {
			criteria.andAppNameLike("%" + param.getParam().getAppName() + "%");
		}

		List<AppInformation> list = appInformationDao.selectByPage(example, param);
		if (list.size() == 0) {
			response.setErrorCode(ErrorCode.ERROR_SEARCH_UNEXIST);
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
        Map<String ,Object> map=new HashMap<String ,Object>();
		boolean hasMore=param.getCurrentPageNo()<param.getTotalPageCount()?true:false;
		map.put("hasMore", hasMore);
		page.setPageNo(param.getPageNo());
		page.setExtraParam(map);
		page.setPageSize(param.getPageSize());
		page.setTotalCount(param.getTotalCount());
		page.setTotalPageCount(param.getTotalPageCount());
		page.setResult(appData);
		response.success(page);
		return response;
	}

	@Override
	public Response update(AppInformation param) {
		Response resp = new Response();

		param.setUpdated(DateUtil.getLinuxTimeStamp());
		appInformationDao.updateByPrimaryKeySelective(param);
		
		List<AppChannelList> channelList = param.getChannelList();
		AppChannelListExample example = new AppChannelListExample();
		AppChannelListExample.Criteria criteria = example.createCriteria();
		criteria.andAppIdEqualTo(param.getId());
		appChannelListDao.deleteByExample(example);
		if (null != channelList && channelList.size() > 0) {
			for (AppChannelList appChannel : channelList) {
				appChannelListDao.insertSelective(appChannel);
			}
		}
		return resp.success();
	}

	@Override
	public Response insert(AppInformation param) {
		Response resp = new Response();

		param.setCreated(DateUtil.getLinuxTimeStamp());
		param.setUpdated(DateUtil.getLinuxTimeStamp());
		
		//获取文件的MD5值
		String md5=null;
		try {
		    FileInputStream fis= new FileInputStream(param.getDownload());  
            md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));  
            IOUtils.closeQuietly(fis);  
            System.out.println("MD5:"+md5); 
            //md5=DigestUtils.md5Hex(new FileInputStream(param.getDownload()));
        } catch (IOException e) {
            e.printStackTrace();
            resp.setCode(400);
            resp.setMsg("MD5值获取失败");
            return resp;
        }

		try {
		    String aaptPath=HttpContext.getRequest().getRealPath("/WEB-INF/resource/exe/aapt.exe");
		    ApkUtil.setAaptPath(aaptPath);
            ApkInfo apkInfo = ApkUtil.getApkInfo(param.getDownload());
            
            System.out.println(">>>>>>>>>>>>>>>>"+apkInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(400);
            resp.setMsg("获取apk信息失败");
            return resp;
        }
		
		System.out.println("============="+md5);
		param.setMd5(md5);
		appInformationDao.insertSelective(param);
		
		return resp.success();
	}

	@Override
	public Response appDelete(Integer id) {
		Response response = new Response();
		int result = appInformationDao.deleteByPrimaryKey(id);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_SEARCH_UNEXIST);
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
			response.failure(ErrorCode.ERROR_SEARCH_UNEXIST);
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
		con.andIdEqualTo(param);
		List<AppInformation> data = appInformationDao.selectByExample(example);
		if (data.size() == 0) {
			response.setCode(200);
			response.setMsg("亲，找不到您要的APP~");
			return response;
		}
		AppDetailResult result = new AppDetailResult();
		result.init(data.get(0));
		response.success(result);
		return response;
	}

	@Override
	public Response appInsert(AppInformation param, MultipartFile uploadApp) {

		Response response = new Response();

		if (uploadApp.isEmpty()) {
			response.setCode(400);
			response.setMsg("请选择上传的App");
			return response;
		}

		if (StringUtil.isEmpty(param.getImages())) {
			response.setCode(400);
			response.setMsg("请选择上传的图片");
			return response;
		}

		AppInformation appInfo = new AppInformation();
		appInfo.setAppName(param.getAppName());
		appInfo.setAppSpell(param.getAppSpell());
		appInfo.setDevId(param.getDevId());
		appInfo.setDevName(param.getDevName());
		appInfo.setTopCategory(param.getTopCategory());
		appInfo.setCategory(param.getCategory());
		appInfo.setChannelId(param.getChannelId());
		// 0-待审核 1-发布 2-拒绝 3-下架
		appInfo.setStatus(0);
		appInfo.setDescription(param.getDescription());
		appInfo.setImages(param.getImages());

		// download
		String app = null;
		try {
			app = appUpload(uploadApp);
		} catch (Exception e) {
			response.setCode(400);
			response.setMsg("上传应用失败");
			return response;
		}
		
		appInfo.setDownload(url + app);

		appInfo.setSize(getAppSize(uploadApp.getSize()));
		appInfo.setUpdated(DateUtil.getLinuxTimeStamp());
		appInfo.setCreated(DateUtil.getLinuxTimeStamp());
		
		//获取文件的MD5值
        String md5=null;
        ApkInfo apkInfo=null;
        String iconpath=null;
        try {
            FileInputStream fis= new FileInputStream(path + app);
            md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));  
            IOUtils.closeQuietly(fis);  
            //获取apk信息
            String aaptPath=HttpContext.getRequest().getRealPath("/WEB-INF/resource/exe/aapt");
            Runtime.getRuntime().exec("chmod 700 " + aaptPath);
            ApkUtil.setAaptPath(aaptPath);
            apkInfo = ApkUtil.getApkInfo(path + app);
            iconpath = apkInfo.getApplicationIcon().substring(apkInfo.getApplicationIcon().lastIndexOf("/")+1);
            iconpath = iconpath.replace(".", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".");
            IconUtil.extractFileFromApk(path + app, apkInfo.getApplicationIcon(), path + iconpath);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(400);
            response.setMsg("获取apk信息失败");
            return response;
        }
        appInfo.setMd5(md5);
        appInfo.setPackageName(apkInfo.getPackageName());
        appInfo.setVersion(apkInfo.getVersionName());
        appInfo.setVersioncode(Integer.valueOf(apkInfo.getVersionCode()));
        appInfo.setIcon(url + iconpath);
		
	    int result=	appInformationDao.insertSelective(appInfo);
		if (result == 0) {
			response.setErrorCode(ErrorCode.ERROR_PARAM_BIND_EXCEPTION);
			response.setMsg("新增应用失败");
			return response;
		}
		
		//在channellist里添加频道应用，status默认为0 不可用
		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();
		criteria.andAppNameEqualTo(appInfo.getAppName());
		List<AppInformation> list = appInformationDao.selectByExample(example);
		AppInformation app1=null;
		if(list.size()!=0){
		    app1 = list.get(0);
		}
		AppChannelList appChannel = new AppChannelList();
		if (StringUtil.isNotEmpty(param.getChannelIds())) {
			String[] channelIds = param.getChannelIds().split(",");
			for (String channelId : channelIds) {
				appChannel.setChannelId(Integer.valueOf(channelId));
				appChannel.setAppId(app1.getId());
				appChannel.setStatus(1);
				appChannelListDao.insertSelective(appChannel);
			}
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

				String filename = file.getOriginalFilename().replace(".",
						new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".");
				File dir = new File(path, filename);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				file.transferTo(dir);
				Runtime.getRuntime().exec(chown + " " + path+"/"+filename);
				pictureURL = url + filename;
				result.add(pictureURL);
			}
		}
		return result;
	}

	private String appUpload(MultipartFile uploadfile) throws IllegalStateException, IOException {

		String filename = uploadfile.getOriginalFilename().replace(".",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".");
		File dir = new File(path, filename);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		uploadfile.transferTo(dir);

		return filename;
	}

	private String iconUpload(MultipartFile uploadfile) throws IllegalStateException, IOException {


		String filename = uploadfile.getOriginalFilename().replace(".",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".");
		File dir = new File(path, filename);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		uploadfile.transferTo(dir);
		Runtime.getRuntime().exec(chown + " " + path+"/"+filename);

		String downloadURL=url+filename;
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
				appurl = url+appUpload(uploadApp);
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
		//appInfo.setAppIndex(param.getAppIndex());
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
		appInfo.setStatus(0);
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

		AppInformation appInfo = new AppInformation();
		appInfo.setId(developer.getAppId());
		appInfo.setStatus(developer.getStatus());
		appInfo.setUpdated(DateUtil.getLinuxTimeStamp());
		appInfo.setRemarks(developer.getRemarks());

		appInformationDao.updateByPrimaryKeySelective(appInfo);
		
		List<AppChannelList> channelList = developer.getChannelList();
		AppChannelListExample example1 = new AppChannelListExample();
		AppChannelListExample.Criteria criteria = example1.createCriteria();
		criteria.andAppIdEqualTo(developer.getAppId());
		appChannelListDao.deleteByExample(example1);
		if (null != channelList && channelList.size() > 0) {
			for (AppChannelList appChannel : channelList) {
				appChannelListDao.insertSelective(appChannel);
			}
		}
        
		AppInformation appResult=appInformationDao.selectByPrimaryKey(developer.getAppId());
		try {
			HtmlEmail email = new HtmlEmail();// 不用更改
			email.setHostName("smtp.exmail.qq.com");// 需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
			email.setCharset("UTF-8");
			email.setSSLOnConnect(true);
			
            //根据开发者的dev_id查出他的email
			WiAdminExample wiexample = new WiAdminExample();
			Criteria crit = wiexample.createCriteria();
			crit.andIdEqualTo(appResult.getDevId());
			List<WiAdmin> list=adminDao.selectByExample(wiexample);
			WiAdmin admin=null;
			for(int i=0;i<list.size();i++){
				admin=list.get(0);
			}
			email.addTo(admin.getEmail());// 收件地址
			email.setFrom("chenzc@fise.com.cn", "fise智能");// 此处填邮箱地址和用户名,用户名可以任意填写
			email.setAuthentication("chenzc@fise.com.cn", "Fise321");// 此处填写邮箱地址和客户端授权码
			email.setSubject("孙大大通讯");// 此处填写邮件名，邮件名可任意填写
			if (appResult.getStatus() == 2) {
				email.setMsg("亲，您好,您在沸石应用宝平台申请的应用审核未通过，原因是:" + appResult.getRemarks() + "。");// 此处填写邮件内容
			}
			if (appResult.getStatus() == 1) {
				email.setMsg("亲，恭喜您，你在沸石应用宝平台申请的应用已审核通过，祝您生活愉快。");// 此处填写邮件内容
			}
			if (appResult.getStatus() == 3) {
			    email.setMsg("亲，您好，你在沸石应用宝平台申请的应用已审核下架。");
			}
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setMsg("该应用审核完成。");
		response.success();
		return response;
	}

	@Override
	public Response queryByParam(Page<AppInformation> page) {
		Response response = new Response();

		AppInformationExample example = new AppInformationExample();
		AppInformationExample.Criteria criteria = example.createCriteria();
		// 先根据devdId查出对应得有哪些
		if (page.getParam().getDevId() != null) {
			criteria.andDevIdEqualTo(page.getParam().getDevId());
		}
		if (page.getParam().getChannelId() != null) {
			criteria.andChannelIdEqualTo(page.getParam().getChannelId());
		}

		if(StringUtil.isNotEmpty(page.getParam().getChannelName())){
			criteria.andChannelNameLike("%" + page.getParam().getChannelName() + "%");
		}
		if(StringUtil.isNotEmpty(page.getParam().getAppName())){
			criteria.andAppNameLike("%" + page.getParam().getAppName() + "%");
		}
		if(page.getParam().getStatus()!=null){
			criteria.andStatusEqualTo(page.getParam().getStatus());
		}
		if (StringUtil.isNotEmpty(page.getOrderby())) {
			example.setOrderByClause(page.getOrderby());
		} else {
			example.setOrderByClause("created desc");
		}
		List<AppInformation> list = appInformationDao.selectAppByPage(example, page);

		if (list.size() == 0) {
			response.setErrorCode(ErrorCode.ERROR_SEARCH_UNEXIST);
			response.setMsg("App资源不足");
			return response;
		}
		List<Integer> idList = new ArrayList<Integer>();
		for (AppInformation appInformation : list) {
			idList.add(appInformation.getId());
		}
		AppInformationExample example1 = new AppInformationExample();
		AppInformationExample.Criteria criteria1 = example1.createCriteria();
		criteria1.andIdIn(idList);
		if (StringUtil.isNotEmpty(page.getOrderby())) {
			example1.setOrderByClause(page.getOrderby());
		} else {
			example1.setOrderByClause("created desc");
		}
		List<AppInformation> result = appInformationDao.selectByIdList(example1);
		page.setParam(null);
		page.setResult(result);
		response.success(page);
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
