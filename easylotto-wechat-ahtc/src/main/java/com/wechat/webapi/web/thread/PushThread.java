/**
 * 
 */
package com.wechat.webapi.web.thread;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.easylotto.core.entity.PushMsg;
import com.wechat.builder.TextBuilder;
import com.wechat.service.BaseWxService;
import com.wechat.webapi.web.config.SpringContext;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

/**
 * @author CreateName:  UpdateName: 
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see PushThread
 * @see CreateDate: 2016年6月15日 上午11:50:05 UpdateDate: 2016年6月15日 上午11:50:05
 * @see Copyright 
 * @since JDK1.7.*
 * @version 1.0
 */
public class PushThread  implements Runnable {

	
	private final Logger logger = LogManager.getLogger(PushThread.class);
	
	private PushMsg pushMsg;
	
	private String key;

	public PushThread(PushMsg pushMsg,String key) {
		this.pushMsg = pushMsg;
		this.key=key;
	}

	
	public void run() {
		
		if(null != pushMsg){
			try {
				pushMsgToUser(pushMsg.getSignDays(), pushMsg.getBeanCount(), pushMsg.getOpenId(),pushMsg.getBalance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * 签到完成  发送一条客服消息给用户 
	 * @throws WxErrorException 
	 */
	private void pushMsgToUser(int signDays, double beanCount, String openId, double balance){
		try {
			BaseWxService wxService = (BaseWxService) SpringContext.getBean(key + "WxService");
			int beans = (int) beanCount;
			int beanBalanece = (int) balance;
			String content = "恭喜您签到成功!您已连续签到"+signDays+"天，本次签到奖励彩蛋"+beans+"个!\n\n您一共有"+beanBalanece+"个彩蛋, 彩蛋的好处多多可用于开心大转盘、订购服务等众多服务！";
			wxService.getKefuService().sendKefuMessage(WxMpKefuMessage
					  .TEXT()
					  .toUser(openId)
					  .content(content)
					  .build());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
	}
}
