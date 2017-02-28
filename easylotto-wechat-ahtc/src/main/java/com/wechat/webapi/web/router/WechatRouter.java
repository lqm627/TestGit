package com.wechat.webapi.web.router;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;

public interface WechatRouter {
	public WxMpMessageRouter add(WxMpMessageRouter wxMpMessageRouter, WxMpMessageHandler handler);
}
