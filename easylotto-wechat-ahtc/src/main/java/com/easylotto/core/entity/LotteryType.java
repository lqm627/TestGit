package com.easylotto.core.entity;

/**
 * 彩种枚举类
 * 注：需要用到的彩种请自己添加
 * @author wucx
 *
 */
public enum LotteryType {

	DLT(23, "大乐透", 60l), 
	SEVEN_STAR(4, "七星彩", 60l),
	PL_3(13, "排列三", 60l), 
	PL_5(14, "排列五", 60l), 
	WIN_MATCH(1, "胜负彩", 60l), 
	FOUR_GOAL(8, "四场进球彩", 60l), 
	SIX_WIN(6, "六场半全场", 60l);

	Integer code;
	String name;
	Long timeout;

	LotteryType(Integer code, String name, Long timeout) {
		this.code = code;
		this.name = name;
		this.timeout = timeout;
	}

	public static String getName(int code) {
		for (LotteryType value : values()) {
			if (value.getCode().intValue() == code) {
				return value.getName();
			}
		}
		return null;
	}
	
	public static LotteryType value(int code) {
		for (LotteryType value : values()) {
			if (value.getCode().intValue() == code) {
				return value;
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getCodeToString() {
		return code.toString();
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}
	
	
}
