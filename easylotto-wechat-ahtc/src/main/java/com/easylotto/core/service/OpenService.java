package com.easylotto.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.core.dao.ArrangeResultDAO;
import com.easylotto.core.dao.EcpLotteryOpenResultDAO;
import com.easylotto.core.entity.ArrangeResult;
import com.easylotto.core.entity.EcpLotteryOpenResult;

/**
 * @源文件：OpenService.java
 * @内 容：开奖信息业务类
 * @日 期：2012-05-31
 * @author huangjun
 */
@Service
@Transactional(readOnly = true)
public class OpenService {
	/**
	 * 开奖信息数据服务类
	 */
	@Autowired
	private EcpLotteryOpenResultDAO ecpLotteryOpenResultDAO;


	/**
	 * 赛程
	 */
	private ArrangeResultDAO arrangeResultDAO;
	
	public ArrangeResultDAO getArrangeResultDAO() {
		return arrangeResultDAO;
	}

	public void setArrangeResultDAO(ArrangeResultDAO arrangeResultDAO) {
		this.arrangeResultDAO = arrangeResultDAO;
	}

	public EcpLotteryOpenResultDAO getLotteryOpenResultDAO() {
		return ecpLotteryOpenResultDAO;
	}

	public void setLotteryOpenResultDAO(
			EcpLotteryOpenResultDAO ecpLotteryOpenResultDAO) {
		this.ecpLotteryOpenResultDAO = ecpLotteryOpenResultDAO;
	}

	/**
	 * 取得最新的开奖信息
	 * 
	 * @return
	 */
	public List<EcpLotteryOpenResult> getLastAllList() {
		List<EcpLotteryOpenResult> list = ecpLotteryOpenResultDAO.findOpenResult();
		return list;
	}
	
	/**
	 * 取得福彩最新的开奖信息
	 * @return
	 */
	public List<EcpLotteryOpenResult> getWXCSLastAllList() {
		List<EcpLotteryOpenResult> list = ecpLotteryOpenResultDAO.findWelfareOpenResult();
		return list;
	}

	/**
	 * 单一彩种开奖公告列表,根据彩种取固定期数的开奖信息
	 * 
	 * @param lotteryType
	 * @param count
	 * @return
	 */
	public List<EcpLotteryOpenResult> getLastList(int lotteryType, int page,int pageSize) {
		if(lotteryType == 14) {
			lotteryType= 13;
		}
		if(lotteryType == 2) {
			lotteryType= 1;
		}
		return ecpLotteryOpenResultDAO.findOpenResultByPage(lotteryType, page,pageSize);
	}

	/**
	 * 单一彩种开奖公告详细信息
	 * 
	 * @param lotteryType
	 * @param term
	 * @return
	 */
	public EcpLotteryOpenResult getOpenDetail(int lotteryType, String term) {
		if(lotteryType == 14) {
			lotteryType= 13;
		}
		if(lotteryType == 2) {
			lotteryType= 1;
		}
		EcpLotteryOpenResult openResult = ecpLotteryOpenResultDAO.getLastOpenResultByPlaytype(lotteryType, term);
		return openResult;
	}
	
	/**
	 * 取得胜负彩，任9，6场半赛程
	 * 
	 * @param lotteryType
	 * @param term
	 * @return
	 */
	public List<ArrangeResult> getArrangeWinResult(int lotteryType, String term) {
		return arrangeResultDAO.getArrangeWinResult(lotteryType, term);
	}


	/**
	 * 取得4场进球赛程
	 * 
	 * @param term
	 * @return
	 */
	public List<ArrangeResult> getArrangeGoalResult(String term) {
		return arrangeResultDAO.getArrangeGoalResult(term);
	}
	public void setEcpLotteryOpenResultDAO(
			EcpLotteryOpenResultDAO ecpLotteryOpenResultDAO) {
		this.ecpLotteryOpenResultDAO = ecpLotteryOpenResultDAO;
	}
	
	
	
	/**
	 *  查询前一期开奖信息
	 * 
	 * @return
	 */
	public EcpLotteryOpenResult findPreOpenResult(String lotteryTerm, String lotteryType) {
		return this.ecpLotteryOpenResultDAO.findPreOpenResult(lotteryTerm, lotteryType);
	}

}
