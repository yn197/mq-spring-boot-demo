package com.example.rabbitmq.demo.direct;

import com.example.rabbitmq.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author nisang
 * 2023/11/25 19:23
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class DirectProvider {
    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String ROUTING_KEY_INFO_MSG = "info";
    private static final String ROUTING_KEY_ERR_MSG = "err";
    private static final String ROUTING_KEY_WARNING_MSG = "warn";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道，这是完成大部分API的地方。
        Channel channel = connection.createChannel();
        // 声明（创建）队列，必须声明队列才能够发送消息，我们可以把消息发送到队列中。
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String message = "打印了日志"+ROUTING_KEY_ERR_MSG;
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY_ERR_MSG, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + ROUTING_KEY_ERR_MSG + "':'" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
