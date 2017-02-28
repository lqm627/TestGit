package com.wechat.webapi.web.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.mapper.JsonMapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.easylotto.core.util.SessionUtil;
import com.wechat.config.WxConfig;
import com.wechat.webapi.web.common.ResponseErrorCode;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;

public class BaseController {
	// private static final String ENCODING = "UTF-8";
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired private EcpUserInfoService ecpUserInfoService;
	
	@SuppressWarnings("unchecked")
	public Map<String, String> handleRequest(String requestData) {
		logger.info("request: " + requestData);
		return JsonMapper.nonEmptyMapper().fromJson(requestData, Map.class);
	}

	public <T> T handleRequest(String requestData, Class<T> clz) {
		logger.info("request: " + requestData);
		return JsonMapper.nonEmptyMapper().fromJson(requestData, clz);
	}

	public void updateToken(HttpServletRequest request, String token) {
		request.removeAttribute("token");
		request.getSession().setAttribute("token", token);
	}

	public String getLang(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("LANG");
	}

	public ResponseBean handleResponse(ResponseBean responseBean, HttpServletResponse response) {
		/*
		 * try { responseBean.setSystime(DateFormatUtils.format(new Date(),
		 * "yyyy-MM-dd HH:mm:ss")); String json =
		 * JsonMapper.nonEmptyMapper().toJson(responseBean); byte[] bb =
		 * json.getBytes(ENCODING); if (bb.length > 1024) {
		 * response.setHeader("Content-Encoding", "gzip");
		 * response.setCharacterEncoding(ENCODING); bb = GZipUtil.compress(bb);
		 * } System.out.println("response json ("+json.length()+"/"+bb.length+
		 * ") : "+json); return bb; } catch (Exception e) { e.printStackTrace();
		 * }
		 */
		responseBean.setSystime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		if(logger.isInfoEnabled())
			logger.info("----------------->>>>    response:   "+JsonMapper.nonEmptyMapper().toJson(responseBean));
		return responseBean;
	}
	
	public ResponseBean handleResponse(ResponseBean responseBean, HttpServletRequest request,
			HttpServletResponse response) {
		
		String request_data = request.getParameter("request_data");
		if (StringUtils.isNotBlank(request_data)) {
			JSONObject obj = JSON.parseObject(request_data);
			if (obj != null && obj.containsKey("memberId")) {
				//responseBean.setMemberId(obj.getString("memberId"));
			}
		}
		return handleResponse(responseBean, response);
	}
	
	
	public EcpUserKey getEcpUserKey(HttpServletRequest request, String type){
		String key = SessionUtil.getMemberKey(request);
		EcpUserKey ecpUserKey = ecpUserInfoService.findBy(key, StringUtils.isBlank(type) ? 0 : Integer.valueOf(type));
		return ecpUserKey;
	}
	
	public Long getMemberId(HttpServletRequest request, String type){
		String key = SessionUtil.getMemberKey(request);
		EcpUserKey ecpUserKey = ecpUserInfoService.findBy(key, StringUtils.isBlank(type) ? 0 : Integer.valueOf(type));
		return ecpUserKey.getId();
	}
	
	
	public ResponseBean newResponseBean(){
		return new ResponseBean(ResponseErrorCode.ERROR, ResponseErrorMessage.ERROR);
	}
}
