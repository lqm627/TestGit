package com.easylotto.core.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.PickToolsLog;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.util.FieldUtil;

@Repository
public class PickToolsLogDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String getPick(PickToolsLog pickToolsLog) {
		try{
			String sql = "SELECT t.VC_CODE_CONTENT from ecp_pick_tools_log t WHERE t.INT_ACCOUNT_ID="
					+ pickToolsLog.getInt_account_id() + " and t.INT_LOTTERY_TYPE=" + pickToolsLog.getInt_lottery_type()
					+ " and t.INT_PICK_TYPE= " + pickToolsLog.getInt_pick_type();
			if(pickToolsLog.getInt_lottery_type()==11){
				sql+=" AND (UNIX_TIMESTAMP(NOW()) - UNIX_TIMESTAMP(t.DT_ENTRY_TIME))<600";
			}else{
				sql+=" and t.VC_TERM='"+ pickToolsLog.getVc_term() + "'";
			}
			int pickType = pickToolsLog.getInt_pick_type();
			if (pickType == 1) {
				sql += " and t.VC_NAME='" + pickToolsLog.getVc_name() + "'";
			} else if (pickType == 2) {
				sql += " and t.VC_BIRTHDAY='" + pickToolsLog.getVc_birthday() + "'";
			} else if (pickType == 3) {
				sql += " and t.INT_CONSTELLATION_TYPE=" + pickToolsLog.getInt_constellation_type();
			}
			sql+="  LIMIT 0,1";
			String result = jdbcTemplate.queryForObject(sql, String.class);
			return result;
		}catch(EmptyResultDataAccessException e){
			return null;
		}


	}
	
	public void insertPickToolsLog(final PickToolsLog pickToolsLog){
		
		 jdbcTemplate.update("insert into ecp_pick_tools_log (INT_ACCOUNT_ID,INT_PICK_TYPE,VC_TERM,VC_CODE_CONTENT,INT_LOTTERY_TYPE,INT_CONSTELLATION_TYPE,VC_NAME,VC_BIRTHDAY,DT_ENTRY_TIME) values(?,?,?,?,?,?,?,?,now())",   
	                new PreparedStatementSetter(){  
	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setLong(1,pickToolsLog.getInt_account_id()); 
	                        ps.setInt(2,pickToolsLog.getInt_pick_type());
	                        ps.setString(3, pickToolsLog.getVc_term());  
	                        ps.setString(4, pickToolsLog.getVc_code_content());
	                        ps.setInt(5, pickToolsLog.getInt_lottery_type());
	                        ps.setInt(6, pickToolsLog.getInt_constellation_type());
	                        ps.setString(7, pickToolsLog.getVc_name());
	                        ps.setString(8,pickToolsLog.getVc_birthday());
	                    }

			
	        }); 
	}

}
