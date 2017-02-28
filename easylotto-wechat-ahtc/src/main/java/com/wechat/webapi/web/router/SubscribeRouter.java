package com.wechat.webapi.web.router;

import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;

/**
 * 关注
 * @author 
 *
 */
public class SubscribeRouter implements WechatRouter {
	
	public WxMpMessageRouter add(WxMpMessageRouter wxMpMessageRouter, WxMpMessageHandler handler){
		return wxMpMessageRouter
				 .rule()
				 .async(false)
				 .msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_SUBSCRIBE)
				 .handler(handler)
				 .end();
	}
}
