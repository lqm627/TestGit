package com.easylotto.core.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.easylotto.core.entity.user.EcpUser;
import com.wechat.webapi.web.model.ResponseBean;

@Service
@Transactional
public class LotteryDataService  implements IClientService{
	@Autowired
	private CommonService commonService;	
	@Autowired
	private LotteryTermService lotteryTermService;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();
		responseBean.setErrorCode("success");

		JSONObject data = null;
		try {
			String[] str=parameterMap.get("serkey").split("_");
			String type=str[1];
			if (null == memberId) {
				responseBean.setErrorCode("success");
				responseBean.setErrorMsg("未找到用户信息！");
				return responseBean;
			}
			String int_account_id = "" + memberId;
			// 彩期信息
			data = lotteryTermService.findJSONObject(Integer.valueOf(type));
			if (data == null) {
				data = new JSONObject();
				data.put("lotteryType", null);
				data.put("term", null);
				data.put("deadline", null);
				data.put("sysDateTime", null);
				data.put("maxTerm", null);
				data.put("restTime", null);
			}
			// 彩豆信息
			EcpUser ecpUser = commonService.findLotteryBean(int_account_id);
			if (ecpUser != null) {
				int beanNum = 0;
				if (ecpUser.getDec_lottery_bean() != null) {
					beanNum = ecpUser.getDec_lottery_bean().intValue();
				}
				data.put("decLotteryBean", beanNum);
			} else {
				data.put("decLotteryBean", null);
			}
			responseBean.setData(data);

			return responseBean;
		} catch (Exception e) {
			responseBean.setData(null);
//			logger.error("查询彩期和彩豆信息时出错！");
			e.printStackTrace();
		}
		return responseBean;
	}
	


}
	
	