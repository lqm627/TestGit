package com.easylotto.core.entity.user;

import java.util.Date;

public class AbstractEcpUserInfo implements java.io.Serializable{


	// Fields

	private Long int_account_id; //用户ID 
	private String vc_name;		 //用户名称 
	private String vc_nickname;  //微信端nickName
	private String vc_sex;
	private Date dt_birthdate;
	private String vc_user_address;		//地址
	private String vc_email;			//邮箱
	private String vc_mobile;			//电话
	private String vc_memo;				//描述
	private String vc_recom_name;
	private Date dt_recom_time;
	private Long int_verify;
	private Long int_safe;
	private Long int_recom_type;
	private String vc_card_no;			//身份证号 或 军人证号
	private Long int_award_notify;		//是否需要中奖通知
	private Long int_bank_type;
	private String vc_open_bank;		//开户银行
	private String vc_bank_account;		//开户账号
	private String vc_band_province;	//银行所在省份
	private String vc_band_city;		//银行所在城市
	private String vc_psw_question;		//密保问题
	private String vc_psw_answer;		//密保答案
	private String vc_user_province;	//所在省份
	private String vc_user_city;		//所在城市
	private String vc_zip;				//邮政编码
	
	private String vc_open_id;			//微信用户openId
	private String vc_language;			//用户语言
	private String vc_headimgurl;		//用户头像URL
	private Date dt_sub_time;			//关注时间
	private Integer int_sub_status;		//用户状态 0:表示已取消关注  1：表示关注中
	private String vc_os_name;          //用户端操作系统名称
	private Integer int_wechat_type;    //公众号类型 NULL订阅号 1服务号
	// Constructors

	/** default constructor */
	public AbstractEcpUserInfo() {
	}

	/** minimal constructor */
	public AbstractEcpUserInfo(Long intAccountId) {
		this.int_account_id = intAccountId;
	}

	/** full constructor 
	 * @param vc_psw_question 
	 * @param int_wechat_type 
	 * @param vc_os_name 
	 * @param int_wechat_type */
	public AbstractEcpUserInfo(Long int_account_id, String vc_name,String vc_nickname,
			String vc_sex, Date dt_birthdate, String vc_user_address,
			String vc_email, String vc_mobile, String vc_memo,
			String vc_recom_name, Date dt_recom_time, Long int_verify,
			Long int_safe, Long int_recom_type, String vc_card_no,
			Long int_award_notify, Long int_bank_type, String vc_open_bank,
			String vc_bank_account, String vc_band_province,
			String vc_band_city, String vc_psw_question,String vc_psw_answer,
			String vc_user_province, String vc_user_city, String vc_zip,
			String vc_language,String vc_headimgurl,Date dt_sub_time,Integer int_sub_status,String vc_open_id, String vc_os_name, Integer int_wechat_type) {
		this.int_account_id = int_account_id;
		this.vc_name = vc_name;
		this.vc_nickname = vc_nickname;
		this.vc_sex = vc_sex;
		this.dt_birthdate = dt_birthdate;
		this.vc_user_address = vc_user_address;
		this.vc_email = vc_email;
		this.vc_mobile = vc_mobile;
		this.vc_memo = vc_memo;
		this.vc_recom_name = vc_recom_name;
		this.dt_recom_time = dt_recom_time;
		this.int_verify = int_verify;
		this.int_safe = int_safe;
		this.int_recom_type = int_recom_type;
		this.vc_card_no = vc_card_no;
		this.int_award_notify = int_award_notify;
		this.int_bank_type = int_bank_type;
		this.vc_open_bank = vc_open_bank;
		this.vc_bank_account = vc_bank_account;
		this.vc_band_province = vc_band_province;
		this.vc_band_city = vc_band_city;
		this.vc_psw_question = vc_psw_question;
		this.vc_psw_answer = vc_psw_answer;
		this.vc_user_province = vc_user_province;
		this.vc_user_city = vc_user_city;
		this.vc_zip = vc_zip;
		this.vc_language = vc_language;
		this.vc_headimgurl = vc_headimgurl;
		this.dt_sub_time = dt_sub_time;
		this.int_sub_status = int_sub_status;
		this.vc_open_id = vc_open_id;
		this.vc_os_name=vc_os_name;
		this.int_wechat_type=int_wechat_type;
	}

	
	// Property accessors
	
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

	public String getVc_sex() {
		return vc_sex;
	}

	public void setVc_sex(String vc_sex) {
		this.vc_sex = vc_sex;
	}

	public Date getDt_birthdate() {
		return dt_birthdate;
	}

	public void setDt_birthdate(Date dt_birthdate) {
		this.dt_birthdate = dt_birthdate;
	}

	public String getVc_user_address() {
		return vc_user_address;
	}

	public void setVc_user_address(String vc_user_address) {
		this.vc_user_address = vc_user_address;
	}

