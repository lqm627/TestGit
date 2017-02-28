package com.easylotto.core.common;

public class RespData {

	private String retCode;
	private String retFlag;
	//private String retData;
	private Object retData;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
/*
	public String getRetData() {
		return retData;
	}

	public void setRetData(String retData) {
		this.retData = retData;
	}
*/
	public String getRetFlag() {
		return retFlag;
	}

	public void setRetFlag(String retFlag) {
		this.retFlag = retFlag;
	}

	public Object getRetData() {
		return retData;
	}

	public void setRetData(Object retData) {
		this.retData = retData;
	}
}
