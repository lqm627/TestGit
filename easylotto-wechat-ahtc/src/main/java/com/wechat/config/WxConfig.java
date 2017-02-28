package com.wechat.config;

/**
 * 微信配置的抽象实现类
 * @author Binary Wang
 *
 */
public abstract class WxConfig {
	
    public abstract String getToken();

    public abstract String getAppid();

    public abstract String getAppsecret();

    public abstract String getAesKey();
    
    public abstract String getType();
    
    public abstract String getKey();

    public abstract WxAccountEnum getWxAccountEnum();

    public int getPubId() {
        return getWxAccountEnum().getPubid();
    }

}
