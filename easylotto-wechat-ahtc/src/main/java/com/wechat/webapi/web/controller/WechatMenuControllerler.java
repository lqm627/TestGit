package com.wechat.webapi.web.controller;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMenuServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.config.AhjcWxConfig;
import com.wechat.config.AhtcWxConfig;
import com.wechat.config.JwlWxConfig;

@RestController
public class WechatMenuControllerler extends BaseController {

	@Autowired private EcpUserInfoService ecpUserInfoService;

	private static boolean state = true;
	@Autowired
	private AhtcWxConfig ahtcWxConfig;
	@Autowired
	private JwlWxConfig jwlWxConfig;
	@Autowired
	private AhjcWxConfig ahjcWxConfig;
	
	public WxMpService wechat(String appid,String appsecret,String token){
		
		
		WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
	    wxMpConfigStorage.setAppId(appid); // 设置微信公众号的appid
	    wxMpConfigStorage.setSecret(appsecret); // 设置微信公众号的app corpSecret
	    wxMpConfigStorage.setToken(token); // 设置微信公众号的token	 	    
		
		WxMpService wxMpService = new WxMpServiceImpl();
 	    wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
 	    
 	    return wxMpService;
	}
	
	
	@RequestMapping(value = "/wechat/menu/{server}")
	public void getDataTest(HttpServletRequest request, HttpServletResponse response, @PathVariable String server) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(!state) return; 
		state = false;
		
