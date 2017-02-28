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
public class LotteryTermFindTypeService  implements IClientService{
	@Autowired
	private EcpRichLotteryDAO ecpRichLotteryDAO;
	@Autowired
	private CommonService commonService;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();
		try{
			String[] str=parameterMap.get("serkey").split("_");
			String type=str[1];
			responseBean.setData(find(Integer.valueOf(type)));
		}catch(Exception e){
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}
		return responseBean;
	}
	
	/**
	 * 查询单个彩种的彩期信息
	 * @param lottery
	 * @return
	 */
	public ResponseBean find(int lottery){
		ResponseBean rb = new ResponseBean();
		
		//int lotteryType = Integer.parseInt(lottery);
		//EcpRichLottery ecpRichLottery = lotteryService.getOnSaleTerm(lottery);
		JSONObject respObject = findJSONObject(lottery);
		if (respObject != null) {
//			respObject.put("lotteryType",  ecpRichLottery.getInt_lottery_type()+"");
//			respObject.put("term",  ecpRichLottery.getVc_term());
//			respObject.put("deadline",  DateFormatUtils.format(ecpRichLottery.getDt_deadline(), "yyyy-MM-dd HH:mm:ss"));
//			respObject.put("sysDateTime", DateFormatUtils.format(ecpRichLottery.getSysdate(), "yyyy-MM-dd HH:mm:ss"));
//			respObject.put("maxTerm", commonService.getMaxTerm(ecpRichLottery.getInt_lottery_type()));//彩种当前最大彩期
			
			rb.setErrorCode("success");
			rb.setData(respObject);
		}else{
			rb.setErrorCode("error");
			rb.setErrorMsg("找不到数据！");
		}
		return rb;
	}
	
	public JSONObject findJSONObject(int lottery){
		EcpRichLottery ecpRichLottery = ecpRichLotteryDAO.getOnSaleTerm(lottery);
		JSONObject respObject = new JSONObject();
		if (ecpRichLottery != null) {
			respObject.put("lotteryType",  ecpRichLottery.getInt_lottery_type()+"");
			respObject.put("term",  ecpRichLottery.getVc_term());
			respObject.put("deadline",  DateFormatUtils.format(ecpRichLottery.getDt_deadline(), "yyyy-MM-dd HH:mm:ss"));
			respObject.put("sysDateTime", DateFormatUtils.format(ecpRichLottery.getSysdate(), "yyyy-MM-dd HH:mm:ss"));
			respObject.put("maxTerm", commonService.getMaxTerm(ecpRichLottery.getInt_lottery_type()));//彩种当前最大彩期
			respObject.put("restTime", getRestSecond(ecpRichLottery.getDt_deadline(), ecpRichLottery.getSysdate()));
			
			return respObject;
		}
		return null;
	}
	
	/**
	 * 获取两个时间之间剩余的秒数
	 * @param deadline
	 * @param cur
	 * @return
	 */
	public long getRestSecond(Date deadline, Date cur){
		if(cur.before(deadline)){
			long rest = deadline.getTime() - cur.getTime();
			return rest/1000;
		}
		return 0;
	}


}
	
	