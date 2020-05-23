package com.test.buderdn18_webp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.google.webp.libwebp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "buder";

    static {
        System.loadLibrary("webp");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        //webp，JPEG，png   解码速度测试
        long l = System.currentTimeMillis();
        BitmapFactory.decodeResource(getResources(), R.drawable.splash_bg_webp);
        Log.e(TAG, "解码webp图片耗时:" + (System.currentTimeMillis() - l));
        l = System.currentTimeMillis();
        BitmapFactory.decodeResource(getResources(), R.drawable.splash_bg_jpeg);
        Log.e(TAG, "解码jpeg图片耗时:" + (System.currentTimeMillis() - l));
        l = System.currentTimeMillis();
        BitmapFactory.decodeResource(getResources(), R.drawable.splash_bg_png);
        Log.e(TAG, "解码png图片耗时:" + (System.currentTimeMillis() - l));


        //webp，JPEG，png   编码速度测试
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash_bg_png);

        l = System.currentTimeMillis();
        compressBitmap(bitmap, Bitmap.CompressFormat.PNG, Environment.getExternalStorageDirectory() + "/test.png");
        Log.e(TAG, "------->编码png图片耗时:" + (System.currentTimeMillis() - l));

        l = System.currentTimeMillis();
        compressBitmap(bitmap, Bitmap.CompressFormat.JPEG, Environment.getExternalStorageDirectory() + "/test.jpeg");
        Log.e(TAG, "------->编码jpeg图片耗时:" + (System.currentTimeMillis() - l));

        l = System.currentTimeMillis();
        compressBitmap(bitmap, Bitmap.CompressFormat.WEBP, Environment.getExternalStorageDirectory() + "/test.webp");
        Log.e(TAG, "------->编码webp图片耗时:" + (System.currentTimeMillis() - l));

//        libwebp 编解码速度测试
        l = System.currentTimeMillis();
        decodeWebp();
        Log.e(TAG, "libwebp解码图片耗时:" + (System.currentTimeMillis() - l));
        l = System.currentTimeMillis();
        encodeWebp(bitmap);
        Log.e(TAG, "libwebp编码图片耗时:" + (System.currentTimeMillis() - l));
    }


    private void encodeWebp(Bitmap bitmap) {
        //获取bitmap 宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //获得bitmap中的 ARGB 数据 nio
        ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(buffer);
        //编码 获得 webp格式文件数据  4 *width
        byte[] bytes = libwebp.WebPEncodeRGBA(buffer.array(), width, height, width * 4, 75);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Environment
                    .getExternalStorageDirectory() + "/libwebp.webp");
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap decodeWebp() {
        @SuppressLint("ResourceType") InputStream is = getResources().openRawResource(R.drawable.splash_bg_webp);
        byte[] bytes = stream2Bytes(is);
        //将webp格式的数据转成 argb
        int[] width = new int[1];
        int[] height = new int[1];
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] argb = libwebp.WebPDecodeARGB(bytes, bytes.length, width, height);
        //将argb byte数组转成 int数组
        int[] pixels = new int[argb.length / 4];
        ByteBuffer.wrap(argb).asIntBuffer().get(pixels);
        //获得bitmap
        Bitmap bitmap = Bitmap.createBitmap(pixels, width[0], height[0], Bitmap.Config.ARGB_8888);
        return bitmap;
    }

    byte[] stream2Bytes(InputStream is) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len;
        try {
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }


    private void compressBitmap(Bitmap bitmap, Bitmap.CompressFormat format, String file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(format, 75, fos);
        if (null != fos) {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkPermission() {
        boolean isGranted = true;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //如果没有写sd卡权限
                isGranted = false;
            }
            if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            Log.i("cbs", "isGranted == " + isGranted);
            if (!isGranted) {
                this.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            }
        }

    }
}
