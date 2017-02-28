package com.easylotto.core.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
//import com.easylotto.core.entity.PrizeDetailsVO;
import com.easylotto.core.entity.user.EcpUserFundLog;


/**
 * @源文件：EcpUserFundLogDAO.java
 * @日 期：2012-06-04
 * @内 容：用户资金变动日志数据服务 类
 * @author huangjun
 */
@Repository
public class EcpUserFundLogDAO extends BaseDao<EcpUserFundLog> {

	public EcpUserFundLog findByRecId(long recId){
		String sql = "  select * from ecp_user_fund_log where int_rec_id=?";
		Object obj[] = { recId };
		EcpUserFundLog ecpUserFundLog=getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<EcpUserFundLog>(
				EcpUserFundLog.class),obj);
		return ecpUserFundLog;
	}
	/**
	 * 根据用户ID取得所有资金变动信息
	 * @param userId
	 * @return
	 */
	public List<EcpUserFundLog> findByUserId(long userId) {
		String sql = "  select * from ecp_user_fund_log where int_account_id= "
				+ userId;
		return getJdbcTemplate().query(sql,  
				new BeanPropertyRowMapper<EcpUserFundLog>(EcpUserFundLog.class));
	}
	
	public List<EcpUserFundLog> findForCancel(long userId, String billNo, int operType) {
		String sql = "  select * from ecp_user_fund_log where int_account_id= ? and vc_bill_no=? and int_oper_type=? ";
		Object[] args = {userId, billNo, operType};
		return getJdbcTemplate().query(sql, args, 
				new BeanPropertyRowMapper<EcpUserFundLog>(EcpUserFundLog.class));
	}

	/**
	 * 添加资金变动记录
	 * 
	 * @param ecpUserFundLog
	 */
	public void save(EcpUserFundLog ecpUserFundLog) {
		String sql = "insert into ecp_user_fund_log(int_account_id, int_oper_type, dt_oper_time, "
				+ "dec_amount, vc_bill_no, dec_balance, int_account_type) values "
			+ "(?, ?, now(), ?, ?, ?, ?)";
		Object[] args = {
			ecpUserFundLog.getInt_account_id(),
			ecpUserFundLog.getInt_oper_type(),
			ecpUserFundLog.getDec_amount(),
			ecpUserFundLog.getVc_bill_no(),
			ecpUserFundLog.getDec_balance(),
			ecpUserFundLog.getInt_account_type()
		};
		getJdbcTemplate().update(sql, args);
	}

	/**
	 * 修改资金变动记录
	 * 
	 * @param ecpUserFundLog
	 */
	public void update(EcpUserFundLog ecpUserFundLog) {
		String sql = "update ecp_user_fund_log f  set f.dec_amount=?,f.dec_balance=?,f.dt_oper_time=?,"
				+ "f.int_account_id=?,f.int_account_type=?,f.int_oper_type=?,f.vc_bill_no=? where f.int_rec_id=?";
		Object[] args = { ecpUserFundLog.getDec_amount(),
				ecpUserFundLog.getDec_balance(),
				ecpUserFundLog.getDt_oper_time(),
				ecpUserFundLog.getInt_account_id(),
				ecpUserFundLog.getInt_account_type(),
				ecpUserFundLog.getInt_oper_type(),
				ecpUserFundLog.getVc_bill_no(), ecpUserFundLog.getInt_rec_id() };
		getJdbcTemplate().update(sql, args);
	}

	public long countByType(long accountId, int operType) {
		String sql = "select * from ecp_user_fund_log "
			+ " where int_account_id=? and int_oper_type=? ";
		Object[] args = {accountId, operType};
		//return this.queryForLong(sql, args);
		return getJdbcTemplate().queryForObject(sql, long.class, args);
	}
	
	public List<EcpUserFundLog> findAllPage(long accountId, int page, int pageSize) {
		int min = (page-1)*pageSize;
		String sql = "select * from ecp_user_fund_log "
			+ " where int_account_id=? order by DT_OPER_TIME desc limit ?,?";
		Object[] args = {accountId, min, pageSize};
		return getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<EcpUserFundLog>(EcpUserFundLog.class));
	}
	public int findAllPageCount(long accountId) {
		String sql = "select count(*) from ecp_user_fund_log "
			+ " where int_account_id= "+accountId;
		//return getJdbcTemplate().queryForInt(sql);
		return getJdbcTemplate().queryForObject(sql, int.class);
	}
	
	public List<EcpUserFundLog> findPageByType(long accountId, int operType, String beginTime, String endTime, int page, int pageSize) {
		int min = (page-1)*pageSize;
		String sql = "select * from ecp_user_fund_log where int_account_id=? and int_account_type !=3 ";
		if (operType != -1)
			sql += "and int_oper_type=" + operType;
		if (StringUtils.isNotEmpty(beginTime))
			sql += " and date_format(dt_oper_time,'%Y-%m-%d')>='" + beginTime +"' ";
		if (StringUtils.isNotEmpty(endTime))
			sql += " and date_format(dt_oper_time,'%Y-%m-%d')<='" + endTime +"' ";
		sql += " order by dt_oper_time desc limit ?,? ";
		Object[] args = {accountId, min, pageSize};
		return getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<EcpUserFundLog>(EcpUserFundLog.class));
	}
	
	public int findPageByTypeCount(long accountId, int operType, String beginTime, String endTime) {
		String sql = "select count(*) from ecp_user_fund_log where int_account_id=? and int_account_type !=3 ";
		if (operType != -1)
			sql += "and int_oper_type=" + operType;
		if (StringUtils.isNotEmpty(beginTime))
			sql += " and date_format(dt_oper_time,'%Y-%m-%d')>='" + beginTime +"' ";
		if (StringUtils.isNotEmpty(endTime))
			sql += " and date_format(dt_oper_time,'%Y-%m-%d')<='" + endTime +"' ";
		sql += " order by dt_oper_time desc ";
		Object[] args = {accountId};
		//return getJdbcTemplate().queryForInt(sql, args);
		return getJdbcTemplate().queryForObject(sql, int.class, args);
	}
	
	
	/**
	 * 查询用户奖金明细
	 * @author peidw 2009-11-27
	 * @param pdqc
	 * @return
	 */
