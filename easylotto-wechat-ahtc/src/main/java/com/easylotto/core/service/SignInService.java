package com.easylotto.core.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.dao.LotteryDao;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.dao.NewsDao;
import com.easylotto.core.dao.PickToolsLogDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.entity.ILotteryType;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.News;
import com.easylotto.core.entity.PickToolsLog;
import com.easylotto.core.entity.PushMsg;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserInfo;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpSignInService;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.util.IPAddrUtil;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;
import com.wechat.webapi.web.thread.PushThread;
import com.wechat.webapi.web.thread.UserAccessThread;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;

@Service
@Transactional
public class SignInService  implements IClientService{
	@Autowired
	private EcpUserInfoService ecpUserInfoService;
	@Autowired
	private EcpSignInService ecpSignInService;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean = new ResponseBean();
		  responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			try {

				Map<String,Object> map=new HashMap<String,Object>();
				String type=parameterMap.get("type");
				String key=parameterMap.get("key");
				EcpUser ecpUser = ecpUserInfoService.findEcpUserById(memberId);
				String openId = ecpUser.getVc_open_id();
				String nickName = ecpUser.getVc_account();
				EcpSignIn bean = ecpSignInService.findById(openId);
				EcpUserInfo ecpUserInfo = ecpUserInfoService.findByOpenId(openId,type); 
			
				PushMsg msg = ecpSignInService.updateSignIn(openId, nickName, bean, ecpUserInfo);
				if(null != msg){
					msg.setBalance(ecpSignInService.getBeanBalance(memberId));
					map.put("balance", msg.getBalance());
					new Thread(new PushThread(msg,key)).start();
				}
				responseBean.setData(map);
				responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
				
			
			} catch (Exception e) {
				responseBean.setErrorCode(ResponseErrorMessage.ERROR);
				e.printStackTrace();
			}


		return responseBean;
	}
	
	

}
	
	