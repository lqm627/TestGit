package com.easylotto.core.entity.user;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Table;

@Table(name = "ecp_sign_in")
public class EcpSignIn implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9027789570948042206L;
	private String vcOpenId;
	private String vcName;
	private Date dtSignTime;
	private Integer intSignDays;
	
	public EcpSignIn() {
		super();
	}

	public EcpSignIn(String vcOpenId, String vcName, Date deSignTime, Integer intSignDays) {
		super();
		this.vcOpenId = vcOpenId;
		this.vcName = vcName;
		this.dtSignTime = deSignTime;
		this.intSignDays = intSignDays;
	}

	public String getVcOpenId() {
		return vcOpenId;
	}

	public void setVcOpenId(String vcOpenId) {
		this.vcOpenId = vcOpenId;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public Date getDtSignTime() {
		return dtSignTime;
	}

	public void setDtSignTime(Date deSignTime) {
		this.dtSignTime = deSignTime;
	}

	public Integer getIntSignDays() {
		return intSignDays;
	}

	public void setIntSignDays(Integer intSignDays) {
		this.intSignDays = intSignDays;
	}

	@Override
	public String toString() {
		return "EcpSignIn [vcOpenId=" + vcOpenId + ", vcName=" + vcName + ", deSignTime=" + dtSignTime
				+ ", intSignDays=" + intSignDays + "]";
	}
	
	
}
