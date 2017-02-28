package com.easylotto.core.entity;

import java.util.Date;

import javax.persistence.Table;
/**
 * 		记录用户与公众号交互日志
 *
 */
@Table(name="ecp_menu_click_log")
public class EcpMenuClickLog {
	
	private Long int_rec_id;
	private Long int_account_id;
	private int int_menu_type;   //1关注 2会员中心 3模拟选号
	private String vc_open_id;
	private Date dt_click_time;
	
	
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
	public int getInt_menu_type() {
		return int_menu_type;
	}
	public void setInt_menu_type(int int_menu_type) {
		this.int_menu_type = int_menu_type;
	}
		
	public String getVc_open_id() {
		return vc_open_id;
	}
	public void setVc_open_id(String vc_open_id) {
		this.vc_open_id = vc_open_id;
	}
	public Date getDt_click_time() {
		return dt_click_time;
	}
	public void setDt_click_time(Date dt_click_time) {
		this.dt_click_time = dt_click_time;
	}
	
	
	
	
}
