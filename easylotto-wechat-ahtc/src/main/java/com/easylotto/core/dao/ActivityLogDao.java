package com.easylotto.core.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.ActivityLog;
import com.easylotto.core.entity.VoteCumulative;


@Repository
public class ActivityLogDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public void save(final ActivityLog activityLog){
		 jdbcTemplate.update("insert into ecp_activity_log (INT_ACTIVITY_ID,INT_ACOUNT_ID,VC_NAME,VC_PHONE,VC_CARD_NO,INT_VOTE_CUMULATIVE_ID,VC_UPPER_COUPLET,VC_LOWER_COUPLET,VC_COUPLET_RYHMING,VC_PIC_URL,VC_SERIAL_NUMBER,DT_ENTRY_TIME) values(?,?,?,?,?,?,?,?,?,?,?,now())",   
	                new PreparedStatementSetter(){  
	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setLong(1,activityLog.getInt_activity_id()); 
	                        ps.setLong(2, activityLog.getInt_account_id());
	                        ps.setString(3, activityLog.getVc_name());  
	                        ps.setString(4, activityLog.getVc_phone());
	                        ps.setString(5, activityLog.getVc_card_no());
	                        ps.setLong(6,activityLog.getInt_vote_cumulative_id());
	                        ps.setString(7, activityLog.getVc_upper_couplet());
	                        ps.setString(8, activityLog.getVc_lower_couplet());
	                        ps.setString(9, activityLog.getVc_couplet_ryhming());
	                        ps.setString(10, activityLog.getVc_pic_url());
	                        ps.setString(11, activityLog.getVc_serial_number());
	                    }

			
	        });  
	}

	
	//在当前活动中除了自己以外手机号有没有被使用
	public int findActivityMemberPhoneOnlyOneWithoutMyself(String phone,int activityId,int memberId){
		String sql="SELECT  COUNT(t.VC_PHONE) from ecp_activity_log t WHERE t.VC_PHONE='"+phone+"' and t.INT_ACTIVITY_ID="+activityId +" and t.INT_ACOUNT_ID <> "+memberId;
		int result=jdbcTemplate.queryForObject(sql, int.class);
		return result;
	}

	
	public String queryDateTime() {
		return jdbcTemplate.queryForObject("select sysdate() dataBaseTime", String.class);
	}
	
	
	
    public String getNameByCardNo(String cardNo,int activityId){
    	try{
    		String sql="SELECT t.VC_NAME FROM ecp_activity_log t WHERE t.VC_CARD_NO='"+cardNo+"' AND t.INT_ACTIVITY_ID="+activityId+"  LIMIT 1";
    	    return jdbcTemplate.queryForObject(sql,String.class);
    	}catch(EmptyResultDataAccessException e){
    		return null;
    	}
	
    }
    
	public int findActivityAccountJoinTimes(Long accountId,int activityId){
		String sql="SELECT COUNT(t.INT_REC_ID) from ecp_activity_log t WHERE t.INT_ACOUNT_ID="+accountId+" AND t.INT_ACTIVITY_ID="+activityId;
		int result=jdbcTemplate.queryForObject(sql, int.class);
		return result;
	}
	
	public void updateVoteCumuative(final VoteCumulative voteCumulative){
		 jdbcTemplate.update("update ecp_vote_cumulative set INT_CUMULATIVE=? where INT_REC_ID=?",   
	                new PreparedStatementSetter(){  
	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setLong(1,voteCumulative.getInt_cumulative()); 
	                        ps.setLong(2, voteCumulative.getInt_rec_id());
	                    }

			
	        });  
	}
	public List<VoteCumulative> getVoteCumulativelist(){
		String sql="SELECT t.* FROM ecp_vote_cumulative t ";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<VoteCumulative>(VoteCumulative.class));
	}
	
	public List<ActivityLog> findCoupletsAccountJoinTimes(Long accountId,int activityId){
		String sql="SELECT t.* from ecp_activity_log t where t.INT_ACOUNT_ID='"+accountId+"' AND t.INT_ACTIVITY_ID="+activityId;
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
	}
    
	public Activity getActivity(int activityId){
		String sql="SELECT t.* from ecp_activity t where t.INT_ACTIVITY_ID="+activityId;
		List<Activity> list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<Activity>(Activity.class));
		if(list.size()!=0&&list!=null){
			return list.get(0);
		}
		return null;
	}
	//用户在当前活动中最新提交的一条记录的所有信息
	public ActivityLog getActivityLog(Long accountId,int activityId){
		String sql="SELECT t.* from ecp_activity_log t where t.INT_ACOUNT_ID='"+accountId+"' AND t.INT_ACTIVITY_ID="+activityId+" ORDER BY t.DT_ENTRY_TIME DESC";
		List<ActivityLog> list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<ActivityLog>(ActivityLog.class));
		if(list.size()!=0&&list!=null){
			return list.get(0);
		}
		return null;
	}
    public String findSerialNumberOnlyone(String serialNmuber,int activityId){
    	try{
    		String sql="SELECT t.VC_SERIAL_NUMBER FROM ecp_activity_log t WHERE t.VC_SERIAL_NUMBER='"+serialNmuber+"' AND t.INT_ACTIVITY_ID="+activityId;
    	    return jdbcTemplate.queryForObject(sql,String.class);
    	}catch(EmptyResultDataAccessException e){
    		return null;
    	}
	
    }
	
}
