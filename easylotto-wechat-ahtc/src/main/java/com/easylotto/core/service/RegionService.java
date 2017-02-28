package com.easylotto.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.dao.RegionDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.EcpRogion;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
@Transactional
public class RegionService  implements IClientService{
	@Autowired
	private RegionDao regionDao;	
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();
		try {
			responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
			responseBean.setData(findBy());
		} catch (Exception e) {
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}
		return responseBean;
	}
	
public Map<String, Object> findBy(){
		
		Map<String, Object> data = new HashMap<String, Object>(0);
		List<EcpRogion> list = this.regionDao.findBy(0l);
		if(null == list || 0 == list.size()) return data;
		for(EcpRogion bean : list){
			put(bean, data);
		}
		return data;
	}
	
	public void put(EcpRogion bean, Map<String, Object> data){
		List<EcpRogion> list = this.regionDao.findBy(bean.getInt_rec_id());
		if(null != list && 0 < list.size()){
			Map<String, Object> values = new HashMap<String, Object>(0);
			for(EcpRogion obj : list){
				put(obj, values);
			}
			data.put(bean.getName(), values);
		}else{
			data.put(bean.getName(), bean.getInt_rec_id()+"");
		}
	}

	


}
	
	