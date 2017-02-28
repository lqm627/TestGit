package com.easylotto.core.entity;

/**
 * @author huangjun
 * @version 2012-7-26
 */
public class ArrangeResult {

	private String homeTeamName;// 主队
	private String guestTeamName;// 客队
	private String halfResult;// 半场赛果
	private String wholeResult;// 全场赛果
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	public String getGuestTeamName() {
		return guestTeamName;
	}
	public void setGuestTeamName(String guestTeamName) {
		this.guestTeamName = guestTeamName;
	}
	public String getHalfResult() {
		return halfResult;
	}
	public void setHalfResult(String halfResult) {
		this.halfResult = halfResult;
	}
	public String getWholeResult() {
		return wholeResult;
	}
	public void setWholeResult(String wholeResult) {
		this.wholeResult = wholeResult;
	}

}
