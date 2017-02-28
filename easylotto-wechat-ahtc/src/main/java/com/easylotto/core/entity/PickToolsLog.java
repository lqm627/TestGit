package com.easylotto.core.entity;

import java.util.Date;

public class PickToolsLog {

	private Long  int_rec_id;
	private Long int_account_id;
	private int int_pick_type;
	private String vc_term;
	private String vc_code_content;
	private int int_lottery_type;
	private int int_constellation_type;
	private String vc_name;
	private String vc_birthday;
	private Date entry_time;
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
	public int getInt_pick_type() {
		return int_pick_type;
	}
	public void setInt_pick_type(int int_pick_type) {
		this.int_pick_type = int_pick_type;
	}
	public String getVc_term() {
		return vc_term;
	}
	public void setVc_term(String vc_term) {
		this.vc_term = vc_term;
	}
	public String getVc_code_content() {
		return vc_code_content;
	}
	public void setVc_code_content(String vc_code_content) {
		this.vc_code_content = vc_code_content;
	}
	public int getInt_lottery_type() {
		return int_lottery_type;
	}
	public void setInt_lottery_type(int int_lottery_type) {
		this.int_lottery_type = int_lottery_type;
	}


	public int getInt_constellation_type() {
		return int_constellation_type;
	}
	public void setInt_constellation_type(int int_constellation_type) {
		this.int_constellation_type = int_constellation_type;
	}
	public String getVc_name() {
		return vc_name;
	}
	public void setVc_name(String vc_name) {
		this.vc_name = vc_name;
	}
	public String getVc_birthday() {
		return vc_birthday;
	}
	public void setVc_birthday(String vc_birthday) {
		this.vc_birthday = vc_birthday;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	
}
