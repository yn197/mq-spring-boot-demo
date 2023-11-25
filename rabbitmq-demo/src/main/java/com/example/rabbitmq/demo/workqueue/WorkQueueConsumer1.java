package com.example.rabbitmq.demo.workqueue;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author nisang
 * 2023/11/25 19:28
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class WorkQueueConsumer1 {
    private final static String WORK_QUEUE_NAME = "work_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明队列，第二个属性为持久性
        channel.queueDeclare(WORK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 定义队列的消费者
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
            try {
            } finally {
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        channel.basicConsume(WORK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }
}
