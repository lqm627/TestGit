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
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.entity.ILotteryType;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.News;
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
public class NewsDetailService  implements IClientService{
	@Autowired
	private NewsDao newsDao;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean = new ResponseBean();
		  try{
				if("{}".equals(request_data)){

				}else{
					String ip=parameterMap.get("ip");
					JSONObject jsonObject=JSONObject.parseObject(request_data);
					Map<String,Object> map=new HashMap<String,Object>();
					map=newsDao.getNewsDetail(Integer.valueOf(jsonObject.getString("id")));
					responseBean.setData(map);
					responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
					//打开新闻详情后，为该条新闻添加一条点击记录
//					boolean isNew =request.getSession().isNew();
//					if(isNew){
					EcpAccessLog accessLog = new EcpAccessLog();
					long modelId = Long.valueOf(jsonObject.getString("id"));
					if(memberId!=null){
						 accessLog.setInt_account_id(memberId);
					}
					Date accTime = new Date(System.currentTimeMillis());
					accessLog.setInt_model_type(4);  //4表示新闻模块
					accessLog.setInt_model_id(modelId);  //新闻ID
					accessLog.setVc_ip(ip);
					accessLog.setDt_read_time(accTime);
					new Thread(new UserAccessThread(accessLog)).start();
//					}
					return responseBean;
				}


				
			}catch(Exception e){
				responseBean.setErrorCode(ResponseErrorMessage.ERROR);
				e.printStackTrace();
			}

		return responseBean;
	}
	
	

}
	
	