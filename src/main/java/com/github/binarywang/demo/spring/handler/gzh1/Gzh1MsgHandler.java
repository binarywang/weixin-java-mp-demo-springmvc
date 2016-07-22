package com.github.binarywang.demo.spring.handler.gzh1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.github.binarywang.demo.spring.config.WxConfig;
import com.github.binarywang.demo.spring.config.WxGzh1Config;
import com.github.binarywang.demo.spring.handler.MsgHandler;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Gzh1MsgHandler extends MsgHandler {
  @Autowired
  private  WxGzh1Config wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

}
