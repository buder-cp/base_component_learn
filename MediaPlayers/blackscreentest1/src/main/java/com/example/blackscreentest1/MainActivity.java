package com.example.blackscreentest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    Button btn_go;
    private SurfaceHolder holder;
    private MediaPlayer mMediaPlayer;
    private String mediaPath = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surfaceView);
        btn_go = findViewById(R.id.go_btn);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        startPlayVideo();
    }


    private void startPlayVideo() {

        // 创建一个MediaPlayer对象
        mMediaPlayer = new MediaPlayer();
        // 设置播放的视频数据源
        try {
            mMediaPlayer.setDataSource(mediaPath);
        } catch (IOException e) {
            Toast.makeText(this, "视频地址错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // 播放准备，使用异步方式，配合OnPreparedListener
        mMediaPlayer.prepareAsync();
        // 设置相关的监听器
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
            }
        });
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer.start();

            }
        });
        mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int videoX, int videoY) {
            }
        });
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {

                return false;
            }
        });
        mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {

                return false;
            }
        });
        //播放时屏幕保持唤醒
        mMediaPlayer.setScreenOnWhilePlaying(true);

        //显示图像
        holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                mMediaPlayer.setDisplay(holder);

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }
        });

    }
}
