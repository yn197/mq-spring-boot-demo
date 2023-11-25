package com.example.rabbitmq.demo.publishsubscribe;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author nisang
 * 2023/11/25 19:24
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class PublishSubscribeConsumer1 {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        //获取交换机名字
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //绑定交换机跟路由器
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received 1 '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
