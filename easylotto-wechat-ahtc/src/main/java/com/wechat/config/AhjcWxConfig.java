package com.wechat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author
 *
 */

@ConfigurationProperties(prefix = "ahjc.wechat", locations = "file:config/wx-ahjc.properties")
public class AhjcWxConfig extends WxConfig {

	private String token;

	private String appid;

	private String appsecret;

	private String aesKey;

	private String type;

	private String key;

	private String server;

	private String webserver;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getWebserver() {
		return webserver;
	}

	public void setWebserver(String webserver) {
		this.webserver = webserver;
	}



	public String[] getUrls() {
		String[] urls = new String[10];
		urls[0] = getServer();
		urls[1] = getWebserver();
		return urls;
	}

	@Override
	public WxAccountEnum getWxAccountEnum() {
		return WxAccountEnum.AHJC;
	}

}
