package com.wechat.webapi.web.config;

import java.util.Locale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;

/**
 * Spring Context.
 * 
 * @version 1.0
 * 
 * @author MAS BY
 */

@Configuration
public class SpringContext implements ServletContextListener
{
	private static ApplicationContext context;

	public static ApplicationContext getContext()
	{
		return context;
	}
	
	public static <L> L getBean(Class<L> clazz)
	{
		return
				context.getBean(clazz);
	}
	
	public static Object getBean(String name)
	{
		return context.getBean(name);
	}

	public void contextDestroyed(ServletContextEvent event) {
		
	}
	
	public void contextInitialized(ServletContextEvent event) {
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}
	
	public static String getMessage(String code, Object[] args)
	{
	    LocaleResolver localLocaleResolver = (LocaleResolver)getBean(LocaleResolver.class);
	    Locale localLocale = localLocaleResolver.resolveLocale(null);
	    return context.getMessage(code, args, localLocale);
	}
	
	public static String getMessage(String code)
    {
        LocaleResolver localLocaleResolver = (LocaleResolver)getBean(LocaleResolver.class);
        Locale localLocale = localLocaleResolver.resolveLocale(null);
        return context.getMessage(code, null, localLocale);
    }
	
	public static boolean containsBean(String name){
		return context.containsBean(name);
	}
}
