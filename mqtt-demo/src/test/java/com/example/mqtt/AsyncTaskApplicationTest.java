package com.example.mqtt;

import com.example.mqtt.callback.AsyncCallback;
import com.example.mqtt.task.AsyncTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class AsyncTaskApplicationTest {

    // 使用回调和取消任务的示例
    public static void main(String[] args) {
        // 创建异步任务对象
        AsyncTask asyncTask = new AsyncTask();

        // 创建回调对象
        AsyncCallback callback = new AsyncCallback() {
            @Override
            public void onSuccess(String result) {
                System.out.println("Success: " + result);
            }

            @Override
            public void onFailure(String error) {
                System.out.println("Failure: " + error);
            }

            @Override
            public void onMultipleCallbacks(int count) {
                System.out.println("Multiple callbacks received: " + count);
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

        // 取消任务
        asyncTask.cancelTask();
    }
}
