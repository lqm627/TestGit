package com.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wechat.service.AhtcWxService;
import com.wechat.service.BaseWxService;

/**
 *
 */
@RestController
@RequestMapping("/ahtc/service")
public class AhtcWechatController extends AbstractWxPortalController{
	
  @Autowired
  private AhtcWxService wxService;
  
  @Override
  protected BaseWxService getWxService() {
    return this.wxService;
  }

}
