package com.example.workermanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * 上传日志的Worker
 */
public class UploadLogWorker extends Worker {

    /**
     * 构造器
     */
    public UploadLogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    /**
     * 耗时的任务，在doWork()方法中执行
     * <p>
     * 执行成功返回Result.success()
     * 执行失败返回Result.failure()
     * 需要重新执行返回Result.retry()
     */
    @NonNull
    @Override
    public Result doWork() {
        //接收外面传递进来的数据
        String inputData = getInputData().getString("input_data");
        Log.e("UploadLogWorker", "doWork()->get inputData:" + inputData);

        // 任务执行完成后返回数据
        Data outputData = new Data.Builder().putString("output_data", "Task Success!").build();
        return Result.success(outputData);
    }
}
