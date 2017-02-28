//package com.wechat.webapi.web.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import com.wechat.webapi.service.WechatAccountService;
//import com.wechat.webapi.web.common.MenuType;
//import com.wechat.webapi.web.handler.WechatHandler;
//import com.wechat.webapi.web.router.Activity30Router;
//import com.wechat.webapi.web.router.Activity31Router;
//import com.wechat.webapi.web.router.MemberRouter;
//import com.wechat.webapi.web.router.PicktoolsRouter;
//import com.wechat.webapi.web.router.SubscribeRouter;
//import com.wechat.webapi.web.router.UnSubscribeRouter;
//import com.wechat.webapi.web.router.VoteRouter;
//
//import me.chanjar.weixin.mp.api.WxMpMessageRouter;
//
//
//@Configuration
//public class WechatConfig {
//	
//	
//	@Bean
//	@ConfigurationProperties(prefix = "ahtc.wechat")
//	public WechatAccountService ahtcAccountService() {
//		return new WechatAccountService();
//	}
//	
//	@Bean
//	@ConfigurationProperties(prefix = "jcah.wechat")
//	public WechatAccountService jcahAccountService() {
//		return new WechatAccountService();
//	}
//	
//	
//	
//	@Bean
//	@DependsOn(value="ahtcAccountService")
//	public WxMpMessageRouter ahtcWxMpMessageRouter() {
//		WechatAccountService wechatAccountService = ahtcAccountService();
//		ahtcRouter(wechatAccountService);
//		return wechatAccountService.getWxMpMessageRouter();
//	}
//	
//	
//	@Bean
//	@DependsOn(value="jcahAccountService")
//	public WxMpMessageRouter jcahWxMpMessageRouter() {
//		WechatAccountService wechatAccountService = jcahAccountService();
//		jcahRouter(wechatAccountService);
//		return wechatAccountService.getWxMpMessageRouter();
//	}
//	
//	private void jcahRouter(WechatAccountService accountService) {
//		
//		String[] urls = new String[10];
//		urls[0] = accountService.getServer();
//		urls[1] = accountService.getWebserver();
//		urls[2] = accountService.getFront();
//		urls[3] = accountService.getPicktools();
//		urls[4] = accountService.getVote();
//		
//		String key = accountService.getKey();
//
//		WxMpMessageRouter wxMpMessageRouter = accountService.getWxMpMessageRouter();
//		
//	}
//	
//	private void ahtcRouter(WechatAccountService accountService) {
//		
//		String[] urls = new String[10];
//		urls[0] = accountService.getServer();
//		urls[1] = accountService.getWebserver();
//		urls[2] = accountService.getFront();
//		urls[3] = accountService.getPicktools();
//		urls[4] = accountService.getVote();
//		
//		String key = accountService.getKey();
//
//		WxMpMessageRouter wxMpMessageRouter = accountService.getWxMpMessageRouter();
//		
//		// 关注
//		new SubscribeRouter().add(wxMpMessageRouter, new WechatHandler(urls, MenuType.SUB.getKey(), accountService));
//		// 投票活动
//		new VoteRouter().add(wxMpMessageRouter, new WechatHandler(urls, MenuType.VOTE.getKey(), accountService, key));
//		// 取消关注
//		new UnSubscribeRouter().add(wxMpMessageRouter, new WechatHandler("unSub"));
//		// 会员中心
//		new MemberRouter().add(wxMpMessageRouter, new WechatHandler(urls, MenuType.MEMBER.getKey(), accountService, key));
//		// 模拟选号
//		new PicktoolsRouter().add(wxMpMessageRouter, new WechatHandler(urls, MenuType.PICKTOOLS.getKey(), accountService, key));
//		// 活动ID=30的活动，春联活动
//		new Activity30Router().add(wxMpMessageRouter, new WechatHandler(urls, MenuType.activity30.getKey(), accountService, key));
//		// 活动ID=31的活动，顶呱刮奖上奖活动
//		new Activity31Router().add(wxMpMessageRouter, new WechatHandler(urls, MenuType.activity31.getKey(), accountService, key));
//
//	}
//	
//}
