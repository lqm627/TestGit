/**
 * 
 */
package com.easylotto.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easylotto.core.common.Constant;

/**
 * @author CreateName:  UpdateName: 
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see SessionUtil
 * @see CreateDate: 2016年6月29日 下午4:27:03 UpdateDate: 2016年6月29日 下午4:27:03
 * @see Copyright 
 * @since JDK1.7.*
 * @version 1.0
 */
public class SessionUtil {
	
	protected static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);

	
	public static String getMemberKey(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(null != session){
			if(logger.isInfoEnabled()){
				logger.info("  ================= GET SESSION ID ++++++++++++++++    " + session.getId());
			}
			Object token = session.getAttribute(Constant.MEMBER_SESSION_TOKEN);
			if(null != token){
				if(NumberUtils.isNumber(token.toString())){
					return token.toString();
				}
			}
		}
		return null;
	}
	
	public static void setMemberKey(HttpServletRequest request, String key){
		HttpSession session = request.getSession();
		session.setAttribute(Constant.MEMBER_SESSION_TOKEN, key);
		
		if(logger.isInfoEnabled()){
			logger.info("  =================SET SESSION ID ++++++++++++++++    " + session.getId());
		}
	}
}
