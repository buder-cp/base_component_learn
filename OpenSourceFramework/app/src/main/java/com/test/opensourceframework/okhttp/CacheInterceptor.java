package com.test.opensourceframework.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

//拦截器进行缓存
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().removeHeader("param")
                .header("Cache-Control", "max-age=60")
                .build();
    }
}
