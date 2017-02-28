package com.easylotto.core.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.EcpLotteryOpenResult;

@Repository
public class EcpLotteryOpenResultDAO extends BaseDao<EcpLotteryOpenResult> {

	public void add(EcpLotteryOpenResult ecpLotteryOpenResult) {
		String str = "insert into ecp_lottery_open_result(int_lottery_type, vc_term,"
				+ " dt_open_time, dt_create_time,vc_code_content,vc_prize_content," + " vc_pool_award,vc_input_name)"
				+ " values(?,?," + " ?,now(),?,?," + " ?,?)";
		Object obj[] = new Object[] { ecpLotteryOpenResult.getInt_lottery_type(), ecpLotteryOpenResult.getVc_term(),
				ecpLotteryOpenResult.getDt_open_time(), ecpLotteryOpenResult.getVc_code_content(),
				ecpLotteryOpenResult.getVc_prize_content(), ecpLotteryOpenResult.getVc_pool_award(),
				ecpLotteryOpenResult.getVc_input_name() };
		getJdbcTemplate().update(str, obj);
	}

	/**
	 * 查询体彩最新开奖信息
	 * 
	 * @return
	 */
	public List<EcpLotteryOpenResult> findWelfareOpenResult() {

		String sql = "  SELECT * FROM ecp_lottery_open_result A,"
				+ "  (SELECT COUNT(int_lottery_type), int_lottery_type" + "   FROM ecp_lottery_open_result"
				+ "   where VC_ADUT_NAME IS NOT NULL " + "   AND int_lottery_type in (1,2,4,6,8,13,14,15,19,23)"
				+ "   GROUP BY int_lottery_type) B" + "   WHERE A.int_lottery_type = B.int_lottery_type"
				+ "   AND A.vc_term = (SELECT MAX(vc_term)" + "   FROM ecp_lottery_open_result"
				+ "   WHERE int_lottery_type = B.int_lottery_type" + "   AND VC_ADUT_NAME IS NOT NULL)"
				+ "   AND A.VC_ADUT_NAME IS NOT NULL";
		return getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class));
	}

	/**
	 *查询最新开奖信息
	 * 
	 * @return
	 */
	public List<EcpLotteryOpenResult> findOpenResult() {

		String sql = "  SELECT * FROM ecp_lottery_open_result A,"
				+ "  (SELECT COUNT(int_lottery_type), int_lottery_type" + "   FROM ecp_lottery_open_result"
				+ "   where VC_ADUT_NAME IS NOT NULL " + "   GROUP BY int_lottery_type) B"
				+ "   WHERE A.int_lottery_type = B.int_lottery_type" + "   AND A.vc_term = (SELECT MAX(vc_term)"
				+ "   FROM ecp_lottery_open_result" + "   WHERE int_lottery_type = B.int_lottery_type"
				+ "   AND VC_ADUT_NAME IS NOT NULL)" + "   AND A.VC_ADUT_NAME IS NOT NULL";
		return getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class));
	}
	
	/**
	 *  查询前一期开奖信息
	 * 
	 * @return
	 */
	public EcpLotteryOpenResult findPreOpenResult(String lotteryTerm, String lotteryType) {

		String sql = "select t1.* from ecp_lottery_open_result t1 left join (select dt_open_time from ecp_lottery_open_result  where INT_LOTTERY_TYPE = ? and VC_TERM = ? ) t2 on t2.dt_open_time > t1.DT_OPEN_TIME where t1.INT_LOTTERY_TYPE = ? and t1.VC_TERM <> ? and  t1.VC_ADUT_NAME IS NOT NULL order by t1.dt_open_time desc, t1.VC_TERM LIMIT 1";
		List<EcpLotteryOpenResult> list = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class), new Object[]{lotteryType, lotteryTerm, lotteryType, lotteryTerm});
		if(null != list && 0 < list.size()){
			return list.get(0);
		}
		return null;
	}
	
