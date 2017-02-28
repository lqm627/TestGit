package com.easylotto.core.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.ILottery;
import com.easylotto.core.entity.ILotteryType;
import com.wechat.webapi.util.Pagination;


@Repository
public class LotteryDao {
	
	protected static final Logger logger = LoggerFactory.getLogger(LotteryDao.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public Pagination findOpenRusult(Integer currentPage,Integer numPerPage,int lotterytType){
		 String sql="SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+lotterytType+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC";  
	     Pagination page=new Pagination(sql, currentPage, numPerPage,  jdbcTemplate);  
	     return page;  
	}
		
//	public List<ILottery> findQuickthreeOpenRusult(String pageSize,int id){
//		String sql="";
//		if(id==0){
//			 sql = "SELECT t.INT_REC_ID id, t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE=27 GROUP BY t.VC_TERM,t.DT_OPEN_TIME ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//		}else{
//			sql = "SELECT t.INT_REC_ID id, t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE=27 and t.INT_REC_ID < "+id+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//		}
//		
//			return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ILottery>(ILottery.class));
//	}
	
	/**
	 * 查询所有体彩彩种
	 * @return
	 */
	public List<ILotteryType> findAllLottery(){
		
		String sql = "SELECT INT_PLAYTYPE_PELATION type, VC_PLAY_NAME name	, VC_CONTENT content from ecp_lottery_type where INT_ISABLE = 1  order by INT_WEIGHT";
		List<ILotteryType> list = null;
		try {
			list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<ILotteryType>(ILotteryType.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	public List<ILottery> findDoubleOpenRusult(String pageSize,int type,int id){
//	String sql="";
//	if(id==0){
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+"  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}else{
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" and t.INT_REC_ID < "+id+"  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}
//
//		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ILottery>(ILottery.class));
//		
////		   return (List<Lottery>) jdbcTemplate.query(sql, new RowMapper<Lottery>(){
////
////	            @Override
////	            public Lottery mapRow(ResultSet rs, int rowNum) throws SQLException {
////	            	Lottery dou = new Lottery();
////	            	dou.setId(rs.getString("id"));
////	            	dou.setTerm(rs.getString("term"));
////                    dou.setResult(rs.getString("result"));
////                    dou.setOpen_time(rs.getDate("open_time"));
////                    dou.setPool_award(rs.getString("pool_award"));
////                    dou.setPrize_content(rs.getString("prize_content"));
////                return dou;
////	            }
////	            
////
////	        });
//	
//}
//
//public List<ILottery> find3DOpenRusult(String pageSize,int type,int id){
//	String sql="";
//	if(id==0){
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}else{
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" and t.INT_REC_ID < "+id+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}
//
//		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ILottery>(ILottery.class));
//	
//}
//
//public List<ILottery> findSevenLecaiOpenRusult(String pageSize,int type,int id){
//	String sql="";
//	if(id==0){
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}else{
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" and t.INT_REC_ID < "+id+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}
//
//		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ILottery>(ILottery.class));
//	
//}
//
//public List<ILottery> findSixAndOneOpenRusult(String pageSize,int type,int id){
//	String sql="";
//	if(id==0){
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}else{
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" and t.INT_REC_ID < "+id+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}
//
//		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ILottery>(ILottery.class));
//	
//}
//
//public List<ILottery> findTwentyfiveChooseFiveOpenRusult(String pageSize,int type,int id){
//	String sql="";
//	if(id==0){
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}else{
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" and t.INT_REC_ID < "+id+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}
//
//		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ILottery>(ILottery.class));
//	
//}
//
//public List<ILottery> findFifteenChooseFiveOpenRusult(String pageSize,int type,int id){
//	String sql="";
//	if(id==0){
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}else{
//		sql = "SELECT t.INT_REC_ID id,t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result,t.VC_PRIZE_CONTENT prize_content,t.VC_POOL_AWARD pool_award  from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE="+type+" and t.INT_REC_ID < "+id+" GROUP BY t.VC_TERM,t.DT_OPEN_TIME  ORDER BY t.VC_TERM DESC LIMIT 0,"+pageSize; 
//	}
//
//		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ILottery>(ILottery.class));
//	
//}
	
//	public Pagination queryPageQuickthreeOpenRusult(Integer currentPage,Integer numPerPage) {    
//  String sql="SELECT t.INT_REC_ID id, t.VC_TERM term,t.DT_OPEN_TIME open_time,t.VC_CODE_CONTENT result from ecp_lottery_open_result t  where t.INT_LOTTERY_TYPE=27 GROUP BY t.VC_TERM,t.DT_OPEN_TIME ORDER BY t.VC_TERM DESC";  
//  Pagination page=new Pagination(sql, currentPage, numPerPage,  jdbcTemplate);  
//  return page;      
//}  
	
}
