package com.xwtec.androidframe.manager.intercepter;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: ayy
 * created on: 2018/3/5 0005
 * description: 给请求添加请求头
 */
public final class HeaderInterceptor implements Interceptor {

    private String deviceId;
    private String resolution;
    private String imsi;
    private String netType;
    private String channel;

    public HeaderInterceptor(Context context) {
//        this.deviceId = DeviceUtil.getImei(context);
//        this.resolution = DeviceUtil.getScreen(context);
//        this.imsi = DeviceUtil.getImsi(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request addHeaderRequest = chain.request()
                .newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        return chain.proceed(addHeaderRequest);
    }
}
