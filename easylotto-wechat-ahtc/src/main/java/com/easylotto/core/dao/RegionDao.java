/**
 * 
 */
package com.easylotto.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.EcpRogion;


/**
 * @author CreateName:  UpdateName: 
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see RegionDao
 * @see CreateDate: 2016年6月4日 下午4:47:43 UpdateDate: 2016年6月4日 下午4:47:43
 * @see Copyright 
 * @since JDK1.7.*
 * @version 1.0
 */

@Repository
public class RegionDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<EcpRogion> findBy(Long id){
		List<EcpRogion> list = jdbcTemplate.query("SELECT * FROM ecp_region where state = 1 and int_pk_id = "+id+" order by name ", new BeanPropertyRowMapper<EcpRogion>(EcpRogion.class));
		return list;
	}
	
}
