package com.wechat.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wechat.service.BaseWxService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 * @author Binary Wang
 */
public abstract class AbstractWxPortalController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected abstract BaseWxService getWxService();

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String authGet(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
		this.logger.info("\n ----------------------- RequestMethod.GET >>  接收到来自微信服务器的认证消息：[{},{},{},{}]", signature, timestamp, nonce, echostr);

		if (this.getWxService().checkSignature(timestamp, nonce, signature)) {
			return echostr;
		}

		return "非法请求";
	}
	
	
//	public static void main(String[] args) {
//		final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
//	    config.setAppId("wxe50759beade9edb0");// 设置微信公众号的appid
//	    config.setSecret("09d1683fc654faed415e89c44a178533");// 设置微信公众号的app corpSecret
//	    config.setToken("yicaile");// 设置微信公众号的token
//	    config.setAesKey("rLnExfjiANpYPQ1E82cdWsPZ2j3vtFGsEpKQk8u3Tzk");// 设置消息加解密密钥
//	    WxMpService service = new WxMpServiceImpl();
//	    service.setWxMpConfigStorage(config);
//	    
//	    System.out.println(JSON.toJSON(service));
//	}
	

	@RequestMapping(method = RequestMethod.POST, produces = "application/xml; charset=UTF-8")
	public void post(HttpServletRequest request, HttpServletResponse response) {

		try {
			long time = System.currentTimeMillis();

			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);

			String signature = request.getParameter("signature");
			String nonce = request.getParameter("nonce");
			String timestamp = request.getParameter("timestamp");
			String echostr = request.getParameter("echostr");
			
			this.logger.info("\n ----------------------- RequestMethod.POST          >>  接收到来自微信服务器的认证消息：[{},{},{},{}]", signature, timestamp, nonce, echostr);

			if (!getWxService().checkSignature(timestamp, nonce, signature)) {
				// 消息签名不正确，说明不是公众平台发过来的消息
				this.logger.info(" ------------- .... 非法请求");
				response.getWriter().println("非法请求");
				return;
			}
			
			if (StringUtils.isNotBlank(echostr)) {
				// 说明是一个仅仅用来验证的请求，回显echostr
				response.getWriter().println(echostr);
				return;
			}

			this.logger.info(" ----------->>> 3 run time " + (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
			message(request, response, nonce, timestamp);
			this.logger.info(" ----------->>> 4 run time " + (System.currentTimeMillis() - time));
		} catch (Exception e) {
			this.logger.error("", e);
		}
	}

	private void message(HttpServletRequest request, HttpServletResponse response, String nonce, String timestamp)
			throws Exception {
		String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw" : request.getParameter("encrypt_type");

		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
			String msgType = inMessage.getMsgType();
			if (msgType.equals(WxConsts.XML_MSG_TEXT)) {
				response.getWriter().write(autoResponse(inMessage, "", ""));
				return;
			} else {
				WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
				if (outMessage != null) {
					response.getWriter().write(outMessage.toXml());
					return;
				}
			}
		} else if ("aes".equals(encryptType)) {
			WxMpConfigStorage wxMpConfigStorage = getWxService().getWxMpConfigStorage();
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
			WxMpXmlOutMessage outMessage = getWxService().route(inMessage);
			response.getWriter().write(outMessage.toEncryptedXml(wxMpConfigStorage));
			return;
		}

		response.getWriter().println("不可识别的加密类型");
		return;
	}

	/**
	 * 自动回复
	 * 
	 * @param inMessage
	 * @param webserverURL
	 * @param serverUrl
	 * @return
	 */
	public String autoResponse(WxMpXmlMessage inMessage, String webserverURL, String serverUrl) {
		String respXml = null;
		try {
			WxMpXmlOutTextMessage test = new WxMpXmlOutTextMessage();
			test.setToUserName(inMessage.getFromUser());
			test.setFromUserName(inMessage.getToUser());
			test.setCreateTime(new Date().getTime());
			test.setMsgType(WxConsts.XML_MSG_TEXT);
			test.setContent("感谢您的关注!");

			if (inMessage.getMsgType().equals(WxConsts.XML_MSG_TEXT)) {
				 String tocontent=inMessage.getContent();
				 String keyword="买彩票、买、投注";
				 String baoshi="红宝石、宝石、刮刮乐";
				
				 boolean flag=keyword.contains(tocontent);
				 boolean flagbaoshi=baoshi.contains(tocontent);
				 if(flag){
				 test.setContent("你好，目前没有开通网上购彩。如需购买彩票，请选择就近的福彩投注站。");
				 }
				
				 else if(flagbaoshi){
//				 PushActivityMsg pushActivityMsg=new PushActivityMsg();
//				 pushActivityMsg.setWebserverURL(webserverURL);
//				 pushActivityMsg.setOpenId(inMessage.getFromUserName());
//				 pushActivityMsg.setServerUrl(serverUrl);
//				 new Thread(new
//				 PushActivityBaoshiThread(pushActivityMsg)).start();
				 }else{
				 test.setContent("感谢您的关注、福彩与公益同在，爱心与幸运相连！");
				 }
			}
			respXml = test.toXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
