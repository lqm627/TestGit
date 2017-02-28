package com.easylotto.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.ContentData;
@Repository
public class ContentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<ContentData> findList(String wechat_type,String type){
		String sql = "select * from wx_content where int_wechat_type = ? and int_category = ? and STATE=1 ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ContentData>(ContentData.class),new Object[]{wechat_type,type});
	}
	public ContentData findDetail(String id){
		String sql = "select * from wx_content where id= ? and STATE=1 ";
		List<ContentData> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ContentData>(ContentData.class), new Object[]{id});
		if(null != list && 0 < list.size()){
			return list.get(0);
		}
		return null;
	}
	public Integer countContent(String type){
		String sql = "select count(1) from wx_content where int_wechat_type = ? ";
		return jdbcTemplate.queryForObject(sql, Integer.class,new Object[]{type});
	}
}