	public String getVc_email() {
		return vc_email;
	}

	public void setVc_email(String vc_email) {
		this.vc_email = vc_email;
	}

	public String getVc_mobile() {
		return vc_mobile;
	}

	public void setVc_mobile(String vc_mobile) {
		this.vc_mobile = vc_mobile;
	}

	public String getVc_memo() {
		return vc_memo;
	}

	public void setVc_memo(String vc_memo) {
		this.vc_memo = vc_memo;
	}

	public String getVc_recom_name() {
		return vc_recom_name;
	}

	public void setVc_recom_name(String vc_recom_name) {
		this.vc_recom_name = vc_recom_name;
	}

	public Date getDt_recom_time() {
		return dt_recom_time;
	}

	public void setDt_recom_time(Date dt_recom_time) {
		this.dt_recom_time = dt_recom_time;
	}

	public Long getInt_verify() {
		return int_verify;
	}

	public void setInt_verify(Long int_verify) {
		this.int_verify = int_verify;
	}

	public Long getInt_safe() {
		return int_safe;
	}

	public void setInt_safe(Long int_safe) {
		this.int_safe = int_safe;
	}

	public Long getInt_recom_type() {
		return int_recom_type;
	}

	public void setInt_recom_type(Long int_recom_type) {
		this.int_recom_type = int_recom_type;
	}

	public String getVc_card_no() {
		return vc_card_no;
	}

	public void setVc_card_no(String vc_card_no) {
		this.vc_card_no = vc_card_no;
	}

	public Long getInt_award_notify() {
		return int_award_notify;
	}

	public void setInt_award_notify(Long int_award_notify) {
		this.int_award_notify = int_award_notify;
	}

	public Long getInt_bank_type() {
		return int_bank_type;
	}

	public void setInt_bank_type(Long int_bank_type) {
		this.int_bank_type = int_bank_type;
	}

	public String getVc_open_bank() {
		return vc_open_bank;
	}

	public void setVc_open_bank(String vc_open_bank) {
		this.vc_open_bank = vc_open_bank;
	}

	public String getVc_bank_account() {
		return vc_bank_account;
	}

	public void setVc_bank_account(String vc_bank_account) {
		this.vc_bank_account = vc_bank_account;
	}

	public String getVc_band_province() {
		return vc_band_province;
	}

	public void setVc_band_province(String vc_band_province) {
		this.vc_band_province = vc_band_province;
	}

	public String getVc_band_city() {
		return vc_band_city;
	}

	public void setVc_band_city(String vc_band_city) {
		this.vc_band_city = vc_band_city;
	}

	public String getVc_psw_question() {
		return vc_psw_question;
	}

	public void setVc_psw_question(String vc_psw_question) {
		this.vc_psw_question = vc_psw_question;
	}

	public String getVc_psw_answer() {
		return vc_psw_answer;
	}

	public void setVc_psw_answer(String vc_psw_answer) {
		this.vc_psw_answer = vc_psw_answer;
	}

	public String getVc_user_province() {
		return vc_user_province;
	}

	public void setVc_user_province(String vc_user_province) {
		this.vc_user_province = vc_user_province;
	}

	public String getVc_user_city() {
		return vc_user_city;
	}

	public void setVc_user_city(String vc_user_city) {
		this.vc_user_city = vc_user_city;
	}

	public String getVc_zip() {
		return vc_zip;
	}

	public void setVc_zip(String vc_zip) {
		this.vc_zip = vc_zip;
	}

	public String getVc_language() {
		return vc_language;
	}

	public void setVc_language(String vc_language) {
		this.vc_language = vc_language;
	}

	public String getVc_headimgurl() {
		return vc_headimgurl;
	}

	public void setVc_headimgurl(String vc_headimgurl) {
		this.vc_headimgurl = vc_headimgurl;
	}

	public Date getDt_sub_time() {
		return dt_sub_time;
	}

	public void setDt_sub_time(Date dt_sub_time) {
		this.dt_sub_time = dt_sub_time;
	}

	public Integer getInt_sub_status() {
		return int_sub_status;
	}

	public void setInt_sub_status(Integer int_sub_status) {
		this.int_sub_status = int_sub_status;
	}

	public String getVc_open_id() {
		return vc_open_id;
	}

	public void setVc_open_id(String vc_open_id) {
		this.vc_open_id = vc_open_id;
	}

	public String getVc_nickname() {
		return vc_nickname;
	}

	public void setVc_nickname(String vc_nickname) {
		this.vc_nickname = vc_nickname;
	}

	public String getVc_os_name() {
		return vc_os_name;
	}

	public void setVc_os_name(String vc_os_name) {
		this.vc_os_name = vc_os_name;
	}

	public Integer getInt_wechat_type() {
		return int_wechat_type;
	}

	public void setInt_wechat_type(Integer int_wechat_type) {
		this.int_wechat_type = int_wechat_type;
	}
	
	


}
