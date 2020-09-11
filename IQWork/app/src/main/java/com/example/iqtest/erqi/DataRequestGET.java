package com.example.iqtest.erqi;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DataRequestGET {

    public static final String TAG = "buder";

    public static void doPost() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("apkVer", "666");
        map.put("areaIdCity", "666");
        map.put("areaIdCounty", "666");
        map.put("userGroup", "666");
        map.put("pageId", "900001");
        map.put("pageNo", "1");
        map.put("pageSize", "8");
        map.put("userid", "666");
        map.put("mac", "10.8.611.0");

        String result = parseParams(map);
        String url = "http://121.36.46.118:8080/api/page/cis-render?" + result;
        Log.e("buder", "开始请求：" + url);
        //写法一：
//        doRequest(url);

        //写法二：
//        doRequestByQueryParameter();

        //写法三：
//        doGetBodyRequest();
    }


    private static void doRequestByQueryParameter() {
        HttpUrl.Builder builder = HttpUrl.parse("http://121.36.46.118:8080/api/page/cis-render").newBuilder();
        builder.setQueryParameter("key", "666");
        builder.setQueryParameter("areaIdCity", "666");
        builder.setQueryParameter("areaIdCounty", "666");
        builder.setQueryParameter("userGroup", "666");
        builder.setQueryParameter("pageId", "900001");
        builder.setQueryParameter("pageNo", "1");
        builder.setQueryParameter("pageSize", "8");
        builder.setQueryParameter("userid", "666");
        builder.setQueryParameter("mac", "10.8.611.0");

        Request request = new Request.Builder()
                .get()
                .url(builder.build())
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
            }
        });

    }

    private static void doRequest(String url) {
        Request request = new Request.Builder()
                .get()
                .url(url)
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
            }
        });
    }

    private static void doGetBodyRequest() {
        Request request = new Request.Builder()
                .method("GET", RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getJsonString()))
                .url("http://121.36.46.118:8080/api/page/cis-render")
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


    private static String parseParams(HashMap<String, String> mParams) {
        String params = "";
        if (mParams != null) {
            for (String key : mParams.keySet()) {
                params += key + "=" + mParams.get(key) + "&";
            }
            if (!isEmpty(params) && params.endsWith("&")) {
                params = params.substring(0, params.length() - 1);
            }
        }
        return params;
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

}
