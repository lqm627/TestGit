package com.wechat.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qq.eamil", locations = "classpath:config/email.properties")
public class EmailConfig {

	private String sendUser;
	private String key;
	private String sign;
	private boolean state;
	private String group;
	private String groupgz;
	private String groupah;

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroupgz() {
		return groupgz;
	}

	public void setGroupgz(String groupgz) {
		this.groupgz = groupgz;
	}

	public String getGroupah() {
		return groupah;
	}

	public void setGroupah(String groupah) {
		this.groupah = groupah;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String[] getGroups() {
		if (StringUtils.isNotBlank(group)) {
			if (StringUtils.contains(group, ",")) {
				String[] emails = group.split(",");
				return emails;
			}
			return new String[] { group };
		}
		return null;
	}

	public String[] getGroupgzs() {
		if (StringUtils.isNotBlank(groupgz)) {
			if (StringUtils.contains(groupgz, ",")) {
				String[] emails = groupgz.split(",");
				return emails;
			}
			return new String[] { groupgz };
		}
		return null;
	}

	public String[] getGroupahs() {
		if (StringUtils.isNotBlank(groupah)) {
			if (StringUtils.contains(groupah, ",")) {
				String[] emails = groupah.split(",");
				return emails;
			}
			return new String[] { groupah };
		}
		return null;
	}

}
