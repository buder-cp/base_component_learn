package com.example.lifecycletest.timerWithLiveData;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class TimerWithLiveDataViewModel extends ViewModel {

    private String TAG = this.getClass().getName();
    private Timer timer;
    private MutableLiveData<Integer> currentSecond;

    public LiveData<Integer> getCurrentSecond() {
        if (currentSecond == null) {
            currentSecond = new MutableLiveData<>();
        }
        return currentSecond;
    }

    /**
     * 开始计时
     */
    public void startTiming() {
        if (timer == null) {
            currentSecond.setValue(0);
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    //这里要用postValue方法，而不能用setValue方法，否则会报线程异常错误
                    currentSecond.postValue(currentSecond.getValue() + 1);
                }
            };
            timer.schedule(timerTask, 1000, 1000);//延迟3秒执行
        }
    }

    /**
     * 由于屏幕旋转导致的Activity重建，该方法不会被调用
     * <p>
     * 只有ViewModel已经没有任何Activity与之有关联，系统则会调用该方法，你可以在此清理资源
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared()");
        timer.cancel();
    }
}
