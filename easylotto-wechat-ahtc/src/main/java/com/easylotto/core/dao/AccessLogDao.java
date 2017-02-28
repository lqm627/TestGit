package com.easylotto.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.util.FieldUtil;

@Repository
public class AccessLogDao extends BaseDao<EcpAccessLog> {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	public void addAccessLog(final EcpAccessLog ecpAccessLog){
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				try {
					return FieldUtil.buildInsert(con, ecpAccessLog);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		
	}
}
