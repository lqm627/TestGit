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
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.PushSubscribeMsgLog;
import com.easylotto.core.entity.SubscribeLog;
import com.easylotto.core.util.FieldUtil;

@Repository
public class PushSubscribeMsgDao extends BaseDao<EcpLotteryOpenResult> {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public List<SubscribeLog> getSubscriber(int lotteryType,int type){
		String sql="select t.*,u.VC_OPEN_ID FROM ecp_subscribe_log t LEFT JOIN ecp_user_info u on u.INT_ACCOUNT_ID=t.INT_ACCOUNT_ID where t.INT_LOTTERY_TYPE="+lotteryType+" and t.DT_END_TIME>=NOW() and u.INT_WECHAT_TYPE = "+type+"  GROUP BY t.INT_ACCOUNT_ID";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<SubscribeLog>(SubscribeLog.class));
	}
	
	public String getSubscriberState(Long membetId,int type){
		try{
			String sql="SELECT SUBSTR(timediff(NOW(), t.DT_CLICK_TIME),1) nowtime FROM ecp_user_info u LEFT JOIN 	ecp_menu_click_log t on u.INT_ACCOUNT_ID=t.INT_ACCOUNT_ID WHERE t.INT_ACCOUNT_ID = "+membetId+" AND u.INT_WECHAT_TYPE = "+type+" ORDER BY t.DT_CLICK_TIME DESC LIMIT 0,1";
			String result=jdbcTemplate.queryForObject(sql, String.class);
			return result;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
		
	}
	
	public EcpLotteryOpenResult getLastOpenResultByPlaytype(int lotteryType){
		String sql="select * from ecp_lottery_open_result where INT_LOTTERY_TYPE = "+lotteryType+" and VC_ADUT_NAME IS NOT NULL ORDER BY DT_OPEN_TIME DESC LIMIT 0,1";
		return get(jdbcTemplate.query(sql, new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class)));
	}
	
	public void insertPushSubscribeMsgLog(final PushSubscribeMsgLog pushSubscribeMsgLog){
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				try {
					return FieldUtil.buildInsert(con, pushSubscribeMsgLog);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}
}
