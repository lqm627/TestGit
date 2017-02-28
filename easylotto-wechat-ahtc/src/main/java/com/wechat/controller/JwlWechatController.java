package com.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wechat.service.BaseWxService;
import com.wechat.service.JwlWxService;

/**
 *
 */
@RestController
@RequestMapping("/jwl/service")
public class JwlWechatController extends AbstractWxPortalController{
	
  @Autowired
  private JwlWxService wxService;
  
  @Override
  protected BaseWxService getWxService() {
    return this.wxService;
  }

}
