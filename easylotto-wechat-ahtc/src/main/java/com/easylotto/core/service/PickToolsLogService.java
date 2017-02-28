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
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.util.IPAddrUtil;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;
import com.wechat.webapi.web.thread.UserAccessThread;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;

@Service
@Transactional
public class PickToolsLogService  implements IClientService{
	@Autowired
	private PickToolsLogDao pickToolsLogDao; 
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean = new ResponseBean();
		  Map<String, Object> map = new HashMap<String, Object>();
//		  request_data="{\"vc_term\":\"11\",\"int_pick_type\":\"3\",\"int_lottery_type\":\"3\",\"int_constellation_type\":\"2\",\"vc_code_content\":\"111\"}";
			try {
				if ("{}".equals(request_data)) {
					responseBean.setData(new ResponseBean("error", "参数传递错误"));
					responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
					return responseBean;
				} else {
					PickToolsLog pickToolsLog=JSON.parseObject(request_data,PickToolsLog.class);
					pickToolsLog.setInt_account_id(memberId);
					String vc_code_content=pickToolsLogDao.getPick(pickToolsLog);
					if(vc_code_content==null){
						pickToolsLogDao.insertPickToolsLog(pickToolsLog);
						map.put("vc_code_content", pickToolsLog.getVc_code_content());
						responseBean.setData(map);
						responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
						return responseBean;
					}else{
						map.put("vc_code_content", vc_code_content);
						responseBean.setData(map);
						responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
						return responseBean;
					}
					
				}
	            


			} catch (Exception e) {
				e.printStackTrace();
			}


		return responseBean;
	}
	
	

}
	
	