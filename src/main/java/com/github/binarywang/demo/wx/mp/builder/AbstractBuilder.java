package com.github.binarywang.demo.wx.mp.builder;

import com.github.binarywang.demo.wx.mp.service.WeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class AbstractBuilder {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public abstract WxMpXmlOutMessage build(String content,
      WxMpXmlMessage wxMessage, WeixinService service) ;
}
