package com.github.binarywang.demo.spring.config;

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

    public abstract WxAccountEnum getWxAccountEnum();

    public int getPubId() {
        return getWxAccountEnum().getPubid();
    }

}
