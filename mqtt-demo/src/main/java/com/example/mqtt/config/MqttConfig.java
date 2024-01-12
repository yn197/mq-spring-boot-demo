package com.example.mqtt.config;



import com.example.mqtt.MqttCondition;
import com.example.mqtt.client.MqttAcceptClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**启动服务的时候开启监听客户端
 * @author nisang
 * 2024/1/4 15:21
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Configuration
public class MqttConfig {

    @Autowired
    private MqttAcceptClient mqttAcceptClient;

    /**
     * 订阅mqtt
     *
     * @return
     */
    @Conditional(MqttCondition.class)
    @Bean
    public MqttAcceptClient getMqttPushClient() {
        mqttAcceptClient.connect();
        return mqttAcceptClient;
    }
}