//	




	/**
	 * 根据彩种编号获取开奖结果最后一期
	 * 
	 * @param playtype
	 * @return
	 */
	public EcpLotteryOpenResult getLastOpenResultByPlaytype(int playtype) {
		String str = "select * from ecp_lottery_open_result  where "
				+ "INT_LOTTERY_TYPE=?  and vc_term=(select max(vc_term) " + "from ecp_lottery_open_result "
				+ "where INT_LOTTERY_TYPE=? and VC_ADUT_NAME IS NOT NULL)";
		Object objs[] = new Object[] { playtype, playtype };
//		return this.queryForObject(str, new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class),
//				objs);
		return get(getJdbcTemplate().query(str, new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class), objs));
	}

	/**
	 * 根据彩种取得指定期数的开奖信息
	 * 
	 * @param lotteryType
	 * @param nums
	 * @return
	 */
	public List<EcpLotteryOpenResult> findOpenResultByPage(int lotteryType, int page, int pageSize) {
		String sql = "select * from ecp_lottery_open_result where INT_LOTTERY_TYPE =? "
				+ "and VC_ADUT_NAME IS NOT NULL order by VC_TERM desc limit ?,?";

		int min = (page - 1) * pageSize;
		Object objs[] = new Object[] { lotteryType, min, pageSize };
		return getJdbcTemplate().query(sql, objs,
				new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class));
	}

	/**
	 * 获取特定期，取详细信息
	 * 
	 * @param playtype
	 * @param term
	 * @return
	 */
	public EcpLotteryOpenResult getLastOpenResultByPlaytype(int playtype, String term) {
		String str = "select * from ecp_lottery_open_result  "
				+ "where INT_LOTTERY_TYPE = ? and VC_TERM = ? and VC_ADUT_NAME IS NOT NULL ";
		Object objs[] = new Object[] { playtype, term };
		return get(getJdbcTemplate().query(str, new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class), objs));
	}

	/**
	 * 根据彩种类型取得开奖公告列表
	 * 
	 * @param lotteryType
	 * @return
	 */
	public List<EcpLotteryOpenResult> getOpenResuleList(String lotteryType) {
		String sql = "  SELECT * FROM ecp_lottery_open_result A,"
				+ "  (SELECT COUNT(INT_LOTTERY_TYPE), INT_LOTTERY_TYPE" + "   FROM ecp_lottery_open_result"
				+ "   where VC_ADUT_NAME IS NOT NULL " + "   GROUP BY INT_LOTTERY_TYPE) B"
				+ "   WHERE A.INT_LOTTERY_TYPE = B.INT_LOTTERY_TYPE " + "   and A.INT_LOTTERY_TYPE in(" + lotteryType
				+ ")" + "   AND A.vc_Term = (SELECT MAX(vc_term)" + "   FROM ecp_lottery_open_result"
				+ "   WHERE INT_LOTTERY_TYPE = B.INT_LOTTERY_TYPE" + "   AND VC_ADUT_NAME IS NOT NULL)"
				+ "   AND A.VC_ADUT_NAME IS NOT NULL";
		return getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class));
	}

	public EcpLotteryOpenResult findByTermAndType(String term, long type) {
		String sql = "select * from ecp_lottery_open_result " + "where vc_term=? and int_lottery_type=? ";
		Object[] args = new Object[] { term, type };
//		return this.queryForObject(sql, new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class),
//				args);
		return get(getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpLotteryOpenResult>(EcpLotteryOpenResult.class), args));
	}

	// /**
	// * @中奖排行榜
	// * @return
	// */
	// public List<EcpRichResult> queryTopWinList() {
	// String sql =
	// " select A.vc_account,A.DEC_GET from ECP_RICH_RESULT A ORDER BY A.VC_TERM
	// DESC limit 4 ";
	// List<EcpRichResult> resultList = getJdbcTemplate().query(sql,
	// new BeanPropertyRowMapper<EcpRichResult>(EcpRichResult.class));
	// return resultList;
	// }

}
