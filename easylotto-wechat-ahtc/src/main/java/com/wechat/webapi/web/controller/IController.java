package com.wechat.webapi.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.easylotto.commons.util.AESUtil;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.LotteryResultService;
import com.easylotto.core.util.SessionUtil;
import com.wechat.service.BaseWxService;
import com.wechat.webapi.util.IPAddrUtil;
import com.wechat.webapi.web.config.SpringContext;
import com.wechat.webapi.web.thread.UserAccessThread;

@Controller
public class IController extends BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(IController.class);

	@Autowired
	private JedisUtil jedisTemplate;
	
	@Value("${webserver.url}")
	private String webserverUrl;
	
	@Value("${api.aes.key}")
	private String aesKey;
	
	@Value("${api.aes.state}")
	private boolean aesState;
	
	@RequestMapping(value = "/{key}/i/{value}/{id}")
	public View toUrl(@PathVariable String key, @PathVariable String value, @PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		String url = webserverUrl + "errorPage.html";
		try {
			if(aesState){
				key = new String(AESUtil.Decrypt(Hex.decodeHex(key.toCharArray()), aesKey.getBytes()));
			}
			BaseWxService wxService = (BaseWxService) SpringContext.getBean(key + "WxService");
			if (null == wxService)
				return new RedirectView(webserverUrl + "errorPage.html");
			
			
			SessionUtil.setMemberKey(request, id);
			url = jedisTemplate.get("URL:" + value);
			logger.info("REDIS 命中 ---- >>  URL key = " + value +", value = "+url );
			if(StringUtils.contains(url, "?")){
				url += "&_tmptime="+System.currentTimeMillis();
			}else{
				url += "?_tmptime="+System.currentTimeMillis();
			}
			
			if(StringUtils.contains(url, "null")){
				 return new RedirectView(webserverUrl + "errorPage.html");
			}
			
			if(StringUtils.contains(url, "//i/")){
			 url = StringUtils.replace(url, "//i/", "/i/");
			 EcpUserKey ecpUserKey = super.getEcpUserKey(request, wxService.getType());
			 if(ecpUserKey != null){
				 //进入会员中心时添加一条访问记录
				  boolean isNew =request.getSession().isNew();
				  if(isNew){
				 		EcpAccessLog accessLog = accessLogBuild(ecpUserKey, request);
						new Thread(new UserAccessThread(accessLog)).start();
				  }
			 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RedirectView(url);
	}
	
	
	@RequestMapping(value = "/{key}/i/{value}")
	public View toUrl(@PathVariable String key, @PathVariable String value, HttpServletRequest request, HttpServletResponse response) {
		String url = webserverUrl + "errorPage.html";
		try {
			if(aesState){
				key = new String(AESUtil.Decrypt(Hex.decodeHex(key.toCharArray()), aesKey.getBytes()));
			}
			BaseWxService wxService = (BaseWxService) SpringContext.getBean(key + "WxService");
			if (null == wxService)
				return new RedirectView(webserverUrl + "errorPage.html");
			
			
			url = jedisTemplate.get("URL:" + value);
			
			logger.info("REDIS 命中 ---- >>  URL key = " + value +", value = "+url );
			
			if(StringUtils.contains(url, "?")){
				url += "&_tmptime="+System.currentTimeMillis();
			}else{
				url += "?_tmptime="+System.currentTimeMillis();
			}
			if(StringUtils.contains(url, "null")){
				 return new RedirectView(webserverUrl+"errorPage.html");
			}
			if(StringUtils.contains(url, "//i/")){
				 url = StringUtils.replace(url, "//i/", "/i/");
				 EcpUserKey ecpUserKey = super.getEcpUserKey(request, wxService.getType());
				 if(ecpUserKey != null){
					 //进入会员中心时添加一条访问记录
					  boolean isNew =request.getSession().isNew();
					  if(isNew){
					 		EcpAccessLog accessLog = accessLogBuild(ecpUserKey, request);
							new Thread(new UserAccessThread(accessLog)).start();
					  }
				 }
			}
			logger.info(" ---- to url " + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RedirectView(url);
	}
	
	private EcpAccessLog accessLogBuild(EcpUserKey ecpUserKey,HttpServletRequest request){
		EcpAccessLog accessLog = new EcpAccessLog();
	  	accessLog.setInt_account_id(ecpUserKey.getId());
		String ip = IPAddrUtil.getIpAddress(request);
		String strTime = DateTimeUtil.toString(new Date());
		Date accTime = DateTimeUtil.toDate(strTime);
		accessLog.setInt_model_type(1);  //1表示访问的模块为会员中心
		accessLog.setVc_ip(ip);
		accessLog.setDt_read_time(accTime);
		return accessLog;
	}
	
	
//	e9808f4b6f358391341f3bd091f7068c
//	public static void main(String[] args) {
//		try {
//			byte[] bb = AESUtil.Encrypt("xs".getBytes(), "OPyZ03AgyxoJT1xM".getBytes());
//			String respJson = Hex.encodeHexString(bb);
//			System.out.println(respJson);
//			String value = new String(AESUtil.Decrypt(Hex.decodeHex(respJson.toCharArray()), "OPyZ03AgyxoJT1xM".getBytes()));
//			System.out.println(value);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	@Value("${ssl.https.map.url}")
	private String sslHttpsMapUrl;
	
	@Value("${http.map.url}")
	private String httpMapUrl;
	
	
	@RequestMapping(value = "/redirect/ssl/{key}")
	public View redirectView(@PathVariable String key, HttpServletRequest request, HttpServletResponse response) {
		String url = "";
		String user_agent = request.getHeader("user-agent");
		jedisTemplate.set("USER_AGENT:"+System.currentTimeMillis(), user_agent);
		logger.info("---------------------->>>>          " + request.getHeader("user-agent"));
		if("map".equals(key)){
			if(StringUtils.isBlank(user_agent)){
				url = httpMapUrl;
			}else if(StringUtils.contains(user_agent, "iPhone")){
				url = sslHttpsMapUrl;
			}else if(StringUtils.contains(user_agent, "Macintosh")){
				url = sslHttpsMapUrl;
			}else{
				url = httpMapUrl;
			}
		}
		return new RedirectView(url);
	}
	
	
	@Autowired private LotteryResultService lotteryResultService;
    
    @Value("${history.data.state}") 
    private boolean state;
	
//    @RequestMapping(value = "/i/history/data")
//    public void results(HttpServletResponse response){
//    	if(!state) return ;
//    	try {
////			String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
//    		String[] months = {"10", "11", "12"};
//			String[] strs = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
//			for(String month : months){
//				for(String str : strs){
//					lotteryResultService.execute("2016" + month + str);
//					Thread.sleep(1000);
//				}
//			}
//			response.getWriter().println("success");
//		} catch (Exception e) {
//			logger.error("", e);
//			EmailUtil.send("wechatSystemError", "体彩安徽微信系统抓取开奖结果异常，请及时跟进微信服务是否正常！ No.13", new String[]{"chenrixiang@12580tc.com"});
//		}
//    }
}
