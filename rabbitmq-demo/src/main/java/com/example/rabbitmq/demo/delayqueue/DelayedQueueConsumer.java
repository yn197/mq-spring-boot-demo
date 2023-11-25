package com.example.rabbitmq.demo.delayqueue;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


/**
 * @author nisang
 * 2023/11/25 19:59
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class DelayedQueueConsumer {

    private static final String EXCHANGE_NAME = "delayedExchange";
    private static final String QUEUE_NAME = "delayedQueue";
    private static final String ROUTING_KEY = "delayedRoutingKey";

    public static void main(String[] argv) throws Exception {
        try {
            // 创建连接和通道
            try (Connection connection = ConnectionUtil.getConnection();
                 Channel channel = connection.createChannel()) {

                // 声明交换机，类型为 x-delayed-message
                Map<String, Object> exchangeArgs = new HashMap<>();
                exchangeArgs.put("x-delayed-type", "direct");
                channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true, false, exchangeArgs);

                // 声明队列
                channel.queueDeclare(QUEUE_NAME, true, false, false, null);

                // 绑定队列到交换机
                channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

                // 消费消息
                System.out.println(" [*] Waiting for messages. To exit press Ctrl+C");
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String receivedMessage = new String(delivery.getBody(), "UTF-8");
                    System.out.println(" [x] Received '" + receivedMessage + "'");
                };
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
                });

                // 等待消息消费,等待 6 秒，确保消息能够被消费
                Thread.sleep(6000);
            }
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}