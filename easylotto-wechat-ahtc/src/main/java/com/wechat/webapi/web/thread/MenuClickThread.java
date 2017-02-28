package com.wechat.webapi.web.thread;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.core.entity.EcpMenuClickLog;
import com.easylotto.core.entity.user.EcpUserInfo;
import com.easylotto.core.service.MenuClickLogService;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.web.config.SpringContext;

public class MenuClickThread implements Runnable {
	
	protected static final Logger logger = LoggerFactory.getLogger(MenuClickThread.class);
	
	private String openId;
	private int menuType;
	private String type;
	
	
	
	public MenuClickThread(String openId, int menuType, String type) {
		super();
		this.openId = openId;
		this.menuType = menuType;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			if(StringUtils.isNotBlank(openId)){
				EcpMenuClickLog bean = build(openId, menuType, type);
				if(bean != null){
					addClickLog(bean);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	private void addClickLog(EcpMenuClickLog instance){
		MenuClickLogService menuClickLogService = SpringContext.getBean(MenuClickLogService.class);
		menuClickLogService.addClickLog(instance);
	}
	
	private EcpMenuClickLog build(String openId,int menuType, String userType){
		EcpMenuClickLog bean = new EcpMenuClickLog();
		if(StringUtils.isBlank(userType)){
			logger.info("------------------------------------------------------------------ >>>>    Menu log Wechat type is null ");
			return null;
		}
		EcpUserInfoService ecpUserInfoService = SpringContext.getBean(EcpUserInfoService.class);
		EcpUserInfo user = ecpUserInfoService.findByOpenId(openId, userType);
		
		if(user != null){
			String strTime = DateTimeUtil.toString(new Date());
		    Date clickTime = DateTimeUtil.toDate(strTime);
			bean.setInt_account_id(user.getInt_account_id());
			bean.setInt_menu_type(menuType);  
			bean.setVc_open_id(openId);
			bean.setDt_click_time(clickTime);	
		}else{
			bean=null;
		}

		return bean;
	}

}
