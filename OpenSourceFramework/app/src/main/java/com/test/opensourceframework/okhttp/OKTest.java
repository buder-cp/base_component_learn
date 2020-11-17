package com.test.opensourceframework.okhttp;

import android.util.Log;

import java.io.IOException;
import java.util.List;

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

public class OKTest {

    private static final String TAG = "buder";

    private static final String URL = "http://publicobject.com/helloworld.txt";


    //HTTP GET
//    OkHttpClient client = new OkHttpClient();
//    String run(String url) throws IOException {
//        Request request = new Request.Builder().url(url).build();
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
//            return response.body().string();
//        } else {
//            throw new IOException("Unexpected code " + response);
//        }
//    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient().newBuilder()
            .addInterceptor(new LoggingInterceptor())
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

    void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        //同步请求
        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
//            return response.body().string();
//        } else {
//            throw new IOException("Unexpected code " + response);
//        }

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
