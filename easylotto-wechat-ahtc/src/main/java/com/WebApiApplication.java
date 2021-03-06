package com;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ImportResource(locations={"classpath:applicationContext-service.xml"})//导入xml配置文件
//@EnableConfigurationProperties({ CommonData.class })
@ComponentScan(basePackages={"com.easylotto", "com.wechat"})
public class WebApiApplication {
	/**
	 * 启动嵌入式的Tomcat并初始化Spring环境.
	 * 
	 * 无 applicationContext.xml 和 web.xml, 靠下述方式进行配置：
	 * 
	 * 1. 扫描当前package下的class设置自动注入的Bean<br/>
	 * 2. 也支持用@Bean标注的类配置Bean <br/>
	 * 3. 根据classpath中的三方包Class及集中的application.properties条件配置三方包，如线程池 <br/>
	 * 4. 也支持用@Configuration标注的类配置三方包.
	 */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebApiApplication.class); 
//		ConfigurableApplicationContext applicationContext = 
				app.run(args);
		
	}
	
	  @Bean  
	    public MultipartConfigElement multipartConfigElement() {  
	        MultipartConfigFactory factory = new MultipartConfigFactory();  
	        factory.setMaxFileSize("99999999999KB");  
	        factory.setMaxRequestSize("99999999999KB");  
	        return factory.createMultipartConfig();  
	    } 
	
	
}
