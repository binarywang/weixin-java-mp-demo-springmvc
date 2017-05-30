package com.github.binarywang.demo.spring.service;

import javax.annotation.PostConstruct;

import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.demo.spring.config.WxMpConfig;
import com.github.binarywang.demo.spring.handler.AbstractHandler;
import com.github.binarywang.demo.spring.handler.KfSessionHandler;
import com.github.binarywang.demo.spring.handler.LocationHandler;
import com.github.binarywang.demo.spring.handler.LogHandler;
import com.github.binarywang.demo.spring.handler.MenuHandler;
import com.github.binarywang.demo.spring.handler.MsgHandler;
import com.github.binarywang.demo.spring.handler.NullHandler;
import com.github.binarywang.demo.spring.handler.StoreCheckNotifyHandler;
import com.github.binarywang.demo.spring.handler.SubscribeHandler;
import com.github.binarywang.demo.spring.handler.UnsubscribeHandler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
@Service
public class WeixinService extends WxMpServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected LogHandler logHandler;

  @Autowired
  protected NullHandler nullHandler;

  @Autowired
  protected KfSessionHandler kfSessionHandler;

  @Autowired
  protected StoreCheckNotifyHandler storeCheckNotifyHandler;

  @Autowired
  private WxMpConfig wxConfig;

  @Autowired
  private LocationHandler locationHandler;

  @Autowired
  private MenuHandler menuHandler;

  @Autowired
  private MsgHandler msgHandler;

  @Autowired
  private UnsubscribeHandler unsubscribeHandler;

  @Autowired
  private SubscribeHandler subscribeHandler;

  private WxMpMessageRouter router;

  @PostConstruct
  public void init() {
    final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
    config.setAppId(this.wxConfig.getAppid());// 设置微信公众号的appid
    config.setSecret(this.wxConfig.getAppsecret());// 设置微信公众号的app corpSecret
    config.setToken(this.wxConfig.getToken());// 设置微信公众号的token
    config.setAesKey(this.wxConfig.getAesKey());// 设置消息加解密密钥
    super.setWxMpConfigStorage(config);

    this.refreshRouter();
  }

  private void refreshRouter() {
    final WxMpMessageRouter newRouter = new WxMpMessageRouter(this);

    // 记录所有事件的日志
    newRouter.rule().handler(this.logHandler).next();

    // 接收客服会话管理事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
        .handler(this.kfSessionHandler).end();
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
        .handler(this.kfSessionHandler).end();
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
            .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
        .handler(this.kfSessionHandler).end();
    
    // 门店审核事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
      .event(WxMpEventConstants.POI_CHECK_NOTIFY)
      .handler(this.storeCheckNotifyHandler)
      .end();

    // 自定义菜单事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.BUTTON_CLICK).handler(this.getMenuHandler()).end();

    // 点击菜单连接事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.BUTTON_VIEW).handler(this.nullHandler).end();

    // 关注事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_SUBSCRIBE).handler(this.getSubscribeHandler())
        .end();

    // 取消关注事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_UNSUBSCRIBE).handler(this.getUnsubscribeHandler())
        .end();

    // 上报地理位置事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_LOCATION).handler(this.getLocationHandler()).end();

    // 接收地理位置消息
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_LOCATION)
        .handler(this.getLocationHandler()).end();

    // 扫码事件
    newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_SCAN).handler(this.getScanHandler()).end();

    // 默认
    newRouter.rule().async(false).handler(this.getMsgHandler()).end();

    this.router = newRouter;
  }


  public WxMpXmlOutMessage route(WxMpXmlMessage message) {
    try {
      return this.router.route(message);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  public boolean hasKefuOnline() {
    try {
      WxMpKfOnlineList kfOnlineList = this.getKefuService().kfOnlineList();
      return kfOnlineList != null && kfOnlineList.getKfOnlineList().size() > 0;
    } catch (Exception e) {
      this.logger.error("获取客服在线状态异常: " + e.getMessage(), e);
    }

    return false;
  }

  protected MenuHandler getMenuHandler() {
    return this.menuHandler;
  }

  protected SubscribeHandler getSubscribeHandler() {
    return this.subscribeHandler;
  }

  protected UnsubscribeHandler getUnsubscribeHandler() {
    return this.unsubscribeHandler;
  }

  protected AbstractHandler getLocationHandler() {
    return this.locationHandler;
  }

  protected MsgHandler getMsgHandler() {
    return this.msgHandler;
  }

  protected AbstractHandler getScanHandler() {
    return null;
  }

}
