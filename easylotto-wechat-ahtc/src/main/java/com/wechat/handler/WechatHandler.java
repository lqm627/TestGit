package com.wechat.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easylotto.core.entity.user.EcpUserInfo;
import com.easylotto.core.service.WechatHandlerService;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.config.WxConfig;
import com.wechat.service.BaseWxService;
import com.wechat.webapi.web.common.MenuType;
import com.wechat.webapi.web.config.SpringContext;
import com.wechat.webapi.web.thread.MenuClickThread;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class WechatHandler implements WxMpMessageHandler {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private String[] urls;
	private String type;
	private String key;
	
	private String service;
	private WxConfig wxConfig;
	
	
	public WxMpXmlOutMessage message(WxMpXmlMessage wxMessage, String content){
		WxMpXmlOutMessage message = WxMpXmlOutMessage.TEXT().content(content).fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
		return message;
	}
	
	public WxMpXmlOutMessage newsMessage(WxMpXmlMessage wxMessage,Item item){
		WxMpXmlOutNewsMessage m= WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
		m.addArticle(item);
		return m;
	}
	
	public WxMpXmlOutMessage imageMessage(WxMpXmlMessage wxMessage,WxMediaUploadResult wxMediaUploadResult){
		WxMpXmlOutImageMessage m= WxMpXmlOutMessage.IMAGE().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
		m.setMediaId(wxMediaUploadResult.getMediaId());
		return m;
	}
	
	public void update(String openId, WxMpService wxMpService){
		try {
			EcpUserInfoService ecpUserInfoService = SpringContext.getBean(EcpUserInfoService.class);
			EcpUserInfo ecpUserInfo = ecpUserInfoService.findByOpenId(openId, wxConfig.getType());
			if(null != ecpUserInfo){
				 BaseWxService weixinService = (BaseWxService) wxMpService;
				WxMpUser user = weixinService.getUserService().userInfo(openId, "zh_CN");
				ecpUserInfoService.updateUserInfoByWX(user, ecpUserInfo.getInt_account_id());
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
		
		String openId = wxMessage.getFromUser();

	    String _key = wxMessage.getEventKey();
	    
	    if(StringUtils.isBlank(_key)){
	    	_key=service;
	    }
	    logger.info("------------------------------------------------------------------ >>>>    " + _key +"  start !!! ");
		
		this.logger.info(" --------------->> 用户 OPENID: " + wxMessage.getFromUser());
		
		WxMpUser user = new WxMpUser();
		user.setOpenId(openId);
		WxMpXmlOutMessage message = null;
		user.setSubscribeTime(System.currentTimeMillis());
		String _content = "";
		WechatHandlerService wechatHandlerService = (WechatHandlerService) SpringContext.getBean("wechatHandlerService");
		if(StringUtils.isNotBlank(_key)){
			try {
				wechatHandlerService.setOpenId(openId);
				wechatHandlerService.setUrls(urls);
				wechatHandlerService.setUser(user);
				wechatHandlerService.setType(type);
				wechatHandlerService.setKey(this.key);
				
				if(!_key.equals("noactivity")){
					if(!_key.equals("unsubscribe")){
						wechatHandlerService.execute(new MenuClickThread(openId, MenuType.getType(_key), type));	
					}
					Object content = wechatHandlerService.getClass().getDeclaredMethod(_key).invoke(wechatHandlerService);
					
					
					logger.info("------------------------------------------------------------------ >>>>      " + _key + " end !!!  ");
					if(null != content && content instanceof String){
						_content = content.toString();
						logger.info("------------------------------------------------------------------ >>>>    21   _content = "+_content);
					}else if(null != content && content instanceof Item){
						Item  m = (Item)content;
						logger.info("------------------------------------------------------------------ >>>>    22   content instanceof Item ");
						message = newsMessage(wxMessage, m);
					}
					
	
				}else{
					String url=urls[5];
					if(StringUtils.isNotBlank(url)&&url!="null"){
						InputStream inputStream = new FileInputStream(new File(url));
						WxMpMaterialService materialService = new WxMpMaterialServiceImpl(wxMpService);
						WxMediaUploadResult uploadMediaResVoice = materialService.mediaUpload(WxConsts.MEDIA_IMAGE, WxConsts.FILE_JPG, inputStream);
						message=imageMessage(wxMessage, uploadMediaResVoice);
					}else{
						_content = "敬请期待更多精彩活动！";
					}

				}
				
				if(StringUtils.isNotBlank(_content)){
					message = message(wxMessage, _content);
				}


			} catch (Exception e) {
				logger.error("", e);
			}
		}
		
		try {
			update(openId, wxMpService);
		} catch (Exception e) {
			logger.error("", e);
		}
		  
		return message;

	}
	
	public WechatHandler(String[] urls, String service, WxConfig wxConfig) {
		this.urls = urls;
		this.service=service;
		if(null != wxConfig){
			this.type = wxConfig.getType();
			this.wxConfig = wxConfig;
			this.key = wxConfig.getKey();
		}
	}
	
	public WechatHandler(String[] urls, WxConfig wxConfig) {
		this.urls = urls;
		if(null != wxConfig){
			this.type = wxConfig.getType();
			this.wxConfig = wxConfig;
			this.key = wxConfig.getKey();
		}
	}
	
	public WechatHandler(String[] urls, String service) {
		this.urls = urls;
		this.service=service;
	}
	
	
	public WechatHandler(String service) {
		this.service=service;
	}

	public WechatHandler(String service, WxConfig wxConfig) {
		this.service=service;
		if(null != wxConfig){
			this.type = wxConfig.getType();
			this.wxConfig = wxConfig;
			this.key = wxConfig.getKey();
		}
	}

	protected WxConfig getWxConfig() {
		return wxConfig;
	}
	
}
