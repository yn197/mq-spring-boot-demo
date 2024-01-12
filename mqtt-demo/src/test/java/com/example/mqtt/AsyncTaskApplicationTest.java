package com.example.mqtt;

import com.example.mqtt.callback.AsyncCallback;
import com.example.mqtt.task.AsyncTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
class AsyncTaskApplicationTest {

    // 使用回调和取消任务的示例
    public static void main(String[] args) {
        // 创建异步任务对象
        AsyncTask asyncTask = new AsyncTask();

        // 创建回调对象
        AsyncCallback callback = new AsyncCallback() {
            @Override
            public void onComplete(String result) {
                System.out.println("Complete: " + result);
            }

            @Override
            public void onSuccess(Function<String, String> successHandler) {
                System.out.println("Success Handler: " + successHandler.apply("Original Result"));
            }

            @Override
            public void onFailure(Consumer<String> failureHandler) {
                failureHandler.accept("Task failed");
            }

            @Override
            public void onResult(Predicate<String> resultPredicate) {
                System.out.println("Result Predicate: " + resultPredicate.test("Sample Result"));
            }

            @Override
            public void onNextTask(Supplier<CompletableFuture<String>> nextTaskSupplier) {
                nextTaskSupplier.get().thenAccept(result -> System.out.println("Next Task Result: " + result));
            }
        };

        // 执行异步任务，并传递回调对象
        asyncTask.executeAsyncTask(callback);

        // 模拟在执行任务一段时间后取消任务
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取异步任务的 Future 对象并取消任务
        Future<?> future = asyncTask.getFuture();
        if (future != null && !future.isCancelled()) {
            future.cancel(true);
        }
        // 关闭线程池
        asyncTask.shutdown();
    }
}
