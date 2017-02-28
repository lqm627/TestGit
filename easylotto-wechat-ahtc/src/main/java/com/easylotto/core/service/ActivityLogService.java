package com.easylotto.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.core.dao.ActivityLogDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.ActivityLog;
import com.easylotto.core.entity.VoteCumulative;


@Service
@Transactional
public class ActivityLogService {
	
	@Autowired
	private ActivityLogDao activityLogDao;
	
	
	public void save(ActivityLog activityLog){
		activityLogDao.save(activityLog);
	}
	
	public int findActivityMemberPhoneOnlyOneWithoutMyself(String phone,int activityId,int memberId){
		return activityLogDao.findActivityMemberPhoneOnlyOneWithoutMyself(phone, activityId,memberId);
	}

	public String queryDateTime(){
		return activityLogDao.queryDateTime();
	}
	public String getNameByCardNo(String cardNo,int activityId){
		return activityLogDao.getNameByCardNo(cardNo, activityId);
	}
	public int findActivityAccountJoinTimes(Long accountId,int activityId){
		return activityLogDao.findActivityAccountJoinTimes(accountId, activityId);
	}
	public void updateVoteCumuative(VoteCumulative voteCumulative){
		activityLogDao.updateVoteCumuative(voteCumulative);
	}
	public List<VoteCumulative> getVoteCumulativelist(){
		return activityLogDao.getVoteCumulativelist();
	}
	public List<ActivityLog> findCoupletsAccountJoinTimes(Long accountId,int activityId){
		return activityLogDao.findCoupletsAccountJoinTimes(accountId, activityId);
	}
	
	public Activity getActivity(int activityId){
		return activityLogDao.getActivity(activityId);
	}
	public ActivityLog getActivityLog(Long accountId,int activityId){
		return activityLogDao.getActivityLog(accountId, activityId);
	}
	 public String findSerialNumberOnlyone(String serialNmuber,int activityId){
		 return activityLogDao.findSerialNumberOnlyone(serialNmuber,activityId);
	 }
	
}
