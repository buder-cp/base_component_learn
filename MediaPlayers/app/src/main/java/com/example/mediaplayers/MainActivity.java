package com.example.mediaplayers;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener
        , MediaPlayer.OnVideoSizeChangedListener {

    private SurfaceView surfaceView;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private ProgressBar progressBar;

    private int surfaceWidth;
    private int surfaceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("buder", "onCreate");

        surfaceView = findViewById(R.id.surfaceView);
        progressBar = findViewById(R.id.progressBar);

        String url = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";

        player = new MediaPlayer();
        try {
            player.setDataSource(url);
            holder = surfaceView.getHolder();
            holder.addCallback(new MyCallBack());

            Log.e("buder", "onPrepared start");
            player.prepare();
            player.setOnPreparedListener(this);
            player.setOnVideoSizeChangedListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e("buder", "onPrepared end");
        progressBar.setVisibility(View.INVISIBLE);
        player.start();
        player.setLooping(true);
    }

    /**
     * 调整video的尺寸，以适配surfaceView的大小
     * @param mp media的参数
     * @param width 视频的宽度
     * @param height 视频的高度
     */
    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        Log.e("buder", "onVideoSizeChanged");
        Log.e("buder", "getVideoWidth：" + mp.getVideoWidth() + " getVideoHeight：" + mp.getVideoHeight()
                + " width：" + width + " height：" + height);
        changeVideoSize();
    }

    private void changeVideoSize() {
        int videoWidth = player.getVideoWidth();
        int videoHeight = player.getVideoHeight();

        float scale;
        if (surfaceWidth != 0 && surfaceHeight != 0) {
            scale = Math.max((float) videoWidth / (float) surfaceWidth, (float) videoHeight / (float) surfaceHeight);
            Log.e("buder", "videoWidth:" + videoWidth + " videoHeight:" + videoHeight +
                    " surfaceWidth:" + surfaceWidth + " surfaceHeight:" + surfaceHeight + " scale:" + scale);

            videoWidth = (int) Math.ceil((float) videoWidth / scale);
            videoHeight = (int) Math.ceil((float) videoHeight / scale);
            surfaceView.setLayoutParams(new RelativeLayout.LayoutParams(videoWidth, videoHeight));
        }

    }

    private class MyCallBack implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.d("buder", "surfaceCreated");
            surfaceWidth = surfaceView.getWidth();
            surfaceHeight = surfaceView.getHeight();
            Log.d("buder", "surfaceWidth:" + surfaceWidth + " surfaceHeight:" + surfaceHeight);

            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d("buder", "surfaceChanged");
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d("buder", "surfaceDestroyed");
        }
    }

}
