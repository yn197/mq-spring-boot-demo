package com.example.rabbitmq.demo.topic;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author nisang
 * 2023/11/25 19:27
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class TopicConsumer {
    private static final String EXCHANGE_NAME = "topic_logs";
    private final static String WORK_QUEUE_NAME = "topic_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明队列，第二个属性为持久性
        channel.queueDeclare(WORK_QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //绑定交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //绑定交换机跟路由器
        channel.queueBind(WORK_QUEUE_NAME, EXCHANGE_NAME, "*.white.*");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(WORK_QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}
