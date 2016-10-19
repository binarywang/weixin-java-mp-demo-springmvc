#### 本Demo使用Spring MVC 框架实现微信公众号后台管理功能，支持多公众号，欢迎帮忙维护添加新功能，或提供更好的实现。
1. 如果想使用更多公众号，请复制相关配置文件，修改spring配置文件添加相应配置，同时还需要增加相应的controller和service，具体可以参考已有源码进行操作。
1. 如果只是使用一个公众号，建议复制生成两个配置文件后，其中一个不用的配置文件不用理会即可，如果确实看着不舒服，可以自行删除，并删除相关的配置和controller及service。

## 使用步骤：
1. 配置
	复制/src/main/resources/wx-gzh1.properties.template 生成wx-gzh1.properties 文件，填写相关配置;
	复制/src/main/resources/wx-gzh2.properties.template 生成wx-gzh2.properties 文件，填写相关配置.
		

1. 使用maven运行demo程序
	mvn jetty:run
	
1. 配置微信公众号中的接口地址：
	http://xxx/api/gzh1/portal 或 http://xxx/api/gzh2/portal
	
