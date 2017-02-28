package com.wechat.webapi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easylotto.commons.util.AESUtil;
import com.easylotto.core.entity.ContentData;
import com.easylotto.core.service.ContentService;
import com.wechat.service.BaseWxService;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.config.SpringContext;
import com.wechat.webapi.web.model.ResponseBean;

@RestController
public class ContentServiceController extends BaseController {

	@Autowired
	private ContentService contentService;
	
	@Value("${api.aes.key}")
	private String aesKey;
	
	@Value("${api.aes.state}")
	private boolean aesState;
	
	
	@RequestMapping("/{key}/{type}/list")
	public ResponseBean list(HttpServletRequest request, HttpServletResponse response, @PathVariable String key, @PathVariable String type) throws Exception {
		ResponseBean responseBean = new ResponseBean(ResponseErrorMessage.ERROR);
		try {
			if(aesState){
				key = new String(AESUtil.Decrypt(Hex.decodeHex(key.toCharArray()), aesKey.getBytes()));
			}
			BaseWxService wxService = (BaseWxService) SpringContext.getBean(key + "WxService");
			if (null == wxService)
				return super.handleResponse(responseBean, response);
			
			List<ContentData> list= contentService.findList(wxService.getType(), type);
			responseBean.setData(list);
			responseBean.success();
		} catch (Exception e) {
			logger.error("", e);
		}
		return super.handleResponse(responseBean, response);
	}
	
	@RequestMapping("/{id}/detail")
	public ResponseBean value(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) throws Exception {
		ResponseBean responseBean = new ResponseBean(ResponseErrorMessage.ERROR);
		try {
			ContentData contentData= contentService.findDetail(id);
			responseBean.setData(contentData);
			responseBean.success();
		} catch (Exception e) {
			logger.error("", e);
		}
		return super.handleResponse(responseBean, response);
	}
	
}
