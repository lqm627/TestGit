package com.wechat.webapi.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wechat.webapi.web.filter.LoggerMDCFilter;
import com.wechat.webapi.web.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
public class WebApiConfig extends WebMvcConfigurerAdapter {
	/**
	 * 配置拦截器
	 * 
	 * @param registry
	 */  
    public void addInterceptors(InterceptorRegistry registry) {  
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/member/**", "/activity/**", "/analogbuy/**", "/lotteryBuyInfo/**", "/region", "/sign/**", "/analogbuy/**/data")
				.excludePathPatterns("/toMember/toLogin/**", "/wechatcore", "/lottery/**", "/lotteryterm/**", "/lotteryterm/**/**", "/trend/chart/**/**/data", "/openResult/**/open", "/news/**", "/nearbet/ticketInfo");
//		registry.addInterceptor(new LangInterceptor()).addPathPatterns("/**");
    }  

	@Bean
	public LoggerMDCFilter loggerMDCFilter() {
		return new LoggerMDCFilter();
	}
	
}
