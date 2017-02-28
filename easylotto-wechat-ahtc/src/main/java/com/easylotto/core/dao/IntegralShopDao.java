package com.easylotto.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.EcpRotarytableLog;
import com.easylotto.core.entity.SubscribeLog;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.util.FieldUtil;

@Repository
public class IntegralShopDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(final SubscribeLog subscribeLog){
		 jdbcTemplate.update("insert into ecp_subscribe_log (INT_ACCOUNT_ID,INT_LOTTERY_TYPE,DT_CREATE_TIME,DT_END_TIME) values(?,?,NOW(),date_add(NOW(), interval 1 month))",   
	                new PreparedStatementSetter(){  
	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setLong(1,subscribeLog.getInt_account_id()); 
	                        ps.setLong(2,subscribeLog.getInt_lottery_type()); 
	                    }

			
	        });  
	}
	
	public int getBeanBalance(Long memberId){
		String sql="SELECT t.DEC_LOTTERY_BEAN from ecp_user t WHERE t.INT_ACCOUNT_ID="+memberId;
		int result=jdbcTemplate.queryForObject(sql, int.class);
		return result;
	}
	
	public int getSubscribeState(Long memberId,int lotteryType){
		String sql="select COUNT(*) FROM ecp_subscribe_log t LEFT JOIN ecp_user_info u on u.INT_ACCOUNT_ID=t.INT_ACCOUNT_ID where t.INT_LOTTERY_TYPE="+lotteryType+" and t.DT_END_TIME>=NOW() and t.INT_ACCOUNT_ID="+memberId;
		int result=jdbcTemplate.queryForObject(sql, int.class);
		return result;
	}
	
	public void updateUser(final int lotteryBean,final Long memberId){
		 jdbcTemplate.update("update ecp_user set DEC_LOTTERY_BEAN=? where INT_ACCOUNT_ID=?",   
	                new PreparedStatementSetter(){  	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setInt(1,lotteryBean);  
	                        ps.setLong(2,memberId); 
	                    }

			
	        });   
	}
	public void insertUserFundLog(final EcpUserFundLog ecpUserFundLog){
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				try {
					return FieldUtil.buildInsert(con, ecpUserFundLog);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}
	
	public void insertRotarytable(final EcpRotarytableLog rotarytableLog){
		 jdbcTemplate.update("insert into ecp_rotarytable_log (INT_ACCOUNT_ID,INT_AMOUNT,DT_ENTRY_TIME) values(?,?,NOW())",   
	                new PreparedStatementSetter(){  
	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setLong(1,rotarytableLog.getInt_account_id()); 
	                        ps.setInt(2,rotarytableLog.getInt_amount()); 
	                    }

			
	        });  
	}
	
	public int getRotarytableLog(Long memberId){
		String sql="SELECT COUNT(t.INT_REC_ID) FROM ecp_rotarytable_log t WHERE date(t.DT_ENTRY_TIME)=curdate() and t.INT_ACCOUNT_ID="+memberId;
		int result=jdbcTemplate.queryForObject(sql, int.class);
		return result;
	}

}
