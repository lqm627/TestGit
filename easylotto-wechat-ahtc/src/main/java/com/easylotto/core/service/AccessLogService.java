package com.easylotto.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.core.dao.AccessLogDao;
import com.easylotto.core.entity.EcpAccessLog;

@Service
@Transactional
public class AccessLogService {
	
	@Autowired
	private AccessLogDao accessLogDao;
	
	public void addAccessLog(EcpAccessLog ecpAccessLog){
		accessLogDao.addAccessLog(ecpAccessLog);
	}
}
