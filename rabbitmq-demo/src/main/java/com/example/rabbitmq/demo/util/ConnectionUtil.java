package com.example.rabbitmq.demo.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author nisang
 * 2023/11/25 19:22
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class ConnectionUtil {
    /**
     * 建立与RabbitMQ的连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
