package com.easylotto.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easylotto.commons.util.JedisUtil;
import com.wechat.webapi.web.controller.ContentServiceController;

@Service
public class BaseService {

	protected static final Logger logger = LoggerFactory.getLogger(ContentServiceController.class);
	
	@Autowired
	private JedisUtil jedisTemplate;
	
	
	public JedisUtil getJedisTemplate(){
		return this.jedisTemplate;
	}
}
