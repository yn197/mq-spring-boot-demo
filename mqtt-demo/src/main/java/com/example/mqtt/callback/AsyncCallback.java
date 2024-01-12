package com.example.mqtt.callback;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 回调接口，使用函数式接口作为参数
 * @author nisang
 * 2024/1/12 20:17
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public interface AsyncCallback {
    /**
     * 异步任务完成时调用，通知回调对象
     * @param result 完成的结果
     */
    void onComplete(String result);

    /**
     * 处理成功的结果
     * @param successHandler 处理成功结果的函数
     */
    void onSuccess(Function<String, String> successHandler);

    /**
     * 处理失败的情况
     * @param failureHandler 处理失败的消费者
     */
    void onFailure(Consumer<String> failureHandler);

    /**
     * 结果判断
     * @param resultPredicate 判断结果的断言
     */
    void onResult(Predicate<String> resultPredicate);

    /**
     * 生成下一个任务
     * @param nextTaskSupplier 生成下一个任务的供应商
     */
    void onNextTask(Supplier<CompletableFuture<String>> nextTaskSupplier);
}
