package com.example.workermanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * 压缩日志的Worker
 */
public class CompressLogWorker extends Worker {
    /**
     * 构造器
     */
    public CompressLogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
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
        Log.e("CompressLogWorker", "doWork()");
        return Result.success();
    }
}
