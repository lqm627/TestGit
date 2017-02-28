package com.easylotto.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserInfo;
import com.easylotto.core.service.userCenter.EcpSignInService;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.config.EmailConfig;
import com.wechat.webapi.util.EmailUtil;
import com.wechat.webapi.web.config.SpringContext;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class WechatHandlerService {

	protected static final Logger logger = LoggerFactory.getLogger(WechatHandlerService.class);

	private String key;
	private WxMpUser user;
	private String[] urls;
	private String type;
	private String openId;
	
	@Autowired private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Autowired private EmailConfig config;

	public void execute(Runnable runnable){
		logger.info(" ------->>>  threadPoolTaskExecutor.getActiveCount() = " + threadPoolTaskExecutor.getActiveCount());
		threadPoolTaskExecutor.execute(runnable);
	}
	
	public WxMpUser getUser() {
		return user;
	}

	public void setUser(WxMpUser user) {
		this.user = user;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	public String redirectUrl(final String _key, final String key, String preUrl, final String redirectUrl){
		execute(new Runnable() {
			public void run() {
				JedisUtil jedisTemplate = SpringContext.getBean(JedisUtil.class);
				jedisTemplate.set("URL:" + _key, redirectUrl);
			}
		});
		
		String _url = preUrl + this.key + "/i/"+ _key + "/" + key + "?_tmptime="+System.currentTimeMillis();
		return _url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	private Lock lock = new ReentrantLock();
	
	private void create(WxMpUser wxUser, String type, String sign, String key){
		lock.lock();
		EcpUserInfoService ecpUserInfoService = SpringContext.getBean(EcpUserInfoService.class);
		EcpSignInService ecpSignInService = SpringContext.getBean(EcpSignInService.class);
		EcpUserInfo ecpUserInfo = null;
		try {
			ecpUserInfo = ecpUserInfoService.findByOpenId(wxUser.getOpenId(), type);
			int result = 0;
			boolean state = false;
			if (ecpUserInfo == null) {
				result = ecpUserInfoService.save(wxUser, Integer.valueOf(type));
				state = true;
				ecpUserInfo = ecpUserInfoService.findByOpenId(wxUser.getOpenId(), type);
			}
			
			if(null != ecpUserInfo && StringUtils.isNotBlank(key)){
				ecpUserInfoService.createUserKey(ecpUserInfo, wxUser.getOpenId(), key);
			}
			// 还需要向ecp_user表中插入一条数据
			// 获取到刚添加的用户对象
			if (result > 0 || ecpUserInfo != null) {
				if(null == ecpUserInfo.getInt_sub_status() || 0 == ecpUserInfo.getInt_sub_status().intValue()){
					state = true;
				}
				long intAccountId = ecpUserInfo.getInt_account_id();

				// 验证ecp_user表是否已存在该数据
				EcpUser ecpUser = ecpUserInfoService.findEcpUserById(intAccountId);
				if (ecpUser == null) {
					ecpUserInfoService.addEcpUser(ecpUserInfo);
					// 第一次添加用户时会赠送100积分 需要添加积分交易明细,目前方案没确定，新关注用户都不赠送积分
					ecpSignInService.addFundLog(intAccountId, 100D, 4, 7);
				}
			}
			
//			if(state){
//			
//			}
		} catch (Exception e) {
			logger.error("", e);
			EmailUtil.sendToGroup(type + "wechat", "系统异常，请及时跟进各微信服务是否正常！ No.3", config);
		}finally {
			lock.unlock();
		}
	}
	
	
	public void pushActivity(){
//		if(state){
//		String scanCode=activityLogService.getQCScanLog(wxUser.getOpenId());
//		if(scanCode!=null){
//			PushBJActivityMsg bjActivityMsg=new PushBJActivityMsg();
//			bjActivityMsg.setOpenId(wxUser.getOpenId());
//			bjActivityMsg.setVcCode(scanCode);
//			bjActivityMsg.setWebserverURL(webServerUrl);
//			bjActivityMsg.setServerUrl(serverUrl);
//			bjActivityMsg.setDesKey(desKey);
//			new Thread(new PushBJActivityThread(bjActivityMsg)).start();
//		}
//		PushActivityMsg activityMsg = new PushActivityMsg();
//		activityMsg.setOpenId(wxUser.getOpenId());
//		activityMsg.setWebserverURL(webServerUrl);
//		activityMsg.setServerUrl(serverUrl);
//		new Thread(new PushActivityThread(activityMsg)).start();
//	}
	}
	
	
	
	
	
	public String subscribe() {
		try {
			execute(new Runnable() {
				public void run() {
					create(user, type, "subscribe", "");
				}
			});
		} catch (Exception e) {
			logger.error("", e);
			EmailUtil.sendToGroup(type + "wechat", "系统异常，请及时跟进各微信服务是否正常！ No.4", config);
		}
		return "谢谢您的关注！";
	}
	
	public String member(){
		String _url = "";
		try {
			if(StringUtils.isNotBlank(openId)){
				final String key = (System.currentTimeMillis() + RandomUtils.nextLong())+"";
				if(null == user){
					user = new WxMpUser();
					user.setOpenId(openId);
				}
				execute(new Runnable() {
					public void run() {
						create(user, type, "member", key);
					}
				});
				String _key = "" + (System.currentTimeMillis() + RandomUtils.nextInt());
				String memberCenterUrl = urls[2];
				String serverUrl=urls[0];
				_url = redirectUrl(_key, key, serverUrl, memberCenterUrl);
				_url = "<a href='" + _url + "'>点击进入会员中心</a>";
			}
		} catch (Exception e) {
			logger.error("", e);
			EmailUtil.sendToGroup(type + "wechat", "系统异常，请及时跟进各微信服务是否正常！ No.5", config);
		}
		return _url;
	}
	
	public String picktools(){
		String _url = "";
		try {
			if(StringUtils.isNotBlank(openId)){
				final String key = (System.currentTimeMillis() + RandomUtils.nextLong())+"";
				if(null == user){
					user = new WxMpUser();
					user.setOpenId(openId);
				}
				execute(new Runnable() {
					public void run() {
						create(user, type, "picktools", key);
					}
				});
				String _key = "" + (System.currentTimeMillis() + RandomUtils.nextInt());
				String picktoolsUrl = urls[3];
				String serverUrl=urls[0];
				_url = redirectUrl(_key, key, serverUrl, picktoolsUrl);
				_url = "<a href='" + _url + "'>点击进入模拟选号</a>";
			}
		} catch (Exception e) {
			logger.error("", e);
			EmailUtil.sendToGroup(type + "wechat", "系统异常，请及时跟进各微信服务是否正常！ No.6", config);
		}
		return _url;
	}
	
	public String vote(){
		String _url = "";
		try {
			if(StringUtils.isNotBlank(openId)){
				final String key = (System.currentTimeMillis() + RandomUtils.nextLong())+"";
				if(null == user){
					user = new WxMpUser();
					user.setOpenId(openId);
				}
				execute(new Runnable() {
					public void run() {
						create(user, type, "vote", key);
					}
				});
				String _key = "" + (System.currentTimeMillis() + RandomUtils.nextInt());
				String voteUrl = urls[4];
				String serverUrl=urls[0];
				_url = redirectUrl(_key, key, serverUrl, voteUrl);
				_url = "<a href='" + _url + "'>点击进入投票</a>";
			}
		} catch (Exception e) {
			logger.error("", e);
			EmailUtil.sendToGroup(type + "wechat", "系统异常，请及时跟进各微信服务是否正常！ No.7", config);
		}
		return _url;
	}
	
	
	public String unsubscribe(){
		
		execute(new Runnable() {
			public void run() {
				EcpUserInfoService ecpUserInfoService = SpringContext.getBean(EcpUserInfoService.class);
				String accountId= ecpUserInfoService.getAccountId(openId);
				if(accountId != null){
					ecpUserInfoService.updateUserStatus(Long.valueOf(accountId));
					String sysDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					Date unSubscribeTime = DateTimeUtil.toDate(sysDate);
					EcpUserFundLog ecpUserFundLog = new EcpUserFundLog();
					ecpUserFundLog.setInt_account_id(Long.valueOf(accountId));
					ecpUserFundLog.setInt_oper_type(-1);
					ecpUserFundLog.setDt_oper_time(unSubscribeTime);
					ecpUserFundLog.setDec_amount(0.0);
					ecpUserFundLog.setVc_bill_no("0");
					ecpUserFundLog.setDec_balance(0.0);
					ecpUserFundLog.setInt_account_type(8);
					ecpUserInfoService.addUnsubLog(ecpUserFundLog);
				}
			}
		});
		return null;
	}
	
	public Item activity30() {
		WxMpXmlOutNewsMessage.Item item=null;
		ActivityLogService activityLogService = SpringContext.getBean(ActivityLogService.class);
		String _url = "";
		try {			
			Activity activity=activityLogService.getActivity(30);
			if(StringUtils.isNotBlank(openId)){
				final String key = (System.currentTimeMillis() + RandomUtils.nextLong())+"";
				if(null == user){
					user = new WxMpUser();
					user.setOpenId(openId);
				}
				
				execute(new Runnable() {
					public void run() {
						create(user, type, "activity30", key);
					}
				});
				
				if(activity!=null){
					String _key = "" + (System.currentTimeMillis() + RandomUtils.nextInt());
					String coupletsUrl = activity.getVc_activity_href_url();
					String serverUrl=urls[0];
					_url = redirectUrl(_key, key, serverUrl, coupletsUrl);
									
					item = new WxMpXmlOutNewsMessage.Item();
					item.setTitle(activity.getVc_activity_title());
					item.setDescription(activity.getVc_activity_content());
					item.setPicUrl(activity.getVc_activity_image());
				    item.setUrl(_url);
				}

			}
		
		} catch (Exception e) {
			logger.error("", e);
			EmailUtil.sendToGroup(type + "wechat", "系统异常，请及时跟进各微信服务是否正常！ No.8", config);
		}
		return item;
	}
	
	public Item activity31() {
		WxMpXmlOutNewsMessage.Item item=null;
		ActivityLogService activityLogService = SpringContext.getBean(ActivityLogService.class);
		String _url = "";
		try {			
			Activity activity=activityLogService.getActivity(31);
			if(StringUtils.isNotBlank(openId)){
				final String key = (System.currentTimeMillis() + RandomUtils.nextLong())+"";
				if(null == user){
					user = new WxMpUser();
					user.setOpenId(openId);
				}
				
				execute(new Runnable() {
					public void run() {
						create(user, type, "activity31", key);
					}
				});
				
				if(activity!=null){
					String _key = "" + (System.currentTimeMillis() + RandomUtils.nextInt());
					String dggjsjUrl = activity.getVc_activity_href_url();
					String serverUrl=urls[0];
					String webserverUrl=urls[1];
					_url = redirectUrl(_key, key, serverUrl, dggjsjUrl);
									
					item = new WxMpXmlOutNewsMessage.Item();
					item.setTitle(activity.getVc_activity_title());
					item.setDescription(activity.getVc_activity_content());
					item.setPicUrl(webserverUrl+activity.getVc_activity_image());
				    item.setUrl(_url);
				}

			}
		
		} catch (Exception e) {
			logger.error("", e);
			EmailUtil.sendToGroup(type + "wechat", "系统异常，请及时跟进各微信服务是否正常！ No.9", config);
		}
		return item;
	}
	
	
	
	public Item toactivity31() {
		WxMpXmlOutNewsMessage.Item item=null;
		ActivityLogService activityLogService = SpringContext.getBean(ActivityLogService.class);
		String _url = "";
		try {			
			Activity activity=activityLogService.getActivity(31);
			if(StringUtils.isNotBlank(openId)){
				final String key = (System.currentTimeMillis() + RandomUtils.nextLong())+"";
				if(null == user){
					user = new WxMpUser();
					user.setOpenId(openId);
				}
				
				execute(new Runnable() {
					public void run() {
						create(user, type, "activity31", key);
					}
				});
				
				if(activity!=null){
					String _key = "" + (System.currentTimeMillis() + RandomUtils.nextInt());
					String dggjsjUrl = activity.getVc_activity_href_url();
					String serverUrl=urls[0];
					String webserverUrl=urls[1];
					_url = redirectUrl(_key, key, serverUrl, dggjsjUrl);
									
					item = new WxMpXmlOutNewsMessage.Item();
					item.setTitle(activity.getVc_activity_title());
					item.setDescription(activity.getVc_activity_content());
					item.setPicUrl(webserverUrl+activity.getVc_activity_image());
				    item.setUrl(_url);
				}

			}
		
		} catch (Exception e) {
			logger.error("", e);
			EmailUtil.sendToGroup(type + "wechat", "系统异常，请及时跟进各微信服务是否正常！ No.10", config);
		}
		return item;
	}

}
