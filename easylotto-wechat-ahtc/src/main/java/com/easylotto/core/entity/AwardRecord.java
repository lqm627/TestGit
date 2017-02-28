package com.easylotto.core.entity;

/**
 * @author huangjun
 * @version 2012-7-26
 */
public class AwardRecord {

	/**
	 *奖级
	 */
	private String awardLevel;
	/**
	 * 注数
	 */
	private String openCount;
	/**
	 * 金额
	 */
	private String openAward;

	public String getAwardLevel() {
		return awardLevel;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}

	public String getOpenCount() {
		return openCount;
	}

	public void setOpenCount(String openCount) {
		this.openCount = openCount;
	}

	public String getOpenAward() {
		return openAward;
	}

	public void setOpenAward(String openAward) {
		this.openAward = openAward;
	}

}
