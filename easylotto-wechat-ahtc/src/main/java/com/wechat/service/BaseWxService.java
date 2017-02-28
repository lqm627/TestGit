package com.wechat.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.wechat.config.WxConfig;
import com.wechat.handler.AbstractHandler;
import com.wechat.handler.KfSessionHandler;
import com.wechat.handler.LogHandler;
import com.wechat.handler.MenuHandler;
import com.wechat.handler.MsgHandler;
import com.wechat.handler.NullHandler;
import com.wechat.handler.StoreCheckNotifyHandler;
import com.wechat.handler.SubscribeHandler;
import com.wechat.handler.UnsubscribeHandler;
import com.wechat.handler.WechatHandler;
import com.wechat.webapi.web.common.MenuType;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class BaseWxService extends WxMpServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected LogHandler logHandler;

  @Autowired
  protected NullHandler nullHandler;

  @Autowired
  protected KfSessionHandler kfSessionHandler;

  @Autowired
  protected StoreCheckNotifyHandler storeCheckNotifyHandler;

  private WxMpMessageRouter router;

  protected abstract WxConfig getServerConfig();

  protected abstract MenuHandler getMenuHandler();

  protected abstract SubscribeHandler getSubscribeHandler();

  protected abstract UnsubscribeHandler getUnsubscribeHandler();

  protected abstract AbstractHandler getLocationHandler();

  protected abstract MsgHandler getMsgHandler();
  
  protected abstract AbstractHandler getScanHandler();
  
  protected abstract String[] getUrls();

  @PostConstruct
  public void init() {
	logger.info(JSON.toJSONString(getServerConfig()));
    final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
    config.setAppId(this.getServerConfig().getAppid());// 设置微信公众号的appid
    config.setSecret(this.getServerConfig().getAppsecret());// 设置微信公众号的app corpSecret
    config.setToken(this.getServerConfig().getToken());// 设置微信公众号的token
    config.setAesKey(this.getServerConfig().getAesKey());// 设置消息加解密密钥
    super.setWxMpConfigStorage(config);
    this.refreshRouter();
  }

  private void refreshRouter() {

    final WxMpMessageRouter newRouter = new WxMpMessageRouter(this);

    // 记录所有事件的日志
    newRouter.rule().handler(this.logHandler).next();

    // 接收客服会话管理事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_KF_CREATE_SESSION)
        .handler(this.kfSessionHandler).end();
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_KF_CLOSE_SESSION)
        .handler(this.kfSessionHandler).end();
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_KF_SWITCH_SESSION)
        .handler(this.kfSessionHandler).end();
    
    // 门店审核事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
      .event(WxConsts.EVT_POI_CHECK_NOTIFY)
      .handler(this.storeCheckNotifyHandler)
      .end();

    // 自定义菜单事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.BUTTON_CLICK).handler(new WechatHandler(getUrls(), getServerConfig())).end();
//
    // 点击菜单连接事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.BUTTON_VIEW).handler(this.nullHandler).end();

    // 上报地理位置事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_LOCATION).handler(this.getLocationHandler()).end();

    // 接收地理位置消息
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_LOCATION)
        .handler(this.getLocationHandler()).end();

    // 扫码事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_SCAN).handler(this.getScanHandler()).end();
    
    // 关注事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_SUBSCRIBE).handler(new WechatHandler(getUrls(), MenuType.SUB.getKey(), getServerConfig()))
        .end();

    // 取消关注事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_UNSUBSCRIBE).handler(new WechatHandler("unsubscribe", getServerConfig()))
        .end();
    
  

    this.router = newRouter;
  }


  public WxMpXmlOutMessage route(WxMpXmlMessage message) {
    try {
      return this.router.route(message);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  public boolean hasKefuOnline() {
    try {
      WxMpKfOnlineList kfOnlineList = this.getKefuService().kfOnlineList();
      return kfOnlineList != null && kfOnlineList.getKfOnlineList().size() > 0;
    } catch (Exception e) {
      this.logger.error("获取客服在线状态异常: " + e.getMessage(), e);
    }

    return false;
  }
  
  public String getType(){
	  return this.getServerConfig().getType();
  }

}
