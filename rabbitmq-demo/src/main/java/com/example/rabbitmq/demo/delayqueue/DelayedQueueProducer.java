package com.example.rabbitmq.demo.delayqueue;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


/**
 * @author nisang
 * 2023/11/25 19:59
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class DelayedQueueProducer {

    private static final String EXCHANGE_NAME = "delayedExchange";
    private static final String QUEUE_NAME = "delayedQueue";
    private static final String ROUTING_KEY = "delayedRoutingKey";


    public static void main(String[] args) throws Exception {
        try {
            // 创建连接和通道
            try (Connection connection = ConnectionUtil.getConnection();
                 Channel channel = connection.createChannel()) {

                // 声明交换机，类型为 x-delayed-message
                Map<String, Object> exchangeArgs = new HashMap<>(4);
                exchangeArgs.put("x-delayed-type", "direct");
                channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true, false, exchangeArgs);

                // 声明队列
                channel.queueDeclare(QUEUE_NAME, true, false, false, null);

                // 绑定队列到交换机
                channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

                // 发送延迟消息
                String message = "Hello, RabbitMQ!";
                //5 seconds
                int delayMillis = 5000;
                AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                        .headers(Collections.singletonMap("x-delay", delayMillis))
                        .build();

                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, message.getBytes());
                System.out.println(" [x] Sent '" + message + "' with a delay of " + delayMillis + " milliseconds");


            }
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}