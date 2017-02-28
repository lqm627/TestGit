package com.easylotto.core.entity;
import java.util.Date;

public class ActivityLog {
  private Long int_rec_id;
  private Long int_activity_id;
  private Long int_account_id;
  private String vc_name;
  private String vc_phone;
  private String vc_card_no;
  private Long int_vote_cumulative_id;
  private String vc_upper_couplet;
  private String vc_lower_couplet;
  private String vc_couplet_ryhming;
  private String vc_pic_url;
  private String vc_serial_number;
  private Date dt_entry_time;
public Long getInt_rec_id() {
	return int_rec_id;
}
public void setInt_rec_id(Long int_rec_id) {
	this.int_rec_id = int_rec_id;
}
public Long getInt_activity_id() {
	return int_activity_id;
}
public void setInt_activity_id(Long int_activity_id) {
	this.int_activity_id = int_activity_id;
}
public Long getInt_account_id() {
	return int_account_id;
}
public void setInt_account_id(Long int_account_id) {
	this.int_account_id = int_account_id;
}
public String getVc_name() {
	return vc_name;
}
public void setVc_name(String vc_name) {
	this.vc_name = vc_name;
}
public String getVc_phone() {
	return vc_phone;
}
public void setVc_phone(String vc_phone) {
	this.vc_phone = vc_phone;
}

public Date getDt_entry_time() {
	return dt_entry_time;
}
public void setDt_entry_time(Date dt_entry_time) {
	this.dt_entry_time = dt_entry_time;
}

public String getVc_card_no() {
	return vc_card_no;
}
public void setVc_card_no(String vc_card_no) {
	this.vc_card_no = vc_card_no;
}
public Long getInt_vote_cumulative_id() {
	return int_vote_cumulative_id;
}
public void setInt_vote_cumulative_id(Long int_vote_cumulative_id) {
	this.int_vote_cumulative_id = int_vote_cumulative_id;
}
public String getVc_upper_couplet() {
	return vc_upper_couplet;
}
public void setVc_upper_couplet(String vc_upper_couplet) {
	this.vc_upper_couplet = vc_upper_couplet;
}
public String getVc_lower_couplet() {
	return vc_lower_couplet;
}
public void setVc_lower_couplet(String vc_lower_couplet) {
	this.vc_lower_couplet = vc_lower_couplet;
}
public String getVc_couplet_ryhming() {
	return vc_couplet_ryhming;
}
public void setVc_couplet_ryhming(String vc_couplet_ryhming) {
	this.vc_couplet_ryhming = vc_couplet_ryhming;
}
public String getVc_pic_url() {
	return vc_pic_url;
}
public void setVc_pic_url(String vc_pic_url) {
	this.vc_pic_url = vc_pic_url;
}
public String getVc_serial_number() {
	return vc_serial_number;
}
public void setVc_serial_number(String vc_serial_number) {
	this.vc_serial_number = vc_serial_number;
}
  
  

}
