/**
 * 
 */
package com.easylotto.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserInfo;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.util.FieldUtil;

import me.chanjar.weixin.mp.bean.result.WxMpUser;


/**
 * @author CreateName:  UpdateName: 
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see EcpUserInfoDao
 * @see CreateDate: 2016年6月4日 下午4:47:43 UpdateDate: 2016年6月4日 下午4:47:43
 * @see Copyright 
 * @since JDK1.7.*
 * @version 1.0
 */

@Repository
public class EcpUserInfoDao {
	
	private final Logger logger = LogManager.getLogger(EcpUserInfoDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(final WxMpUser wxUser){
		if(null != wxUser.getSubscribeTime()){
			Date now = new Date(wxUser.getSubscribeTime());
			String sysDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
			Date subTime = com.easylotto.commons.util.DateTimeUtil.toDate(sysDate);
			int result = jdbcTemplate.update("insert into ecp_user_info (VC_OPEN_ID,DT_SUB_TIME,INT_WECHAT_TYPE) values (?,?,?)", new Object[]{wxUser.getOpenId(),subTime,0});
			return result;
		}else{
			int result = jdbcTemplate.update("insert into ecp_user_info (VC_OPEN_ID,INT_WECHAT_TYPE) values (?,?)", new Object[]{wxUser.getOpenId(),0});
			return result;
		}
	}
	
	
	
	public int save(final WxMpUser wxUser, int type){
		int result = jdbcTemplate.update("insert into ecp_user_info (VC_OPEN_ID,DT_SUB_TIME,INT_WECHAT_TYPE) values (?,now(),?)", new Object[]{wxUser.getOpenId(),type});
		return result;
	}
	
	
	public void create(EcpUserInfo bean, String key){
		jdbcTemplate.update("insert into ecp_user_key (id,open_id, vc_key, int_timeout) values (?,?,?,UNIX_TIMESTAMP() + 1000 * 60 * 60)", new Object[]{bean.getInt_account_id(), bean.getVc_open_id(), key});
	}
	
	
	public void update(String sql, Object[] data){
		jdbcTemplate.update(sql, data);
	}
	
	public List<EcpUser> query(String sql, Object[] data){
		List<EcpUser> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<EcpUser>(EcpUser.class), data);
		return list;
	}
	
	public List<EcpUserInfo> find(String sql, Object[] data){
		List<EcpUserInfo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<EcpUserInfo>(EcpUserInfo.class), data);
		return list;
	}
	
	
	public EcpUserKey findBy(Long id, int type){
		List<EcpUserKey> list = jdbcTemplate.query("SELECT t.* FROM ecp_user_info u LEFT JOIN ecp_user_key t ON u.INT_ACCOUNT_ID=t.id WHERE u.INT_WECHAT_TYPE=? AND t.id=?", new BeanPropertyRowMapper<EcpUserKey>(EcpUserKey.class), new Object[]{type,id});
		if(null == list || 0 == list.size()) return null;
		return list.get(0);
	}
	
	
	public EcpUserKey findBy(String key, int type){
		logger.info("------------------>>>   key:"+key+",type:"+type);
		List<EcpUserKey> list = jdbcTemplate.query("SELECT t.* FROM ecp_user_info u LEFT JOIN ecp_user_key t ON u.INT_ACCOUNT_ID=t.id WHERE u.INT_WECHAT_TYPE=? AND t.vc_key=?", new BeanPropertyRowMapper<EcpUserKey>(EcpUserKey.class), new Object[]{type,key});
		if(null == list || 0 == list.size()) return null;
		return list.get(0);
	}
	
	
	public EcpUserKey findByOpenId(String openid, int type){
		List<EcpUserKey> list = jdbcTemplate.query("SELECT t.* FROM ecp_user_info u LEFT JOIN ecp_user_key t ON u.INT_ACCOUNT_ID=t.id WHERE u.INT_WECHAT_TYPE=? AND t.open_id=?", new BeanPropertyRowMapper<EcpUserKey>(EcpUserKey.class), new Object[]{type,openid});
		if(null == list || 0 == list.size()) return null;
		return list.get(0);
	}
	
	
	public List<EcpUserKey> delete(){
//		List<EcpUserKey> list = jdbcTemplate.query("select * from ecp_user_key where int_timeout < UNIX_TIMESTAMP()", new BeanPropertyRowMapper<EcpUserKey>(EcpUserKey.class));
//		if(null != list && 0 < list.size()){
		jdbcTemplate.update("delete from ecp_user_key where int_timeout < UNIX_TIMESTAMP()");
//		}
//		return list
		return null;
	}
	
	public void updateUserOS(Long id, String osName){
		String sql = "update ecp_user_info set vc_os_name=? where int_account_id=?";
	    Object[] args={osName,id};
		jdbcTemplate.update(sql, args);
	}
	     
		public void updateUserInfo(final EcpUserInfo userInfo,final int accountId){
			 jdbcTemplate.update("update ecp_user_info set VC_NAME=?,VC_MOBILE=?,VC_EMAIL=?,VC_USER_PROVINCE=?,VC_USER_CITY=?,VC_USER_ADDRESS=? where INT_ACCOUNT_ID=?",   
		                new PreparedStatementSetter(){  	              
		                    @Override  
		                    public void setValues(PreparedStatement ps) throws SQLException {  
		                        ps.setString(1,userInfo.getVc_name()); 
		                        ps.setString(2,userInfo.getVc_mobile()); 
		                        ps.setString(3,userInfo.getVc_email()); 
		                        ps.setString(4,userInfo.getVc_user_province()); 
		                        ps.setString(5,userInfo.getVc_user_city()); 
		                        ps.setString(6,userInfo.getVc_user_address()); 
		                        ps.setInt(7,accountId); 


		                    }

				
		        }); 

		}
		//获取用户微信详细个人信息
		public void updateUserInfoByWX(final WxMpUser user,final Long accountId){
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
		public String  getAccountId(String openId){
			try{
				String sql="SELECT t.INT_ACCOUNT_ID FROM ecp_user_info t where t.vc_open_id=?";
				return jdbcTemplate.queryForObject(sql, new Object[]{openId}, String.class);
			}catch(EmptyResultDataAccessException e){
				return null;
			}
		}
		//取消关注时将用户状态置为0
		public void updateUserStatus(long accountId){
			String sql = "update ecp_user_info set INT_SUB_STATUS = 0 where INT_ACCOUNT_ID=?";
			Object[] args = {accountId};
			try {
				jdbcTemplate.update(sql, args);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		public void addUnsubLog( final EcpUserFundLog ecpUserFundLog){
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					try {
						return FieldUtil.buildInsert(con, ecpUserFundLog);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			});

		}
}
