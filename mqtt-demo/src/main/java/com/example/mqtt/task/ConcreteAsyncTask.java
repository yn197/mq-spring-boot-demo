package com.example.mqtt.task;

import com.example.mqtt.observer.Observer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nisang
 * 2024/1/12 20:47
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Service
public class ConcreteAsyncTask implements AsyncSimpleTask{
    private List<Observer> observers = new ArrayList<>();
    @Override
    public void execute() {
        // 执行异步任务逻辑
        String result = "Task completed successfully.";

        // 通知观察者
        notifyObservers(result);
    }

    /**
     * 添加观察者
     *
     * @param observer the observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 通知观察者
     * @param result
     */
    private void notifyObservers(String result) {
        for (Observer observer : observers) {
            observer.update(result);
        }
    }
}
