package com.easylotto.core.entity;

import java.util.Date;

public class SubscribeLog {
  private Long  int_rec_id;
  private Long int_account_id;
  private int int_lottery_type;
  private Date dt_create_time;
  private Date dt_end_time;
  private String vc_open_id;
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
public Date getDt_create_time() {
	return dt_create_time;
}
public void setDt_create_time(Date dt_create_time) {
	this.dt_create_time = dt_create_time;
}
public Date getDt_end_time() {
	return dt_end_time;
}
public void setDt_end_time(Date dt_end_time) {
	this.dt_end_time = dt_end_time;
}
public String getVc_open_id() {
	return vc_open_id;
}
public void setVc_open_id(String vc_open_id) {
	this.vc_open_id = vc_open_id;
}
  
}
