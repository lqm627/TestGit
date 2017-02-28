/**
 * 
 */
package com.easylotto.core.task;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.commons.util.HttpUtil;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.service.LotteryResultService;
import com.wechat.config.EmailConfig;
//import com.easylotto.log.model.EcpServiceLog;
//import com.easylotto.log.service.WechatServiceLogService;
import com.wechat.webapi.util.EmailUtil;

/**
 * @author CreateName:  UpdateName: 
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see UserKey
 * @see CreateDate: 2016年6月29日 下午8:05:56 UpdateDate: 2016年6月29日 下午8:05:56
 * @see Copyright 
 * @since JDK1.7.*
 * @version 1.0
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{
	
	private final Logger logger = LogManager.getLogger(ScheduledTasks.class);
	

    @Autowired private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    @Autowired private LotteryResultService lotteryResultService;
    
    @Autowired private EmailConfig config;
    
    @Scheduled(fixedRate = 1000 * 10)
    public void activeCount(){
    	int activeCount = threadPoolTaskExecutor.getActiveCount();
    	if(logger.isInfoEnabled()){
    		logger.info(" ---------->>  ThreadPoolTaskExecutor.getActiveCount() = ("+activeCount+") ---> "+ DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS));
    	}
    	
    	if(activeCount > 100){
    		EmailUtil.sendToGroup("wechatSystemThreadActiveCount", "体彩安徽微信系统线程异常，请及时跟进微信服务是否正常！ No.10", config);
    	}
    }
    
    
    @Scheduled(cron="0 0/5 20-23 * * ?")
    public void lotteryResult(){
    	if(logger.isInfoEnabled()){
    		logger.info(" ---------->>  lotteryResult  ---> "+ DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS));
    	}
    	
    	String hh = DateTimeUtil.toString("HH");
    	if(StringUtils.isNotBlank(hh)){
    		Long _hh = Long.parseLong(hh);
    		if(20 < _hh && _hh < 23){
    			try {
					lotteryResultService.execute(DateTimeUtil.toString(DateTimeUtil.YYYYMMDD));
				} catch (Exception e) {
					logger.error("", e);
					EmailUtil.sendToGroup("wechatSystemError", "体彩安徽微信系统抓取开奖结果异常，请及时跟进微信服务是否正常！ No.13", config);
				}
    		}
    	}
    	
    }
    
    @Autowired private JedisUtil jedisTemplate;
    
    @Scheduled(cron="0 0 4 * * ?")
    public void flushCache(){
    	jedisTemplate.flushDb();
    	if(logger.isInfoEnabled()){
    		logger.info("\n\t ---------->>  flushDb success! "+ DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS + " \n\t"));
    	}
    }
    
}
