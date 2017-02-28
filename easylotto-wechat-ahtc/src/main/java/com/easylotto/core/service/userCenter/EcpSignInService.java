package com.easylotto.core.service.userCenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.core.dao.EcpSignInDao;
import com.easylotto.core.entity.PushMsg;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserInfo;

import me.chanjar.weixin.common.exception.WxErrorException;

@Service
@Transactional
public class EcpSignInService {
	
	private final Logger logger = LogManager.getLogger(EcpSignInService.class);
	
	
	@Autowired
	private EcpUserInfoService ecpUserInfoService;
	
	@Autowired private EcpSignInDao ecpSignInDao;
	
	
	public EcpSignIn findById(String openId){
		return this.ecpSignInDao.findById(openId);
	}
	
	
	public PushMsg updateSignIn(String openId,String nickName, EcpSignIn ecpSignIn, EcpUserInfo ecpUserInfo) throws ParseException, WxErrorException{
		
		
		try {
			long intAccountId = ecpUserInfo.getInt_account_id();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if(ecpSignIn==null){
				//该用户还没有签到记录，为改用户向签到记录表插入一条数据
				Integer signDays = 1;
				if(StringUtils.isBlank(nickName))
					nickName = "";
				
				this.ecpSignInDao.insert(openId, nickName, signDays, intAccountId);
				

				giveBean(openId, signDays);
				
				double beanCount = signDays*2;
				//添加积分交易记录
				addFundLog(intAccountId, beanCount, 4, 8);
				
				//签到成功  回复消息     因为微信原因  暂时无法解决
//				pushMsgToUser(signDays, beanCount, openId);
				PushMsg msg = new PushMsg();
				msg.setBeanCount(beanCount);
				msg.setOpenId(openId);
				msg.setSignDays(signDays);
				return msg;
			}else{
				/*如果该用户已有签到记录 则直接更新该用户签到记录
				*  第一步应该判断用户当天是否已经签到  防止通过js跳过校验无限签到
				*  获取到已有的签到时间和连续签到天数
				*  将当前时间减掉签到时间 如果结果小于等于1则将现有签到天数+1
				*  如果结果大于1 则将签到天数置为1
				*/
				
				Integer oldSignDays = ecpSignIn.getIntSignDays();
				//将日期统一转换成long类型
				String signDate = sdf.format(ecpSignIn.getDtSignTime());
				String sysDate = sdf.format(new Date());
				
				long longSignDate = sdf.parse(signDate).getTime();
				long longSysDate = sdf.parse(sysDate).getTime();
				
				if(longSysDate - longSignDate == 0){
					return null;
				}else if(longSysDate - longSignDate == 86400000){
					//即两个日期相隔一天，判断为连续签到 执行连续签到的update操作
					Integer newSignDays = oldSignDays+1;
					
					this.ecpSignInDao.update(new Object[]{newSignDays, openId});
					giveBean(openId, newSignDays);
					
					double beanCount = newSignDays>5 ? 10 : newSignDays*2;
					//赠送完积分 添加积分明细
					addFundLog(intAccountId, beanCount, 4, 8);
					
					//签到成功  回复消息
//					pushMsgToUser(newSignDays, beanCount, openId);
					
					PushMsg msg = new PushMsg();
					msg.setBeanCount(beanCount);
					msg.setOpenId(openId);
					msg.setSignDays(newSignDays);
					return msg;
				}else{
					//不是连续签到执行非连续签到的update操作
					
					Object[] args = {1,openId};
					this.ecpSignInDao.update(args);
					
					giveBean(openId, 1);
					
					double beanCount = 2;
					addFundLog(intAccountId, beanCount, 4, 8);
					
					//签到成功  回复消息
					PushMsg msg = new PushMsg();
					msg.setBeanCount(beanCount);
					msg.setOpenId(openId);
					msg.setSignDays(1);
					return msg;
//					pushMsgToUser(1, beanCount, openId);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void giveBean(String openId,Integer signDays){
		double beanCount;
		String sql = "update ecp_user set dec_lottery_bean = dec_lottery_bean + ? where vc_open_id = ?";
		if(signDays>5){
			//如果连续签到天数已经大于5天 则赠送积分数量固定为10
			beanCount = 10;
			Object[] args = {beanCount,openId};
			this.ecpSignInDao.update(sql, args);
			
		}else{
			//如果连续签到天数小于5天 则赠送积分数量等于 连续签到天数*2
			beanCount = signDays*2;
			Object[] args = {beanCount,openId};
			this.ecpSignInDao.update(sql, args);
		}
	}
	/**
	 * 添加用户资金变动日志  暂时只提供给彩豆交易使用
	 * @param accountId    用户ID
	 * @param count	   	      交易金额
	 * @param accountType  账户类型   4:积分账户
	 * @param operType     交易类型   8:签到
	 */
	public void addFundLog(Long accountId,Double count,Integer accountType,Integer operType){
		//获取彩豆余额
		double balance = getBeanBalance(accountId);
		Date operTime = new Date();
		final EcpUserFundLog ecpUserFundLog = new EcpUserFundLog();
		
		ecpUserFundLog.setInt_account_id(accountId);
		ecpUserFundLog.setInt_oper_type(operType);
		ecpUserFundLog.setDt_oper_time(operTime);
		ecpUserFundLog.setDec_amount(count);
		ecpUserFundLog.setVc_bill_no("0");
		ecpUserFundLog.setDec_balance(balance);
		ecpUserFundLog.setInt_account_type(accountType);
		this.ecpSignInDao.insert(ecpUserFundLog);
	}
	
	public Double getBeanBalance(Long accountId){
		double balance = 0;
		String sql = "select dec_lottery_bean from ecp_user where int_account_id=?";
		balance = this.ecpSignInDao.query(sql, new Object[]{accountId});
		return balance;
	}
	
}
