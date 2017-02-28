package com.wechat.webapi.web.handler;

import java.util.List;

import com.easylotto.core.entity.EcpLotteryOpenResult;

public interface LotteryResultHandler{

	public void handler(List<String> values);
	
	/**
	 * 截取彩期
	 * @param value
	 * @return
	 */
	public String getTerm(String value);
	
	/**
	 * 截取开奖结果
	 * @param value
	 * @return
	 */
	public String getResult(String value);
	
	/**
	 * 获取奖池
	 * @param term
	 * @param type
	 * @return
	 */
	public String getPoolAward(String term);
	
	/**
	 * 校验唯一
	 * @return
	 */
	public boolean checkUnique(String term, Integer type);
	
	/**
	 * 校验并保存
	 * @param bean
	 * @return
	 */
	public void checkAndSave(EcpLotteryOpenResult bean);
	
	/**
	 * 推送
	 */
	public void push(EcpLotteryOpenResult bean);
	
	/**
	 * 开奖
	 */
	public void execute(EcpLotteryOpenResult bean);
}
