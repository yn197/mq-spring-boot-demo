package com.example.rabbitmq.demo.direct;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author nisang
 * 2023/11/25 19:22
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class DirectInfoConsumer {
    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String ROUTING_KEY_INFO_MSG = "info";
    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        //获取交换机名字
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //绑定交换机跟路由器
        channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY_INFO_MSG);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received 1 '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
