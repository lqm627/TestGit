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

import com.alibaba.fastjson.JSONObject;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.entity.Activity;
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
public class MemberInfoUpdateService  implements IClientService{
	@Autowired
	private MemberDao memberDao;	
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();
		try{
			if("{}".equals(request_data)){
				
			}else{				
				Member member=new Member();
				Map<String,Object> map=new HashMap<String,Object>();
				JSONObject jsonobject = JSONObject.parseObject(request_data);
                member.setVc_name(jsonobject.getString("vc_name"));
                member.setVc_mobile(jsonobject.getString("vc_mobile"));
                member.setVc_email(jsonobject.getString("vc_email"));
                member.setVc_user_province(jsonobject.getString("vc_user_province"));
                member.setVc_user_city(jsonobject.getString("vc_user_city"));
                member.setVc_user_address(jsonobject.getString("vc_user_address"));
                memberDao.updateMemberInfo(member, memberId.intValue());
                memberDao.updateUser(jsonobject.getString("vc_mobile"), memberId.intValue());
		        map.put("submittedResult", 1);
				responseBean.setData(map);
				responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
			}
			
		}catch(Exception e){
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}
		return responseBean;
	}
	


}
	
	