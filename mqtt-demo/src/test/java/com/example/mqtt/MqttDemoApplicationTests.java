package com.example.mqtt;

import com.example.mqtt.client.MqttSendClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MqttDemoApplicationTests {
    @Autowired
    private MqttSendClient mqttSendClient;
    @Test
    void contextLoads() {
        String topic = "publishTopic";
        String sendMessage="Hello mqtt!";
        this.mqttSendClient.publish(false, topic, sendMessage);
        log.info("topic:" + topic + "\nmessage:" + sendMessage);
    }

}
