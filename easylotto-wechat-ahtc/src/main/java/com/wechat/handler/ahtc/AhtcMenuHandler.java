package com.wechat.handler.ahtc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wechat.config.AhtcWxConfig;
import com.wechat.config.WxConfig;
import com.wechat.handler.MenuHandler;

@Component
public class AhtcMenuHandler extends MenuHandler {
  @Autowired
  private  AhtcWxConfig wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

}
