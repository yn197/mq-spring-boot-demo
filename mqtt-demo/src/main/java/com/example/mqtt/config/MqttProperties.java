package com.example.mqtt.config;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @author nisang
 * 2024/1/4 15:15
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Component
@ConfigurationProperties("mqtt")
@Data
public class MqttProperties {

    /**
     * 用户名
     */
    private String  username;

    /**
     * 密码
     */
    private String  password;

    /**
     * 连接地址
     */
    private String  hostUrl;

    /**
     * 客户端Id，同一台服务器下，不允许出现重复的客户端id
     */
    private String  clientId;

    /**
     * 默认连接主题，以/#结尾表示订阅所有以test开头的主题
     */
    private String  defaultTopic;

    /**
     * 默认服务器发送主题前缀，格式：server:${env}:report:${topic}
     */
    private String  serverTopic;

    /**
     * 超时时间
     */
    private int     timeout;

    /**
     * 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端
     * 发送个消息判断客户端是否在线，但这个方法并没有重连的机制
     */
    private int     keepAlive;

    /**
     * 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连
     * 接记录，这里设置为true表示每次连接到服务器都以新的身份连接
     */
    private Boolean cleanSession;

    /**
     * 是否断线重连
     */
    private Boolean reconnect;

    /**
     * 启动的时候是否关闭mqtt
     */
    private Boolean isOpen;

    /**
     * 连接方式
     */
    private Integer qos;

    /**
     * 获取默认主题，以/#结尾表示订阅所有以test开头的主题
     * @return
     */
    public String getDefaultTopic() {
        return defaultTopic + "/#";
    }

    /**
     * 获取服务器发送主题，格式：server/${env}/report/${topic}
     * @param topic
     * @return
     */
    public String getServerTopic(String topic) {
        return serverTopic + "/" + topic;
    }
}
