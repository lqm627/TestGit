package com.easylotto.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.core.dao.MenuClickLogDao;
import com.easylotto.core.entity.EcpMenuClickLog;


@Service
@Transactional
public class MenuClickLogService {
	
	@Autowired
	private MenuClickLogDao menuClickLogDao;
	
	
	public void addClickLog(EcpMenuClickLog instance){
		menuClickLogDao.addClickLog(instance);
	}
}
