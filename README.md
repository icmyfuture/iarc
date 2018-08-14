# 准备开发环境

##### 1. 安装 [JAVA8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 

##### 2. 安装 [IDEA Ultimate版本](https://www.jetbrains.com/idea/download/)

##### 3. 安装 [Node.js 6.X 版本](https://nodejs.org/en/)

##### 4. 安装 [Zookeeper 3.4.10](https://zookeeper.apache.org/releases.html#download)
```
# 配置Zookeeperd单机模式
把文件 Zookeeper安装目录/conf/zoo_simple.cfg 重命名为 zoo.cfg 即可

# 启动Zookeeper
执行 Zookeeper安装目录/bin/zkServer.cmd
```

##### 5. 设置MAVEN 阿里云镜像
IDEA默认自带了MAVEN，请修改以下路径的配置文件
IDEA安装目录\plugins\maven\lib\maven3\conf
把settings.xml 文件里配置mirrors的子节点，添加如下mirror
```
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror> 
```

##### 6. 设置NODE.JS 阿里云镜像 

```
npm config set registry https://registry.npm.taobao.org
```

##### 7. 安装git客户端 [参考](http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/00137396287703354d8c6c01c904c7d9ff056ae23da865a000)
如果喜欢使用GUI的客户端可以安装 [Github client](https://desktop.github.com/)
```
# 安装完成后，还需要最后一步设置，在命令行输入：

$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
```

##### 8. 安装IDEA插件
打开 File->Settings->Plugins->Browse repositories 对话框搜索
 1. vue.js
 2. Lombok
 
# 开发规范
1. JAVA 开发规范 [参考阿里巴巴Java开发手册](https://yq.aliyun.com/articles/69327)
2. VUE.JS 开发规范 [参考](https://github.com/pablohpsilva/vuejs-component-style-guide/blob/master/README-CN.md)
3. ES6 开发规范 [参考](https://github.com/Yunkou/FE-code-style/blob/master/ES6-Style-Guide.md) 


# 学习资料
1. [Better Java](https://github.com/cxxr/better-java/blob/master/README.zh-cn.md)
2. [深入浅出MyBatis](https://my.oschina.net/xianggao/blog/548873)
3. [Lombok](https://projectlombok.org/)
4. [Git简易指南](http://www.bootcss.com/p/git-guide/)
5. [Spring Boot 入门](http://www.infoq.com/cn/articles/microframeworks1-spring-boot/)
6. Spring boot的业务验证 [参考1](http://www.jianshu.com/p/a9b1e2f7a749) [参考2](http://www.jianshu.com/p/2c2da2adef81)
7. [guava](http://ifeve.com/google-guava/)
8. [ES6](http://es6.ruanyifeng.com/)
9. [vue-router2](https://router.vuejs.org/zh-cn/)
10. [vue2](https://vuefe.cn/v2/guide/)
11. [vue Element](http://element.eleme.io/#/zh-CN/component/installation)
 

# 辅助页面
1. API文档 [Swagger2](http://localhost:8001/swagger-ui.html)
2. DB监控 [Druid](http://localhost:8002/druid/index.html) 登录账户admin admin

# 描述
架构之旅工程

# 工程结构
```
--root
----iarc-app
----iarc-data
----iarc-entity
----iarc-service
----iarc-service-api
----iarc-zzz
```
##### 0. root
maven父工程

##### 1. iarc-entity
实体模块

##### 2. iarc-service-api
服务api模块，定义服务接口

##### 3. iarc-data
spring boot 站点
* 使用 mybatis+druid 访问mysql数据库
* 使用 dubbo+zookeeper 作为服务提供者

##### 4. iarc-service
spring boot 站点
* 使用 dubbo+zookeeper 作为服务消费者

##### 5. iarc-zzz
spring boot 站点
实验性的demo站点

