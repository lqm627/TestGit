package com.wechat.webapi.web.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easylotto.core.service.ActivityDataService;

@RestController
public class WechatActivityControllerler extends BaseController {

	@Autowired private ActivityDataService activityDataService;
	
	@RequestMapping(value = "/wechat/activity/data/{type}")
	public void getDataTest(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		try {
			logger.info("----------------------------------->>>    {} 活动数据导出开始", type);
			activityDataService.export(request, response, type);
			logger.info("----------------------------------->>>    {} 活动数据导出结束", type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value = "/wechat/activity/data/test/{type}")
	public void data(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		try {
			logger.info("----------------------------------->>>    {} 活动数据开始", type);
			activityDataService.export(type, response);
			logger.info("----------------------------------->>>    {} 活动数据结束", type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
