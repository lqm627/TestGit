package com.easylotto.core.entity;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @源文件：EcpLotteryOpenResult.java
 * @内 容：开奖公告表(ecp_lottery_open_result)实体
 * @日 期：2012-06-05
 * @author huangjun
 */

@Table(name = "Ecp_Lottery_Open_Result")
public class EcpLotteryOpenResult {

	/**
	 * 主键，自增:INT_REC_ID
	 */
	private Long int_rec_id;

	/**
	 * 彩种类型INT_LOTTERY_TYPE
	 */
	private Integer int_lottery_type;

	/**
	 * 彩期VC_TERM
	 */
	private String vc_term;

	/**
	 * 开奖时间DT_OPEN_TIME
	 */
	private Date dt_open_time;

	/**
	 * 录入时间DT_CREATE_TIME
	 */
	private Date dt_create_time;

	/**
	 * 开奖号码VC_CODE_CONTENT
	 */
	private String vc_code_content;

	/**
	 * 奖级说明VC_PRIZE_CONTENT
	 */
	private String vc_prize_content;

	/**
	 * 奖池VC_POOL_AWARD
	 */
	private String vc_pool_award = "0";

	/**
	 * 审核人VC_ADUT_NAME
	 */
	private String vc_adut_name;

	/**
	 * 审核时间DT_ADUT_TIME
	 */
	private Date dt_adut_time;

	/**
	 * 录入人VC_INPUT_NAME
	 */
	private String vc_input_name;
	
	public Long getInt_rec_id() {
		return int_rec_id;
	}

	public void setInt_rec_id(Long int_rec_id) {
		this.int_rec_id = int_rec_id;
	}

	public Integer getInt_lottery_type() {
		return int_lottery_type;
	}

	public void setInt_lottery_type(Integer int_lottery_type) {
		this.int_lottery_type = int_lottery_type;
	}

	public String getVc_term() {
		return vc_term;
	}

	public void setVc_term(String vc_term) {
		this.vc_term = vc_term;
	}

	public Date getDt_open_time() {
		return dt_open_time;
	}

	public void setDt_open_time(Date dt_open_time) {
		this.dt_open_time = dt_open_time;
	}

	public Date getDt_create_time() {
		return dt_create_time;
	}

	public void setDt_create_time(Date dt_create_time) {
		this.dt_create_time = dt_create_time;
	}

	public String getVc_code_content() {
		return vc_code_content;
	}

	public void setVc_code_content(String vc_code_content) {
		this.vc_code_content = vc_code_content;
	}

	public String getVc_prize_content() {
		return vc_prize_content;
	}

	public void setVc_prize_content(String vc_prize_content) {
		this.vc_prize_content = vc_prize_content;
	}

	public String getVc_pool_award() {
		return vc_pool_award;
	}

	public void setVc_pool_award(String vc_pool_award) {
		this.vc_pool_award = vc_pool_award;
	}

	public String getVc_adut_name() {
		return vc_adut_name;
	}

	public void setVc_adut_name(String vc_adut_name) {
		this.vc_adut_name = vc_adut_name;
	}

	public Date getDt_adut_time() {
		return dt_adut_time;
	}

	public void setDt_adut_time(Date dt_adut_time) {
		this.dt_adut_time = dt_adut_time;
	}

	public String getVc_input_name() {
		return vc_input_name;
	}

	public void setVc_input_name(String vc_input_name) {
		this.vc_input_name = vc_input_name;
	}


}
