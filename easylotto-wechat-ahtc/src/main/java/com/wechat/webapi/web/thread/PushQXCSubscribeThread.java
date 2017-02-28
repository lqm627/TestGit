package com.wechat.webapi.web.thread;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;

import org.apache.commons.lang3.StringUtils;

import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.PushSubscribeMsgLog;
import com.easylotto.core.entity.SubscribeLog;
import com.easylotto.core.service.PushSubscribeMsgService;
import com.wechat.service.BaseWxService;
import com.wechat.webapi.web.config.SpringContext;


public class PushQXCSubscribeThread implements Runnable {

	private String key;
	
	private Lock lock = new ReentrantLock();

	public void run() {
		lock.lock();
		try {
			pushMsgToSubscriber();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public PushQXCSubscribeThread(String key){
		this.key=key;
	}
	
	private void pushMsgToSubscriber(){
		
		try {
			EcpLotteryOpenResult  ecpLotteryOpenResult=null;
			PushSubscribeMsgService puSubscribeMsgService = SpringContext.getBean(PushSubscribeMsgService.class);
			ecpLotteryOpenResult=puSubscribeMsgService.getLastOpenResultByPlaytype(4);
			if(null == ecpLotteryOpenResult) return ;
			BaseWxService wxService = (BaseWxService) SpringContext.getBean(key + "WxService");
			List<SubscribeLog> listssq = puSubscribeMsgService.getSubscriber(4,Integer.valueOf(wxService.getType()));
			
			String ssqfirstCount="";
			String ssqfirstPrize="";
			String ssqSecCount="";
			String ssqSecPrize="";
			
			if(null == listssq || 0 == listssq.size()){
				return ;
			}else{
				String[] test=new String[7];
				String[] test2=new String[4];
				test=ecpLotteryOpenResult.getVc_prize_content().split("@");
				for(int j=0;j<test.length;j++){
					if(j==0){
						test2=test[0].split("\\|");
						ssqfirstCount=test2[1];
						ssqfirstPrize=test2[3];
					}
					else if(j==1){
						test2=test[1].split("\\|");
						ssqSecCount=test2[1];
						ssqSecPrize=test2[3];
					}
				}
			}
		
			String contentssq = "开奖公告:第{0}期七星彩开奖号码:\n{1}。\n当期开出\n一等奖:{2}注,每注{3}元\n二等奖:{4}注,每注{5}元\n奖池金额:{6}元";
			String ssq_content = "";
			if(null != listssq && 0 != listssq.size()){
				for(SubscribeLog msg : listssq){
					String interval=puSubscribeMsgService.getSubscriberState(msg.getInt_account_id(),Integer.valueOf(wxService.getType()));
					String[] intervalstr=null;
					if(interval!=null&&interval!=""&&interval.length()!=0){
						intervalstr=interval.split(":");
					}
					if(interval==null||interval==""||interval.length()==0){
						continue;
						//微信目前更改为48小时内
					}else if(Integer.valueOf(intervalstr[0])==47&&Integer.valueOf(intervalstr[1])>=50){
						continue;
					}else if(Integer.valueOf(intervalstr[0])>=48){
						continue;
					}else{
						ssq_content = StringUtils.replace(contentssq, "{0}",  ecpLotteryOpenResult.getVc_term() );
						ssq_content = StringUtils.replace(ssq_content, "{1}", ecpLotteryOpenResult.getVc_code_content());
						ssq_content = StringUtils.replace(ssq_content, "{2}", ssqfirstCount);
						ssq_content = StringUtils.replace(ssq_content, "{3}", ssqfirstPrize);
						ssq_content = StringUtils.replace(ssq_content, "{4}", ssqSecCount);
						ssq_content = StringUtils.replace(ssq_content, "{5}", ssqSecPrize);
						ssq_content = StringUtils.replace(ssq_content, "{6}", ecpLotteryOpenResult.getVc_pool_award());
						
						wxService.getKefuService().sendKefuMessage(WxMpKefuMessage.TEXT().toUser(msg.getVc_open_id()).content(ssq_content).build());
						
						PushSubscribeMsgLog pushSubscribeMsgLog=new PushSubscribeMsgLog();
						pushSubscribeMsgLog.setInt_account_id(msg.getInt_account_id());
						pushSubscribeMsgLog.setInt_lottery_type(3);
						pushSubscribeMsgLog.setVc_term(ecpLotteryOpenResult.getVc_term());
						Date currentDate = new Date(System.currentTimeMillis());
						pushSubscribeMsgLog.setDt_entry_time(currentDate);
						
						puSubscribeMsgService.insertPushSubscribeMsgLog(pushSubscribeMsgLog);
					}

				}
			}


		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}
}
