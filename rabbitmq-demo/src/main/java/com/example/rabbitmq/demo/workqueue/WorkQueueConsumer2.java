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
public class WorkQueueConsumer2 {
    private final static String WORK_QUEUE_NAME = "work_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(WORK_QUEUE_NAME, true, false, false, null);
        //设置每个消费者只能同时处理一条消息
        channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 定义队列的消费者
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
            try {
                //模拟耗时
                doWork(message);
            } finally {
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(WORK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }
    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