		if("lqm".equals(server)){
			cai();
			logger.info("--------------------------------------------------  乐趣门 菜单已更新！ ");
		}else if("tcahwechat".equals(server)){
			weicai();
			logger.info("--------------------------------------------------  体彩安徽订阅号菜单已更新！ ");
		}
		else if("test".equals(server)){
			testcai();
			logger.info("--------------------------------------------------  测试号菜单已更新！ ");
		}		
		else if("jwl".equals(server)){
			jwlcai();
			logger.info("--------------------------------------------------  竞玩乐菜单已更新！ ");
		}else if("ahjc".equals(server)){
			ahjccai();
			logger.info("--------------------------------------------------  安徽竞彩菜单已更新！ ");
		}
		try {
			response.getWriter().write("ok");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/wechat/getMenu")
	public void getMenu(){
		try {
			WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
		    wxMpConfigStorage.setAppId("wxf9eb8797182d7cd4"); // 设置微信公众号的appid
		    wxMpConfigStorage.setSecret("5f9ebdf2ae312e3d6126dbed448d6c8d"); // 设置微信公众号的app corpSecret
//		    wxMpConfigStorage.setToken("yicaile"); // 设置微信公众号的token	 	    
			
			WxMpService wxMpService = new WxMpServiceImpl();
	 	    wxMpService.setWxMpConfigStorage(wxMpConfigStorage);	
	 	    
	 	   WxMpMenuService menuService = new WxMpMenuServiceImpl(wxMpService);
	 	   WxMpGetSelfMenuInfoResult wxMpGetSelfMenuInfoResult=menuService.getSelfMenuInfo();
		   System.out.println(wxMpGetSelfMenuInfoResult);
		   
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	public void cai(){ 
//	    String url = "http://wechat.weicai.com";
		String url = "http://lqm.24cai.com";

	    
		String appId="wxe50759beade9edb0";
		String appSecret="09d1683fc654faed415e89c44a178533";
		String token="yicalle";
	    
	    WxMpService wxMpService=wechat(appId, appSecret, token);
		
		// 设置菜单
		try {
			WxMenu wxMenu = new WxMenu();

			WxMenuButton btn11=new WxMenuButton();
			btn11.setType("view");
			btn11.setName("新闻资讯");
			btn11.setUrl(url+"/news.html");
			
			WxMenuButton btn12=new WxMenuButton();
			btn12.setType("view");
			btn12.setName("最新开奖");
			btn12.setUrl(url+"/latestLottery.html#/index");
			
			WxMenuButton btn13=new WxMenuButton();
			btn13.setType("view");
			btn13.setName("公益活动");
			btn13.setUrl(url + "/pwNews.html");
			
			WxMenuButton btn14=new WxMenuButton();
			btn14.setType("view");
			btn14.setName("顶呱刮");
			btn14.setUrl(url + "/dgg.html");
			
			WxMenuButton men1=new WxMenuButton();
			men1.setName("体彩驿站");
			men1.getSubButtons().add(btn11);
			men1.getSubButtons().add(btn12);
			men1.getSubButtons().add(btn13);
			men1.getSubButtons().add(btn14);
			
			WxMenuButton btn21 = new WxMenuButton();
			btn21.setType("click");
			btn21.setName("春联征集活动");
			btn21.setKey("activity30");
			
			WxMenuButton btn22 = new WxMenuButton();
			btn22.setType("click");
			btn22.setName("晒票有奖");
			btn22.setKey("activity31");
			
			WxMenuButton btn20 = new WxMenuButton();
			btn20.setType("click");
			btn20.setName("精彩互动");
			btn20.setKey("noactivity");
			
			WxMenuButton men2=new WxMenuButton();
			men2.setName("精彩");
			men2.getSubButtons().add(btn21);
			men2.getSubButtons().add(btn22);
						
			WxMenuButton btn31=new WxMenuButton();
			btn31.setType("click");
			btn31.setName("模拟选号");
			btn31.setKey("picktools");
			
			WxMenuButton btn32=new WxMenuButton();
			btn32.setType("click");
			btn32.setName("会员中心");
			btn32.setKey("member");
			
			WxMenuButton btn33=new WxMenuButton();
			btn33.setType("view");
			btn33.setName("购彩指南");
			btn33.setUrl(url+"/lotteryGuide.html");
			
			WxMenuButton btn34=new WxMenuButton();
			btn34.setType("view");
			btn34.setName("附近网点");
			btn34.setUrl(url+"/TEST_SEV/redirect/ssl/map");
			
			WxMenuButton men3=new WxMenuButton();
			men3.setName("彩蛋");
			men3.getSubButtons().add(btn31);
			men3.getSubButtons().add(btn32);
			men3.getSubButtons().add(btn33);
	//		men3.getSubButtons().add(btn34);

			List<WxMenuButton> buttons=new ArrayList<WxMenuButton>();
			buttons.add(men1);
			buttons.add(btn20);
			buttons.add(men3);


			wxMenu.setButtons(buttons);

			
			 WxMpMenuService menuService = new WxMpMenuServiceImpl(wxMpService);
			 menuService.menuCreate(wxMenu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void weicai(){ 
		    
		    String url = "http://ahtc.24cai.com";
			
			String appId=ahtcWxConfig.getAppid();
			String appSecret=ahtcWxConfig.getAppsecret();
			String token=ahtcWxConfig.getToken();
			WxMpService wxMpService =wechat(appId, appSecret, token);
			
			// 设置菜单
			try {
				WxMenu wxMenu = new WxMenu();

				WxMenuButton btn11=new WxMenuButton();
				btn11.setType("view");
				btn11.setName("新闻资讯");
				btn11.setUrl(url+"/news.html");
				
				WxMenuButton btn12=new WxMenuButton();
				btn12.setType("view");
				btn12.setName("最新开奖");
				btn12.setUrl(url+"/latestLottery.html#/index");
				
				WxMenuButton btn13=new WxMenuButton();
				btn13.setType("view");
				btn13.setName("公益活动");
				btn13.setUrl(url + "/pwNews.html");
				
				WxMenuButton btn14=new WxMenuButton();
				btn14.setType("view");
				btn14.setName("顶呱刮");
				btn14.setUrl(url + "/dgg.html");
				
				WxMenuButton men1=new WxMenuButton();
				men1.setName("体彩驿站");
				men1.getSubButtons().add(btn11);
				men1.getSubButtons().add(btn12);
				men1.getSubButtons().add(btn13);
				men1.getSubButtons().add(btn14);
				
				WxMenuButton btn21 = new WxMenuButton();
				btn21.setType("click");
				btn21.setName("春联征集活动");
				btn21.setKey("activity30");
				
				WxMenuButton btn22 = new WxMenuButton();
				btn22.setType("click");
				btn22.setName("晒票有奖");
				btn22.setKey("activity31");
				
				WxMenuButton btn20 = new WxMenuButton();
				btn20.setType("click");
				btn20.setName("精彩互动");
				btn20.setKey("noactivity");
				
				WxMenuButton men2=new WxMenuButton();
				men2.setName("精彩");
				men2.getSubButtons().add(btn21);
				men2.getSubButtons().add(btn22);
							
				WxMenuButton btn31=new WxMenuButton();
				btn31.setType("click");
				btn31.setName("模拟选号");
				btn31.setKey("picktools");
				
				WxMenuButton btn32=new WxMenuButton();
				btn32.setType("click");
				btn32.setName("会员中心");
				btn32.setKey("member");
				
				WxMenuButton btn33=new WxMenuButton();
				btn33.setType("view");
				btn33.setName("购彩指南");
				btn33.setUrl(url+"/lotteryGuide.html");
				
				WxMenuButton btn34=new WxMenuButton();
				btn34.setType("view");
				btn34.setName("附近网点");
				btn34.setUrl(url+"/TEST_SEV/redirect/ssl/map");
				
				WxMenuButton men3=new WxMenuButton();
				men3.setName("彩蛋");
				men3.getSubButtons().add(btn31);
				men3.getSubButtons().add(btn32);
				men3.getSubButtons().add(btn33);
		//		men3.getSubButtons().add(btn34);

				List<WxMenuButton> buttons=new ArrayList<WxMenuButton>();
				buttons.add(men1);
				buttons.add(btn20);
				buttons.add(men3);


				wxMenu.setButtons(buttons);

				
				 WxMpMenuService menuService = new WxMpMenuServiceImpl(wxMpService);
				 menuService.menuCreate(wxMenu);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
	}
	public void testcai(){ 
	    String url = "http://172.20.52.109:9999";
	    
		String appId=ahtcWxConfig.getAppid();
		String appSecret=ahtcWxConfig.getAppsecret();
		String token=ahtcWxConfig.getToken();
	    
	    WxMpService wxMpService=wechat(appId, appSecret, token);
		
		// 设置菜单
		try {
			WxMenu wxMenu = new WxMenu();

			WxMenuButton btn11=new WxMenuButton();
			btn11.setType("view");
			btn11.setName("新闻资讯");
			btn11.setUrl(url+"/news.html");
			
			WxMenuButton btn12=new WxMenuButton();
			btn12.setType("view");
			btn12.setName("最新开奖");
			btn12.setUrl(url+"/latestLottery.html#/index");
			
			WxMenuButton btn13=new WxMenuButton();
			btn13.setType("view");
			btn13.setName("公益活动");
			btn13.setUrl(url + "/pwNews.html");
			
			WxMenuButton btn14=new WxMenuButton();
			btn14.setType("view");
			btn14.setName("顶呱刮");
			btn14.setUrl(url + "/dgg.html");
			
			WxMenuButton men1=new WxMenuButton();
			men1.setName("体彩驿站");
			men1.getSubButtons().add(btn11);
			men1.getSubButtons().add(btn12);
			men1.getSubButtons().add(btn13);
			men1.getSubButtons().add(btn14);
			
			WxMenuButton btn21 = new WxMenuButton();
			btn21.setType("click");
			btn21.setName("春联征集活动");
			btn21.setKey("activity30");
			
			WxMenuButton btn22 = new WxMenuButton();
			btn22.setType("click");
			btn22.setName("晒票有奖");
			btn22.setKey("activity31");
			
			WxMenuButton btn20 = new WxMenuButton();
			btn20.setType("click");
			btn20.setName("精彩互动");
			btn20.setKey("noactivity");
			
			WxMenuButton men2=new WxMenuButton();
			men2.setName("精彩");
			men2.getSubButtons().add(btn21);
			men2.getSubButtons().add(btn22);
						
			WxMenuButton btn31=new WxMenuButton();
			btn31.setType("click");
			btn31.setName("模拟选号");
			btn31.setKey("picktools");
			
			WxMenuButton btn32=new WxMenuButton();
			btn32.setType("click");
			btn32.setName("会员中心");
			btn32.setKey("member");
			
			WxMenuButton btn33=new WxMenuButton();
			btn33.setType("view");
			btn33.setName("购彩指南");
			btn33.setUrl(url+"/lotteryGuide.html");
			
			WxMenuButton btn34=new WxMenuButton();
			btn34.setType("view");
			btn34.setName("附近网点");
			btn34.setUrl(url+"/TEST_SEV/redirect/ssl/map");
			
			WxMenuButton men3=new WxMenuButton();
			men3.setName("彩蛋");
			men3.getSubButtons().add(btn31);
			men3.getSubButtons().add(btn32);
			men3.getSubButtons().add(btn33);
	//		men3.getSubButtons().add(btn34);

			List<WxMenuButton> buttons=new ArrayList<WxMenuButton>();
			buttons.add(men1);
			buttons.add(btn20);
			buttons.add(men3);


			wxMenu.setButtons(buttons);

			
			 WxMpMenuService menuService = new WxMpMenuServiceImpl(wxMpService);
			 menuService.menuCreate(wxMenu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void jwlcai(){ 
	    String url = "http://jwl.24cai.com";
	    
		String appId=jwlWxConfig.getAppid();
		String appSecret=jwlWxConfig.getAppsecret();
		String token=jwlWxConfig.getToken();
	    
	    WxMpService wxMpService=wechat(appId, appSecret, token);
		
		// 设置菜单
		try {
			WxMenu wxMenu = new WxMenu();

			WxMenuButton btn11=new WxMenuButton();
			btn11.setType("view");
			btn11.setName("赛事资讯");
			btn11.setUrl(url+"/news.html");
			
			WxMenuButton btn12=new WxMenuButton();
			btn12.setType("view");
			btn12.setName("赛果开奖");
			btn12.setUrl("http://info.sporttery.cn/wechat/fb_result_list.html");
			
			WxMenuButton btn13=new WxMenuButton();
			btn13.setType("view");
			btn13.setName("受注赛程");
			btn13.setUrl("http://info.sporttery.cn/wechat/fb_match_list.html");
			
			
			WxMenuButton men1=new WxMenuButton();
			men1.setName("赛事赛果");
			men1.getSubButtons().add(btn11);
			men1.getSubButtons().add(btn12);
			men1.getSubButtons().add(btn13);
			
			WxMenuButton btn21 = new WxMenuButton();
			btn21.setType("view");
			btn21.setName("附近网点");
			btn21.setUrl("http://info.sporttery.cn/wechat/wxmap.htm");
			
			WxMenuButton men2=new WxMenuButton();
			men2.setName("附近网点");
			men2.getSubButtons().add(btn21);
						
//			WxMenuButton btn31=new WxMenuButton();
//			btn31.setType("view");
//			btn31.setName("竞彩活动");
//			btn31.setUrl("http://info.sporttery.cn/wechat/wxmap.htm");
			
			WxMenuButton btn32=new WxMenuButton();
			btn32.setType("view");
			btn32.setName("投注指南");
			btn32.setUrl("http://c.eqxiu.com/s/OrwggWcN?eqrcode=1&from=singlemessage");
			
			
			WxMenuButton men3=new WxMenuButton();
			men3.setName("竞彩学院");
//			men3.getSubButtons().add(btn31);
			men3.getSubButtons().add(btn32);

			List<WxMenuButton> buttons=new ArrayList<WxMenuButton>();
			buttons.add(men1);
			buttons.add(btn21);
			buttons.add(men3);


			wxMenu.setButtons(buttons);

			
			 WxMpMenuService menuService = new WxMpMenuServiceImpl(wxMpService);
			 menuService.menuCreate(wxMenu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		public void ahjccai(){ 
		    String url = "http://ahjc.24cai.com";
		    
			String appId=ahjcWxConfig.getAppid();
			String appSecret=ahjcWxConfig.getAppsecret();
			String token=ahjcWxConfig.getToken();
		    
		    WxMpService wxMpService=wechat(appId, appSecret, token);
			
			// 设置菜单
			try {
				WxMenu wxMenu = new WxMenu();

				WxMenuButton btn11=new WxMenuButton();
				btn11.setType("view");
				btn11.setName("赛事资讯");
				btn11.setUrl(url+"/news.html");
				
				WxMenuButton btn12=new WxMenuButton();
				btn12.setType("view");
				btn12.setName("赛果开奖");
				btn12.setUrl("http://info.sporttery.cn/wechat/fb_result_list.html");
				
				WxMenuButton btn13=new WxMenuButton();
				btn13.setType("view");
				btn13.setName("受注赛程");
				btn13.setUrl("http://info.sporttery.cn/wechat/fb_match_list.html");
				
				
				WxMenuButton men1=new WxMenuButton();
				men1.setName("赛事赛果");
				men1.getSubButtons().add(btn11);
				men1.getSubButtons().add(btn12);
				men1.getSubButtons().add(btn13);
				
				WxMenuButton btn21 = new WxMenuButton();
				btn21.setType("view");
				btn21.setName("附近网点");
				btn21.setUrl("http://info.sporttery.cn/wechat/wxmap.htm");
				
				WxMenuButton men2=new WxMenuButton();
				men2.setName("附近网点");
				men2.getSubButtons().add(btn21);
							
//				WxMenuButton btn31=new WxMenuButton();
//				btn31.setType("view");
//				btn31.setName("竞彩活动");
//				btn31.setUrl("http://info.sporttery.cn/wechat/wxmap.htm");
				
				WxMenuButton btn32=new WxMenuButton();
				btn32.setType("view");
				btn32.setName("投注指南");
				btn32.setUrl("http://c.eqxiu.com/s/OrwggWcN?eqrcode=1&from=singlemessage");
				
				
				WxMenuButton men3=new WxMenuButton();
				men3.setName("竞彩学院");
//				men3.getSubButtons().add(btn31);
				men3.getSubButtons().add(btn32);

				List<WxMenuButton> buttons=new ArrayList<WxMenuButton>();
				buttons.add(men1);
				buttons.add(btn21);
				buttons.add(men3);


				wxMenu.setButtons(buttons);

				
				 WxMpMenuService menuService = new WxMpMenuServiceImpl(wxMpService);
				 menuService.menuCreate(wxMenu);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}