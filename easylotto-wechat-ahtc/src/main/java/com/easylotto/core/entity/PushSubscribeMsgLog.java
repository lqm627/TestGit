package com.easylotto.core.entity;

import java.util.Date;

import javax.persistence.Table;
@Table(name="push_subscribe_msg_log")
public class PushSubscribeMsgLog {

	private Long int_rec_id;
	private Long int_account_id;
	private int int_lottery_type;
	private String vc_term;
	private Date dt_entry_time;
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
	public int getInt_lottery_type() {
		return int_lottery_type;
	}
	public void setInt_lottery_type(int int_lottery_type) {
		this.int_lottery_type = int_lottery_type;
	}
	public String getVc_term() {
		return vc_term;
	}
	public void setVc_term(String vc_term) {
		this.vc_term = vc_term;
	}
	public Date getDt_entry_time() {
		return dt_entry_time;
	}
	public void setDt_entry_time(Date dt_entry_time) {
		this.dt_entry_time = dt_entry_time;
	}
	
	
}
