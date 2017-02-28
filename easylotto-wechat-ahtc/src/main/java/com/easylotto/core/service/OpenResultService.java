package com.easylotto.core.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.wechat.webapi.web.model.ResponseBean;

@Service
@Transactional
public class OpenResultService  implements IClientService{
	
	@Autowired
	private OpenService openService;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean = new ResponseBean();
		  responseBean.setErrorCode("success");
			try{
				String[] str=parameterMap.get("serkey").split("_");
				String lotteryType=str[1];
				JSONObject obj = JSON.parseObject(request_data);
				String term = obj.getString("term");
				if(StringUtils.isBlank(term)){
					responseBean.setErrorMsg("彩期参数不能为空。");
					
					return responseBean;
				}
				EcpLotteryOpenResult result = null;
				int i = 0;
				do {
					term = nextTerm(term,lotteryType);
					result = openService.getOpenDetail(Integer.valueOf(lotteryType), term);
					if(i > 3) break;
					i++;
				} while (null == result);
				
				responseBean.setData(result);
			}catch(Exception e){
				responseBean.setErrorMsg("执行时出错。");
				e.printStackTrace();
			}

		return responseBean;
	}
	
	private String nextTerm(String term, String lotteryType){
		if("27".equals(lotteryType)){
			String _term = StringUtils.substring(term, 7);
			Date date = new Date(System.currentTimeMillis());
			if("01".equals(_term)){
				_term = "80";
				date = DateTimeUtil.getYesterday(-1);
			}else{
				if(StringUtils.isNotBlank(_term)){
					int value = Integer.valueOf(_term);
					value-=1;
					_term = value+"";
					if(10 > value){
						_term = "0" + _term;
					}
				}
			}
			String _date = DateTimeUtil.toString(DateTimeUtil.YYMMDD, date);
			term = (_date + "0"+ _term); 
			return term;
		}else{
			String _term = StringUtils.substring(term, 4);
			Date date = new Date(System.currentTimeMillis());
			if(StringUtils.isNotBlank(_term)){
				int value = Integer.valueOf(_term);
				value-=1;
				_term = value+"";
				if(10 > value){
					_term = "00" + _term;
				}else if(100 > value && 9 < value){
					_term = "0" + _term;
				}
			}
			String _date = DateTimeUtil.toString(DateTimeUtil.YYYY, date);
			term = (_date + _term); 
			return term;
		}
	}

}
	
	