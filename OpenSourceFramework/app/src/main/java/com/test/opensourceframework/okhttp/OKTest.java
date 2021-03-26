package com.test.opensourceframework.okhttp;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 * okhttp 整个流程：
 * 通过OkHttpClient将构建的Request转换为Call，
 * 然后在RealCall中进行异步或同步任务，
 * 最后通过一些的拦截器interceptor发出网络请求和得到返回的response。
 */

//OKHTTP之缓存配置详解
//https://blog.csdn.net/briblue/article/details/52920531

public class OKTest {
    private static final String URL = "http://publicobject.com/helloworld.txt";

    public static final CacheControl FORCE_CACHE = new CacheControl.Builder()
            .onlyIfCached()//只使用缓存
            .noCache()//不使用缓存，用网络请求
            .noStore()//不使用缓存，也不存储缓存
            .maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS)
            .build();
    //缓存文件夹
    File cacheFile = new File(/*getExternalCacheDir().toString()*/"路径/路径/路径","cache");
    //缓存大小为10M
    int cacheSize = 10 * 1024 * 1024;
    //创建缓存对象
    final Cache cache = new Cache(cacheFile,cacheSize);
    //设置缓存时间为60秒
    CacheControl cacheControl = new CacheControl.Builder()
            .maxAge(60, TimeUnit.SECONDS)
            .build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient().newBuilder()
            .addInterceptor(new LoggingInterceptor())
            .addNetworkInterceptor(new CacheInterceptor())//设置cache方式一，不推荐
            .cache(cache)
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    return null;
                }
            })
            .build();

    //HTTP POST请求
    void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL)
                .cacheControl(cacheControl)
                .cacheControl(FORCE_CACHE)
                .post(body)
                .build();

        //同步请求
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
//            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }

        //异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });

    }
}
