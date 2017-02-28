package com.wechat.webapi.web.handler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.util.PickDataUtil;

@Component("lottery13ResultHandler")
public class Lottery13ResultHandler implements LotteryResultHandler {

	private static Logger logger = Logger.getLogger(Lottery13ResultHandler.class);
	
	@Autowired private JedisUtil jedisTemplate;
	
	public void handler(List<String> values) {
		int i = 0;
		String term = "";
		String prizeContent = "pl3zhxCount|排列3直选注数|pl3zhxPrize|排列3直选奖金@pl3zx3Count|排列3组选3注数|pl3zx3Prize|排列3组选3奖金@pl3zx6Count|排列3组选6注数|pl3zx6Prize|排列3组选6奖金@pl5zxCount|排列5直选注数|pl5zxPrize|排列5直选奖金";
		term = values.get(0);
		term = getTerm(term);
		if(! NumberUtils.isNumber(term)) return ;
		
		String value = PickDataUtil.getValue("http://www.ahtycp.cn/kaijiangxinxi.ahtc?drawid=35&drawnum=" + term);
		
		String[] _values = value.split("全国本期中奖情况");
		
		value = _values[1];
		String[] levels = {"直选", "组选3", "组选6"};
		
		prizeContent = PickDataUtil.replace(levels, prizeContent, value, "排列3");
		
		logger.info("+++++++++++ >>> " + term + " " + prizeContent);
		if(StringUtils.isNotBlank(term))
			jedisTemplate.set("PL3:" + term, prizeContent, 500, TimeUnit.SECONDS);
	}

	public String getTerm(String value) {
		value = StringUtils.replace(value, "排列3第", "");
		value = StringUtils.replace(value, "期开奖结果", "");
		value = StringUtils.trim(value);
		return value;
	}

	public String getResult(String value) {
		return "";
	}

	public String getPoolAward(String term) {
		return "";
	}

	public boolean checkUnique(String term, Integer type) {
		return false;
	}

	public void checkAndSave(EcpLotteryOpenResult bean) {
		// TODO Auto-generated method stub
	}

	public void push(EcpLotteryOpenResult bean) {
		// TODO Auto-generated method stub
	}

	public void execute(EcpLotteryOpenResult bean) {
		// TODO Auto-generated method stub
	}

	
	
}
