package com.github.binarywang.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.demo.spring.config.WxGzh1Config;
import com.github.binarywang.demo.spring.config.WxConfig;
import com.github.binarywang.demo.spring.handler.AbstractHandler;
import com.github.binarywang.demo.spring.handler.MenuHandler;
import com.github.binarywang.demo.spring.handler.MsgHandler;
import com.github.binarywang.demo.spring.handler.SubscribeHandler;
import com.github.binarywang.demo.spring.handler.UnsubscribeHandler;
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1LocationHandler;
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1MenuHandler;
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1MsgHandler;
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1SubscribeHandler;
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1UnSubscribeHandler;

/**
 * 
 * @author Binary Wang
 *
 */
@Service
public class Gzh1WxService extends BaseWxService {
  @Autowired
  private WxGzh1Config wxConfig;

  @Autowired
  private Gzh1LocationHandler locationHandler;
  
  @Autowired
  private Gzh1MenuHandler menuHandler;
  
  @Autowired
  private Gzh1MsgHandler msgHandler;
  
  @Autowired
  private Gzh1UnSubscribeHandler unSubscribeHandler;
  
  @Autowired
  private Gzh1SubscribeHandler subscribeHandler;

  @Override
  protected WxConfig getServerConfig() {
    return this.wxConfig;
  }

  @Override
  protected MenuHandler getMenuHandler() {
    return this.menuHandler;
  }

  @Override
  protected SubscribeHandler getSubscribeHandler() {
    return this.subscribeHandler;
  }

  @Override
  protected UnsubscribeHandler getUnsubscribeHandler() {
    return this.unSubscribeHandler;
  }

  @Override
  protected AbstractHandler getLocationHandler() {
    return this.locationHandler;
  }

  @Override
  protected MsgHandler getMsgHandler() {
    return this.msgHandler;
  }

  @Override
  protected AbstractHandler getScanHandler() {
    return null;
  }

}
