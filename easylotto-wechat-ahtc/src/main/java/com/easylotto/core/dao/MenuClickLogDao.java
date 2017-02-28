package com.easylotto.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.EcpMenuClickLog;
import com.easylotto.core.util.FieldUtil;

@Repository
public class MenuClickLogDao extends BaseDao<EcpMenuClickLog> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addClickLog(final EcpMenuClickLog instance){
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				try {
					return FieldUtil.buildInsert(con, instance);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}

}
