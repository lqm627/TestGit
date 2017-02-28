package com.wechat.webapi.web.config;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springside.modules.mapper.JsonMapper;

@ConfigurationProperties(prefix = "common", locations = "classpath:config/common.properties")
public class CommonData {

	private Map<String, String> flags;
	private String[] betslips;
	private String[] accounts;
	private String[] mslots;
	private String[] regs;
	private Map<String, String> spmarkets;
	
	@PostConstruct
	public void init() {
		System.out.println(JsonMapper.nonEmptyMapper().toJson(flags));
		System.out.println(JsonMapper.nonEmptyMapper().toJson(betslips));
		System.out.println(JsonMapper.nonEmptyMapper().toJson(accounts));
		System.out.println(JsonMapper.nonEmptyMapper().toJson(mslots));
		System.out.println(JsonMapper.nonEmptyMapper().toJson(regs));
		System.out.println(JsonMapper.nonEmptyMapper().toJson(spmarkets));
		
	}

	public String getFlag(String type) {
		if (flags.containsKey(type)) {
			return flags.get(type).split(",")[0];
		}

		return null;
	}

	public String getFlagLogo(String type) {
		if (flags.containsKey(type)) {
			return flags.get(type).split(",")[1];
		}

		return null;
	}

	public String getSpSortName(String sort) {
		if (spmarkets.containsKey(sort)) {
			return spmarkets.get(sort);
		}

		return null;
	}

	public String getSpSort(String name) {
		if (spmarkets.values().contains(name)) {
			int i = 0;
			for (String n : spmarkets.values()) {
				if (name.equals(n)) {
					return (String) spmarkets.keySet().toArray()[i];
				}
				i++;
			}
		}
		return null;
	}

	public Map<String, String> getSpmarkets() {
		return spmarkets;
	}

	public void setSpmarkets(Map<String, String> spmarkets) {
		this.spmarkets = spmarkets;
	}

	public Map<String, String> getFlags() {
		return flags;
	}

	public void setFlags(Map<String, String> flags) {
		this.flags = flags;
	}

	public String[] getBetslips() {
		return betslips;
	}

	public void setBetslips(String[] betslips) {
		this.betslips = betslips;
	}

	public String[] getMslots() {
		return mslots;
	}

	public void setMslots(String[] mslots) {
		this.mslots = mslots;
	}

	public String[] getAccounts() {
		return accounts;
	}

	public void setAccounts(String[] accounts) {
		this.accounts = accounts;
	}

	public String[] getRegs() {
		return regs;
	}

	public void setRegs(String[] regs) {
		this.regs = regs;
	}
}
