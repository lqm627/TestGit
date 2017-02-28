package com.easylotto.core.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.easylotto.core.dao.user.EcpUserDAO;
import com.easylotto.core.dao.user.EcpUserFundLogDAO;
import com.easylotto.core.dao.user.EcpUserLogDAO;

import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserLog;


@Service
@Transactional
public class CommonService {
	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private EcpUserFundLogDAO ecpUserFundLogDAO;
	@Autowired
	private EcpUserLogDAO ecpUserLogDAO;
//	@Autowired
//	private EcpVerifyInfoDAO ecpVerifyInfoDAO;
//	@Autowired
//	private EcpSendMessageDao ecpSendMessageDao;
//	@Autowired
//	private EcpStationLogDAO ecpStationLogDAO;
//	@Autowired
//	private EcpStationFundLogDAO ecpStationFundLogDAO;
	@Autowired
	private EcpUserDAO ecpUserDAO;
	
	/**
	 * 资金流向操作日志
	 * @param 发生金额
	 * @param 发生后金额
	 * @param 账户类型
	 * @param 业务ID
	 * @param 账户ID
	 */
	public void addEcpUserFundLog(long accountId, double amount, double balance, int accountType, int operType, String billNo) {
		EcpUserFundLog ecpUserFundLog = new EcpUserFundLog();
		ecpUserFundLog.setDec_amount(amount);//发生金额
		ecpUserFundLog.setDec_balance(balance);//发生后余额
		ecpUserFundLog.setInt_account_id(accountId);
		ecpUserFundLog.setInt_account_type(accountType);//可提现账户
		ecpUserFundLog.setInt_oper_type(operType);//系统自动扣款
		ecpUserFundLog.setVc_bill_no(billNo);
		ecpUserFundLogDAO.save(ecpUserFundLog);//新增一条资金变化记录
	}
	
	/**
	 * 用户操作日志
	 * @param accountId 用户ID
	 * @param optMethod 操作方式  1：网站、2：WAP、3：客户端
	 * @param optType 	操作类型
	 * @param newInfo 	操作后信息
	 * @param oldInfo 	操作前信息
	 * @param optName 	操作人
	 */
	public void addUserOperLog(long accountId, int optMethod, int optType, String newInfo, String oldInfo, String optName) {
		EcpUserLog ecpUserLog = new EcpUserLog();
		ecpUserLog.setInt_account(accountId);
		ecpUserLog.setInt_opt_method(optMethod);
		ecpUserLog.setInt_opt_type(optType);
		ecpUserLog.setVc_new_info(newInfo);
		ecpUserLog.setVc_old_info(oldInfo);
		ecpUserLog.setVc_opt_name(optName);
		ecpUserLogDAO.save(ecpUserLog);
	}
	
	/**
	 * 添加站长操作日志信息
	 * @param stationId
	 * @param optMethod
	 * @param optType
	 * @param newInfo
	 * @param oldInfo
	 * @param optName
	 */
//	public void addStationLog(long stationId, int optMethod, int optType,
//			String newInfo, String oldInfo, String optName) {
//		EcpStationLog ecpStationLog = new EcpStationLog();
//		ecpStationLog.setInt_station_id(stationId);
//		ecpStationLog.setInt_opt_type(optType);
//		ecpStationLog.setInt_opt_method(optMethod);
//		ecpStationLog.setVc_opt_name(optName);
//		ecpStationLog.setVc_old_info(oldInfo);
//		ecpStationLog.setVc_new_info(newInfo);
//		ecpStationLogDAO.save(ecpStationLog);
//	}
	
