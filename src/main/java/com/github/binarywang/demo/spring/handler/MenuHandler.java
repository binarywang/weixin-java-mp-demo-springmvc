package com.github.binarywang.demo.spring.handler;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.demo.spring.builder.AbstractBuilder;
import com.github.binarywang.demo.spring.builder.ImageBuilder;
import com.github.binarywang.demo.spring.builder.TextBuilder;
import com.github.binarywang.demo.spring.dto.WxMenuKey;
import com.github.binarywang.demo.spring.service.BaseWxService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class MenuHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
      Map<String, Object> context, WxMpService wxMpService,
      WxSessionManager sessionManager) {
    BaseWxService weixinService = (BaseWxService) wxMpService;

    String key = wxMessage.getEventKey();
    WxMenuKey menuKey = null;
    try {
      menuKey = JSON.parseObject(key, WxMenuKey.class);
    } catch (Exception e) {
      return WxMpXmlOutMessage.TEXT().content(key)
          .fromUser(wxMessage.getToUserName())
          .toUser(wxMessage.getFromUserName()).build();
    }

    AbstractBuilder builder = null;
    switch (menuKey.getType()) {
    case WxConsts.XML_MSG_TEXT:
      builder = new TextBuilder();
      break;
    case WxConsts.XML_MSG_IMAGE:
      builder = new ImageBuilder();
      break;
    case WxConsts.XML_MSG_VOICE:
      break;
    case WxConsts.XML_MSG_VIDEO:
      break;
    case WxConsts.XML_MSG_NEWS:
      break;
    default:
      break;
    }

    if (builder != null) {
      try {
        return builder.build(menuKey.getContent(), wxMessage, weixinService);
      } catch (Exception e) {
        this.logger.error(e.getMessage(), e);
      }
    }

    return null;

  }

}
