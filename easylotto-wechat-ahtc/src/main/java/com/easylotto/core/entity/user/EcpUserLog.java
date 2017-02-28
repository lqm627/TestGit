package com.easylotto.core.entity.user;

import java.util.Date;

import javax.persistence.Table;

/**
 * @源文件：EcpUserLog.java
 * @内 容：账户操作日志信息实体类
 * @日 期：2012-06-04
 * @author huangjun
 */
@Table(name="ecp_user_log")
public class EcpUserLog {

	/**
	 * 主键，INT_ID
	 */
	private Long int_rec_id;

	/**
	 * 用户ID:INT_ACCOUNT
	 */
	private Long int_account;

	/**
	 * 操作类型：INT_OPT_TYPE
	 */
	private Integer int_opt_type;

	/**
	 * 操作时间：DT_OPT_TIME
	 */
	private Date dt_opt_time;

	/**
	 * 操作方式：INT_OPT_METHOD 1 网站
	 */
	private Integer int_opt_method;

	/**
	 * 操作人：VC_OPT_NAME
	 */
	private String vc_opt_name;

	/**
	 * 操作前信息：OLD_INFO
	 */
	private String vc_old_info;

	/**
	 * 操作后信息：NEW_INFO
	 */
	private String vc_new_info;

	public Long getInt_rec_id() {
		return int_rec_id;
	}

	public void setInt_rec_id(Long int_rec_id) {
		this.int_rec_id = int_rec_id;
	}

	public Long getInt_account() {
		return int_account;
	}

	public void setInt_account(Long int_account) {
		this.int_account = int_account;
	}

	public Integer getInt_opt_type() {
		return int_opt_type;
	}

	public void setInt_opt_type(Integer int_opt_type) {
		this.int_opt_type = int_opt_type;
	}

	public Date getDt_opt_time() {
		return dt_opt_time;
	}

	public void setDt_opt_time(Date dt_opt_time) {
		this.dt_opt_time = dt_opt_time;
	}

	public Integer getInt_opt_method() {
		return int_opt_method;
	}

	public void setInt_opt_method(Integer int_opt_method) {
		this.int_opt_method = int_opt_method;
	}

	public String getVc_opt_name() {
		return vc_opt_name;
	}

	public void setVc_opt_name(String vc_opt_name) {
		this.vc_opt_name = vc_opt_name;
	}

	public String getVc_old_info() {
		return vc_old_info;
	}

	public void setVc_old_info(String vc_old_info) {
		this.vc_old_info = vc_old_info;
	}

	public String getVc_new_info() {
		return vc_new_info;
	}

	public void setVc_new_info(String vc_new_info) {
		this.vc_new_info = vc_new_info;
	}

}
