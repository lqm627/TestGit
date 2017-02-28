package com.easylotto.core.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.EcpRichLottery;
import com.easylotto.core.entity.LotteryTypeDef;

@Repository
public class EcpRichLotteryDAO extends BaseDao<EcpRichLottery> {

	// 单式比复式截止时间提前的小时数
	private int hour = 1;

	/**
	 * 获取体彩
	 * 
	 * @return
	 */
	public List<EcpRichLottery> getWelfareAllOnSaleTerm() {
		String sql = " SELECT * FROM ecp_rich_lottery A," + " (SELECT COUNT(int_lottery_type), int_lottery_type"
				+ " FROM ecp_rich_lottery" + " where VC_ADUT_NAME IS NOT NULL"
				+ " AND int_lottery_type in (1,2,4,6,8,13,14,15,19,23)" + " GROUP BY int_lottery_type) B"
				+ " WHERE A.int_lottery_type = B.int_lottery_type" + " AND A.vc_term = (SELECT min(vc_term)"
				+ " FROM ecp_rich_lottery" + " WHERE int_lottery_type = B.int_lottery_type"
				+ " AND int_lottery_type in (1,2,4,6,8,13,14,15,19,23)"
				+ " AND VC_ADUT_NAME IS NOT NULL and DT_DEADLINE >now() )"
				+ " AND A.VC_ADUT_NAME IS NOT NULL AND  A.DT_DEADLINE >now() ";

		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class));
	}

	public List<EcpRichLottery> getAllOnSaleTerm() {
		String sql = " SELECT * FROM ecp_rich_lottery A," + " (SELECT COUNT(int_lottery_type), int_lottery_type"
				+ " FROM ecp_rich_lottery" + " where VC_ADUT_NAME IS NOT NULL" + " GROUP BY int_lottery_type) B"
				+ " WHERE A.int_lottery_type = B.int_lottery_type" + " AND A.vc_term = (SELECT min(vc_term)"
				+ " FROM ecp_rich_lottery" + " WHERE int_lottery_type = B.int_lottery_type"
				+ " AND VC_ADUT_NAME IS NOT NULL and DT_DEADLINE >now() )"
				+ " AND A.VC_ADUT_NAME IS NOT NULL AND  A.DT_DEADLINE >now() ";

		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class));
	}

	/**
	 * 获取当前在售的彩期(系统开始时间小于当前时间，系统截止时间大于当前时间，状态审核通过) 按彩期排序，只取第一条
	 * 
	 * @param lotteryType
	 * @return
	 */
	public EcpRichLottery getOnSaleTerm(int lotteryType) {
		if (lotteryType == LotteryTypeDef.PL_3.intValue()) {
			lotteryType = LotteryTypeDef.PL_5.intValue();
		}
		// 如果是找任9的彩期，变为查胜负彩的彩期
		if (lotteryType == LotteryTypeDef.NINE_MATCH.intValue()) {
			lotteryType = LotteryTypeDef.WIN_MATCH.intValue();
		}

		// 新的方法：取不到在售的彩期时，取下一期
		String sql = " select a.*,now() sysdate from ECP_RICH_LOTTERY a where VC_ADUT_NAME is not null and INT_LOTTERY_TYPE=? ";
		if (lotteryType == 27)
			sql += " and dt_begin_time <= now() ";
		sql += " and DT_DEADLINE >now() order by vc_term asc,dt_deadline asc limit 1";
		// Object[] args = { lotteryType };
		// return this.queryForObject(sql, new
		// BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class), args);
		return get(getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class),
				new Object[] { lotteryType }));
	}

	public EcpRichLottery getTerminalTerm(int lotteryType) {
		// 如果是找排3的彩期，变为查排列5的彩期
		if (lotteryType == LotteryTypeDef.PL_3.intValue()) {
			lotteryType = LotteryTypeDef.PL_5.intValue();
		}
		// 如果是找任9的彩期，变为查胜负彩的彩期
		if (lotteryType == LotteryTypeDef.NINE_MATCH.intValue()) {
			lotteryType = LotteryTypeDef.WIN_MATCH.intValue();
		}

		String sql = " select *  from Ecp_Rich_Lottery vo " + " where vo.dt_Terminal_Begintime < now() "
				+ " and vo.dt_Terminal_Deadline > now() " + " and vo.vc_adut_name is not Null "
				+ " and vo.int_Lottery_Type=?" + " order by vo.dt_Terminal_Deadline asc,vo.vc_term asc ";
		Object[] args = { lotteryType };
		List<EcpRichLottery> list = getJdbcTemplate().query(sql, args,
				new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class));
		// 如果有两期同时在售期数 取得最新的截止时间
		EcpRichLottery vo = null;
		if (list != null && list.size() > 0) {
			vo = (EcpRichLottery) list.get(0);
		} else {
			List<EcpRichLottery> ecpRichLotteries = getTerminalLastestSaleTerm(lotteryType);
			if (null != ecpRichLotteries && ecpRichLotteries.size() > 0)
				vo = ecpRichLotteries.get(0);
		}
		return vo;
	}

	public EcpRichLottery getLotteryByTerm(String term, int lotteryType) {
		// 如果是找排3的彩期，变为查排列5的彩期
		if (lotteryType == LotteryTypeDef.PL_3.intValue()) {
			lotteryType = LotteryTypeDef.PL_5.intValue();
		}

		// 如果是找任9的彩期，变为查胜负彩的彩期
		if (lotteryType == LotteryTypeDef.NINE_MATCH.intValue()) {
			lotteryType = LotteryTypeDef.WIN_MATCH.intValue();
		}

		String sql = " select *from  Ecp_Rich_Lottery vo where vo.vc_term=? and vo.int_Lottery_Type=? ";
		// Object[] args = { term, lotteryType };
		// return this.queryForObject(sql, new
		// BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class), args);

		return get(getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class),
				new Object[] { term, lotteryType }));
	}

	/**
	 * 得到玩法的终端机最新在售的彩期，包括截止时间1小时内的
	 * 
	 * @author zhaofeng
	 * @serialData 2008-04-28
	 * @param lotteryType
	 *            彩票类型 -1：查所有玩法的在售彩期
	 * @return List 彩期list
	 */

	public List<EcpRichLottery> getTerminalLastestSaleTerm(int lotteryType) {
		// 如果是找排3的彩期，变为查排列5的彩期
		if (lotteryType == LotteryTypeDef.PL_3.intValue()) {
			lotteryType = LotteryTypeDef.PL_5.intValue();
		}
		// 如果是找任9的彩期，变为查胜负彩的彩期
		if (lotteryType == LotteryTypeDef.NINE_MATCH.intValue()) {
			lotteryType = LotteryTypeDef.WIN_MATCH.intValue();
		}

		String sql = " select *  from Ecp_Rich_Lottery vo where 1=1 and vo.vc_adut_name is not null ";
		sql = sql + " and vo.dt_Terminal_Begintime < now() ";

		if (lotteryType != -1) {
			sql = sql + " and vo.int_Lottery_Type=" + lotteryType;
		}
		sql = sql + " order by vo.dt_Terminal_Deadline desc limit 1";
		JdbcTemplate jt = getJdbcTemplate();
		List<EcpRichLottery> list = jt.query(sql, new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class));
		List<EcpRichLottery> resultList = new ArrayList<EcpRichLottery>();
		if (list != null) {
			for (EcpRichLottery vo : list) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(vo.getDt_deadline());
				if (lotteryType == LotteryTypeDef.FC_3D.intValue()
						|| lotteryType == LotteryTypeDef.DOUBLE_COLOR.intValue()) {// 福彩3D
																					// 提前10分钟
					cal.add(Calendar.MINUTE, -10);
				} else {
					cal.add(Calendar.HOUR, -hour);
				}
				resultList.add(vo);
			}

		}

		return resultList;
	}

	/**
	 * 根据彩票类型查询已开奖期数列表,最新的在最上面 可分页
	 * 
	 * @author liuliping
	 * @date 2012-06-28
	 * @param lotteryType
	 *            int 彩票类型
	 * @return List
	 */
	public List<EcpRichLottery> getOpenAwardTermList(int lotteryType, final int length) {

		if (lotteryType == LotteryTypeDef.PL_3.intValue()) {
			lotteryType = LotteryTypeDef.PL_5.intValue();
		}
		// 如果是找任9的彩期，变为查胜负彩的彩期
		if (lotteryType == LotteryTypeDef.NINE_MATCH.intValue()) {
			lotteryType = LotteryTypeDef.WIN_MATCH.intValue();
		}
		String sql = " select * from ecp_rich_lottery where int_lottery_type =? and dt_open_time < now() "
				+ " order by dt_open_time desc limit ? ";
		Object[] args = { lotteryType, length };
		List<EcpRichLottery> list = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class), args);
		return list;
	}

	public EcpRichLottery getLotteryType(int lotteryType, String term) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select * from ecp_rich_lottery where int_lottery_type = ? and vc_term < ? "
				+ "order by dt_terminal_deadline desc limit 1 ");
		Object[] args = { lotteryType, term };
		// EcpRichLottery ecpRichLottery = this.queryForObject(hsql.toString(),
		// new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class),
		// args);
		// return ecpRichLottery;
		return get(getJdbcTemplate().query(hsql.toString(), new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class),
				new Object[] { lotteryType, term }));
	}

	public void add(EcpRichLottery ecpRichLottery) {
		String sql = "insert into ecp_rich_lottery(vc_term, int_lottery_type,dt_begin_time, dt_deadline, dt_open_time, dt_terminal_deadline, "
				+ "dt_terminal_begintime, vc_input_name, dt_input_time,vc_adut_name, dt_adut_time) values ( "
				+ "?,?,?,?,?,?,?,?,now(),null,null) ";
		Object[] args = { ecpRichLottery.getVc_term(), ecpRichLottery.getInt_lottery_type(),
				ecpRichLottery.getDt_begin_time(), ecpRichLottery.getDt_deadline(), ecpRichLottery.getDt_open_time(),
				ecpRichLottery.getDt_terminal_deadline(), ecpRichLottery.getDt_terminal_begintime(),
				ecpRichLottery.getVc_input_name() };
		getJdbcTemplate().update(sql, args);
	}

	public List<EcpRichLottery> getCheckterminalTerm(int lotteryType) {
		JdbcTemplate jt = getJdbcTemplate();
		String sql;
		// 如果是找排3的彩期，变为查排列5的彩期
		if (lotteryType == LotteryTypeDef.PL_3.intValue()) {
			lotteryType = LotteryTypeDef.PL_5.intValue();
		}
		// 如果是找任9的彩期，变为查胜负彩的彩期
		if (lotteryType == LotteryTypeDef.NINE_MATCH.intValue()) {
			lotteryType = LotteryTypeDef.WIN_MATCH.intValue();
		}

		sql = " select *  from Ecp_Rich_Lottery vo where 1=1  ";
		sql = sql + " and vo.dt_Terminal_Begintime < now() ";
		sql = sql + " and vo.dt_Terminal_Deadline > date_sub(now(),interval 30 minute) "; // sysdate-1/48
																							// 相隔半小时
		sql = sql + " and vo.vc_adut_name is not Null "; //
		if (lotteryType != -1) {
			sql = sql + " and vo.int_Lottery_Type=?";
		}
		sql = sql + " order by vo.dt_Terminal_Deadline asc,vo.vc_term asc ";
		List resultList = new ArrayList();
		Object[] args = { lotteryType };
		List<EcpRichLottery> list = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class), args);

		// 如果有两期同时在售期数 取得最新的截止时间
		if (list != null && list.size() > 0) {
			resultList = new ArrayList();
			EcpRichLottery vo = (EcpRichLottery) list.get(0);
			Calendar cal = Calendar.getInstance();
			cal.setTime(vo.getDt_deadline());
			cal.add(Calendar.HOUR, -hour);
			resultList.add(vo);
		} else {
			List list2 = getTerminalLastestSaleTerm(lotteryType);
			if (list2 != null && list2.size() > 0) {
				EcpRichLottery vo = (EcpRichLottery) getTerminalLastestSaleTerm(lotteryType).get(0);
				resultList.add(vo);
			}
			return null;

		}
		return resultList;
	}

	public List<EcpRichLottery> getCheckK3TerminalTerm() {
		String sql = " select *  from Ecp_Rich_Lottery vo where 1=1  ";
		sql = sql + " and vo.dt_Terminal_Begintime < now() ";
		sql = sql + " and vo.dt_Terminal_Deadline > date_sub(now(),interval 30 minute) "; //
		sql = sql + " and vo.vc_adut_name is not Null "; //
		sql = sql + " and vo.int_Lottery_Type=27";
		sql = sql + " order by vo.vc_term desc ";
		List resultList = new ArrayList();
		List<EcpRichLottery> list = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<EcpRichLottery>(EcpRichLottery.class));

		if (list != null && list.size() > 0) {
			resultList.add(list.get(0));
			if (list.size() > 1)
				resultList.add(list.get(1));
		}

		return resultList;
	}
}
