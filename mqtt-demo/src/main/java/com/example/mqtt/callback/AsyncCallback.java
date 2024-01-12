package com.example.mqtt.callback;

/**
 * 定义回调接口
 * @author nisang
 * 2024/1/12 20:17
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public interface AsyncCallback {
    /**
     * 当异步任务成功完成时调用该方法
     * @param result 成功完成的结果信息
     */
    void onSuccess(String result);

    /**
     * 当异步任务失败时调用该方法
     * @param error 失败的错误信息
     */
    void onFailure(String error);

    /**
     * 当异步任务有多次回调时调用该方法
     * @param count 回调次数
     */
    void onMultipleCallbacks(int count);
}
