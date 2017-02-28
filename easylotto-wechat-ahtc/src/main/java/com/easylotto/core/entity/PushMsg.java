/**
 * 
 */
package com.easylotto.core.entity;

/**
 * @author CreateName: UpdateName:
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see PushMsg
 * @see CreateDate: 2016年6月15日 上午11:22:44 UpdateDate: 2016年6月15日 上午11:22:44
 * @see Copyright
 * @since JDK1.7.*
 * @version 1.0
 */
public class PushMsg {
	int signDays;
	double beanCount;
	String openId;
	double balance;

	public int getSignDays() {
		return signDays;
	}

	public void setSignDays(int signDays) {
		this.signDays = signDays;
	}

	public double getBeanCount() {
		return beanCount;
	}

	public void setBeanCount(double beanCount) {
		this.beanCount = beanCount;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
