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


public class PushDLTSubscribeThread implements Runnable {

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
	public PushDLTSubscribeThread(String key){
		this.key=key;
	}
	
	private void pushMsgToSubscriber(){
		
		try {
			EcpLotteryOpenResult  ecpLotteryOpenResult=null;
			PushSubscribeMsgService puSubscribeMsgService = SpringContext.getBean(PushSubscribeMsgService.class);
			ecpLotteryOpenResult=puSubscribeMsgService.getLastOpenResultByPlaytype(23);
			if(null == ecpLotteryOpenResult) return ;
			BaseWxService wxService = (BaseWxService) SpringContext.getBean(key + "WxService");
			List<SubscribeLog> listqlc = puSubscribeMsgService.getSubscriber(23, Integer.valueOf(wxService.getType()));
			
			
			String qlcfirstCount="";
			String qlcfirstPrize="";
			String qlcSecCount="";
			String qlcSecPrize="";
			String qlcthirdCount="";
			String qlcthirdPrize="";
			
			if(null == listqlc || 0 == listqlc.size()){
				return ;
			}else{

				String[] test=new String[7];
				String[] test2=new String[4];
				test = ecpLotteryOpenResult.getVc_prize_content().split("@");
				for(int j=0;j<test.length;j++){
					if(j==0){
						test2=test[0].split("\\|");
						qlcfirstCount=test2[1];
						qlcfirstPrize=test2[3];
					}
					else if(j==1){
						test2=test[1].split("\\|");
						qlcSecCount=test2[1];
						qlcSecPrize=test2[3];
					}else if(j==2){
						test2=test[2].split("\\|");
						qlcthirdCount=test2[1];
						qlcthirdPrize=test2[3];
					}
				}
			}
		
			String contentqlc = "开奖公告:第{0}期大乐透开奖号码:\n{1}。\n当期开出\n一等奖:{2}注,每注{3}元\n二等奖:{4}注,每注{5}元\n三等奖:{6}注,每注{7}元\n奖池金额:{8}元";
			String qlc_content = "";

			if(null != listqlc && 0 != listqlc.size()){
				for(SubscribeLog msg : listqlc){
					String interval = puSubscribeMsgService.getSubscriberState(msg.getInt_account_id(), Integer.valueOf(wxService.getType()));
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
						qlc_content = StringUtils.replace(contentqlc, "{0}",ecpLotteryOpenResult.getVc_term() );
						qlc_content = StringUtils.replace(qlc_content, "{1}",ecpLotteryOpenResult.getVc_code_content());
						qlc_content = StringUtils.replace(qlc_content, "{2}", qlcfirstCount);
						qlc_content = StringUtils.replace(qlc_content, "{3}", qlcfirstPrize);
						qlc_content = StringUtils.replace(qlc_content, "{4}", qlcSecCount);
						qlc_content = StringUtils.replace(qlc_content, "{5}", qlcSecPrize);
						qlc_content = StringUtils.replace(qlc_content, "{6}", qlcthirdCount);
						qlc_content = StringUtils.replace(qlc_content, "{7}", qlcthirdPrize);
						qlc_content = StringUtils.replace(qlc_content, "{8}", ecpLotteryOpenResult.getVc_pool_award());
						
						wxService.getKefuService().sendKefuMessage(WxMpKefuMessage.TEXT().toUser(msg.getVc_open_id()).content(qlc_content).build());
						
						PushSubscribeMsgLog pushSubscribeMsgLog=new PushSubscribeMsgLog();
						pushSubscribeMsgLog.setInt_account_id(msg.getInt_account_id());
						pushSubscribeMsgLog.setInt_lottery_type(24);
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
