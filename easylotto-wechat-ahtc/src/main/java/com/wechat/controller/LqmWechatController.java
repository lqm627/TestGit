package com.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wechat.service.BaseWxService;
import com.wechat.service.LqmWxService;

/**
 *
 */
@RestController
@RequestMapping("/lqm/service")
public class LqmWechatController extends AbstractWxPortalController{
	
  @Autowired
  private LqmWxService wxService;
  
  @Override
  protected BaseWxService getWxService() {
    return this.wxService;
  }

}
