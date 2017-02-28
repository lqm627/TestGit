//package com.wechat.webapi.web.handler;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.easylotto.commons.util.DateTimeUtil;
//import com.easylotto.core.entity.user.EcpUserFundLog;
//import com.easylotto.core.service.userCenter.EcpUserInfoService;
//import com.wechat.webapi.web.config.SpringContext;
//
//import me.chanjar.weixin.common.session.WxSessionManager;
//import me.chanjar.weixin.mp.api.WxMpMessageHandler;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
//import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
//
//public class UnSubscribeHandler implements WxMpMessageHandler {
//
//	protected static final Logger logger = LoggerFactory.getLogger(UnSubscribeHandler.class);
//	
//	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
//
//		String openId = wxMessage.getFromUserName();
//		EcpUserInfoService ecpUserInfoService = SpringContext.getBean(EcpUserInfoService.class);
//		String accountId= ecpUserInfoService.getAccountId(openId);
//		if(accountId!=null){
//			ecpUserInfoService.updateUserStatus(Long.valueOf(accountId));
//			String sysDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//			Date unSubscribeTime = DateTimeUtil.toDate(sysDate);
//			EcpUserFundLog ecpUserFundLog = new EcpUserFundLog();
//			ecpUserFundLog.setInt_account_id(Long.valueOf(accountId));
//			ecpUserFundLog.setInt_oper_type(-1);
//			ecpUserFundLog.setDt_oper_time(unSubscribeTime);
//			ecpUserFundLog.setDec_amount(0.0);
//			ecpUserFundLog.setVc_bill_no("0");
//			ecpUserFundLog.setDec_balance(0.0);
//			ecpUserFundLog.setInt_account_type(8);
//			ecpUserInfoService.addUnsubLog(ecpUserFundLog);
//		}
//		return null;
//
//	}
//
//	public UnSubscribeHandler() {
//		
//	}
//	
//}
