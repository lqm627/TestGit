package com.easylotto.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.core.dao.PushSubscribeMsgDao;
import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.PushSubscribeMsgLog;
import com.easylotto.core.entity.SubscribeLog;


@Service
@Transactional
public class PushSubscribeMsgService {
	
	@Autowired
	private PushSubscribeMsgDao pushSubscribeMsgDao; 
	
	public List<SubscribeLog> getSubscriber(int lotteryType,int type){
		return pushSubscribeMsgDao.getSubscriber(lotteryType,type);
	}
	
	public String getSubscriberState(Long membetId,int type){
	   return pushSubscribeMsgDao.getSubscriberState(membetId,type);
	}
	public EcpLotteryOpenResult getLastOpenResultByPlaytype(int lotteryType){
		return pushSubscribeMsgDao.getLastOpenResultByPlaytype(lotteryType);
	}
	public void insertPushSubscribeMsgLog(PushSubscribeMsgLog pushSubscribeMsgLog){
		pushSubscribeMsgDao.insertPushSubscribeMsgLog(pushSubscribeMsgLog);
	}
}
