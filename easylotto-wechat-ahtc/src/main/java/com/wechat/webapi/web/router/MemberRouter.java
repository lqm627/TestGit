package com.wechat.webapi.web.router;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;

public class MemberRouter implements WechatRouter {
	
	public WxMpMessageRouter add(WxMpMessageRouter wxMpMessageRouter, WxMpMessageHandler handler){
		return wxMpMessageRouter
				  .rule()
			        .async(false)
			        .msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_CLICK).eventKey("member")
			        .handler(handler)
			      .end();
	}
}
