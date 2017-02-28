package com.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.wechat.config.LqmWxConfig;
import com.wechat.config.WxConfig;
import com.wechat.handler.AbstractHandler;
import com.wechat.handler.MenuHandler;
import com.wechat.handler.MsgHandler;
import com.wechat.handler.SubscribeHandler;
import com.wechat.handler.UnsubscribeHandler;

/**
 * 
 * @author
 *
 */
@Service
@EnableConfigurationProperties(LqmWxConfig.class)
public class LqmWxService extends BaseWxService {

	@Autowired
	private LqmWxConfig lqmWxConfig;

	@Override
	protected WxConfig getServerConfig() {
		return lqmWxConfig;
	}

	@Override
	protected MenuHandler getMenuHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SubscribeHandler getSubscribeHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UnsubscribeHandler getUnsubscribeHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractHandler getLocationHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MsgHandler getMsgHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractHandler getScanHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getUrls() {
		return lqmWxConfig.getUrls();
	}

	

}
