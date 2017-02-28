package com.easylotto.core.entity;
import java.util.Date;

import javax.persistence.Table;

/**
 * @源文件：EcpLotteryOpenResult.java
 * @内 容：彩期表实体类
 * @日 期：2012-06-05
 * @author huangjun
 */
@Table(name="ecp_rich_lottery")
public class EcpRichLottery {
	/**
	 * 主键自增：INT_REC_ID
	 */
	private Long int_rec_id;

	/**
	 * 期数 VC_TERM
	 */
	private String vc_term;

	/**
	 * 彩种编号 INT_LOTTERY_TYPE
	 */
	private Integer int_lottery_type;

	/**
	 * 开始日期 DT_BEGIN_TIME
	 */
	private Date dt_begin_time;

	/**
	 * 结束日期 DT_DEADLINE
	 */
	private Date dt_deadline;

	/**
	 * 开奖日期 DT_OPEN_TIME
	 */
	private Date dt_open_time;

	/**
	 * 终端结束时间 DT_TERMINAL_DEADLINE
	 */
	private Date dt_terminal_deadline;

	/**
	 * 终端开售时间 DT_TERMINAL_BEGINTIME
	 */
	private Date dt_terminal_begintime;

	/**
	 * 录入人VC_INPUT_NAME
	 */
	private String vc_input_name;

	/**
	 * 录入时间 DT_INPUT_TIME
	 */
	private Date dt_input_time;

	/**
	 * 审核人 VC_ADUT_NAME
	 */
	private String vc_adut_name;

	/**
	 * 审核时间 DT_ADUT_TIME
	 */
	private Date dt_adut_time;
	
	private Date sysdate;

	public Long getInt_rec_id() {
		return int_rec_id;
	}

	public void setInt_rec_id(Long int_rec_id) {
		this.int_rec_id = int_rec_id;
	}

	public String getVc_term() {
		return vc_term;
	}

	public void setVc_term(String vc_term) {
		this.vc_term = vc_term;
	}

	public Integer getInt_lottery_type() {
		return int_lottery_type;
	}

	public void setInt_lottery_type(Integer int_lottery_type) {
		this.int_lottery_type = int_lottery_type;
	}

	public Date getDt_begin_time() {
		return dt_begin_time;
	}

	public void setDt_begin_time(Date dt_begin_time) {
		this.dt_begin_time = dt_begin_time;
	}

	public Date getDt_deadline() {
		return dt_deadline;
	}

	public void setDt_deadline(Date dt_deadline) {
		this.dt_deadline = dt_deadline;
	}

	public Date getDt_open_time() {
		return dt_open_time;
	}

	public void setDt_open_time(Date dt_open_time) {
		this.dt_open_time = dt_open_time;
	}

	public Date getDt_terminal_deadline() {
		return dt_terminal_deadline;
	}

	public void setDt_terminal_deadline(Date dt_terminal_deadline) {
		this.dt_terminal_deadline = dt_terminal_deadline;
	}

	public Date getDt_terminal_begintime() {
		return dt_terminal_begintime;
	}

	public void setDt_terminal_begintime(Date dt_terminal_begintime) {
		this.dt_terminal_begintime = dt_terminal_begintime;
	}

	public String getVc_input_name() {
		return vc_input_name;
	}

	public void setVc_input_name(String vc_input_name) {
		this.vc_input_name = vc_input_name;
	}

	public Date getDt_input_time() {
		return dt_input_time;
	}

	public void setDt_input_time(Date dt_input_time) {
		this.dt_input_time = dt_input_time;
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

	public Date getSysdate() {
		return sysdate;
	}

	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}
}
