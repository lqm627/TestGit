package com.easylotto.core.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.easylotto.core.dao.IntegralShopDao;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.SubscribeLog;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
@Transactional
public class SubscribeLotteryService  implements IClientService{
	@Autowired
	private IntegralShopDao integralShopDao;
	
	private Lock lock = new ReentrantLock();
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean=new ResponseBean(); 
		  Map<String, Object> map = new HashMap<String, Object>();
			try {
				String[] str=parameterMap.get("serkey").split("_");
				String lotteryType=str[1];
				SubscribeLog subscribeLog=new SubscribeLog();
				lock.lock();
				int subscribeState=integralShopDao.getSubscribeState(memberId,Integer.valueOf(lotteryType));
				int beanBalance=integralShopDao.getBeanBalance(memberId);
				if(subscribeState>0){
					if(lotteryType.equals("23")){
						map.put("subscribeState", "大乐透开奖订阅");
					}else if(lotteryType.equals("4")){
						map.put("subscribeState", "七星彩开奖订阅");
					}
					map.put("state", 0);
					responseBean.setData(map);
					responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
					return responseBean;
				}else if(beanBalance>=10){
					subscribeLog.setInt_lottery_type(Integer.valueOf(lotteryType));
					subscribeLog.setInt_account_id(memberId);
					save(subscribeLog,beanBalance);
				
					responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
					map.put("state", 1);
					map.put("subscribeState", "订阅成功！");
					responseBean.setData(map);
					return responseBean;
				}else if(beanBalance<10){
					map.put("state", 2);
					map.put("subscribeState", "余额不足，无法订阅！");
					responseBean.setData(map);
					responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
					return responseBean;
				}


			} catch (Exception e) {
				map.put("state", 3);
				map.put("subscribeState", "订阅失败！");
				responseBean.setData(map);
				responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
	      
		return responseBean;
	}
	
	public void save(SubscribeLog subscribeLog,int beanBalance){
		integralShopDao.save(subscribeLog);
				
		integralShopDao.updateUser(beanBalance-10,subscribeLog.getInt_account_id());
		
		EcpUserFundLog ecpUserFundLog=new EcpUserFundLog();
		ecpUserFundLog.setInt_account_id(subscribeLog.getInt_account_id());
		ecpUserFundLog.setInt_oper_type(9);
		Date currentDate = new Date(System.currentTimeMillis());
		ecpUserFundLog.setDt_oper_time(currentDate);
		ecpUserFundLog.setDec_amount(Double.valueOf(-10));
		ecpUserFundLog.setVc_bill_no("0");
		ecpUserFundLog.setDec_balance(Double.valueOf(beanBalance-10));
		ecpUserFundLog.setInt_account_type(4);
		integralShopDao.insertUserFundLog(ecpUserFundLog);
	}

}
	
	