//	public List<PrizeDetailsVO> queryAwardRecord(String user_id, String lotteryType,String refund_startDate,String refund_endDate,String pd_status,String pd_accepted_number,int page,int pageSize){
//		try{
//			StringBuffer sb=new StringBuffer();
//			sb.append(" select distinct(C.Int_Order_No)       int_order_no," +
//					" A.DT_SETTLEMENT_TIME awarddate," +
//					" A.DEC_PRIZE_POSTTAX  awardamount, C.Vc_Term            term," +
//					" D.VC_PLAY_NAME       lotteryname," +
//					" B.Int_Status         Int_Status  from ecp_rich_join   A " +
//					" inner join ecp_rich_order  C on A.INT_ORDER_NO = C.INT_ORDER_NO" +
//					" and A.DEC_PRIZE_POSTTAX >0 and A.INT_ACCOUNT_ID =" +user_id+
//					" inner join ECP_LOTTERY_TYPE D on C.Int_Lottery_Type = D.Int_Paly_Id " + 
//					" left outer join mb_distil_cash  B on A.INT_REC_ID = B.Vc_Input_Name" + 
//					" and (B.Int_Status is null or  (B.Int_Status is not null and B.int_status <> 5) " + 
//					" or (B.Int_Status=5 )) where");
//			
//			/*//简写
//			sb.append(" select distinct(C.Int_Order_No)       int_order_no," +
//					" A.DT_SETTLEMENT_TIME awarddate," +
//					" A.DEC_PRIZE_POSTTAX  awardamount, C.Vc_Term            term," +
//					" D.VC_PLAY_NAME       lotteryname," +
//					" B.Int_Status         Int_Status  from ecp_rich_join   A " +
//					" left outer join mb_distil_cash  B on A.INT_REC_ID = B.Vc_Input_Name" + 
//					" and (B.Int_Status is null or  (B.Int_Status is not null and B.int_status <> 5) " + 
//					" or (B.Int_Status=5 )) " +
//					" ,ecp_rich_order  C ,ECP_LOTTERY_TYPE D where A.INT_ORDER_NO = C.INT_ORDER_NO" +
//					" and A.DEC_PRIZE_POSTTAX >0 and A.INT_ACCOUNT_ID =" +user_id+
//					" and C.Int_Lottery_Type = D.Int_Paly_Id and");*/
//
//			if(StringUtils.isNotEmpty(lotteryType)){
//				sb.append("  C.Int_Lottery_Type="+lotteryType+" and ");
//			}
//			if(StringUtils.isNotEmpty(pd_accepted_number)){//受理号
//				sb.append(" C.Int_Order_No ="+pd_accepted_number+" and ");
//			}
//			if(StringUtils.isNotEmpty(refund_startDate )){
//				sb.append("  A.DT_SETTLEMENT_TIME>=date_format('"+refund_startDate+"','%Y-%m-%d')  " + " and ");
//			}
//			if(StringUtils.isNotEmpty(refund_endDate )){
//				sb.append("  A.DT_SETTLEMENT_TIME<=date_format('"+refund_endDate+"','%Y-%m-%d')  " + " and ");
//			}
//			if(StringUtils.isNotEmpty(pd_status)){
//				 if(pd_status.equals("2")){
//					sb.append(" B.Int_Status =4   " + " and ");
//				}else{
//					sb.append(" B.Int_Status in(1,2,3) " + " and ");
//				}
//			}
//			sb.append(" 1=1 ");
//
//			sb.append(" order by A.DT_SETTLEMENT_TIME desc limit " + (page-1)*pageSize +"," + pageSize + "");//分页
//			Object[] args={};
//			return getJdbcTemplate().query(sb.toString(),args , new PdvorowMap());
//
//		}catch(RuntimeException re){
//			logger.error("查询用户中奖明细时出现异常", re);
//			re.printStackTrace();
//			throw re;
//		}
//	}
	
	/**
	 * 查询用户中奖明细rowmapper
	 * @author peidw 2009-11-27
	 *
	 */
