package com.wechat.webapi.web.handler;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.LotteryTypeDef;
import com.easylotto.core.service.LotteryTermService;
import com.easylotto.core.util.PickDataUtil;

@Component("lottery14ResultHandler")
public class Lottery14ResultHandler implements LotteryResultHandler {

	private static Logger logger = Logger.getLogger(Lottery14ResultHandler.class);
	
	@Autowired private LotteryTermService lotteryTermService;
	
	@Autowired private JedisUtil jedisTemplate;
	
	public void handler(List<String> values) {
		try {
			EcpLotteryOpenResult bean = new EcpLotteryOpenResult();
			int i = 0;
			String codeContent = "";
			String prizeContent = "pl3zhxCount|0|pl3zhxPrize|0@pl3zx3Count|0|pl3zx3Prize|0@pl3zx6Count|0|pl3zx6Prize|0@pl5zxCount|排列5直选注数|pl5zxPrize|排列5直选奖金";
			String term = "";
			
			term = values.get(0);
			term = getTerm(term);
			if(! NumberUtils.isNumber(term)) return ;
			
			
			
			bean.setVc_term(term);
			Thread.sleep(5000);
			String key = "PL3:" + term;
			if(jedisTemplate.exists(key)){
				prizeContent = jedisTemplate.get(key);
			}else{
				Thread.sleep(5000);
				if(jedisTemplate.exists(key)){
					prizeContent = jedisTemplate.get(key);
				}else{
					Thread.sleep(5000);
					if(jedisTemplate.exists(key)){
						prizeContent = jedisTemplate.get(key);
					}
				}
			}
			codeContent = values.get(1);
			codeContent = getResult(codeContent);
			if(StringUtils.isNotBlank(codeContent)){
				bean.setVc_code_content(codeContent);
			}
			
			String value = PickDataUtil.getValue("http://www.ahtycp.cn/kaijiangxinxi.ahtc?drawid=350133&drawnum=" + term);
			
			String[] _values = value.split("全国本期中奖情况");
			
			value = _values[1];
			String[] levels = {"排列5直选"};
			
			if(StringUtils.contains(value, "一等奖")){
				prizeContent = StringUtils.replace(prizeContent, "排列5直选", "一等奖");
				levels = new String[]{"一等奖"};
			}
			
			prizeContent = PickDataUtil.replace(levels, prizeContent, value, "");
			logger.info("+++++++++++ >>> " + term + " " + prizeContent);
			bean.setInt_lottery_type(LotteryTypeDef.PL_3);
			bean.setVc_prize_content(prizeContent);
			checkAndSave(bean);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public String getTerm(String value) {
		value = StringUtils.replace(value, "排列5第", "");
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
				value = StringUtils.replace(value, "+", ",");
				return value;
			}
		}
		return "";
	}
	
	public String getPoolAward(String term) {
		try {
			String pl3 = PickDataUtil.getPoolAward("http://www.ahtycp.cn/kaijiangxinxi.ahtc?drawid=35&drawnum=" + term);
			String pl5 = PickDataUtil.getPoolAward("http://www.ahtycp.cn/kaijiangxinxi.ahtc?drawid=350133&drawnum=" + term);
			if(StringUtils.isBlank(pl3)) return pl5;
			if(StringUtils.isBlank(pl5)) return pl3;
			return pl3 + "|" + pl5;
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
		// TODO Auto-generated method stub
		
	}

	public void execute(EcpLotteryOpenResult bean) {
		// TODO Auto-generated method stub
		
	}

	
	
}
