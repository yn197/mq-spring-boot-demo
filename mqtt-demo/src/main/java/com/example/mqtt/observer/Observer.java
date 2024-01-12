package com.example.mqtt.observer;

/**
 * 观察者接口
 * @author nisang
 * 2024/1/12 20:45
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public interface Observer {
    /**
     * Update.
     *
     * @param result the result
     */
    void update(String result);
}
