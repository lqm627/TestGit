package com.wechat.webapi.web.common;

public final class ResponseErrorMessage {
	
	/**
	 * 今天已经超过500万
	 */
	public final static String DPLR = "1200";
	/**
	 * 再继续买这些注就超过500万
	 */
	public final static String BPEDPL = "1201";
	/**
	 * 单注里有超过50万
	 */
	public final static String BPELPL = "1202";
	
	public final static String ANA = "207";
	public final static String MSG_ANA = "account not active";
	
	public final static String SIE = "103";
	
	/**
	 * 投注系统繁忙 稍后再试
	 */
	public final static String STAKEBET_ITR = "STAKEBET_ITR";//"interception timeout rejection";
	public final static String MSG_STAKEBET_ITR = "interception timeout rejection";
	public final static String MSG_SIE="server internal error";
	
	/**
	 * invalid token
	 * 登录超时 重新登录
	 */
	public final static String STAKEBET_IT = "STAKEBET_IT";//"invalid token";
	public final static String MSG_STAKEBET_IT = "invalid token";
	
	/**
	 * 余额不足
	 */
	public final static String STAKEBET_IFTPTB = "STAKEBET_IFTPTB";//"insufficient funds to place the bet";
	public final static String MSG_STAKEBET_IFTPTB = "insufficient funds to place the bet";

	/**
	 * 比赛已开始
	 */
	public final static String STAKEBET_EHS = "STAKEBET_EHS";//"event has started";
	public final static String MSG_STAKEBET_EHS = "event has started";
	
	/**
	 * 所選擇賠率之剩餘投注上限、請重新輸入
	 */
	public final static String STAKEBET_STH = "STAKEBET_STH";//"outcome is changed";
	public final static String MSG_STAKEBET_STH = "stake too high";//"outcome is changed";
	
	/**
	 * 所選擇賠率之剩餘投注上限、請重新輸入
	 */
	public final static String STAKEBET_STL = "STAKEBET_STL";//"outcome is changed";
	public final static String MSG_STAKEBET_STL = "stake too low";//"outcome is changed";
	
	/**
	 * 无效投注类型
	 */
	public final static String STAKEBET_IBT="STAKEBET_IBT";
	public final static String MSG_STAKEBET_IBT="invalid bet type";
	
	/**
	 * 赔率已停止下注
	 */
	public final static String OIS = "OIS";//"outcome is suspended";
	public final static String MSG_OIS = "outcome is suspended";

	/**
	 * 赔率已更改
	 */
	public final static String OIC = "OIC";//"outcome is changed";
	public final static String MSG_OIC = "outcome is changed";//"outcome is changed";
	
	/**
	 * 中场投注已结束
	 */
	public final static String HTBE = "HTBE";//"outcome is changed";
	public final static String MSG_HTBE = "HT betting ended";//"outcome is changed";
	
	/**
	 * 用户已被取消
	 */
	public final static String LOGIN_AIC = "LOGIN_AIC";//"account is cancelled";
	public final static String MSG_LOGIN_AIC = "account is cancelled";

	/**
	 * 账户密码错误
	 */
	public final static String LOGIN_ICOCC = "LOGIN_ICOCC";//"invalid combination of customer credentials";
	public final static String MSG_LOGIN_ICOCC = "invalid combination of customer credentials";
	/**
	 * 暂时被锁
	 */
	public final static String LOGIN_AITL = "LOGIN_AITL";//"account is temporarily locked";
	public final static String MSG_LOGIN_AITL = "account is temporarily locked";
	/**
	 * 正在使用中
	 */
	public final static String LOGIN_AIU = "LOGIN_AIU";//"account in use";
	public final static String MSG_LOGIN_AIU = "account in use";
	/**
	 * 被冻结
	 */
	public final static String LOGIN_AIS = "LOGIN_AIS";//"account is suspended";
	public final static String MSG_LOGIN_AIS = "account is suspended";
	/**
	 * 被锁
	 */
	public final static String LOGIN_AIL = "LOGIN_AIL";//"account is locked";
	public final static String MSG_LOGIN_AIL = "account is locked";
	
	public final static String LOGIN_ANA = "LOGIN_ANA";// "account is locked";
	public final static String MSG_LOGIN_ANA = "account not active";

	public final static String BANK_MAL = "BANK_MAL";
	public final static String MSG_BANK_MAL = "min amount limit";
	
	public final static String BANK_BNE = "BANK_BNE";
	public final static String MSG_BNE = "balance not enough";
	
	
	public final static String SUCCESS = "success";
	public final static String ERROR = "error";
	public final static String ERROR_NL = "ERROR_NL";
	public final static String MSG_ERROR_NL = "not login";
	public final static String ERROR_TO = "time out";
	
	/**
	 * 存取款
	 */
	//信用卡
	public final static String Credit = "Credit";
	//现金
	public final static String Cash = "Cash";
	//银行卡转账
	public final static String BankTransfers = "BankTransfers";
	//支票
	public final static String Cheque = "Cheque";
	
	
	
	public final static String TIMEOUT = "timeout";
	
	
}
