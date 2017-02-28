//package com.wechat.webapi.web.handler;
//
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import me.chanjar.weixin.common.session.WxSessionManager;
//import me.chanjar.weixin.mp.api.WxMpMessageHandler;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
//import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
//
//public class MemberHandler implements WxMpMessageHandler {
//
//	protected static final Logger logger = LoggerFactory.getLogger(MemberHandler.class);
//	
//	private String[] urls;
//	private String service;
//	private String type;
//	private String accountService;
//	private WechatHandler wechatHandler;
//	
//	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
//		return wechatHandler.handle(wxMessage, context, wxMpService, sessionManager);
//	}
//	
//	
//	public void WechatHandler(){
//		
//	}
//	
//	public MemberHandler(WechatHandler wechatHandler) {
//		this.wechatHandler = wechatHandler;
//	}
//	
//	public MemberHandler(String[] urls, String service, String type) {
//		this.urls = urls;
//		this.service = service;
//		this.type = type;
//	}
//	
//	public MemberHandler(String service, String type) {
//		this.service = service;
//		this.type = type;
//	}
//	
//	public MemberHandler(String[] urls, String service) {
//		this.urls = urls;
//		this.service = service;
//	}
//	
//	public MemberHandler(String[] urls, String service, String type, String accountService) {
//		this.urls = urls;
//		this.service = service;
//		this.type = type;
//		this.accountService = accountService;
//	}
//	
//	public MemberHandler(String service) {
//		this.service = service;
//	}
//
//	public MemberHandler() {
//		
//	}
//	
//}
