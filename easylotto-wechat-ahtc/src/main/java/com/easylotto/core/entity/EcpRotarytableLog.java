package com.easylotto.core.entity;

import java.util.Date;

public class EcpRotarytableLog {

	private Long int_rec_id;
	private Long int_account_id;
	private int int_amount;
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
	public int getInt_amount() {
		return int_amount;
	}
	public void setInt_amount(int int_amount) {
		this.int_amount = int_amount;
	}
	public Date getDt_entry_time() {
		return dt_entry_time;
	}
	public void setDt_entry_time(Date dt_entry_time) {
		this.dt_entry_time = dt_entry_time;
	}
	
}
