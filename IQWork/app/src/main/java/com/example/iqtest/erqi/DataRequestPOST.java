package com.example.iqtest.erqi;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DataRequestPOST {

    public static final String TAG = "buder";

    private static final String URL = "http://121.36.46.118:8080/api/page/cis-render";

    public static void doRequest() {
        Log.e("buder", "开始请求");
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, getJsonString());
        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });

    }

    private static String getJsonString() {
        JSONObject cc = new JSONObject();
        cc.put("key", "6");
        cc.put("areaIdCity", "6");
        cc.put("areaIdCounty", "6");
        cc.put("userGroup", "6");
        cc.put("pageId", "900001");
        cc.put("pageNo", "1");
        cc.put("pageSize", "8");
        cc.put("userid", "6");
        cc.put("mac", "10.8.611.0");
        Log.e("buder", "body json: " + cc.toJSONString());
        return cc.toJSONString();
    }

}
