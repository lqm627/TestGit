package com.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.wechat.config.AhtcWxConfig;
import com.wechat.config.WxConfig;
import com.wechat.handler.AbstractHandler;
import com.wechat.handler.MenuHandler;
import com.wechat.handler.MsgHandler;
import com.wechat.handler.SubscribeHandler;
import com.wechat.handler.UnsubscribeHandler;
import com.wechat.handler.ahtc.AhtcLocationHandler;
import com.wechat.handler.ahtc.AhtcMenuHandler;
import com.wechat.handler.ahtc.AhtcMsgHandler;
import com.wechat.handler.ahtc.AhtcUnSubscribeHandler;

/**
 * 
 * @author
 *
 */
@Service
@EnableConfigurationProperties(AhtcWxConfig.class)
public class AhtcWxService extends BaseWxService {

	@Autowired
	private AhtcWxConfig wxConfig;

	@Autowired
	private AhtcLocationHandler locationHandler;

	@Autowired
	private AhtcMenuHandler menuHandler;

	@Autowired
	private AhtcMsgHandler msgHandler;

	@Autowired
	private AhtcUnSubscribeHandler unSubscribeHandler;
	
	@Override
	protected WxConfig getServerConfig() {
		return this.wxConfig;
	}

	@Override
	protected MenuHandler getMenuHandler() {
		return this.menuHandler;
	}

	@Override
	protected SubscribeHandler getSubscribeHandler() {
		return null;
	}

	@Override
	protected UnsubscribeHandler getUnsubscribeHandler() {
		return this.unSubscribeHandler;
	}

	@Override
	protected AbstractHandler getLocationHandler() {
		return null;
	}

	@Override
	protected MsgHandler getMsgHandler() {
		return this.msgHandler;
	}

	@Override
	protected AbstractHandler getScanHandler() {
		return null;
	}

	@Override
	protected String[] getUrls() {
		return wxConfig.getUrls();
	}

}
