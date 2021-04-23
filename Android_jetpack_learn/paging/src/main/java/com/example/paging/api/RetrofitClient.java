package com.example.paging.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.douban.com/v2/";
    private static final String API_KEY = "0df993c66c0c636e29ecbb5344252a4a";

    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }

    /**
     * 为每个请求添加API_KEY参数
     */
    private OkHttpClient getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", API_KEY)
                        .build();
                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient != null) {
            return retrofitClient;
        }
        synchronized (RetrofitClient.class) {
            if (retrofitClient == null) {
                retrofitClient = new RetrofitClient();
            }
        }
        return retrofitClient;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

}
