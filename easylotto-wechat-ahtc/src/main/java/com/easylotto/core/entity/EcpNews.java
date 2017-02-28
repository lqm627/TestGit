package com.easylotto.core.entity;


import java.util.Date;

import javax.persistence.Table;

@Table(name = "ecp_news")
public class EcpNews {

	private Long int_rec_id;
	private String vc_lang;
	private Long int_news_type;
	private String vc_title;
	private Long int_is_top;
	private Date dt_news_date;
	private String vc_user_id;
	private Date dt_create_time;
	private Long int_status;
	private String cb_content;
	private Integer int_lottery_type;
	private String vc_website;
	private String vc_digest;
	private String vc_fromurl;
	private Long int_from_type;
	private Long int_red;
	private String vc_pic_url;
	private String vc_mov_url;
	private String vc_mus_url;
	private String vc_href_url;
	private Integer int_wechat_type;

	public Long getInt_rec_id() {
		return int_rec_id;
	}

	public void setInt_rec_id(Long int_rec_id) {
		this.int_rec_id = int_rec_id;
	}

	public String getVc_lang() {
		return vc_lang;
	}

	public void setVc_lang(String vc_lang) {
		this.vc_lang = vc_lang;
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

	public Long getInt_is_top() {
		return int_is_top;
	}

	public void setInt_is_top(Long int_is_top) {
		this.int_is_top = int_is_top;
	}

	public Date getDt_news_date() {
		return dt_news_date;
	}

	public void setDt_news_date(Date dt_news_date) {
		this.dt_news_date = dt_news_date;
	}

	public String getVc_user_id() {
		return vc_user_id;
	}

	public void setVc_user_id(String vc_user_id) {
		this.vc_user_id = vc_user_id;
	}

	public Date getDt_create_time() {
		return dt_create_time;
	}

	public void setDt_create_time(Date dt_create_time) {
		this.dt_create_time = dt_create_time;
	}

	public Long getInt_status() {
		return int_status;
	}

	public void setInt_status(Long int_status) {
		this.int_status = int_status;
	}

	public String getCb_content() {
		return cb_content;
	}

	public void setCb_content(String cb_content) {
		this.cb_content = cb_content;
	}

	public Integer getInt_lottery_type() {
		return int_lottery_type;
	}

	public void setInt_lottery_type(Integer int_lottery_type) {
		this.int_lottery_type = int_lottery_type;
	}

	public String getVc_website() {
		return vc_website;
	}

	public void setVc_website(String vc_website) {
		this.vc_website = vc_website;
	}

	public String getVc_digest() {
		return vc_digest;
	}

	public void setVc_digest(String vc_digest) {
		this.vc_digest = vc_digest;
	}

	public String getVc_fromurl() {
		return vc_fromurl;
	}

	public void setVc_fromurl(String vc_fromurl) {
		this.vc_fromurl = vc_fromurl;
	}
	
	public Long getInt_from_type() {
		return int_from_type;
	}

	public void setInt_from_type(Long int_from_type) {
		this.int_from_type = int_from_type;
	}

	public Long getInt_red() {
		return int_red;
	}

	public void setInt_red(Long int_red) {
		this.int_red = int_red;
	}

	public String getVc_pic_url() {
		return vc_pic_url;
	}

	public void setVc_pic_url(String vc_pic_url) {
		this.vc_pic_url = vc_pic_url;
	}

	public String getVc_href_url() {
		return vc_href_url;
	}

	public void setVc_href_url(String vc_href_url) {
		this.vc_href_url = vc_href_url;
	}

	public Integer getInt_wechat_type() {
		return int_wechat_type;
	}

	public void setInt_wechat_type(Integer int_wechat_type) {
		this.int_wechat_type = int_wechat_type;
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
