package com.wechat.webapi.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.constants.MediaTypes;

import com.alibaba.fastjson.JSON;
import com.easylotto.core.service.ClientServiceFactory;
import com.easylotto.core.service.IClientService;
import com.easylotto.core.util.SessionUtil;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.config.SpringContext;
import com.wechat.webapi.web.model.RequestBean;
import com.wechat.webapi.web.model.ResponseBean;

@RestController
public class WechatClientServiceControllerler extends BaseController {

	@RequestMapping(value = "/wechat/client/service", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseBean getData(HttpServletRequest request, HttpServletResponse response, @RequestParam String request_data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ResponseBean responseBean = new ResponseBean(ResponseErrorMessage.ERROR);
		try {
			RequestBean bean = request(request_data);
			Map<String, String> map=new HashMap<String, String>();
			if(null == bean || StringUtils.isBlank(bean.getReqType())) return super.handleResponse(responseBean, response);
			ClientServiceFactory clientServiceFactory = SpringContext.getBean(ClientServiceFactory.class);
			IClientService clientService = clientServiceFactory.getService(bean.getReqType());
			if(null == clientService) return super.handleResponse(responseBean, response);
			String data = decrypt(bean);
			String key = SessionUtil.getMemberKey(request);
			Long memberId = getMemberId(key);
			responseBean = clientService.execute(data, memberId,map);
			responseBean.setReqFlag(bean.getReqFlag());
		} catch (Exception e) {
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}
		return super.handleResponse(responseBean, response);
	}
	
	private Long getMemberId(String key){
		if(StringUtils.isBlank(key)) return null;
//		EcpUserKey bean = ecpUserInfoService.findBy(key);
//		if(null != bean) return bean.getId();
		return null;
	}
	
	
	private RequestBean request(String request_data){
		if(StringUtils.isBlank(request_data)) return null;
		RequestBean bean = JSON.parseObject(request_data, RequestBean.class);
		return bean;
	}
	
	private String decrypt(RequestBean bean){
		return bean.getReqData();
	}
	
//	
//	@RequestMapping(value = "/wechat/client/service/test")
//	public void getDataTest(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		System.out.println("----------------"+request.getSession().getId());
//	}
}
