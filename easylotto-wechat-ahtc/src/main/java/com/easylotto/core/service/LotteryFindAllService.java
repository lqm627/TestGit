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
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.dao.LotteryDao;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.ILotteryType;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;

@Service
@Transactional
public class LotteryFindAllService  implements IClientService{
	@Autowired
	private LotteryDao lotteryDao;	
	
	@Autowired
	private JedisUtil jedisTemplate;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();
		try{
			responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
			responseBean.setData(findAllLottery());
			
			
		}catch(Exception e){
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}
		return responseBean;
	}
	
	private final String KEY = "LOTTERY:ALL";
	
	public List<ILotteryType> findAllLottery() {
		String value = "";
		if (jedisTemplate.exists(KEY)) {
			value = jedisTemplate.get(KEY);
		}
		List<ILotteryType> list = new ArrayList<ILotteryType>();
		if (StringUtils.isBlank(value)) {
			list = this.lotteryDao.findAllLottery();
			if (null != list && 0 < list.size()) {
				jedisTemplate.set(KEY, JSON.toJSONString(list), 60 * 60, TimeUnit.SECONDS);
			}
		} else {
			list = JSON.parseArray(value, ILotteryType.class);
		}

		return list;
	}


}
	
	