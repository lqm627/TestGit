package com.wechat.webapi.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.easylotto.core.util.SessionUtil;

/**
 * 存放在MDC中的数据，log4j可以直接引用并作为日志信息打印出来.
 * 
 * <pre>
 * 示例使用:
 * log4j.appender.stdout.layout.conversionPattern=%d [%X{loginUserId}/%X{req.remoteAddr}/%X{req.id} - %X{req.requestURI}?%X{req.queryString}] %-5p %c{2} - %m%n
 * </pre>
 * @author lotto
 * @version 1.2
 */
public class LoggerMDCFilter extends OncePerRequestFilter implements Filter{
	
	private static Logger logger = LoggerFactory.getLogger(LoggerMDCFilter.class);
    
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain chain)throws ServletException,IOException {
        try {
            MDC.put("req.requestURI", StringUtils.defaultString(request.getRequestURI()));
			MDC.put("req.remoteAddr", StringUtils.defaultString(getIpAddr(request)));
            //为每一个请求创建一个ID，方便查找日志时可以根据ID查找出一个http请求所有相关日志
			
			String key = SessionUtil.getMemberKey(request);
			if(null != key){
				MDC.put("req.user", "MEMBER_KEY_"+key);
			}
			else{
				SessionUtil.setMemberKey(request, "5934805294001960078");
			}
			HttpSession session = request.getSession();
			if(null != session){
				MDC.put("req.id", "SESSION_ID_" + session.getId());
			}
            chain.doFilter(request, response);
        }finally {
            clearMDC();
        }
    }

    private void clearMDC(){
    	MDC.clear();
    }

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	
}
