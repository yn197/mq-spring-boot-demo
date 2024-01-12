package com.example.mqtt.task;

import com.example.mqtt.callback.AsyncCallback;

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
    private Future<?> future;

    /**
     * 执行异步任务，并在完成时调用回调函数
     * @param callback 回调接口的实现对象
     */
    public void executeAsyncTask(final AsyncCallback callback) {
        future = executorService.submit(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(2000);

                // 操作完成，调用回调函数通知成功
                callback.onSuccess("Task completed successfully.");

                // 模拟多次回调
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(1000);
                    callback.onMultipleCallbacks(i + 1);
                }
            } catch (InterruptedException e) {
                // 操作出现异常，调用回调函数通知失败
                callback.onFailure("Task failed: " + e.getMessage());
            }
        });
    }
    /**
     * 取消任务
     */
    public void cancelTask() {
        if (future != null && !future.isDone()) {
            //对于 future.cancel(true); 的使用，这是 Future 类的方法，用于取消异步任务的执行。
            // 参数 true 表示进行中的任务会被中断（如果正在运行），而 false 表示允许任务运行完成后再取消。
            //在上述代码中，future.cancel(true); 的目的是取消异步任务的执行，如果任务正在执行，
            // 就会尝试中断执行中的线程。这在某些情况下可能是有用的，比如在用户请求取消任务时，或者在任务执行时间过长时。
            //需要注意的是，取消任务并不一定会立即生效，具体取决于任务的实现。有些任务可能需要检查中断状态并主动处理中断请求。
            //在代码中的这个例子中，future.cancel(true); 主要是为了演示如何取消任务，
            // 以便在 shutdown() 方法中终止线程池。在实际应用中，你可能需要根据具体情况来决定是否使用任务取消功能。
            future.cancel(true);
            System.out.println("Task canceled.");
        }
        shutdown();
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        executorService.shutdown();
    }

}


