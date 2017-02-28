package com.easylotto.core.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BaseDao<Model> {

	protected static Logger logger = LoggerFactory.getLogger(BaseDao.class);

	@Autowired
	public JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public Model get(List<Model> list) {
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public Date queryDateTime() {
		return getJdbcTemplate().queryForObject("select current_timestamp();", Date.class);
	}

	/**
	 * 查某条sql能查询出来的数据总数
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public Integer findTotalSize(String sql, Object[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from (").append(sql).append(") alias");
		return getJdbcTemplate().queryForObject(sb.toString(), Integer.class, args);
	}

	/**
	 * 获取数据库当前时间
	 * 
	 * @return
	 */
	public Date getDatebaseNow() {
		String sql = "SELECT NOW()";
		return getJdbcTemplate().queryForObject(sql, Date.class);
	}
}
