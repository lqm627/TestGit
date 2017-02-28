package com.wechat.config;

/**
 * 公众号标识的枚举类
 * @author Binary Wang
 *
 */
public enum WxAccountEnum {
    AHTC(0, "体彩安徽"),
    AHJC(1, "竞彩安徽"),
	LQM(2, "乐趣门"),
	JWL(3, "竞玩乐");
	

    private int pubid;
    private String name;

    private WxAccountEnum(int pubid, String name) {
        this.name = name;
        this.pubid = pubid;
    }

    public int getPubid() {
        return this.pubid;
    }

    public String getName() {
        return this.name;
    }

    public static int queryPubid(String wxCode) {
        return WxAccountEnum.valueOf(wxCode.toUpperCase()).getPubid();
    }

    public static String queryWxCode(int pubid) {
        for (WxAccountEnum e : values()) {
            if (e.getPubid() == pubid) {
                return e.name().toLowerCase();
            }
        }

        return null;
    }
}
