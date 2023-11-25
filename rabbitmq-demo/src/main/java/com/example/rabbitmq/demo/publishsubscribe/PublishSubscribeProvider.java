package com.example.rabbitmq.demo.publishsubscribe;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author nisang
 * 2023/11/25 19:24
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class PublishSubscribeProvider {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道，这是完成大部分API的地方。
        Channel channel = connection.createChannel();
        // 声明（创建）队列，必须声明队列才能够发送消息，我们可以把消息发送到队列中。
        channel.exchangeDeclare("logs", "fanout");
        // 声明一个队列是幂等的 - 只有当它不存在时才会被创建
        String message = argv.length < 1 ? "info: Hello World!" :
                String.join(" ", argv);
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
