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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.dao.LotteryDao;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.dao.NearBetMapDao;
import com.easylotto.core.dao.NewsDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.BetCenter;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.entity.ILotteryType;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.NearBetInfo;
import com.easylotto.core.entity.News;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.easylotto.core.util.HttpInvoker;
import com.wechat.webapi.util.IPAddrUtil;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.util.SphericalDistance;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;
import com.wechat.webapi.web.thread.UserAccessThread;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;

@Service
@Transactional
public class TicketInfoService  implements IClientService{
	@Autowired
	private NearBetMapDao nearBetMapDao;
	
	@Autowired
	private JedisUtil jedisTemplate;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean = new ResponseBean();
		  Map<String, Object> map = new HashMap<String, Object>();
			try {
	            List<BetCenter> list=getBetCenterLists();
	            if(list!=null&&list.size()!=0){
	            	map.put("rows", list);
	            	responseBean.setData(map);
	            	responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
	            	return responseBean;
	            }else{
	            	map.put("error", "无法查询到相关信息！");
	            	responseBean.setData(map);
	            	responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
	            	return responseBean;
	            }

			} catch (Exception e) {
				e.printStackTrace();
			}
		return responseBean;
	}
	
	private final String KEY = "BETCENTER:LISTS";
	
	public List<BetCenter> getBetCenterLists(){
		String value = "";
		if (jedisTemplate.exists(KEY)) {
			value = jedisTemplate.get(KEY);
		}
		List<BetCenter> list = new ArrayList<BetCenter>();
		if (StringUtils.isBlank(value)) {
			list = this.nearBetMapDao.getBetCenterLists();
			if (null != list && 0 < list.size()) {
				jedisTemplate.set(KEY, JSON.toJSONString(list), 60 * 60, TimeUnit.SECONDS);
			}
		} else {
			list = JSON.parseArray(value, BetCenter.class);
		}
		return list;
	}
	

	
	

}
	
	