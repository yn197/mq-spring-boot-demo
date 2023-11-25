package com.example.rabbitmq.demo.workqueue;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * @author nisang
 * 2023/11/25 19:29
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class WorkQueueProvider {
    private final static String WORK_QUEUE_NAME = "work_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道，这是完成大部分API的地方。
        Channel channel = connection.createChannel();

        // 声明（创建）队列，必须声明队列才能够发送消息，我们可以把消息发送到队列中。
        // 声明一个队列是幂等的 - 只有当它不存在时才会被创建
        channel.queueDeclare(WORK_QUEUE_NAME, true, false, false, null);

        // 消息内容
        for (int i = 0; i < 100; i++) {
            String message = "第"+i+"条"+"study work_queue!";
            channel.basicPublish("", WORK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message+"'");
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
