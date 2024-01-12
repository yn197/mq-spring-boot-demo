package com.example.mqtt.observer;

/**
 * 具体观察者实现
 * @author nisang
 * 2024/1/12 20:49
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class ConcreteObserver implements Observer{
    @Override
    public void update(String result) {
        // 处理异步任务完成的结果
        System.out.println("Observer received result: " + result);
    }
}
