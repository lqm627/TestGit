package com.wechat.webapi.web.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.wechat.webapi.web.common.ResponseErrorCode;
import com.wechat.webapi.web.common.ResponseErrorMessage;



@JsonSerialize(include=Inclusion.NON_NULL)
public class ResponseBean {

	private String errorCode;
	private String errorMsg;
	private String systime;
	private String accountStatus;
	private String mslotStatus;
	private String reqFlag;
	
	
	private Object data;
	
	public ResponseBean(){
		setErrorCode(ResponseErrorCode.ERROR);
		setErrorMsg(ResponseErrorMessage.ERROR);
	}
	
	public ResponseBean(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public ResponseBean(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public ResponseBean(String errorCode, String errorMsg, Object data) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}



	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}



	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getMslotStatus() {
		return mslotStatus;
	}

	public void setMslotStatus(String mslotStatus) {
		this.mslotStatus = mslotStatus;
	}

	public String getReqFlag() {
		return reqFlag;
	}

	public void setReqFlag(String reqFlag) {
		this.reqFlag = reqFlag;
	}

	public void success(){
		setErrorCode(ResponseErrorCode.SUCCESS);
		setErrorMsg(ResponseErrorMessage.SUCCESS);
	}
	
	public void error() {
		setErrorCode(ResponseErrorCode.ERROR);
		setErrorMsg(ResponseErrorMessage.ERROR);
	}
	
	
}
