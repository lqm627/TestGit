/**
 * 
 */
package com.easylotto.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.ActivityLog;



/**
 * @author CreateName:  UpdateName: 
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see ActivityDataDao
 * @see CreateDate: 2016年6月4日 下午4:47:43 UpdateDate: 2016年6月4日 下午4:47:43
 * @see Copyright 
 * @since JDK1.7.*
 * @version 1.0
 */


@Repository
public class ActivityDataDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public List<ActivityLog> findBy6(){
		List<ActivityLog> list = jdbcTemplate.query("select * from ecp_activity_log where INT_ACTIVITY_ID = 6 and vc_sales_period = 2 and vc_name is not null and (DATE_FORMAT(DT_ENTRY_TIME,'%Y-%m-%d') BETWEEN '2016-09-26' and '2016-10-16') order by DT_ENTRY_TIME ASC ", new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		return list;
	}
	
	
	public List<ActivityLog> findBy(){
		List<ActivityLog> list = jdbcTemplate.query("select * from ecp_activity_log where INT_ACTIVITY_ID = 3 and (DATE_FORMAT(DT_ENTRY_TIME,'%Y-%m-%d') BETWEEN '2016-06-20' and '2016-07-09') order by DT_ENTRY_TIME ASC ", new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		return list;
	}
	
	public List<ActivityLog> findBy1(){
		List<ActivityLog> list = jdbcTemplate.query("select * from ecp_activity_log where INT_ACTIVITY_ID = 1 and (DATE_FORMAT(DT_ENTRY_TIME,'%Y-%m-%d') BETWEEN '2016-08-07' and '2016-08-26') order by DT_ENTRY_TIME ASC LIMIT 60000, 100000 ", new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		return list;
	}
	
	public List<ActivityLog> findBy2(){
		List<ActivityLog> list = jdbcTemplate.query("select * from ecp_activity_log where INT_ACTIVITY_ID = 2 and (DATE_FORMAT(DT_ENTRY_TIME,'%Y-%m-%d') BETWEEN '2016-07-16' and '2016-08-15') order by DT_ENTRY_TIME ASC ", new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		return list;
	}
	
	public List<ActivityLog> findBy29(){
		List<ActivityLog> list = jdbcTemplate.query("SELECT * FROM ecp_activity_log "
				+ "WHERE INT_ACTIVITY_ID = 29 AND (DATE_FORMAT(DT_ENTRY_TIME, '%Y-%m-%d') ='2016-11-08') "
				+ "ORDER BY DT_ENTRY_TIME ASC", new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		return list;
	}
	
	public List<ActivityLog> findBy29(int startNum){
		List<ActivityLog> list = jdbcTemplate.query("SELECT * FROM ecp_activity_log "
				+ "WHERE INT_ACTIVITY_ID = 29 AND (DATE_FORMAT(DT_ENTRY_TIME, '%Y-%m-%d') ='2016-11-08') "
				+ "ORDER BY DT_ENTRY_TIME ASC LIMIT "+startNum+",60000", new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		return list;
	}
	
	public List<ActivityLog> findBy30(){
		List<ActivityLog> list = jdbcTemplate.query("select * from ecp_activity_log where INT_ACTIVITY_ID = 30 and (DATE_FORMAT(DT_ENTRY_TIME,'%Y-%m-%d') BETWEEN '2016-11-25' and '2016-12-15') order by DT_ENTRY_TIME ASC", new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		return list;
	}
	
	public List<ActivityLog> findBy31(){
		List<ActivityLog> list = jdbcTemplate.query("select * from ecp_activity_log where INT_ACTIVITY_ID = 31 and (DATE_FORMAT(DT_ENTRY_TIME,'%Y-%m-%d') BETWEEN '2017-02-03' and '2017-02-17') order by DT_ENTRY_TIME ASC", new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		return list;
	}
	
	
	public List find(String type){
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT DATE_FORMAT(DT_ENTRY_TIME,'%y-%m-%d') ,INT_ACTIVITY_ID, COUNT(1) FROM ecp_activity_log where 1=1 and INT_ACTIVITY_ID =? GROUP BY  DATE_FORMAT(DT_ENTRY_TIME,'%y-%m-%d'), INT_ACTIVITY_ID", new Object[]{type});
		return list;
	} 
	
	public List find2(String type){
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select bean.day, count(1) from (SELECT DATE_FORMAT(DT_ENTRY_TIME,'%y-%m-%d') day ,VC_PHONE, COUNT(1) FROM ecp_activity_log where 1=1 and INT_ACTIVITY_ID =? GROUP BY  DATE_FORMAT(DT_ENTRY_TIME,'%y-%m-%d'), VC_PHONE ) bean where 1=1 GROUP BY bean.day", new Object[]{type});
		return list;
	} 
}
