package com.github.binarywang.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.demo.spring.config.WxGzh2Config;
import com.github.binarywang.demo.spring.config.WxConfig;
import com.github.binarywang.demo.spring.handler.AbstractHandler;
import com.github.binarywang.demo.spring.handler.MenuHandler;
import com.github.binarywang.demo.spring.handler.MsgHandler;
import com.github.binarywang.demo.spring.handler.SubscribeHandler;
import com.github.binarywang.demo.spring.handler.UnsubscribeHandler;

/**
 * 
 * @author Binary Wang
 *
 */
@Service
public class Gzh2WxService extends BaseWxService {

  @Autowired
  private WxGzh2Config wxConfig;

  @Override
  protected WxConfig getServerConfig() {
    return this.wxConfig;
  }

  @Override
  protected MenuHandler getMenuHandler() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected SubscribeHandler getSubscribeHandler() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected UnsubscribeHandler getUnsubscribeHandler() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected AbstractHandler getLocationHandler() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected MsgHandler getMsgHandler() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected AbstractHandler getScanHandler() {
    // TODO Auto-generated method stub
    return null;
  }

}