	/**
	 * 发送验证码
	 * @param accountId
	 * @param mobile
	 * @param type
	 * @return
	 */
//	public int sendRandomCode(String accountId, String mobile, int type) {
//		//判断手机号码是否合法
//		if (MobileVerify.checkMobile(mobile) == false)
//			return -2;
//		
//		long account = 0;
//		if (StringUtils.isNotEmpty(accountId))
//			account = Long.parseLong(accountId);
//		
//		Date nowDate = new Date();
//		String randomCode = StringTools.randomSmsCode();
//		EcpVerifyInfo ecpVerifyInfo = new EcpVerifyInfo();
//		ecpVerifyInfo.setInt_account_id(account);
//		ecpVerifyInfo.setDt_valid_time(DateUtils.addMinutes(nowDate, 10));
//		ecpVerifyInfo.setInt_type(type);
//		ecpVerifyInfo.setInt_status(0);
//		ecpVerifyInfo.setVc_verify_data(mobile);
//		ecpVerifyInfo.setVc_verify_code(DigestUtils.md5Hex(randomCode));
//		ecpVerifyInfoDAO.add(ecpVerifyInfo);
//		
//		//调用发送验证码短信接口
//		String infos=SendIdentifyingCode.sendSms(mobile, mobile,randomCode);
//		JSONObject json = JSONObject.parseObject(infos);
//		int rs = Integer.parseInt(json.get("res_code").toString());
//		EcpSendMessage ecpSendMessage = new EcpSendMessage();
//		ecpSendMessage.setUser_name(mobile);
//		ecpSendMessage.setIdentifyingcode(randomCode);
//		ecpSendMessage.setSend_adress(mobile);
//		ecpSendMessage.setType(rs);
//		ecpSendMessage.setSend_time(nowDate);
//		ecpSendMessage.setContent(mobile+","+mobile+","+randomCode);
//		ecpSendMessage.setRemark(infos);
//		ecpSendMessageDao.addMessageInfo(ecpSendMessage);
//		
//		return rs;
//	}
	
//	public String queryArea(String mobile) {
//		
//		String area = null;
//		
//		try {
//			String url = StatData.AREA_URL + "?mobile=" + mobile;
//			area = HCUtils.doGet(url);
//		} catch (Exception e) {
//			logger.error("查询归属地出错", e);
//		}
//		
//		return area;
//	}
	
//	public double queryBalc(String mobile, int type) {
//		
//		double balance = -9999.0;
//		
//		try {
//			String url = StatData.BALC_URL + "?mobile="+mobile+"&type="+type;
//			String s = HCUtils.doGet(url);
//			if (StringUtils.isNotEmpty(s))
//				balance = Double.parseDouble(s);
//		} catch (Exception e) {
//			logger.error("查询话费余额出错", e);
//		}
//		
//		return balance;
//	}
	
//	public String payFee(String mobile, int lotteryType, double amount, int add, long joinId) {
//		String rs = "";
//		try {
//			String url = StatData.FEE_URL + "?mobile="+mobile+"&order="+joinId+
//					"&amount="+amount+"&type=10&lottery="+lotteryType+"&add="+add;
//			rs = HCUtils.doGet(url);
//		} catch (Exception e) {
//			logger.error("扣话费出错", e);
//		}
//		
//		return rs;
//	}
	
	public String getMaxTerm(int lotteryType) {
		String maxTerm = "";
		
		switch (lotteryType) {
		case 27:
			maxTerm = "80";
			break;

		default:
			break;
		}
		
		return maxTerm;
	}
	
	public Date getServerTime() {
		return ecpUserFundLogDAO.queryDateTime();
	}

//	public static void main(String [] args){
//		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/*.xml");
//		CommonService commonService = (CommonService)context.getBean("CommonService");
//		commonService.sendRandomCode("675", "13545055112", 1);
//	}

	/**
	 * 资金流向操作日志
	 * @param 发生金额
	 * @param 发生后金额
	 * @param 账户类型
	 * @param 业务ID
	 * @param 账户ID
	 */
//	public void addEcpStationFundLog(long stationId, double amount, double balance, int accountType, int operType, String billNo) {
//		EcpStationFundLog ecpStationFundLog = new EcpStationFundLog();
//		ecpStationFundLog.setDec_amount(amount);//发生金额
//		ecpStationFundLog.setDec_balance(balance);//发生后余额
//		ecpStationFundLog.setInt_station_id(stationId);
//		ecpStationFundLog.setInt_account_type(accountType);//可提现账户
//		ecpStationFundLog.setInt_oper_type(operType);//系统自动扣款
//		ecpStationFundLog.setVc_bill_no(billNo);
//		ecpStationFundLogDAO.save(ecpStationFundLog);//新增一条资金变化记录
//	}
	
	public EcpUser findLotteryBean(String int_account_id){
		return ecpUserDAO.findBeanCount(int_account_id);
	}
}
