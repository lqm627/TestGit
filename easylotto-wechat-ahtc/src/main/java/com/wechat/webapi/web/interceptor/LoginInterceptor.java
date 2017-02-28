package com.wechat.webapi.web.interceptor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.easylotto.core.util.SessionUtil;


public class LoginInterceptor implements HandlerInterceptor {
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		String key = SessionUtil.getMemberKey(request);
		
		//if(logger.isInfoEnabled()) logger.info(" ----------------------------------->>>>>> user key = "+ key + "\n\n");
		
		if(StringUtils.isEmpty(key)) {
			response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
			String rs = "{\"errorCode\": \"" + ResponseErrorMessage.TIMEOUT + "\",\"systime\": \"" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "\"}";
			if(logger.isInfoEnabled()) logger.info(rs);
			response.getWriter().write(rs);
			return false;
		}
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
	
}
