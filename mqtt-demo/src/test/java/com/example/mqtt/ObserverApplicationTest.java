package com.example.mqtt;

import com.example.mqtt.observer.ConcreteObserver;
import com.example.mqtt.task.ConcreteAsyncTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ObserverApplicationTest {

    public static void main(String[] args) {
        // 创建异步任务对象
        ConcreteAsyncTask asyncTask = new ConcreteAsyncTask();

        // 创建观察者对象
        ConcreteObserver observer = new ConcreteObserver();

        // 添加观察者
        asyncTask.addObserver(observer);

        // 执行异步任务
        asyncTask.execute();
    }
}
