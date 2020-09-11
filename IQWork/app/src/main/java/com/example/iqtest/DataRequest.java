package com.example.iqtest;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedHashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DataRequest {

    public static final String TAG = "buder";

    static void doPost() {
        String mac = "c0:13:2b:b4:fd:2b";
        String deviceChip = "ZLS82GiA";
        String deviceModel = "75A7E";
        String SECRET_KEY = "f531a67057234610955db3f2ad0f38d08b5ce2cade3249a7985b34a682061a52";
        String timestamp = String.valueOf(System.currentTimeMillis());//TODO ????
        String COMMOND = "f8fcadcfd7da421486c38a47ef79fe48";
        String pageId = "100001";
        String pageNo = "1";
        String version = "10.8.611.0";

        StringBuilder sb = new StringBuilder();
        sb.append(mac).append(deviceChip).append(deviceModel).append(SECRET_KEY)
                .append(timestamp).append(COMMOND).append(pageId).append(pageNo).append("8")
                .append(version);

        String sign = "";
        try {
            Log.e("buder", "sign before: " + sb.toString());
            sign = sign(sb.toString().getBytes());
            Log.e("buder", "sign: " + sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("sign", sign);
        map.put("timestamp", timestamp);
        map.put("nonce", "202008281847524047B7B0D1D0314618");
        String result = parseParams(map);
        String url = "http://launcher.chsmarttv.com/chtest/launcher/v4/getRecommendData/externalQySys?" + result;
        Log.e("buder", "开始请求：" + url);
        doRequest(url);
    }

    private static void doRequest(String url) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, getJsonString());
        Request request = new Request.Builder()
                .url(url)
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
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    public static String sign(byte[] bytes) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digested = md.digest(bytes);
        return byte2hex(digested);
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }


    private static String getJsonString() {
        JSONObject aa = new JSONObject();
        aa.put("deviceChip", "ZLS82GiA");
        aa.put("deviceModel", "75A7E");

        JSONObject bb = new JSONObject();
        bb.put("client", aa);
        bb.put("pageId", "100001");
        bb.put("pageNo", 1);
        bb.put("pageSize", 8);
        bb.put("version", "10.8.611.0");

        JSONObject cc = new JSONObject();
        cc.put("mac", "c0:13:2b:b4:fd:2b");
        cc.put("parameter", bb);

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
