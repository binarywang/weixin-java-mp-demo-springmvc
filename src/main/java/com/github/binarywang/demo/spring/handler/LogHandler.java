package com.github.binarywang.demo.spring.handler;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import com.github.binarywang.demo.spring.config.WxConfig;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
@Component
public class LogHandler extends AbstractHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
      Map<String, Object> context, WxMpService wxMpService,
      WxSessionManager sessionManager) {
    this.logger.info("\n接收到请求消息，内容：【{}】", ToStringBuilder
      .reflectionToString(wxMessage, ToStringStyle.MULTI_LINE_STYLE));
    return null;
  }

  @Override
  protected WxConfig getWxConfig() {
    return null;
  }

}
