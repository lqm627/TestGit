///**
// * 
// */
//package com.wechat.webapi.web.thread;
//
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//
////import com.ahwechat.webapi.web.service.ActivityLogService;
////import com.ahwechat.webapi.web.service.MemberService;
//import com.easylotto.core.entity.GetUserInfo;
//import com.easylotto.core.entity.user.EcpUser;
//import com.easylotto.core.entity.user.EcpUserInfo;
//import com.easylotto.core.service.ActivityLogService;
//import com.easylotto.core.service.WechatAccountService;
//import com.easylotto.core.service.userCenter.EcpSignInService;
//import com.easylotto.core.service.userCenter.EcpUserInfoService;
//import com.wechat.webapi.web.config.SpringContext;
//
//import me.chanjar.weixin.mp.bean.result.WxMpUser;
//
///**
// * 更新用户信息，当不存在用户信息，为新增用户推送 促销活动信息 
// * 
// * @author CreateName: UpdateName:
// * @see QQ：
// * @see E-MAIL：
// * @see Function: TODO
// * @see UpdateUserInfoThread
// * @see CreateDate: 2016年6月15日 上午11:50:05 UpdateDate: 2016年6月15日 上午11:50:05
// * @see Copyright
// * @since JDK1.7.*
// * @version 1.0
// */
//public class UpdateUserInfoThread implements Runnable {
//
//	private final Logger logger = LogManager.getLogger(UpdateUserInfoThread.class);
//
//	private WxMpUser wxUser;
//	
//	private String webserverUrl;
//	private String serverUrl;
//	private String desKey;
//	private String wechatService;
//
//	public UpdateUserInfoThread(WxMpUser wxUser, String[] urls, String desKey, String wechatService) {
//		this.wxUser = wxUser;
//		this.serverUrl= urls[0];
//		this.webserverUrl = urls[1];
//		this.desKey=desKey;
//		this.wechatService = wechatService;
//	}
//	
//	public UpdateUserInfoThread(WxMpUser wxUser,String[] urls, String desKey) {
//		this.wxUser = wxUser;
//		this.serverUrl= urls[0];
//		this.webserverUrl = urls[1];
//		this.desKey=desKey;
//	}
//
//	public void run() {
//		lock.lock();
//		try {
//			if (null != wxUser) {
//				create(wxUser, serverUrl, webserverUrl,desKey, wechatService);
//			}
//		} catch (Exception e) {
//			logger.error("", e);
//		}finally{
//			lock.unlock();
//		}
//	}
//
//	private Lock lock = new ReentrantLock();
//
//	public void create(WxMpUser wxUser, String serverUrl, String webServerUrl,String desKey, final String service) {
//
//		EcpUserInfoService ecpUserInfoService = SpringContext.getBean(EcpUserInfoService.class);
//		EcpSignInService ecpSignInService = SpringContext.getBean(EcpSignInService.class);
//		ActivityLogService activityLogService=SpringContext.getBean(ActivityLogService.class);
//		try {
//			EcpUserInfo ecpUserInfo = ecpUserInfoService.findByOpenId(wxUser.getOpenId());
//			int result = 0;
//			boolean state = false;
//			if (ecpUserInfo == null) {
//				result = ecpUserInfoService.save(wxUser);
//				state = true;
//			}
//			
//			// 还需要向ecp_user表中插入一条数据
//			// 获取到刚添加的用户对象
//			if (result > 0 || ecpUserInfo != null) {
//				ecpUserInfo = ecpUserInfoService.findByOpenId(wxUser.getOpenId());
//				if(null == ecpUserInfo.getInt_sub_status() || 0 == ecpUserInfo.getInt_sub_status().intValue()){
//					state = true;
//				}
//				long intAccountId = ecpUserInfo.getInt_account_id();
//
//				// 从微信获取用户信息更新ecp_user_info表数据
//				String lang = "zh_CN";
//				final GetUserInfo getUserInfo = new GetUserInfo();
//				getUserInfo.setLang(lang);
//				getUserInfo.setOpenId(wxUser.getOpenId());
//				getUserInfo.setInt_account_id(intAccountId);
//
//				new Thread(new Runnable() {
//					public void run() {
//						updateUserInfo(getUserInfo.getOpenId(),
//								getUserInfo.getLang(),
//								getUserInfo.getInt_account_id(), service);
//						
//					}
//				}).start();
//
//				// 验证ecp_user表是否已存在该数据
//				EcpUser ecpUser = ecpUserInfoService.findEcpUserById(intAccountId);
//
//				if (ecpUser == null) {
//					ecpUserInfoService.addEcpUser(ecpUserInfo);
//					// 第一次添加用户时会赠送100积分 需要添加积分交易明细,目前方案没确定，新关注用户都不赠送积分
//					ecpSignInService.addFundLog(intAccountId, 0D, 4, 7);
//				}
//			}
//			
////			if(state){
////				String scanCode=activityLogService.getQCScanLog(wxUser.getOpenId());
////				if(scanCode!=null){
////					PushBJActivityMsg bjActivityMsg=new PushBJActivityMsg();
////					bjActivityMsg.setOpenId(wxUser.getOpenId());
////					bjActivityMsg.setVcCode(scanCode);
////					bjActivityMsg.setWebserverURL(webServerUrl);
////					bjActivityMsg.setServerUrl(serverUrl);
////					bjActivityMsg.setDesKey(desKey);
////					new Thread(new PushBJActivityThread(bjActivityMsg)).start();
////				}
////				PushActivityMsg activityMsg = new PushActivityMsg();
////				activityMsg.setOpenId(wxUser.getOpenId());
////				activityMsg.setWebserverURL(webServerUrl);
////				activityMsg.setServerUrl(serverUrl);
////				new Thread(new PushActivityThread(activityMsg)).start();
////			}
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//
//	}
//
//	private void updateUserInfo(String openId, String lang, Long accountId, String service) {
//		try {
//			EcpUserInfoService ecpUserInfoService = SpringContext.getBean(EcpUserInfoService.class);
//			logger.info("  service  --------------------->>    "+service);
//			WechatAccountService wechatAccountService =(WechatAccountService) SpringContext.getBean(service);
//			
//			WxMpUser user = wechatAccountService.getWxMpService().userInfo(openId, lang);
//
//			ecpUserInfoService.updateUserInfoByWX(user, accountId);
//
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//	}
//
//
//
//	public String getWebserverUrl() {
//		return webserverUrl;
//	}
//
//	public void setWebserverUrl(String webserverUrl) {
//		this.webserverUrl = webserverUrl;
//	}
//
//	public String getDesKey() {
//		return desKey;
//	}
//
//	public void setDesKey(String desKey) {
//		this.desKey = desKey;
//	}
//
//	public String getServerUrl() {
//		return serverUrl;
//	}
//
//	public void setServerUrl(String serverUrl) {
//		this.serverUrl = serverUrl;
//	}
//
//
//	
//	
//	
//}
