package com.github.binarywang.demo.spring.builder;

import com.github.binarywang.demo.spring.service.BaseWxService;

import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public class ImageBuilder extends AbstractBuilder {

  @Override
  public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
      BaseWxService service) {

    WxMpXmlOutImageMessage m = WxMpXmlOutMessage.IMAGE().mediaId(content)
        .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
        .build();

    return m;
  }

}
