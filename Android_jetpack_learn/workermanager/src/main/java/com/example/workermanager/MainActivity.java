package com.example.workermanager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private String TAG = "PeriodicTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startOneTimeTask();
        startPeriodicWorkRequest();
        startChainTask();
    }

    /**
     * 开启一个一次性任务
     */
    private void startOneTimeTask() {
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();

        Data inputData = new Data.Builder()
                .putString("input_data", "Hello World!")
                .build();

        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadLogWorker.class)
//                .setConstraints(constraints)//设置触发条件
                .setInitialDelay(10, TimeUnit.SECONDS)//符合触发条件后，延迟10秒执行
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)//设置指数退避算法
                .setInputData(inputData)
                .addTag("UploadTag")
                .build();

        WorkManager.getInstance(this).enqueue(uploadWorkRequest);

        //实时监听变化
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.getId()).observe(MainActivity.this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                Log.d("onChanged()->", "workInfo:" + workInfo);
                if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    String outputData = workInfo.getOutputData().getString("output_data");
                    Log.d("onChanged()->", "doWork()->get outputData:" + outputData);
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * 开启一个定期任务
     * <p>
     * 通过设置TAG的方式来监听任务状态的变化，也可以使用ID的方式来监听
     */
    private void startPeriodicWorkRequest() {
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        //不能少于15分钟
        PeriodicWorkRequest uploadWorkRequest = new PeriodicWorkRequest.Builder(UploadLogWorker.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)//设置触发条件
                .addTag(TAG)
                .build();

        WorkManager.getInstance(this).enqueue(uploadWorkRequest);

        WorkManager.getInstance(this).getWorkInfosByTagLiveData(TAG).observe(MainActivity.this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                Log.d("onChanged()->", "workInfo:" + workInfos.get(0));
            }
        });
    }

    /**
     * 取消任务
     */
    private void cancelAllWork() {
        WorkManager.getInstance(MainActivity.this).cancelAllWork();
    }

    /**
     * 开启任务链，任务的执行具有先后顺序
     */
    private void startChainTask() {
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        OneTimeWorkRequest compressWorkRequest = new OneTimeWorkRequest.Builder(CompressLogWorker.class)
                .setConstraints(constraints)//设置触发条件
                .setInitialDelay(10, TimeUnit.SECONDS)//符合触发条件后，延迟10秒执行
                .build();

        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadLogWorker.class)
                .setConstraints(constraints)//设置触发条件
                .setInitialDelay(10, TimeUnit.SECONDS)//符合触发条件后，延迟10秒执行
                .build();

        //实时监听变化
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.getId()).observe(MainActivity.this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                Log.d("onChanged()->", "workInfo:" + workInfo);
            }
        });
    }
}