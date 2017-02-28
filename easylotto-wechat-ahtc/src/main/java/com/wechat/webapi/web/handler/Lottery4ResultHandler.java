package com.wechat.webapi.web.handler;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.LotteryTypeDef;
import com.easylotto.core.service.LotteryTermService;
import com.easylotto.core.util.PickDataUtil;

@Component("lottery4ResultHandler")
public class Lottery4ResultHandler implements LotteryResultHandler {

	private static Logger logger = Logger.getLogger(Lottery4ResultHandler.class);
	
	@Autowired private LotteryTermService lotteryTermService;
	
	public void handler(List<String> values) {
		EcpLotteryOpenResult bean = new EcpLotteryOpenResult();
		int i = 0;
		String term = "";
		term = values.get(0);
		term = getTerm(term);
		if(! NumberUtils.isNumber(term)) return ;
		String codeContent = "";
		String prizeContent = "firstCount|一等奖注数|firstPrize|一等奖奖金@secCount|二等奖注数|secPrize|二等奖奖金@thirdCount|三等奖注数|thirdPrize|三等奖奖金@forthCount|四等奖注数|forthPrize|四等奖奖金@fifthCount|五等奖注数|fifthPrize|五等奖奖金@sixthCount|六等奖注数|sixthPrize|六等奖奖金";
		
		bean.setVc_term(term);
		
		codeContent = values.get(1);
		codeContent = getResult(codeContent);
		if(StringUtils.isNotBlank(codeContent)){
			bean.setVc_code_content(codeContent);
		}
		
		String value = PickDataUtil.getValue("http://www.ahtycp.cn/kaijiangxinxi.ahtc?drawid=04&drawnum=" + term);
		
		String[] _values = value.split("全国本期中奖情况");
		

		value = _values[1];
		String[] levels = {"一等奖", "二等奖", "三等奖", "四等奖", "五等奖", "六等奖"};
		prizeContent = PickDataUtil.replace(levels, prizeContent, value, "");
		
		logger.info("+++++++++++ >>> " + term + " " + prizeContent);
		
		bean.setInt_lottery_type(LotteryTypeDef.SEVEN_STAR);
		bean.setVc_prize_content(prizeContent);
		checkAndSave(bean);
	}

	public String getTerm(String value) {
		value = StringUtils.replace(value, "七星彩第", "");
		value = StringUtils.replace(value, "期开奖结果", "");
		value = StringUtils.trim(value);
		return value;
	}

	public String getResult(String value) {
		if(StringUtils.contains(value, "</b></font>")){
			String[] values = value.split("</b></font>");
			value = values[0];
			if(StringUtils.contains(value, "<b>")){
				values = value.split("<b>");
				value = values[1];
				value = StringUtils.replace(value, " ", ",");
				return value;
			}
		}
		return "";
	}
	
	public String getPoolAward(String term) {
		try {
			return PickDataUtil.getPoolAward("http://www.ahtycp.cn/kaijiangxinxi.ahtc?drawid=04&drawnum=" + term);
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	public boolean checkUnique(String term, Integer type) {
		return this.lotteryTermService.checkUnique(term, type);
	}

	public void checkAndSave(EcpLotteryOpenResult bean) {
		if(lotteryTermService.checkAndSave(bean, this)){
			push(bean);
			execute(bean);
		}
	}

	public void push(EcpLotteryOpenResult bean) {
		lotteryTermService.push(bean);
	}

	public void execute(EcpLotteryOpenResult bean) {
		// TODO Auto-generated method stub
		
	}

}
