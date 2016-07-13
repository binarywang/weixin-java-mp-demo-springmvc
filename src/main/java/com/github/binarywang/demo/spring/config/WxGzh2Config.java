package com.github.binarywang.demo.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Binary Wang
 *
 */
@Configuration
public class WxGzh2Config extends WxConfig {
    @Value("#{gzh2WxProperties.wx_token}")
    private String token;

    @Value("#{gzh2WxProperties.wx_appid}")
    private String appid;

    @Value("#{gzh2WxProperties.wx_appsecret}")
    private String appsecret;

    @Value("#{gzh2WxProperties.wx_aeskey}")
    private String aesKey;

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public String getAppid() {
        return this.appid;
    }

    @Override
    public String getAppsecret() {
        return this.appsecret;
    }

    @Override
    public String getAesKey() {
        return this.aesKey;
    }

    @Override
    public WxAccountEnum getWxAccountEnum() {
        return WxAccountEnum.GZH2;
    }

}
