package com.easylotto.core.entity;


import java.util.Date;

import javax.persistence.Table;

@Table(name="ecp_access_log")
public class EcpAccessLog {
	private Long int_rec_id;
	private Long int_account_id;
	private Long int_model_id;
	private int int_model_type;
	private String vc_ip;
	private Date dt_read_time;
	
	public Long getInt_rec_id() {
		return int_rec_id;
	}
	public void setInt_rec_id(Long int_rec_id) {
		this.int_rec_id = int_rec_id;
	}
	public Long getInt_account_id() {
		return int_account_id;
	}
	public void setInt_account_id(Long int_account_id) {
		this.int_account_id = int_account_id;
	}
	public Long getInt_model_id() {
		return int_model_id;
	}
	public void setInt_model_id(Long int_model_id) {
		this.int_model_id = int_model_id;
	}
	public int getInt_model_type() {
		return int_model_type;
	}
	public void setInt_model_type(int int_model_type) {
		this.int_model_type = int_model_type;
	}
	public String getVc_ip() {
		return vc_ip;
	}
	public void setVc_ip(String vc_ip) {
		this.vc_ip = vc_ip;
	}
	public Date getDt_read_time() {
		return dt_read_time;
	}
	public void setDt_read_time(Date dt_read_time) {
		this.dt_read_time = dt_read_time;
	}
	
	
	
	
}
