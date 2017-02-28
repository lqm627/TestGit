package com.wechat.webapi.web.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class LotteryResultHandlerProxy {
	
	@Autowired
	private Map<String, LotteryResultHandler> handlers;
	
	@Autowired private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public void execute(final List<String> values, final Integer lotteryType){
		threadPoolTaskExecutor.execute(new Runnable() {
			public void run() {
				handlers.get("lottery" + lotteryType + "ResultHandler").handler(values);
			}
		}, 1000 * 10);
	}
	
}
