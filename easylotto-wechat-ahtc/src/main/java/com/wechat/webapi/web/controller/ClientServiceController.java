package com.wechat.webapi.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.constants.MediaTypes;

import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.ClientServiceFactory;
import com.easylotto.core.service.IClientService;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.service.BaseWxService;
import com.wechat.webapi.util.IPAddrUtil;
import com.wechat.webapi.web.config.SpringContext;
import com.wechat.webapi.web.model.ResponseBean;

import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

@RestController
public class ClientServiceController extends BaseController {
	@Autowired private ClientServiceFactory clientServiceFactory;
	@Autowired private EcpUserInfoService ecpUserInfoService;
	
	@Value("${news.pagesize}")
	private String pageSize;
	
	@Value("${lottery.pagesize}")
	private String lotteryPageSize;
	
	@Value("${nearbet.pagesize}")
	private String nearbetPageSize;
	
	@Value("${api.aes.key}")
	private String aesKey;
	
	@Value("${api.aes.state}")
	private boolean aesState;
	
	
    private Map<String, String> map=new HashMap<String, String>();
	
	
	@RequestMapping(value = "/{key}/{serkey}", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseBean excute(@RequestParam String request_data,HttpServletRequest request, HttpServletResponse response,@PathVariable String serkey,@PathVariable String key) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		try {
			BaseWxService wxService = (BaseWxService) SpringContext.getBean(key + "WxService");
			if (null == wxService)
				return super.handleResponse(responseBean, response);
			
			String ip = IPAddrUtil.getIpAddress(request);
			map.put("pageSize", pageSize);
			map.put("nearbetPageSize", nearbetPageSize);
			map.put("lotteryPageSize", lotteryPageSize);
			map.put("serkey", serkey);
			map.put("key", key);
			map.put("ip", ip);
			map.put("type", wxService.getType());
			
			EcpUserKey ecpUserKey = super.getEcpUserKey(request, wxService.getType());
			Long memberId=null;
	          if(ecpUserKey!=null){
	              String ua = request.getHeader("User-Agent");
	              UserAgent userAgent = UserAgent.parseUserAgentString(ua);
	              OperatingSystem os = userAgent.getOperatingSystem();
	              ecpUserInfoService.updateUserOS(ecpUserKey.getId(),os.getName());
	              memberId=ecpUserKey.getId();
	          }
			IClientService iClientService=clientServiceFactory.getService(serkey);
			responseBean=iClientService.execute(request_data, memberId,map);
		} catch (Exception e) {
			logger.error("", e);
		}
		if(responseBean==null){
			return null;
		}
		return super.handleResponse(responseBean, response);
	}
	
//	@RequestMapping(value = "/{key}/{serkey}/test", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
//	public ResponseBean excutetest(HttpServletRequest request, HttpServletResponse response,@PathVariable String serkey,@PathVariable String key) throws Exception {
//		ResponseBean responseBean = new ResponseBean();
//		String request_data="{}";
//		try {
//			if(aesState){
//				key = new String(AESUtil.Decrypt(Hex.decodeHex(key.toCharArray()), aesKey.getBytes()));
//			}
//			WechatAccountService accountService = (WechatAccountService) SpringContext.getBean(key + "AccountService");
//			if (null == accountService)
//				return super.handleResponse(responseBean, response);
//			
//			String ip = IPAddrUtil.getIpAddress(request);
//			map.put("pageSize", pageSize);
//			map.put("nearbetPageSize", nearbetPageSize);
//			map.put("lotteryPageSize", lotteryPageSize);
//			map.put("serkey", serkey);
//			map.put("key", key);
//			map.put("ip", ip);
//			map.put("type", accountService.getType());
//			
//			EcpUserKey ecpUserKey = super.getEcpUserKey(request, accountService);
//			Long memberId=null;
//	          if(ecpUserKey!=null){
//	              String ua = request.getHeader("User-Agent");
//	              UserAgent userAgent = UserAgent.parseUserAgentString(ua);
//	              OperatingSystem os = userAgent.getOperatingSystem();
//	              ecpUserInfoService.updateUserOS(ecpUserKey.getId(),os.getName());
//	              memberId=ecpUserKey.getId();
//	          }
//			IClientService iClientService=clientServiceFactory.getService(serkey);
//			responseBean=iClientService.excute(request_data,memberId, map);
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		if(responseBean==null){
//			return null;
//		}
//		return super.handleResponse(responseBean, response);
//	}
}
