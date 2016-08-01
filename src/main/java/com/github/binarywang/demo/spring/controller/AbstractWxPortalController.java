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
    this.logger.info("\n接收到来自微信服务器的认证消息：[{},{},{},{}]",
            signature, timestamp, nonce, echostr);

    if (this.getWxService().checkSignature(timestamp, nonce, signature)) {
      return echostr;
    }

    return "非法请求";
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/xml; charset=UTF-8")
  public @ResponseBody String post(@RequestBody String requestBody,
                                   @RequestParam("signature") String signature,
                                   @RequestParam("encrypt_type") String encType,
                                   @RequestParam("msg_signature") String msgSignature,
                                   @RequestParam("timestamp") String timestamp,
                                   @RequestParam("nonce") String nonce) {
    this.logger.info("\n接收微信请求：[{},{},{},{},{}]\n{} ",
                        signature, encType, msgSignature, timestamp, nonce, requestBody);

    String out = null;
    if (encType == null) {
      // 明文传输的消息
      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
      WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
      if (outMessage == null) {
        return "";
      }
      out = outMessage.toXml();
    }else if ("aes".equals(encType)) {
      // aes加密的消息
      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
              this.getWxService().getWxMpConfigStorage(), timestamp, nonce, msgSignature);
      this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
      WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
      if (outMessage == null) {
        return "";
      }

      out = outMessage.toEncryptedXml(this.getWxService().getWxMpConfigStorage());
    }

    this.logger.debug("\n组装回复信息：{}", out);

    return out;
  }

  protected abstract BaseWxService getWxService();

}
