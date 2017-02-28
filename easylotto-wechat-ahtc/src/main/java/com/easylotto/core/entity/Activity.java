package com.easylotto.core.entity;

import java.util.Date;

public class Activity {
	private Long int_activity_id;
	private String vc_activity_title;
	private String vc_activity_content;
	private String vc_activity_image;
	private String vc_activity_href_url;
	private Integer int_activity_belong;
	private Date dt_create_time;
	private Date dt_begin_time;
	private Date de_end_time;

	
	public Long getInt_activity_id() {
		return int_activity_id;
	}
	public void setInt_activity_id(Long int_activity_id) {
		this.int_activity_id = int_activity_id;
	}

	public String getVc_activity_title() {
		return vc_activity_title;
	}
	public void setVc_activity_title(String vc_activity_title) {
		this.vc_activity_title = vc_activity_title;
	}
	public String getVc_activity_content() {
		return vc_activity_content;
	}
	public void setVc_activity_content(String vc_activity_content) {
		this.vc_activity_content = vc_activity_content;
	}
	public String getVc_activity_image() {
		return vc_activity_image;
	}
	public void setVc_activity_image(String vc_activity_image) {
		this.vc_activity_image = vc_activity_image;
	}
	public Integer getInt_activity_belong() {
		return int_activity_belong;
	}
	public void setInt_activity_belong(Integer int_activity_belong) {
		this.int_activity_belong = int_activity_belong;
	}
	public Date getDt_create_time() {
		return dt_create_time;
	}
	public void setDt_create_time(Date dt_create_time) {
		this.dt_create_time = dt_create_time;
	}
	public Date getDt_begin_time() {
		return dt_begin_time;
	}
	public void setDt_begin_time(Date dt_begin_time) {
		this.dt_begin_time = dt_begin_time;
	}
	public Date getDe_end_time() {
		return de_end_time;
	}
	public void setDe_end_time(Date de_end_time) {
		this.de_end_time = de_end_time;
	}
	public String getVc_activity_href_url() {
		return vc_activity_href_url;
	}
	public void setVc_activity_href_url(String vc_activity_href_url) {
		this.vc_activity_href_url = vc_activity_href_url;
	}
	

}
