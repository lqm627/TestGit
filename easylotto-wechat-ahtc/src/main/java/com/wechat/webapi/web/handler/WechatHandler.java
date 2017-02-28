//package com.wechat.webapi.web.handler;
//
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.easylotto.core.entity.user.EcpUserInfo;
//import com.easylotto.core.service.WechatHandlerService;
//import com.easylotto.core.service.userCenter.EcpUserInfoService;
//import com.wechat.webapi.service.WechatAccountService;
//import com.wechat.webapi.util.EmailUtil;
//import com.wechat.webapi.web.common.MenuType;
//import com.wechat.webapi.web.config.SpringContext;
//import com.wechat.webapi.web.thread.MenuClickThread;
//
//import me.chanjar.weixin.common.session.WxSessionManager;
//import me.chanjar.weixin.mp.api.WxMpMessageHandler;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
//import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
//import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
//import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage.Item;
//import me.chanjar.weixin.mp.bean.result.WxMpUser;
//
//public class WechatHandler implements WxMpMessageHandler{
//
//	protected static final Logger logger = LoggerFactory.getLogger(WechatHandler.class);
//	
//	private String[] urls;
//	private String service;
//	private String type;
//	private String key;
//	private WechatAccountService accountService;
//	
//	
//	public WxMpXmlOutMessage message(WxMpXmlMessage wxMessage, String content){
//		WxMpXmlOutMessage message = WxMpXmlOutMessage.TEXT().content(content).fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
//		return message;
//	}
//	
//	public WxMpXmlOutMessage newsMessage(WxMpXmlMessage wxMessage,Item item){
//		WxMpXmlOutNewsMessage m= WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
//		m.addArticle(item);
//		return m;
//	}
//	
//	public void update(String openId, WechatAccountService accountService){
//		try {
//			EcpUserInfoService ecpUserInfoService = SpringContext.getBean(EcpUserInfoService.class);
//			EcpUserInfo ecpUserInfo = ecpUserInfoService.findByOpenId(openId, accountService.getType());
//			if(null != ecpUserInfo){
//				WxMpUser user = accountService.getWxMpService().userInfo(openId, "zh_CN");
//				ecpUserInfoService.updateUserInfoByWX(user, ecpUserInfo.getInt_account_id());
//			}
//		} catch (Exception e) {
//			logger.error("", e);
//			EmailUtil.send(accountService.getType()+"wechat", "系统异常，请及时跟进各微信服务是否正常！ No.1");
//		}
//	}
//	
//	
//	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
//		logger.info("------------------------------------------------------------------ >>>>    " + service +"  start !!! ");
//		String openId = wxMessage.getFromUserName();
//		WxMpUser user = new WxMpUser();
//		user.setOpenId(openId);
//		WxMpXmlOutMessage message = null;
//		user.setSubscribeTime(System.currentTimeMillis());
//		String _content = "";
//		WechatHandlerService wechatHandlerService = (WechatHandlerService) SpringContext.getBean("wechatHandlerService");
//		if(StringUtils.isNotBlank(service)){
//			try {
//				wechatHandlerService.setOpenId(openId);
//				wechatHandlerService.setUrls(urls);
//				wechatHandlerService.setUser(user);
//				wechatHandlerService.setType(type);
//				wechatHandlerService.setKey(key);
//				
//				wechatHandlerService.execute(new MenuClickThread(openId, MenuType.getType(service), type));
//				
//				if(service.contains("activity")){
//					Item  m = (Item) wechatHandlerService.getClass().getDeclaredMethod(service).invoke(wechatHandlerService);
//					if(m!=null){
//						message=newsMessage(wxMessage,m);
//					}
//				}else{
//					Object content = wechatHandlerService.getClass().getDeclaredMethod(service).invoke(wechatHandlerService);
////					logger.info("------------------------------------------------------------------ >>>>      " + service + " end !!!  ");
//					if(null != content && content instanceof String){
//						_content = content.toString();
////						logger.info("------------------------------------------------------------------ >>>>    21   _content = "+_content);
//					}
//					
//					if(StringUtils.isNotBlank(_content)){
//						message = message(wxMessage, _content);
//					}
//				}
//
//			} catch (Exception e) {
//				logger.error("", e);
//				EmailUtil.send(accountService.getType()+"wechat", "系统异常，请及时跟进各微信服务是否正常！ No.2");
//			}
//		}
//		
//		if(null != accountService){
//			update(openId, accountService);
//		}
//		  
//		return message;
//
//	}
//	
//	public WechatHandler(String[] urls, String service, WechatAccountService accountService) {
//		this.urls = urls;
//		this.service = service;
//		if(null != accountService){
//			this.type = accountService.getType();
//			this.accountService = accountService;
//		}
//	}
//	
//	public WechatHandler(String[] urls, String service, WechatAccountService accountService, String key) {
//		this.urls = urls;
//		this.key = key;
//		this.service = service;
//		if(null != accountService){
//			this.type = accountService.getType();
//			this.accountService = accountService;
//		}
//	}
//	
//	public WechatHandler(String[] urls, String service) {
//		this.urls = urls;
//		this.service = service;
//	}
//	
//	
//	public WechatHandler(String service) {
//		this.service = service;
//	}
//
//	public WechatHandler() {
//		
//	}
//	
//}
