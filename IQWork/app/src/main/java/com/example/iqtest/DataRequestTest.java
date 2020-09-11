package com.example.iqtest;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DataRequestTest {
    public static final String TAG = "buder";

//    private static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");

    public static void post(/*String url, String json*/) throws IOException {
//        RequestBody requestBody = RequestBody.create(mediaType, json);
        String requestBody = "I am Jdqm.";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
//                .post(body)
                .post(RequestBody.create(mediaType, requestBody))
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " +response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }





}
