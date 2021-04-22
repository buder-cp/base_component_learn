package com.example.lifecycletest.sharedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareDataViewModel extends ViewModel {
    private MutableLiveData<Integer> progress;

    public LiveData<Integer> getProgress() {
        if (progress == null) {
            progress = new MutableLiveData<>();
        }
        return progress;
    }

    /**
     * 由于屏幕旋转导致的Activity重建，该方法不会被调用
     * <p>
     * 只有ViewModel已经没有任何Activity与之有关联，系统则会调用该方法，你可以在此清理资源
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        progress = null;
    }
}
