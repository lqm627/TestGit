package com.easylotto.core.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserInfo;

/**
 * @源文件：EcpEcardDAO.java
 * @日 期：2012-5-30
 * @内 容：个人信息管理服务 类
 * @author huangjun
 */
@Repository
public class EcpUserDAO extends BaseDao<EcpUser> {

	/**
	 * 注册。</br>
	 * </br>
	 * 
	 * 更新：添加字段：vc_from 的 insert。-----
	 * <a href="http://wpa.qq.com/msgrd?V=1&uin=245690288&Site=OK&Menu=yes">MAS
	 * </a> BY 2014-5-16。
	 */
	public long save(final EcpUser ecpUser) {
		final String sql = "insert into ecp_user(vc_account,vc_password, int_acc_type,int_acc_status, int_gen_type, dt_reg_date, vc_from)"
				+ " values(?,?,?,0,?,now(),?) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql,
						new String[] { "vc_account", "vc_password", "int_acc_type", "int_gen_type", "vc_from" });
				ps.setString(1, ecpUser.getVc_account());
				ps.setString(2, ecpUser.getVc_password());
				ps.setLong(3, ecpUser.getInt_acc_type());
				ps.setLong(4, ecpUser.getInt_gen_type());
				ps.setString(5, ecpUser.getVc_from());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	/**
	 * 检查帐户是否存在
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public EcpUser isAccount(String vc_account) {
		String sql = " select *from ecp_user where vc_account=? ";
//		Object[] args = { vc_account };
//		return this.queryForObject(sql, new BeanPropertyRowMapper<EcpUser>(EcpUser.class), args);
		return get(getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpUser>(EcpUser.class), new Object[]{vc_account}));
	}

	/**
	 * 检查帐户是否存在
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public EcpUser isAccount_Business(String vc_account) {
		String sql = " select *from ecp_user where vc_account=? and int_acc_type=3";
		Object[] args = { vc_account };
		//return this.queryForObject(sql, new BeanPropertyRowMapper<EcpUser>(EcpUser.class), args);
		return get(getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpUser>(EcpUser.class), args));
	}

	public void updateUserFund(EcpUser ecpUser) {

		String sql = " update ecp_user set DEC_PREEZE_AMOUNT=?,DEC_PAYPAL_AMOUNT=?,DEC_NOPAYPAL_AMOUNT=?,INT_USER_SCORE=?,INT_ACC_STATUS=?,DEC_PRESENT_AMOUNT=? where INT_ACCOUNT_ID=?  ";
		Object[] args = new Object[] { ecpUser.getDec_preeze_amount(), ecpUser.getDec_paypal_amount(),
				ecpUser.getDec_nopaypal_amount(), ecpUser.getInt_user_score(), ecpUser.getInt_acc_status(),
				ecpUser.getDec_present_amount(), ecpUser.getInt_account_id() };
		getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 修改彩豆账户
	 */
	public void updateUserFundBean(EcpUser ecpUser){
		String sql = "update ecp_user set dec_lottery_bean=? where INT_ACCOUNT_ID=?";
		getJdbcTemplate().update(sql, new Object[]{ecpUser.getDec_lottery_bean(), ecpUser.getInt_account_id()});
	}
	
