package com.example.lifecycletest.sharedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lifecycletest.R;


public class TwoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_two, container, false);

        final SeekBar seekBar = parentView.findViewById(R.id.seekBar);

        //注意：这里ViewModelProviders.of(getActivity())这里的参数需要是Activity，而不能是Fragment，否则收不到监听
        final ShareDataViewModel shareDataViewModel = ViewModelProviders.of(getActivity()).get(ShareDataViewModel.class);
        final MutableLiveData<Integer> liveData = (MutableLiveData<Integer>) shareDataViewModel.getProgress();

        //通过observe方法观察ViewModel中字段数据的变化，并在变化时，得到通知
        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer progress) {
                seekBar.setProgress(progress);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //用户操作SeekBar时，更新ViewModel中的数据
                liveData.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return parentView;
    }
}
