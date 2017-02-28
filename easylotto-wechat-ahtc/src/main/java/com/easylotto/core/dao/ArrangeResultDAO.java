package com.easylotto.core.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.ArrangeResult;
import com.easylotto.core.entity.AwardRecord;

/**
 * @author huangjun
 * @version 2012-7-26
 */
@Repository
public class ArrangeResultDAO extends BaseDao<AwardRecord> {

	/**
	 * 取得胜负彩，任9，6场半赛程
	 * 
	 * @param lotteryType
	 * @param term
	 * @return
	 */
	public List<ArrangeResult> getArrangeWinResult(int lotteryType, String term) {

		String sql = "select r.vc_teams homeTeamName from ecp_rich_win_arrange r "
				+ "  where r.int_lottery_type=? and r.vc_term=? order by r.int_game_order asc";
		Object[] object = { lotteryType, term };
		return getJdbcTemplate().query(sql, object, new BeanPropertyRowMapper<ArrangeResult>(ArrangeResult.class));
	}

	/**
	 * 取得4场进球赛程
	 * 
	 * @param term
	 * @return
	 */
	public List<ArrangeResult> getArrangeGoalResult(String term) {
		String sql = "select e.vc_team_name homeTeamName from ECP_RICH_GOAL_ARRANGE e where e.vc_term=?"
				+ "  order by e.int_game_order asc";
		Object[] objects = { term };

		return getJdbcTemplate().query(sql, objects, new BeanPropertyRowMapper<ArrangeResult>(ArrangeResult.class));
	}
}
