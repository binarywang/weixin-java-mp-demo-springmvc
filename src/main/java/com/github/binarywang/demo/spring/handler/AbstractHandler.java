package com.github.binarywang.demo.spring.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.binarywang.demo.spring.config.WxConfig;
import com.google.gson.Gson;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class AbstractHandler implements WxMpMessageHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected final Gson gson = new Gson();

    protected abstract WxConfig getWxConfig();
    
}
