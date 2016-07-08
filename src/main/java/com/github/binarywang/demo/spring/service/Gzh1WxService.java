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
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1MenuHadler;
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1MsgHadler;
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1SubscribeHadler;
import com.github.binarywang.demo.spring.handler.gzh1.Gzh1UnSubscribeHadler;

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
  private Gzh1MenuHadler menuHadler;
  
  @Autowired
  private Gzh1MsgHadler msgHadler;
  
  @Autowired
  private Gzh1UnSubscribeHadler unSubscribeHadler;
  
  @Autowired
  private Gzh1SubscribeHadler subscribeHadler;

  @Override
  protected WxConfig getServerConfig() {
    return this.wxConfig;
  }

  @Override
  protected MenuHandler getMenuHandler() {
    return this.menuHadler;
  }

  @Override
  protected SubscribeHandler getSubscribeHandler() {
    return this.subscribeHadler;
  }

  @Override
  protected UnsubscribeHandler getUnsubscribeHandler() {
    return this.unSubscribeHadler;
  }

  @Override
  protected AbstractHandler getLocationHandler() {
    return this.locationHandler;
  }

  @Override
  protected MsgHandler getMsgHandler() {
    return this.msgHadler;
  }

  @Override
  protected AbstractHandler getScanHandler() {
    return null;
  }

}
