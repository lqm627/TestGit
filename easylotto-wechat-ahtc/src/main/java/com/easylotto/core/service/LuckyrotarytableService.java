package com.easylotto.core.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.easylotto.core.entity.EcpRotarytableLog;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.Prize;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.util.LotteryUtil;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
@Transactional
public class LuckyrotarytableService  implements IClientService{
	@Autowired
	private IntegralShopDao integralShopDao;	
	
	private Lock lock = new ReentrantLock();
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			lock.lock();
			int beanBalance=integralShopDao.getBeanBalance(memberId);
			int rotarytableLogNum=integralShopDao.getRotarytableLog(memberId);
			if(rotarytableLogNum>=3){
				responseBean.setData(new ResponseBean("rotarytableLogNumState", "次数已用完，请明天再来！"));
				responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
				return responseBean;
			}else{
				 if(beanBalance<2){
					 responseBean.setData(new ResponseBean("beanBalanceState", "余额不足!"));
					 responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
						return responseBean;
					}else{
						  List<Prize> gifts = new ArrayList<Prize>();
					        //物品名称==概率		        
					        gifts.add(new Prize("1", 20d));
					        gifts.add(new Prize("2", 15d));
					        gifts.add(new Prize("3", 15d));
					        gifts.add(new Prize("4", 15d));
					        gifts.add(new Prize("5", 12.9d));
					        gifts.add(new Prize("50", 10d));
					        gifts.add(new Prize("100", 2d));
					        gifts.add(new Prize("1000", 0.1d));
					        gifts.add(new Prize("0", 10d));

					        List<Double> orignalRates = new ArrayList<Double>(gifts.size());
					        for (Prize gift : gifts) {
					            double probability = gift.getProbability();
					            if (probability < 0) {
					                probability = 0;
					            }
					            orignalRates.add(probability);
					        }
					        int orignalIndex1 = LotteryUtil.lottery(orignalRates);
//					        System.out.println(orignalIndex1);
//					        System.out.println(gifts.get(orignalIndex1));
//					        System.out.println(gifts.get(orignalIndex1).getPrizeName());
					        
					        EcpRotarytableLog rotarytableLog=new EcpRotarytableLog();
					        rotarytableLog.setInt_account_id(memberId);
					        int prizeName=Integer.valueOf(gifts.get(orignalIndex1).getPrizeName());
					        if(prizeName==0){
					        	map.put("prizeName", "未中奖！");
					        }
					        rotarytableLog.setInt_amount(prizeName);
					        save(rotarytableLog, beanBalance);
					        map.put("bean", gifts.get(orignalIndex1).getPrizeName());
					        map.put("number", 2-rotarytableLogNum);
					        responseBean.setData(map);
					        responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
					        return responseBean;
					}
			}



		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
		return responseBean;
	}
	
	public void save(EcpRotarytableLog rotarytableLog,int beanBalance){
		EcpUserFundLog deductEcpUserFundLog=new EcpUserFundLog();
		deductEcpUserFundLog.setInt_account_id(rotarytableLog.getInt_account_id());
		deductEcpUserFundLog.setInt_oper_type(10);
		Calendar calendar = Calendar.getInstance();
		deductEcpUserFundLog.setDt_oper_time(calendar.getTime());
		deductEcpUserFundLog.setDec_amount(Double.valueOf(-2));
		deductEcpUserFundLog.setVc_bill_no("0");
		deductEcpUserFundLog.setDec_balance(Double.valueOf(beanBalance-2));
		deductEcpUserFundLog.setInt_account_type(4);
		integralShopDao.insertUserFundLog(deductEcpUserFundLog);
		
		integralShopDao.updateUser(beanBalance-2,rotarytableLog.getInt_account_id());
		
		int newbeanBalance=integralShopDao.getBeanBalance(rotarytableLog.getInt_account_id());
		
		EcpUserFundLog winEcpUserFundLog=new EcpUserFundLog();
		winEcpUserFundLog.setInt_account_id(rotarytableLog.getInt_account_id());
		winEcpUserFundLog.setInt_oper_type(10);
	    Calendar calendar1 = Calendar.getInstance();
	    calendar1.add (Calendar.SECOND, 1);
		winEcpUserFundLog.setDt_oper_time(calendar1.getTime());
		winEcpUserFundLog.setDec_amount(Double.valueOf(rotarytableLog.getInt_amount()));
		winEcpUserFundLog.setVc_bill_no("0");
		winEcpUserFundLog.setDec_balance(Double.valueOf(newbeanBalance+rotarytableLog.getInt_amount()));
		winEcpUserFundLog.setInt_account_type(4);
		integralShopDao.insertUserFundLog(winEcpUserFundLog);
		
		integralShopDao.updateUser(newbeanBalance+rotarytableLog.getInt_amount(),rotarytableLog.getInt_account_id());
		
		integralShopDao.insertRotarytable(rotarytableLog);
		
		
		
	}
	


}
	
	