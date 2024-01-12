package com.example.mqtt.callback;


import com.example.mqtt.client.MqttAcceptClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author nisang
 * 2024/1/4 15:18
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Component
public class MqttSendCallBack implements MqttCallbackExtended {

    private static final Logger logger = LoggerFactory.getLogger(MqttSendCallBack.class);

    /**
     * 客户端断开后触发
     *
     * @param throwable
     */
    @Override
    public void connectionLost(Throwable throwable) {
        logger.info("连接断开，可以重连");
    }

    /**
     * 客户端收到消息触发
     *
     * @param topic       主题
     * @param mqttMessage 消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        logger.info("【接收消息主题】: " + topic);
        logger.info("【接收消息Qos】: " + mqttMessage.getQos());
        logger.info("【接收消息内容】: " + new String(mqttMessage.getPayload()));
    }

    /**
     * 发布消息成功
     *
     * @param token token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        String[] topics = token.getTopics();
        for (String topic : topics) {
            logger.info("向主题【" + topic + "】发送消息成功！");
        }
        try {
            MqttMessage message = token.getMessage();
            byte[] payload = message.getPayload();
            String s = new String(payload, "UTF-8");
            logger.info("【消息内容】:" + s);
        } catch (Exception e) {
            logger.error("MqttSendCallBack deliveryComplete error,message:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 连接emq服务器后触发
     *
     * @param b
     * @param s
     */
    @Override
    public void connectComplete(boolean b, String s) {
        logger.info("============================= 客户端【" + MqttAcceptClient.client.getClientId() + "】连接成功！=============================");
    }
}
