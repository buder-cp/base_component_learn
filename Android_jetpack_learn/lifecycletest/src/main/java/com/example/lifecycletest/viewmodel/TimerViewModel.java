package com.example.lifecycletest.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class TimerViewModel extends ViewModel {

    private String TAG = this.getClass().getName();
    private Timer timer;
    private int currentSecond;

    /**
     * 开始计时
     */
    public void startTiming() {
        if (timer == null) {
            currentSecond = 0;
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentSecond++;
                    if (onTimeChangeListener != null) {
                        onTimeChangeListener.onTimeChanged(currentSecond);
                    }
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }
    }

    /**
     * 通过接口的方式，完成对调用者的通知，这种方式不是太好，更好的方式是通过LiveData组件来实现
     */
    public interface OnTimeChangeListener {
        void onTimeChanged(int second);
    }

    private OnTimeChangeListener onTimeChangeListener;

    public void setOnTimeChangeListener(OnTimeChangeListener onTimeChangeListener) {
        this.onTimeChangeListener = onTimeChangeListener;
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
