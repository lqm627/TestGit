package com.wechat.webapi.web.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	@SuppressWarnings("unchecked")
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2).genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
				.forCodeGeneration(true).pathMapping("/").select().paths(or(regex("/.*")))
				.build().apiInfo(WebApiInfo());
	}

	private ApiInfo WebApiInfo() {
		ApiInfo apiInfo = new ApiInfo("Macauslot Web Platform API", //大标题
				"Web API, for system administrator", //小标题
				"0.9", //版本
				"NO terms of service", "yanzhengxin@revenco.com", //作者
				"The Apache License, Version 2.0", //链接显示文字
				"http://www.apache.org/licenses/LICENSE-2.0.html"//网站链接
		);

		return apiInfo;
	}
}
