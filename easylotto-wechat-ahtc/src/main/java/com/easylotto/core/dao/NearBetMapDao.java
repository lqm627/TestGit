package com.easylotto.core.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.BetCenter;
import com.easylotto.core.entity.NearBetInfo;
import com.easylotto.core.entity.News;


@Repository
public class NearBetMapDao extends BaseDao<News> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<NearBetInfo> getNearBetList(String longitude, String latitude, int pageSize) {
		String sql = "SELECT t.*,(POWER(MOD(ABS(t.VC_LONGITUDE - " + longitude
				+ "), 360), 2) + POWER(ABS(t.VC_LATITUDE - " + latitude
				+ "), 2)) AS distance FROM ecp_betting_info t GROUP BY t.VC_ADDRESS ORDER BY distance   LIMIT "
				+ pageSize;
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NearBetInfo>(NearBetInfo.class));
	}
	
	public List<BetCenter> getBetCenterLists(){
		String sql="SELECT t.* from ecp_betting_center t";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<BetCenter>(BetCenter.class));
	}

}
