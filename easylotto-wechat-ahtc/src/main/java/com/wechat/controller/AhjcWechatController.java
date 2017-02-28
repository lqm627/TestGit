package com.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wechat.service.AhjcWxService;
import com.wechat.service.BaseWxService;

/**
 *
 */
@RestController
@RequestMapping("/ahjc/service")
public class AhjcWechatController extends AbstractWxPortalController{
	
  @Autowired
  private AhjcWxService wxService;
  
  @Override
  protected BaseWxService getWxService() {
    return this.wxService;
  }

}
