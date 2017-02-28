package com.easylotto.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.util.FieldUtil;
import com.wechat.webapi.util.Pagination;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Repository
public class MemberDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Member> getMemberInfo(int memberId){
		String sql="SELECT i.INT_ACCOUNT_ID,i.VC_NAME,i.VC_NICKNAME"
				+ ",i.vc_headimgurl,u.dec_lottery_bean,f.DEC_AMOUNT,f.DEC_BALANCE"
				+ ",f.INT_OPER_TYPE,f.DT_OPER_TIME,i.VC_MOBILE,i.VC_EMAIL,i.VC_USER_PROVINCE"
				+ ",i.VC_USER_CITY,i.VC_USER_ADDRESS,i.VC_CARD_NO"
				+ " FROM ecp_user_info i "
				+ "LEFT JOIN ecp_user_fund_log f ON I.INT_ACCOUNT_ID=f.INT_ACCOUNT_ID  "
				+ "LEFT JOIN ecp_user u ON i.INT_ACCOUNT_ID=u.INT_ACCOUNT_ID "
				+ "WHERE f.INT_ACCOUNT_TYPE=4 and  I.INT_ACCOUNT_ID="+memberId+" order by f.DT_OPER_TIME DESC LIMIT 1";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Member>(Member.class));
	}
	
	
	public Pagination getColorBeanLists(Integer currentPage,Integer numPerPage,int memberId) {    
		String sql="SELECT i.INT_ACCOUNT_ID,u.dec_lottery_bean,f.DEC_AMOUNT,f.DEC_BALANCE,f.INT_OPER_TYPE,f.DT_OPER_TIME FROM ecp_user_info i LEFT JOIN ecp_user_fund_log f ON I.INT_ACCOUNT_ID=f.INT_ACCOUNT_ID  LEFT JOIN ecp_user u ON i.INT_ACCOUNT_ID=u.INT_ACCOUNT_ID WHERE f.INT_ACCOUNT_TYPE=4 and  I.INT_ACCOUNT_ID="+memberId+" order by f.DT_OPER_TIME DESC ";
        Pagination page=new Pagination(sql, currentPage, numPerPage,  jdbcTemplate);  
        return page;      
    }  
	
	public String  getMemberId(String openId){
		try{
			String sql="SELECT t.INT_ACCOUNT_ID FROM ecp_user_info t where t.vc_open_id='"+openId+"'";
			return jdbcTemplate.queryForObject(sql, String.class);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public Map<String,Object> getActivityMemberInfo(int memberId,int activityId){
		String sql="SELECT t.VC_NAME name,t.VC_PHONE phone FROM ecp_activity_log t where t.INT_ACOUNT_ID="+memberId+" and t.INT_ACTIVITY_ID="+activityId+"  LIMIT 0,1";
	    return jdbcTemplate.queryForMap(sql);
	}
	
	public int getMemberPhone(String phone,int memberId){
		String sql="SELECT COUNT(t.VC_MOBILE) from ecp_user_info t where t.VC_MOBILE='"+phone+"'  AND t.INT_ACCOUNT_ID <> "+memberId;
	    return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	public void updateMemberInfo(final Member member,final int memberId){
		 jdbcTemplate.update("update ecp_user_info set VC_NAME=?,VC_MOBILE=?,VC_EMAIL=?,VC_USER_PROVINCE=?,VC_USER_CITY=?,VC_USER_ADDRESS=? where INT_ACCOUNT_ID=?",   
	                new PreparedStatementSetter(){  	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setString(1,member.getVc_name()); 
	                        ps.setString(2,member.getVc_mobile()); 
	                        ps.setString(3,member.getVc_email()); 
	                        ps.setString(4,member.getVc_user_province()); 
	                        ps.setString(5,member.getVc_user_city()); 
	                        ps.setString(6,member.getVc_user_address()); 
	                        ps.setInt(7,memberId); 


	                    }

			
	        }); 

	}
	
	public void updateUser(final String account,final int memberId){
		 
		 jdbcTemplate.update("update ecp_user set VC_ACCOUNT=? where INT_ACCOUNT_ID=?",   
	                new PreparedStatementSetter(){  	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setString(1,account); 
	                        ps.setInt(2,memberId); 


	                    }

			
	        });
	}
	
	public void updateUserInfo(final WxMpUser user,final Long accountId){
		 jdbcTemplate.update("update ecp_user_info set VC_NICKNAME=?,VC_SEX=?,VC_USER_PROVINCE=?,VC_USER_CITY=?,VC_LANGUAGE=?,VC_HEADIMGURL=?,INT_SUB_STATUS=? where INT_ACCOUNT_ID=?",   
	                new PreparedStatementSetter(){  	              
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setString(1,user.getNickname()); 
	                        ps.setString(2,user.getSex()); 
	                        ps.setString(3,user.getProvince()); 
	                        ps.setString(4,user.getCity()); 
	                        ps.setString(5,user.getLanguage()); 
	                        ps.setString(6,user.getHeadImgUrl()); 
	                        ps.setInt(7, 1);
	                        ps.setLong(8,accountId); 
	                    }

			
	        }); 

	}
	
	public Date getSignDate(String openId){
		String sql = "select dt_sign_time from ecp_sign_in where vc_open_id=?";
		Object[] args={openId};
		return jdbcTemplate.queryForObject(sql, Date.class,args);
	}
	public EcpSignIn getSignLog(String openId){
		EcpSignIn ecpSignIn = new EcpSignIn(); 
		String sql = "select * from ecp_sign_in where vc_open_id=?";
		Object[] args={openId};
		List<EcpSignIn> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<EcpSignIn>(EcpSignIn.class), args);
		if(list!=null && list.size()>0){
			ecpSignIn = list.get(0);
			return ecpSignIn;
		}
		
		return null;
	}
	//取消关注时将用户状态置为0
	public void updateUserStatus(long memberId){
		String sql = "update ecp_user_info set INT_SUB_STATUS = 0 where INT_ACCOUNT_ID=?";
		Object[] args = {memberId};
		try {
			jdbcTemplate.update(sql, args);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void addUnsubLog( final EcpUserFundLog ecpUserFundLog){
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				try {
					return FieldUtil.buildInsert(con, ecpUserFundLog);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		
		
		
//		jdbcTemplate.update(sql, new PreparedStatementSetter() {
//			
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setLong(1, memberId);
//				ps.setInt(2, operType);
//				ps.setDate(3, (Date) unSubTime);
//				ps.setDouble(4, amount);
//				ps.setString(5, billNo);
//				ps.setDouble(6, balance);
//				ps.setInt(7, accountType);
//			}
//		});
	}
	public List<Activity> getActivityING(){
		String sql="SELECT t.* FROM ecp_activity t WHERE t.INT_ACTIVITY_BELONG=0 AND NOW()>t.DT_BEGIN_TIME and NOW()<t.DT_END_TIME";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Activity>(Activity.class));
	}
	public List<EcpLotteryOpenResult> getAllNewLotteryInfo(){
		String sql="SELECT a.* FROM "
				+ " (SELECT e.VC_PLAY_NAME,t.VC_TERM,t.INT_LOTTERY_TYPE,t.DT_OPEN_TIME,t.VC_CODE_CONTENT "
				+ " FROM ecp_lottery_open_result t RIGHT JOIN ecp_lottery_type e ON t.INT_LOTTERY_TYPE=e.INT_PALY_ID "
				+ "WHERE t.VC_TERM is NOT NULL AND t.VC_CODE_CONTENT IS NOT NULL ORDER BY t.DT_OPEN_TIME DESC) a GROUP BY a.VC_PLAY_NAME";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class));
		
	}
}
