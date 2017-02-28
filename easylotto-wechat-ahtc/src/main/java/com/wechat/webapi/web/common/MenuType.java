package com.wechat.webapi.web.common;

public enum MenuType {
	
	SUB(1, "subscribe", "关注"),
	MEMBER(2, "member", "会员中心"),
	PICKTOOLS(3, "picktools", "模拟选号"),
	VOTE(4, "vote", "创意评选"),
	noactivity(5, "noactivity", "没有活动"),
	activity30(4, "activity30", "春联活动"),
	activity31(4, "activity31", "顶呱刮活动"),
//	UNSUB(0, "unsubscribe", "取消关注")
	;
	private int type;
	private String name;
	private String key;

	MenuType(int type, String key, String name) {
		this.type = type;
		this.name = name;
		this.key = key;
	}

	public static int getType(String key) {
		for (MenuType menu : values()) {
			if (menu.key.equals(key)) {
				return menu.type;
			}
		}
		return 0;
	}
	
	
	public String getKey() {
		return this.key;
	}

}
