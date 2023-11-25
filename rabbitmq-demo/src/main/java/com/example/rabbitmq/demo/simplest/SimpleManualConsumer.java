package com.example.rabbitmq.demo.simplest;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author nisang
 * 2023/11/25 19:26
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class SimpleManualConsumer {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println("收到simpleProvider消息 {}" + msg + "!");
                //手动ack
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //异常测试手动ack
        //int i=1/0;
        // 监听队列，第二个参数为false,手动ack
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
