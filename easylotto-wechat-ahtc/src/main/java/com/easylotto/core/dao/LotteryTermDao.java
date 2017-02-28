package com.easylotto.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.EcpRichLottery;
import com.easylotto.core.util.FieldUtil;

@Repository
public class LotteryTermDao extends BaseDao<EcpRichLottery> {

	
	public EcpLotteryOpenResult findByTermAndType(String term, Integer lotteryType) {
		String sql = "select model.* from ecp_lottery_open_result model where model.vc_term=? and model.int_lottery_type=?";
		List<EcpLotteryOpenResult> list = getJdbcTemplate().query(sql, new Object[]{term, lotteryType}, new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class));
		if(null != list && 0 < list.size()) return list.get(0);
		return null;
	}
	
	public EcpRichLottery findByExample(String term, Integer lotteryType) {
		String sql = "select model.* from ecp_rich_lottery model where model.vc_adut_name is not null and model.dt_adut_time is not null and model.vc_term=? and model.int_lottery_type=?";
		List<EcpRichLottery> list = getJdbcTemplate().query(sql, new Object[]{term, lotteryType}, new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class));
		if(null != list && 0 < list.size()) return list.get(0);
		return null;
	}  
	
	
	
	public void save(final EcpLotteryOpenResult bean){
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				try {
					return FieldUtil.buildInsert(con, bean);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}
}
