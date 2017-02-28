package com.wechat.webapi.web.thread;

import com.wechat.webapi.web.config.SpringContext;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.service.AccessLogService;

public class UserAccessThread implements Runnable {
	
	EcpAccessLog ecpAccessLog;
	
	
	
	public UserAccessThread(EcpAccessLog ecpAccessLog) {
		super();
		this.ecpAccessLog = ecpAccessLog;
	}


	@Override
	public void run() {
		if(ecpAccessLog!=null){
			addAccessLog(ecpAccessLog);
		}
	}


	private void addAccessLog(EcpAccessLog accessLog) {
		
		AccessLogService accessLogService = SpringContext.getBean(AccessLogService.class);
		
		accessLogService.addAccessLog(accessLog);
	}

}
