package com.wechat.webapi.web.model;

import java.io.Serializable;





public class RequestBean implements Serializable {

	private static final long serialVersionUID = -2404036581816878816L;
	
	private String reqType;
	private String reqFlag;
	private String reqData;
	
	public RequestBean(){}

	public RequestBean(String reqType, String reqFlag, String reqData) {
		this.reqType = reqType;
		this.reqFlag = reqFlag;
		this.reqData = reqData;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getReqFlag() {
		return reqFlag;
	}

	public void setReqFlag(String reqFlag) {
		this.reqFlag = reqFlag;
	}

	public String getReqData() {
		return reqData;
	}

	public void setReqData(String reqData) {
		this.reqData = reqData;
	}
	
}
