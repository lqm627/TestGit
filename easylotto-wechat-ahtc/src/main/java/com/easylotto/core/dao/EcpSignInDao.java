/**
 * 
 */
package com.easylotto.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.util.FieldUtil;


/**
 * @author CreateName:  UpdateName: 
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see EcpSignInDao
 * @see CreateDate: 2016年6月4日 下午4:47:43 UpdateDate: 2016年6月4日 下午4:47:43
 * @see Copyright 
 * @since JDK1.7.*
 * @version 1.0
 */

@Repository
public class EcpSignInDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(String openId, String nickName, int signDays, Long intAccountId){
		jdbcTemplate.update("insert into ecp_sign_in (vc_open_id,vc_name,dt_sign_time,int_sign_days,int_account_id) values('"+openId+"','"+nickName+"',NOW(),"+signDays+","+intAccountId+")");
	}
	
	
	public EcpSignIn findById(String openId){
		
		String sql = "select * from ecp_sign_in where vc_open_id=?";
		
		Object[] args ={openId};
		
		List<EcpSignIn> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<EcpSignIn>(EcpSignIn.class), args);
	
		if(list!=null && list.size()>0){
			EcpSignIn ecpSignIn = list.get(0);
			return ecpSignIn;
		}else{
			return null;
		}
		
	}
	
	public void update(Object[] args){
		String updateSQL1="update ecp_sign_in set dt_sign_time=now(),int_sign_days=? where vc_open_id=?";
		update(updateSQL1, args);
	}
	
	public void update(String sql, Object[] obj){
		jdbcTemplate.update(sql, obj);
	}
	
	public void insert(final EcpUserFundLog ecpUserFundLog){
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					return FieldUtil.buildInsert(ecpUserFundLog, conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	
	
	
	public Double query(String sql, Object[] obj){
		Double balance = jdbcTemplate.queryForObject(sql, obj, Double.class);
		return balance;
	}
}
