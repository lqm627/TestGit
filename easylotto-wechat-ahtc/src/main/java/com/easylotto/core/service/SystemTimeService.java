package com.easylotto.core.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easylotto.core.dao.BaseDao;

/**
 * 获取当前时间
 * @author wucx
 *
 */
@Service
public class SystemTimeService {
	
	@Autowired
	private BaseDao baseDao;
	
	public Date excute(){
		return baseDao.getDatebaseNow();
	}
}
