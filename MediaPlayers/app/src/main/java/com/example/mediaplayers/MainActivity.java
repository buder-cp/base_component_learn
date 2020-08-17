package com.example.mediaplayers;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

/**
 * 启动：
 * 08-17 17:00:21.522 17704-17704/com.example.mediaplayers E/buder: onCreate
 * 08-17 17:00:21.573 17704-17704/com.example.mediaplayers E/buder: onPrepared start
 * 08-17 17:00:21.637 17704-17704/com.example.mediaplayers D/buder: surfaceCreated
 * 08-17 17:00:21.637 17704-17704/com.example.mediaplayers D/buder: surfaceWidth:1920 surfaceHeight:600
 * 08-17 17:00:21.646 17704-17704/com.example.mediaplayers D/buder: surfaceChanged
 * 08-17 17:00:22.305 17704-17704/com.example.mediaplayers E/buder: onVideoSizeChanged
 * 08-17 17:00:22.307 17704-17704/com.example.mediaplayers E/buder: getVideoWidth：320 getVideoHeight：176 width：320 height：176
 * 08-17 17:00:22.307 17704-17704/com.example.mediaplayers E/buder: videoWidth:320 videoHeight:176 surfaceWidth:1920 surfaceHeight:600 scale:0.29333332
 * 08-17 17:00:22.329 17704-17704/com.example.mediaplayers E/buder: onPrepared end
 * 08-17 17:00:22.403 17704-17704/com.example.mediaplayers D/buder: surfaceChanged
 * 08-17 17:00:22.454 17704-17704/com.example.mediaplayers E/buder: onVideoSizeChanged
 * 08-17 17:00:22.454 17704-17704/com.example.mediaplayers E/buder: getVideoWidth：320 getVideoHeight：176 width：320 height：176
 * 08-17 17:00:22.455 17704-17704/com.example.mediaplayers E/buder: videoWidth:320 videoHeight:176 surfaceWidth:1920 surfaceHeight:600 scale:0.29333332
 *
 * 跳转到第二个activity：
 * 08-17 17:00:27.297 17704-17704/com.example.mediaplayers D/buder: onPause
 * 08-17 17:00:27.439 17704-17704/com.example.mediaplayers D/buder: surfaceDestroyed
 * 08-17 17:00:27.475 17704-17704/com.example.mediaplayers D/buder: onStop
 *
 *
 * 跳转回surfaceView的activity：
 * 08-17 17:00:32.360 17704-17704/com.example.mediaplayers D/buder: surfaceCreated
 * 08-17 17:00:32.361 17704-17704/com.example.mediaplayers D/buder: surfaceWidth:1091 surfaceHeight:600
 * 08-17 17:00:32.376 17704-17704/com.example.mediaplayers D/buder: surfaceChanged
 *
 * back键退出activity：
 * 08-17 17:00:36.371 17704-17704/com.example.mediaplayers D/buder: onPause
 * 08-17 17:00:36.797 17704-17704/com.example.mediaplayers D/buder: surfaceDestroyed
 * 08-17 17:00:36.850 17704-17704/com.example.mediaplayers D/buder: onStop
 * 08-17 17:00:36.850 17704-17704/com.example.mediaplayers D/buder: onDestroy
 */

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener
        , MediaPlayer.OnVideoSizeChangedListener {

    private SurfaceView surfaceView;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private ProgressBar progressBar;
    private Button btn_go;;

    private int surfaceWidth;
    private int surfaceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("buder", "onCreate");

        surfaceView = findViewById(R.id.surfaceView);
        progressBar = findViewById(R.id.progressBar);
        btn_go = findViewById(R.id.btn_jump);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

//        String url = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";
        String url = "http://www.w3school.com.cn/example/html5/mov_bbb.mp4";
//        String url = "http://www.mp4";

        player = new MediaPlayer();
        try {
            player.setDataSource(url.replaceAll(" ", "%20"));
            holder = surfaceView.getHolder();
            holder.addCallback(new MyCallBack());

            Log.e("buder", "onPrepared start");
            player.prepareAsync();
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("buder", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("buder", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("buder", "onDestroy");
    }
}

