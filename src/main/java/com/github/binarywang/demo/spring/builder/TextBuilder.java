package com.github.binarywang.demo.spring.builder;

import com.github.binarywang.demo.spring.service.BaseWxService;

import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public class TextBuilder extends AbstractBuilder {

  @Override
  public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
      BaseWxService service)   {
    WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(content)
        .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
        .build();
    return m;
  }

}
