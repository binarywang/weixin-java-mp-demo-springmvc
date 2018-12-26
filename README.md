[![码云Gitee](https://gitee.com/binary/weixin-java-mp-demo/badge/star.svg?theme=blue)](https://gitee.com/binary/weixin-java-mp-demo)
[![Github](http://github-svg-buttons.herokuapp.com/star.svg?user=binarywang&repo=weixin-java-mp-demo-springmvc&style=flat&background=1081C1)](https://github.com/binarywang/weixin-java-mp-demo-springmvc)
[![Build Status](https://travis-ci.org/binarywang/weixin-java-mp-demo-springmvc.svg?branch=master)](https://travis-ci.org/binarywang/weixin-java-mp-demo-springmvc)

### 本Demo使用Spring MVC 框架实现微信公众号开发功能。

#### 本项目为WxJava的Demo演示程序，更多Demo请[查阅此处](https://github.com/Wechat-Group/WxJava/blob/master/demo.md)。

如果想支持多公众号，请参考spring boot版本的demo：https://github.com/binarywang/weixin-java-mp-demo-springboot ，
或者此项目：https://github.com/binarywang/weixin-java-mp-multi-demo

## 使用步骤：
1. 新手遇到问题，请务必先阅读[【开发文档首页】](https://github.com/Wechat-Group/WxJava/wiki)的常见问题部分，可以少走很多弯路，节省不少时间。
1. 配置: 复制 `/src/main/resources/wx.properties.template` 或者修改其扩展名生成 `wx.properties` 文件，填写相关配置;		
1. 使用maven运行demo程序: `mvn jetty:run`  或者自己打war包发布到tomcat运行；
1. 配置微信公众号中的接口地址（微信公众平台 服务器配置 -> 服务器地址(URL)）：http://xxx/wechat/portal （注意XXX需要是外网可访问的域名，需要符合微信官方的要求）；
1. 根据自己需要修改各个handler的实现，加入自己的业务逻辑。
	
