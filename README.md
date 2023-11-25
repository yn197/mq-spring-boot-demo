# mq-spring-boot-demo
简介：spring-boot整合常用mq，每个单独的模块都有参考官网写的使用**详细记录**，项目采用了jdk1.8版本，maven进行依赖管理

## 项目模块说明：

- rabbitmq-demo

  demo包下都是原生使用，常用5种队列模式，Rpc用的比较少就没有再列出。

  ​		1.simplest  （简单模式）

  ​		2.workqueue（工作队列）

  ​		3.publishsubscribe(发布订阅模式)

  ​		4.direct（路由模式）

  ​		5.topic(通配符模式)

  ​		6.延迟队列（特殊模式）

  

  amqp包下采用注解开发

## 使用：

具体场景具体分析，参考示例代码使用

## 版权和许可：

项目根据Apache License 2.0生成



