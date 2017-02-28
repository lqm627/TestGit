package com.easylotto.core.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.core.entity.LotteryType;
import com.wechat.webapi.web.model.ResponseBean;
import com.wechat.webapi.web.thread.PushDLTSubscribeThread;
import com.wechat.webapi.web.thread.PushQXCSubscribeThread;

@Service
@Transactional
public class PushLotteryService  implements IClientService{
	
	@Autowired private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = null;
		String[] str=parameterMap.get("serkey").split("_");
		String lottery=str[1];
		String key=parameterMap.get("key");
		try {
			if(LotteryType.DLT.getCode().intValue() == Integer.valueOf(lottery)){
				threadPoolTaskExecutor.execute(new PushDLTSubscribeThread(key));
			}else if(LotteryType.SEVEN_STAR.getCode().intValue() ==  Integer.valueOf(lottery)){
				threadPoolTaskExecutor.execute(new PushQXCSubscribeThread(key));
			}
//			if(logger.isInfoEnabled()){
//				logger.info("PUSH {} LOTTERY Subscribe MSG !", lottery);
//			}
		} catch (Exception e) {
//			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}

		return responseBean;
	}
}
	
	