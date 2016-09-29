package com.github.binarywang.demo.spring.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.binarywang.demo.spring.config.WxConfig;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 门店审核事件处理
 * 
 * @author 王彬 (Binary Wang)
 */
@Component
public class StoreCheckNotifyHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
      Map<String, Object> context, WxMpService wxMpService,
      WxSessionManager sessionManager) throws WxErrorException {
    // TODO 处理门店审核事件
    return null;
  }

  @Override
  protected WxConfig getWxConfig() {
    return null;
  }

}
