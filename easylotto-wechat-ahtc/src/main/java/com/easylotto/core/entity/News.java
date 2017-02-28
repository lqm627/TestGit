package com.easylotto.core.entity;

import java.util.Date;

public class News {

	private Long int_rec_id;
	private Long int_news_type;
	private String vc_title;
	private Date dt_news_date;
	private String cb_content;
	private String vc_pic_url;
	private String vc_mov_url;
	private String vc_mus_url;
	private String vc_digest;
	private String vc_href_url;
	private String vc_name;
	

	public Long getInt_rec_id() {
		return int_rec_id;
	}

	public void setInt_rec_id(Long int_rec_id) {
		this.int_rec_id = int_rec_id;
	}

	public Long getInt_news_type() {
		return int_news_type;
	}

	public void setInt_news_type(Long int_news_type) {
		this.int_news_type = int_news_type;
	}

	public String getVc_title() {
		return vc_title;
	}

	public void setVc_title(String vc_title) {
		this.vc_title = vc_title;
	}

	public Date getDt_news_date() {
		return dt_news_date;
	}

	public void setDt_news_date(Date dt_news_date) {
		this.dt_news_date = dt_news_date;
	}

	public String getCb_content() {
		return cb_content;
	}

	public void setCb_content(String cb_content) {
		this.cb_content = cb_content;
	}

	public String getVc_pic_url() {
		return vc_pic_url;
	}

	public void setVc_pic_url(String vc_pic_url) {
		this.vc_pic_url = vc_pic_url;
	}

	public String getVc_digest() {
		return vc_digest;
	}

	public void setVc_digest(String vc_digest) {
		this.vc_digest = vc_digest;
	}

	public String getVc_href_url() {
		return vc_href_url;
	}

	public void setVc_href_url(String vc_href_url) {
		this.vc_href_url = vc_href_url;
	}

	public String getVc_name() {
		return vc_name;
	}

	public void setVc_name(String vc_name) {
		this.vc_name = vc_name;
	}

	public String getVc_mov_url() {
		return vc_mov_url;
	}

	public void setVc_mov_url(String vc_mov_url) {
		this.vc_mov_url = vc_mov_url;
	}

	public String getVc_mus_url() {
		return vc_mus_url;
	}

	public void setVc_mus_url(String vc_mus_url) {
		this.vc_mus_url = vc_mus_url;
	}
	
	
	

}
