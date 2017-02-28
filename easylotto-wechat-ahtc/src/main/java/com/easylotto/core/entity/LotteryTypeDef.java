package com.easylotto.core.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class LotteryTypeDef {
	
	/* 0 ：进球彩 */
	final static public Integer GOAL_MATCH = 0;
	/* 1 ：胜负彩 */
	final static public Integer WIN_MATCH = 1;
	/* 2 ：任选九场 */
	final static public Integer NINE_MATCH = 2;
	/* 3 ：双色球 */
	final static public Integer DOUBLE_COLOR = 3;
	/* 4 ：七星彩 */
	final static public Integer SEVEN_STAR = 4;
	/* 5 ：世界杯8场胜负（期数以E开头） */
	final static public Integer CUP_EIGHT = 5;
	/* 6 ：6场半全场胜负（期数以S开头） */
	final static public Integer SIX_WIN = 6;
	/* 7 ：世界杯6场进球（期数以S开头） */
	final static public Integer CUP_SIX_GOAL = 7;
	/* 8 ：4场进球（期数以F开头） */
	final static public Integer FOUR_GOAL = 8;
	/* 9 ：世界杯八强 */
	final static public Integer CUP_EIGTH_MATCH = 9;
	/* 10：世界杯四强 */
	final static public Integer CUP_FOUR_MATCH = 10;
	/* 11：时时乐 */
	final static public Integer SSL = 11;
	/* 12：福彩3D */
	final static public Integer FC_3D = 12;
	
	
	/* 福彩15选5*/
	final static public Integer FC_15_TO_5 = 20;
	/* 福彩25选5 */
	final static public Integer FC_25_TO_5 = 25;
	/* 福彩东方6+1 */
	final static public Integer FC_DF_61 = 26;
	/* 福彩七乐彩*/
	final static public Integer FC_QLC = 24;
	/* 福彩七快3*/
	final static public Integer FC_K3 = 27;
	
	/* 13：排列3 */
	final static public Integer PL_3 = 13;
	/* 14：排列5 */
	final static public Integer PL_5 = 14;
	/* 15：22选5 */
	final static public Integer TC_22_TO_5 = 15;
	/* 16：体育彩票36选7 */
	final static public Integer TC_36_TO_7 = 16;
	/* 17：南粤风彩26选5 */
	final static public Integer NYFC_26_TO_5 = 17;
	/* 18：南粤风彩36选7 */
	final static public Integer FC_36_TO_7 = 18;
	/* 19：体彩31选7 */
	final static public Integer TC_31_TO_7 = 19;
	/* 22：快乐扑克 */
	final static public Integer POKER = 22;	
	/* 23：大乐透 */
	final static public Integer DLT = 23;
	
	/* 51：单场胜负平 */
	final static public Integer DC_SFP = 51;
	/* 52：单场进球数 */
	final static public Integer DC_JQS = 52;
	/* 53：上下盘单双 */
	final static public Integer DC_SXPDS = 53;
	/* 54：单场比分 */
	final static public Integer DC_BF = 54;
	/* 55：半全场胜负平 */
	final static public Integer DC_BQCSFP = 55;
	
	/* 2008：奥运顶呱刮彩票 */
	final static public Integer AOYUN_2008 = 2008;
	
	//彩票最大倍数 
	final static public Integer MAX_BET_COUNT = 99 ;
	
	/*彩豆支付*/
	final static public String CAIDOU_PAY = "11";
	
	
	/************************** 各玩法保底变量 start  *******************************/
	/* 0 ：进球彩 */
	final static public String GOAL_BAODI = "1";
	/* 1 ：胜负彩 */
	final static public String WIN_BAODI = "0.9";
	/* 2 ：任选九场 */
	final static public String NINE_BAODI = "0.9";
	/* 3 ：双色球 */
	final static public String DOUBLE_COLOR_BAODI = "1";
	/* 4 ：七星彩 */
	final static public String SEVEN_STAR_BAODI = "1";
	/* 5 ：世界杯8场胜负（期数以E开头） */
	final static public String CUP_EIGHT_BAODI = "1";
	/* 6 ：6场半全场胜负（期数以S开头） */
	final static public String SIX_WIN_BAODI = "0.9";
	/* 7 ：世界杯6场进球（期数以S开头） */
	final static public String CUP_SIX_GOAL_BAODI = "1";
	/* 8 ：4场进球（期数以F开头） */
	final static public String FOUR_GOAL_BAODI = "0.9";
	/* 9 ：世界杯八强 */
	final static public String CUP_EIGTH_BAODI = "1";
	/* 10：世界杯四强 */
	final static public String CUP_FOUR_BAODI = "1";
	/* 11：时时乐 */
	final static public String SSL_BAODI = "1";
	/* 12：福彩3D */
	final static public String FC_3D_BAODI = "1";
	/* 13：排列3 */
	final static public String PL_3_BAODI = "1";
	/* 14：排列5 */
	final static public String PL_5_BAODI = "1";
	/* 15：22选5 */
	final static public String TC_22_TO_5_BAODI = "1";
	/* 16：体育彩票36选7 */
	final static public String TC_36_TO_7_BAODI = "1";
	/* 17：南粤风彩26选5 */
	final static public String NYFC_26_TO_5_BAODI = "1";
	/* 18：南粤风彩36选7 */
	final static public String FC_36_TO_7_BAODI = "1";
	/* 19：体彩31选7 */
	final static public String TC_31_TO_7_BAODI ="1";
	/* 22：快乐扑克 */
	final static public String POKER_BAODI = "1";
	/* 22：大乐透 */
	final static public String DLT_BAODI = "1";
	/* 51：单场胜负平 */
	final static public String DC_SFP_BAODI = "1";
	/* 52：单场进球数 */
	final static public String DC_JQS_BAODI = "1";
	/* 53：上下盘单双 */
	final static public String DC_SXPDS_BAODI = "1";
	/* 54：单场比分 */
	final static public String DC_BF_BAODI = "1";
	/* 55：半全场胜负平 */
	final static public String DC_BQCSFP_BAODI = "1";
	
	/* 2008：奥运顶呱刮彩票 */
	final static public String AOYUN_2008_BAODI = "1";
	
	/* 福彩15选5*/
	final static public String FC_15_TO_5_BAODI = "1";
	/* 福彩25选5 */
	final static public String FC_25_TO_5_BAODI = "1";
	/* 福彩东方6+1 */
	final static public String FC_DF_61_BAODI = "1";
	/* 福彩七乐彩*/
	final static public String FC_QLC_BAODI = "1";
	/* 福彩七快3*/
	final static public String FC_K3_BAODI = "1";
	
	/************************** 各玩法保底变量 end  *******************************/
	static public Map<Integer, BeanLabel> map = null;
	
	static public Map<Integer, BeanLabel> baoMap = null;
	
	static public Map<Integer, BeanLabel> dcMap = null;
	
	static{
		map = new LinkedHashMap<Integer, BeanLabel>();
	    
		map.put(WIN_MATCH, new BeanLabel(WIN_MATCH,"胜负彩"));
		map.put(NINE_MATCH,new BeanLabel(NINE_MATCH,"任选九场"));
		map.put(SIX_WIN,new BeanLabel(SIX_WIN,"6场半全场"));
//		map.put(new BeanLabel(GOAL_MATCH,"进球彩"));
		map.put(SEVEN_STAR,new BeanLabel(SEVEN_STAR,"七星彩"));
//		map.put(new BeanLabel(CUP_EIGHT,"世界杯8场胜负"));
//		map.put(new BeanLabel(CUP_SIX_GOAL,"世界杯6场进球"));
		map.put(FOUR_GOAL,new BeanLabel(FOUR_GOAL,"4场进球"));
//		map.put(new BeanLabel(CUP_EIGTH_MATCH,"世界杯八强"));
//		map.put(new BeanLabel(CUP_FOUR_MATCH,"世界杯四强"));
		map.put(PL_3,new BeanLabel(PL_3,"排列3"));
		map.put(PL_5,new BeanLabel(PL_5,"排列5"));
		map.put(TC_22_TO_5,new BeanLabel(TC_22_TO_5,"22选5"));
		map.put(TC_36_TO_7,new BeanLabel(TC_36_TO_7,"体彩36选7"));
		map.put(TC_31_TO_7,new BeanLabel(TC_31_TO_7,"体彩31选7"));
		map.put(DLT,new BeanLabel(DLT,"大乐透"));
		
		map.put(POKER,new BeanLabel(POKER,"快乐扑克"));
		map.put(SSL,new BeanLabel(SSL,"时时乐"));
//		map.put(new BeanLabel(NYFC_26_TO_5,"南粤风彩26选5"));
//		map.put(new BeanLabel(FC_36_TO_7,"南粤风彩36选7"));
	
		map.put(DOUBLE_COLOR,new BeanLabel(DOUBLE_COLOR,"双色球"));
		map.put(FC_3D,new BeanLabel(FC_3D,"福彩3D"));
		map.put(FC_15_TO_5,new BeanLabel(FC_15_TO_5,"15选5"));
		map.put(FC_K3,new BeanLabel(FC_K3,"快3"));
		map.put(FC_25_TO_5,new BeanLabel(FC_25_TO_5,"25选5"));
		map.put(FC_QLC,new BeanLabel(FC_QLC,"七乐彩"));
		map.put(FC_DF_61,new BeanLabel(FC_DF_61,"福彩6+1"));
		
		map.put(DC_SFP,new BeanLabel(DC_SFP,"单场胜负平"));
		map.put(DC_JQS,new BeanLabel(DC_JQS,"单场进球数"));
		map.put(DC_SXPDS,new BeanLabel(DC_SXPDS,"单场上下盘单双"));
		map.put(DC_BF,new BeanLabel(DC_BF,"单场比分"));
		map.put(DC_BQCSFP,new BeanLabel(DC_BQCSFP,"单场半全场胜负平"));
		map.put(AOYUN_2008,new BeanLabel(AOYUN_2008,"奥运顶呱刮"));
		
		dcMap = new LinkedHashMap<Integer, BeanLabel>();
		dcMap.put(DC_SFP,new BeanLabel(DC_SFP,"单场胜负平"));
		dcMap.put(DC_JQS,new BeanLabel(DC_JQS,"单场进球数"));
		dcMap.put(DC_SXPDS,new BeanLabel(DC_SXPDS,"单场上下盘单双"));
		dcMap.put(DC_BF,new BeanLabel(DC_BF,"单场比分"));
		dcMap.put(DC_BQCSFP,new BeanLabel(DC_BQCSFP,"单场半全场胜负平"));
		

		
		/************************** 各玩法保底变量 start  *******************************/
		baoMap = new LinkedHashMap<Integer, BeanLabel>();
		
		baoMap.put(WIN_MATCH,new BeanLabel(WIN_MATCH,WIN_BAODI));
		baoMap.put(NINE_MATCH,new BeanLabel(NINE_MATCH,NINE_BAODI));
		baoMap.put(SIX_WIN,new BeanLabel(SIX_WIN,SIX_WIN_BAODI));
		baoMap.put(SEVEN_STAR,new BeanLabel(SEVEN_STAR,SEVEN_STAR_BAODI));
		baoMap.put(FOUR_GOAL,new BeanLabel(FOUR_GOAL,FOUR_GOAL_BAODI));
		baoMap.put(PL_3,new BeanLabel(PL_3,PL_3_BAODI));
		baoMap.put(PL_5,new BeanLabel(PL_5,PL_5_BAODI));
		baoMap.put(TC_22_TO_5,new BeanLabel(TC_22_TO_5,TC_22_TO_5_BAODI));
		baoMap.put(TC_36_TO_7,new BeanLabel(TC_36_TO_7,TC_36_TO_7_BAODI));
		baoMap.put(TC_31_TO_7,new BeanLabel(TC_31_TO_7,TC_31_TO_7_BAODI));
		baoMap.put(DLT,new BeanLabel(DLT,DLT_BAODI));
		
		baoMap.put(POKER,new BeanLabel(POKER,POKER_BAODI));
		baoMap.put(SSL,new BeanLabel(SSL,SSL_BAODI));
		
		baoMap.put(DOUBLE_COLOR,new BeanLabel(DOUBLE_COLOR,DOUBLE_COLOR_BAODI));
		baoMap.put(FC_3D,new BeanLabel(FC_3D,FC_3D_BAODI));
		baoMap.put(DC_SFP,new BeanLabel(DC_SFP,DC_SFP_BAODI));
		baoMap.put(DC_JQS,new BeanLabel(DC_JQS,DC_JQS_BAODI));
		baoMap.put(DC_SXPDS,new BeanLabel(DC_SXPDS,DC_SXPDS_BAODI));
		baoMap.put(DC_BF,new BeanLabel(DC_BF,DC_BF_BAODI));
		baoMap.put(DC_BQCSFP,new BeanLabel(DC_BQCSFP,DC_BQCSFP_BAODI));
		baoMap.put(AOYUN_2008,new BeanLabel(AOYUN_2008,AOYUN_2008_BAODI));
		
		baoMap.put(FC_15_TO_5,new BeanLabel(FC_15_TO_5,FC_15_TO_5_BAODI));
		baoMap.put(FC_25_TO_5,new BeanLabel(FC_25_TO_5,FC_25_TO_5_BAODI));
		baoMap.put(FC_DF_61,new BeanLabel(FC_DF_61,FC_DF_61_BAODI));
		baoMap.put(FC_QLC,new BeanLabel(FC_QLC,FC_QLC_BAODI));
		baoMap.put(FC_K3,new BeanLabel(FC_K3,FC_K3_BAODI));
		
		/************************** 各玩法保底变量 start  *******************************/
	}
	
	/**
	 * 根据彩种编码获取彩种名称
	 * @param lotteryId
	 * @return
	 * @author peidw 
	 */
	public static String getLotteryNameByLotteryId(Integer lotteryId){
		if (map.containsKey(lotteryId))
			return map.get(lotteryId).getLabel();
		else 
			return null;
	}

	public static int getLotteryIdByLotteryName(String lotteryName){
		Set<Integer> keys = map.keySet();
		for (Integer key : keys) {
			BeanLabel bean = map.get(key);
			if (bean.getLabel().equals(lotteryName))
				return Integer.parseInt(String.valueOf(bean.getValue()));
		}
		
		return -1;
	}
	
}