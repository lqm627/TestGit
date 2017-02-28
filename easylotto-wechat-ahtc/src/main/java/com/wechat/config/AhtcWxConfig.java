package com.wechat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author
 *
 */

@ConfigurationProperties(prefix = "ahtc.wechat", locations = "file:config/wx-ahtc.properties")
public class AhtcWxConfig extends WxConfig {

	private String token;

	private String appid;

	private String appsecret;

	private String aesKey;

	private String type;

	private String key;

	private String server;

	private String webserver;

	private String front;

	private String picktools;

	private String vote;
	
	private String noactivity;

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

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getPicktools() {
		return picktools;
	}

	public void setPicktools(String picktools) {
		this.picktools = picktools;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}
	

	public String getNoactivity() {
		return noactivity;
	}

	public void setNoactivity(String noactivity) {
		this.noactivity = noactivity;
	}

	public String[] getUrls() {
		String[] urls = new String[10];
		urls[0] = getServer();
		urls[1] = getWebserver();
		urls[2] = getFront();
		urls[3] = getPicktools();
		urls[4] = getVote();
		urls[5]=getNoactivity();
		return urls;
	}

	@Override
	public WxAccountEnum getWxAccountEnum() {
		return WxAccountEnum.AHTC;
	}

}
