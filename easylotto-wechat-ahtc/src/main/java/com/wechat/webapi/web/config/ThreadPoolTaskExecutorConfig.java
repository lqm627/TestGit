package com.wechat.webapi.web.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ConfigurationProperties(prefix = "thread.pool.task")
public class ThreadPoolTaskExecutorConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTaskExecutorConfig.class);
	
	// <!-- 线程池维护线程的最少数量 -->
	private int corePoolSize;
	// <!-- 线程池维护线程所允许的空闲时间 -->
	private int keepAliveSeconds;
	// <!-- 线程池维护线程的最大数量 -->
	private int maxPoolSize;
	// <!-- 线程池所使用的缓冲队列 -->
	private int queueCapacity;

	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@PostConstruct
	public void initialize() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		// 线程池所使用的缓冲队列
		threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
		// 线程池维护线程的最少数量
		threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
		// 线程池维护线程的最大数量
		threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
		// 线程池维护线程所允许的空闲时间
		threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
		threadPoolTaskExecutor.initialize();
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
		logger.info("--------------->>>   threadPoolTaskExecutor >>>> " + threadPoolTaskExecutor.getActiveCount());
	}
	
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		return threadPoolTaskExecutor;
	}

	
	@PreDestroy
	public void destroy(){
		logger.info("--------------->>>   threadPoolTaskExecutor >>>> status is destroy");
	}
	
	
	
	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getKeepAliveSeconds() {
		return keepAliveSeconds;
	}

	public void setKeepAliveSeconds(int keepAliveSeconds) {
		this.keepAliveSeconds = keepAliveSeconds;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getQueueCapacity() {
		return queueCapacity;
	}

	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

}
