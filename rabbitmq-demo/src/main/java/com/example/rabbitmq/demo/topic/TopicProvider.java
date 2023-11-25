package com.example.rabbitmq.demo.topic;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.Arrays;
import java.util.List;

/**
 * @author nisang
 * 2023/11/25 19:27
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class TopicProvider {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {

        List<String> list = Arrays.asList("small.orange.cat", "big.white.rabbit", "lazy.white.elephant");
        for (String animal : list) {
            String message = "森林有动物，比如" + animal;
            // 获取到连接以及mq通道
            Connection connection = ConnectionUtil.getConnection();
            // 从连接中创建通道，这是完成大部分API的地方。
            Channel channel = connection.createChannel();
            // 声明（创建）队列，必须声明队列才能够发送消息，我们可以把消息发送到队列中。
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            channel.basicPublish(EXCHANGE_NAME, animal, null, message.getBytes());
            System.out.println(" [x] Sent '" + animal + "':'" + message + "'");
            //关闭通道和连接
            channel.close();
            connection.close();
        }

    }
}
