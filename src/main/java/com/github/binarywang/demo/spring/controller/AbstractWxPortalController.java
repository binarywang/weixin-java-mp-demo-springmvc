package com.github.binarywang.demo.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.binarywang.demo.spring.service.BaseWxService;

import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class AbstractWxPortalController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
  public @ResponseBody String authGet(
      @RequestParam("signature") String signature,
      @RequestParam("timestamp") String timestamp,
      @RequestParam("nonce") String nonce,
      @RequestParam("echostr") String echostr) {
    this.logger.info("接收到来自微信服务器的认证消息");

    if (this.getWxService().checkSignature(timestamp, nonce, signature)) {
      return echostr;
    }

    return "非法请求";
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/xml; charset=UTF-8")
  public @ResponseBody String post(@RequestBody String requestBody) {

    this.logger.debug("\n接收微信请求：{} ", requestBody);

    BaseWxService wxService = this.getWxService();

    WxMpXmlOutMessage out = wxService
        .route(WxMpXmlMessage.fromXml(requestBody));

    if (out == null) {
      return "";
    }

    String outXml = out.toXml();

    this.logger.debug("\n组装回复信息：{}", outXml);

    return outXml;
  }

  protected abstract BaseWxService getWxService();

}
