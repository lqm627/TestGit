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
import com.easylotto.core.dao.EcpRichLotteryDAO;
import com.easylotto.core.dao.LotteryDao;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.EcpLottery;
import com.easylotto.core.entity.EcpRichLottery;
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
import org.apache.commons.lang3.time.DateFormatUtils;

@Service
@Transactional
public class LotteryTermFindAllService  implements IClientService{
	@Autowired
	private EcpRichLotteryDAO ecpRichLotteryDAO;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();
		try{
			responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
			responseBean.setData(findAll());
			
			
		}catch(Exception e){
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}
		return responseBean;
	}
	
	/**
	 * 查询所有彩种的彩期信息
	 * @return
	 */
	public ResponseBean findAll(){
		ResponseBean rb = new ResponseBean();

		List<EcpLottery> list = new ArrayList<EcpLottery>();
		List<EcpRichLottery> tempList = ecpRichLotteryDAO.getAllOnSaleTerm();
		if (tempList != null) {
			for (EcpRichLottery ecpRichLottery : tempList) {
				EcpLottery ecpLottery = new EcpLottery();
				ecpLottery.setLotteryType(ecpRichLottery.getInt_lottery_type()+"");
				ecpLottery.setTerm(ecpRichLottery.getVc_term());
				ecpLottery.setDeadline(DateFormatUtils.format(ecpRichLottery.getDt_deadline(), "yyyy-MM-dd HH:mm:ss"));
				ecpLottery.setSysDateTime("");
				list.add(ecpLottery);
			}
			rb.setErrorCode("success");
			rb.setData(list);
		}else{//找不到数据
			rb.setErrorCode("error");
			rb.setErrorMsg("找不到数据！");
		}
		return rb;
	}


}
	
	