	/**
	 * 查询彩豆账户余额
	 */
	public EcpUser findBeanCount(String int_account_id){
		String sql = "select t.dec_lottery_bean from ecp_user t where t.int_account_id=?";
		return get(getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpUser>(EcpUser.class), new Object[]{int_account_id}));
	}

	/**
	 * @修改用户密码
	 * @param ecpEcard
	 */
	public void updatePsw(long account, String pwd) {
		String sql = " update ECP_User set vc_password =? where int_account_id=? ";
		Object[] args = new Object[] { pwd, account };
		getJdbcTemplate().update(sql, args);
	}

	/**
	 * @修改用户密码
	 * @param ecpEcard
	 */
	public void updatePayPsw(long account, String pwd) {
		String sql = " update ECP_User set vc_pay_password =? where int_account_id=? ";
		Object[] args = new Object[] { pwd, account };
		getJdbcTemplate().update(sql, args);
	}

	/**
	 * 更新账户信息
	 * 
	 * @param ecpUser
	 */
	public void update(EcpUser ecpUser) {
		String sql = "update ecp_user u" + " set u.dec_nopaypal_amount = ?," + "u.dec_paypal_amount   = ?,"
				+ "u.dec_preeze_amount   = ?," + "u.dec_present_amount  = ?," + "u.dt_last_time        = ?,"
				+ "u.int_acc_status      = ?," + "u.int_acc_type        = ?," + "u.int_gen_type        = ?,"
				+ "u.int_user_score      = ?," + "u.vc_last_ip          = ?," + "u.vc_pay_password     = ?"
				+ " where int_account_id = ? ";
		Object[] args = new Object[] { ecpUser.getDec_nopaypal_amount(), ecpUser.getDec_paypal_amount(),
				ecpUser.getDec_preeze_amount(), ecpUser.getDec_present_amount(), ecpUser.getDt_last_time(),
				ecpUser.getInt_acc_status(), ecpUser.getInt_acc_type(), ecpUser.getInt_gen_type(),
				ecpUser.getInt_user_score(), ecpUser.getVc_last_ip(), ecpUser.getVc_pay_password(),
				ecpUser.getInt_account_id() };
		getJdbcTemplate().update(sql, args);
	}

	/**
	 * 
	 * @param lastIp
	 *            最后IP
	 * @param account_id
	 *            用户ID
	 */
	public void updateLastInfo(String lastIp, Long account_id) {
		String sql = "update ecp_user u" + " set u.dt_last_time = now(),u.vc_last_ip=? where int_account_id = ? ";
		Object[] args = new Object[] { lastIp, account_id };
		getJdbcTemplate().update(sql, args);
	}

	/**
	 * 根据ID查找用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public EcpUser findById(long int_account_id) {
		String sql = "select *from ecp_user where  int_account_id=?";
		Object[] args = { int_account_id };
		//return this.queryForObject(sql, new BeanPropertyRowMapper<EcpUser>(EcpUser.class), args);
		return get(getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpUser>(EcpUser.class), args));
	}

	/***
	 * 
	 * @param accountId
	 * @param amount
	 */
	public void updateBalance(long accountId, double amount) {
		String sql = " update ecp_user set dec_paypal_amount=dec_paypal_amount+? where INT_ACCOUNT_ID=?  ";
		Object[] args = new Object[] { amount, accountId };
		getJdbcTemplate().update(sql, args);
	}

	/***
	 * 
	 * @param accountId
	 * @param amount
	 */
	public void updateFund(long accountId, double payPalAmount, double noPayPalAmount, double presentAmount,
			long pointAmount, double freezeAmount) {
		String sql = " update ecp_user set dec_paypal_amount=?, dec_nopaypal_amount=?, dec_present_amount=?, int_user_score=?, dec_preeze_amount=? where INT_ACCOUNT_ID=?  ";
		Object[] args = new Object[] { payPalAmount, noPayPalAmount, presentAmount, pointAmount, freezeAmount,
				accountId };
		getJdbcTemplate().update(sql, args);
	}

	public void updateFund(long accountId, Double payPalAmount, Double noPayPalAmount, Double presentAmount,
			Long pointAmount, Double freezeAmount) {
		String sql = " update ecp_user set INT_ACCOUNT_ID=" + accountId;
		if (payPalAmount != null)
			sql += ", dec_paypal_amount=" + payPalAmount;
		if (noPayPalAmount != null)
			sql += ", dec_nopaypal_amount=" + noPayPalAmount;
		if (presentAmount != null)
			sql += ", dec_present_amount=" + presentAmount;
		if (pointAmount != null)
			sql += ", int_user_score=" + pointAmount;
		if (freezeAmount != null)
			sql += ", dec_preeze_amount=" + freezeAmount;
		sql += " where INT_ACCOUNT_ID=" + accountId;

		getJdbcTemplate().update(sql);
	}

	public void updateCharge(long accountId, double payPalAmount, double noPayPalAmount) {
		String sql = " update ecp_user set dec_paypal_amount=dec_paypal_amount+?,dec_nopaypal_amount=dec_nopaypal_amount+? where INT_ACCOUNT_ID=?  ";
		Object[] args = new Object[] { payPalAmount, noPayPalAmount, accountId };
		getJdbcTemplate().update(sql, args);
	}

	/**
	 * 彩民充值：更新用户表
	 * 
	 * @param userMobile
	 * @param chargeAmount
	 * @param presentAmount
	 */
	public void updateUserFund(String userMobile, double chargeAmount, double presentAmount) {
		// DEC_PRESENT_AMOUNT-红包 DEC_NOPAYPAL_AMOUNT-预存款
		String sql = " update ecp_user set dec_present_amount=dec_present_amount+? "
				+ ", dec_nopaypal_amount=dec_nopaypal_amount+? where VC_ACCOUNT=? ";
		Object[] args = new Object[] { presentAmount, chargeAmount, userMobile };
		getJdbcTemplate().update(sql, args);
	}

	/**
	 * 保存或更新战绩等级
	 * 
	 * @param int_account_id
	 * @param level
	 */
	public void savaOrUpdateRecordLevel(int int_account_id, int level, int id) {
		Integer old_level = findUserRecordLevel(int_account_id);
		if (null == old_level) {
			final String sql = "insert into ecp_user_record_level(int_account_id,int_level) values(?,?) ";
			getJdbcTemplate().update(sql, new Object[] { int_account_id, level });
		} else {
			String sql = " update ecp_user_record_level set int_level=int_level+? where int_account_id=? ";
			getJdbcTemplate().update(sql, new Object[] { level, int_account_id });
		}

		final String sql = "insert into ecp_user_record_level_log (int_account_id,INT_ORDER_NO) values(?,?) ";
		getJdbcTemplate().update(sql, new Object[] { int_account_id, id });
	}

	/**
	 * 获取用户战绩等级
	 */
	public Integer findUserRecordLevel(int int_account_id) {
		String sql = "select model.int_level from ecp_user_record_level model where model.int_account_id=? ";
		Object[] args = { int_account_id };
		List<Map<String, Object>> i = getJdbcTemplate().queryForList(sql, args);
		if (null != i && i.size() > 0) {
			Map<String, Object> bena = i.get(0);
			return Integer.valueOf(bena.get("int_level").toString());
		}
		return null;
	}

	/**
	 * 获取战绩等级列表
	 */
	public List<Map<String, Object>> findRecordLevel(int maxLength) {
		String sql = "select model.int_level, model.int_account_id, euser.VC_ACCOUNT VC_ACCOUNT from ecp_user_record_level model inner join "
				+ " ecp_user euser on euser.int_account_id = model.int_account_id  where 1=1 order by model.int_level desc limit ? ";
		Object[] args = { maxLength };
		return getJdbcTemplate().queryForList(sql, args);
	}
	
}