//	class PdvorowMap implements RowMapper{
//
//		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
//			PrizeDetailsVO result=new PrizeDetailsVO();
//			java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
//			result.setAwardamount(rs.getString("awardamount"));
//			result.setAwarddate(sdf.format(rs.getDate("awarddate")));
//			result.setTerm(rs.getString("term"));
//			/*result.setAwardflow(rs.getString("awardflow"));*/
//			result.setLotteryname(rs.getString("lotteryname"));
//			result.setInt_order_no(rs.getString("int_order_no"));
//			result.setStatus(rs.getString("int_status"));
//			return result;
//		}
//
//	}
	
	
	/**
	 * 查询用户中奖明细
	 * @author peidw 2009-11-27
	 * @param pdqc
	 * @return
	 */
	public long queryAwardRecord_count(String user_id, String lotteryType,String refund_startDate,String refund_endDate,String pd_status,String pd_accepted_number){
		try{
			StringBuffer sb=new StringBuffer();
			
			sb.append(" select count(distinct(C.Int_Order_No))" +
					" from ecp_rich_join   A " +
					" inner join ecp_rich_order  C on A.INT_ORDER_NO = C.INT_ORDER_NO" +
					" and A.DEC_PRIZE_POSTTAX >0 and A.INT_ACCOUNT_ID =" +user_id+
					" inner join ECP_LOTTERY_TYPE D on C.Int_Lottery_Type = D.Int_Paly_Id " + 
					" left outer join mb_distil_cash  B on A.INT_REC_ID = B.Vc_Input_Name" + 
					" and (B.Int_Status is null or  (B.Int_Status is not null and B.int_status <> 5) " + 
					" or (B.Int_Status=5 )) where ");
			
			/*//简写
			sb.append(" select count(distinct(C.Int_Order_No))" +
					" from ecp_rich_join   A " +
					" left outer join mb_distil_cash  B on A.INT_REC_ID = B.Vc_Input_Name" + 
					" and (B.Int_Status is null or  (B.Int_Status is not null and B.int_status <> 5) " + 
					" or (B.Int_Status=5 )) " +
					" ,ecp_rich_order  C ,ECP_LOTTERY_TYPE D where A.INT_ORDER_NO = C.INT_ORDER_NO" +
					" and A.DEC_PRIZE_POSTTAX >0 and A.INT_ACCOUNT_ID =" +user_id+
					" and C.Int_Lottery_Type = D.Int_Paly_Id and");*/
			
			if(StringUtils.isNotEmpty(lotteryType)){
				sb.append("  C.Int_Lottery_Type="+lotteryType + " and ");
			}
			if(StringUtils.isNotEmpty(pd_accepted_number)){//受理号
				sb.append(" C.Int_Order_No ="+pd_accepted_number+""+ " and ");
			}
			if(StringUtils.isNotEmpty(refund_startDate )){
				sb.append("  A.DT_SETTLEMENT_TIME>=date_format('"+refund_startDate+"','%Y-%m-%d')  "+ " and ");
			}
			if(StringUtils.isNotEmpty(refund_endDate )){
				sb.append("  A.DT_SETTLEMENT_TIME<=date_format('"+refund_endDate+"','%Y-%m-%d')  "+ " and ");
			}
			if(StringUtils.isNotEmpty(pd_status)){
	            if(pd_status.equals("2")){
					sb.append(" B.Int_Status =4  "+ " and ");
				}else{
					sb.append(" B.Int_Status in(1,2,3) "+ " and ");
				}
			}
			sb.append("1=1");
			//return	getJdbcTemplate().queryForLong(sb.toString());
			return getJdbcTemplate().queryForObject(sb.toString(), int.class);
		}catch(RuntimeException re){
			logger.error("查询用户中奖明细时出现异常", re);
			re.printStackTrace();
			throw re;
		}

	}
	
	
}
