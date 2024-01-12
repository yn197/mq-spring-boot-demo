package com.example.mqtt.task;

import com.example.mqtt.callback.AsyncCallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 创建异步任务类
 * @author nisang
 * 2024/1/12 20:20
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class AsyncTask {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    /**
     * 用于存储异步任务的 Future 对象
     */
    private Future<?> future;
    /**
     * 执行异步任务，并在完成时调用回调函数
     * @param callback 回调接口的实现对象
     */
    public void executeAsyncTask(AsyncCallback callback) {
        future = CompletableFuture.runAsync(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(2000);

                // 操作完成，调用回调函数通知成功
                callback.onComplete("Task completed successfully.");

                // 模拟多次回调
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(1000);
                    callback.onResult(result -> result.length() > 5);  // 用 Predicate 判断结果长度
                }

                // 使用 Function 处理成功的结果
                callback.onSuccess(result -> "Processed Result: " + result);

                // 使用 Consumer 处理失败的情况
                callback.onFailure(error -> System.out.println("Error: " + error));

                // 使用 Supplier 生成下一个任务
                callback.onNextTask(() -> CompletableFuture.supplyAsync(() -> "Next Task Result"));

            } catch (InterruptedException e) {
                // 操作出现异常，调用回调函数通知失败
                callback.onFailure(error -> System.out.println("Task failed: " + e.getMessage()));
            }
        }, executorService);
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        executorService.shutdown();
    }
    /**
     * 获取异步任务的 Future 对象
     * @return Future 对象
     */
    public Future<?> getFuture() {
        return future;
    }
}


