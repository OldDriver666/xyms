package com.fise.controller.app;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.entity.AppChannel;
import com.fise.model.entity.AppChannelList;
import com.fise.model.result.AppChannelResult;
import com.fise.service.app.IAppChannelListService;
import com.fise.service.app.IAppChannelService;

@RestController
@RequestMapping("/app/channellist")
public class AppChannelListController {
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource
    IAppChannelListService appChannelListService;
    
    @Resource
    IAppChannelService appChannelService;
    
    /*应用商城   频道应用查询*/
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Response query(@RequestBody @Valid Page<AppChannelList> param){
        Response resp = new Response();
        logger.info(param.toString());
        
        resp=appChannelListService.query(param);
        return resp;
    }
    
    /*应用商城    频道应用更新*/
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@RequestBody @Valid AppChannelList param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getId()==null){
            resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            resp.setMsg("参数  id  不能为空！！");
            return resp;
        }
        
        resp = appChannelListService.update(param);
        return resp;
    }
    
    /*应用商城 新增频道应用*/
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Response insert(@RequestBody @Valid AppChannelList param){
        Response resp = new Response();
        logger.info(param.toString());
        
        if(param.getChannelId()==null || param.getAppId()==null){
            return resp.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
        }
        
        resp=appChannelListService.insert(param);
        return resp;
    }
    
    
    /**
	 * 获取指定频道下的app的list
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/channel", method = RequestMethod.POST)
	public Response getChannelInfo(@RequestBody @Valid Page<AppChannel> param) {
		Response response = new Response();
		Integer channelId = param.getParam().getId();
		AppChannelResult data = new AppChannelResult();
		AppChannel channel = new AppChannel();
		channel = appChannelService.getChannelInfo(channelId);
		if (null==channel) {
			response.failure(ErrorCode.ERROR_PARAM_MEMBER_MOBILE_IS_EMPTY);
			response.setMsg("亲，频道信息不存在哦~");
			return response;
		}
		data.init(channel);
		List<Integer> appIdList = appChannelService.getChannelAppId(channelId);
		if(appIdList.size()==0){
			response.failure(ErrorCode.ERROR_SEARCH_APP_UNEXIST);
			response.setMsg("亲，该频道下没有应用哦~");
			return response;
		}
		
		response = appChannelListService.queryByIdList(param, appIdList);
		return response;
	}
}
