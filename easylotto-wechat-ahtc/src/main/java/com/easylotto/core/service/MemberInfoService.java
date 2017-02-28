package com.easylotto.core.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;
import com.wechat.webapi.web.thread.UserAccessThread;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
@Transactional
public class MemberInfoService  implements IClientService{
	@Autowired
	private MemberDao memberDao;	
	@Autowired
	private EcpUserInfoService ecpUserInfoService;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();
		try {
			String ip=parameterMap.get("ip");
			Map<String,Object> map=new HashMap<String,Object>();
			EcpUser ecpUser = ecpUserInfoService.findEcpUserById(memberId);
			String openId = ecpUser.getVc_open_id();
			List<Activity> activityIdList=memberDao.getActivityING();
			List<EcpLotteryOpenResult> lotteryOpenResultList=memberDao.getAllNewLotteryInfo();
			map.put("signAble",getSignStatus(openId));
			map.put("memberInfo", memberDao.getMemberInfo(memberId.intValue()));
			if(activityIdList.size()!=0){
				map.put("activityIdList", activityIdList);
			}else{
				map.put("activityIdList", null);
				
			}
			if(lotteryOpenResultList.size()!=0){
				map.put("lotteryOpenResultList", lotteryOpenResultList);
			}
			EcpAccessLog accessLog = new EcpAccessLog();
			if(memberId!=null){
				 accessLog.setInt_account_id(memberId);
			}
			Date accTime = new Date(System.currentTimeMillis());
			accessLog.setInt_model_type(1);  //1表示会员中心
			accessLog.setVc_ip(ip);
			accessLog.setDt_read_time(accTime);
			new Thread(new UserAccessThread(accessLog)).start();
			responseBean.setData(map);
			responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
		} catch (Exception e) {
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}

		return responseBean;
	}
	

	public String getSignStatus(String openId){
		EcpSignIn ecpSignIn = memberDao.getSignLog(openId);
		if(ecpSignIn == null){
			//改用户从未签到，直接返回1
			return "1";
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String sysDate = sdf.format(date);
			String signDate = sdf.format(memberDao.getSignDate(openId));
			if(sysDate.equals(signDate)){
				//签到日期跟当前日期相同  即今天已签到 
				return "0";
			}else{
				//签到日期跟当前日期不同  即今天未签到 
				return "1";
			}
		}
	}
}
	
	//public List<Member> getMemberInfo(int memberId){
	//return memberDao.getMemberInfo(memberId);

//		public List<Member> getColorBeanLists(int memberId){
//			List<Member> list=memberDao.getColorBeanLists(memberId);
//			for(int i=0;i<list.size();i++){
//				String operType=list.get(i).getInt_oper_type();
//				if(operType.equals("8")){
//					list.get(i).setInt_oper_type("签到");
//				}else if(operType.equals("9")){
//					list.get(i).setInt_oper_type("模拟购彩");
//				}else if(operType.equals("10")){
//					list.get(i).setInt_oper_type("模拟派彩");
//				}else if(operType.equals("7")){
//					list.get(i).setInt_oper_type("赠送");
//				}
//			}
//			
//			return list;
//		}
//		public Pagination getColorBeanLists(Integer currentPage,Integer numPerPage,int memberId) {
//			return memberDao.getColorBeanLists(currentPage, numPerPage, memberId);
//		}
	//	
//		public String  getMemberId(String openId){
//			return memberDao.getMemberId(openId);
//			
//		}
	//	
//		public Map<String,Object> getActivityMemberInfo(int memberId,int activityId){
//			return memberDao.getActivityMemberInfo(memberId, activityId);
//		}
	//	
//		public int getMemberPhone(String phone,int memberId){
//			return memberDao.getMemberPhone(phone,memberId);
//		}
//		public void updateMemberInfo(Member member,int memberId){
//			memberDao.updateMemberInfo(member, memberId);
//		}
	//	
//		public void updateUser(String account,int memberId){
//			memberDao.updateUser(account, memberId);
//		}
	//	
//		public void updateUserInfo(WxMpUser user,Long accountId){
//			memberDao.updateUserInfo(user, accountId);
//		}
	//	
//	public void updateUserStatus(long memberId){
//		memberDao.updateUserStatus(memberId);
//	}
//	
//	public void addUnsubLog(EcpUserFundLog ecpUserFundLog){
//		memberDao.addUnsubLog(ecpUserFundLog);
//	}
//	public List<Activity> getActivityING(){
//		return memberDao.getActivityING();
//	}
	

