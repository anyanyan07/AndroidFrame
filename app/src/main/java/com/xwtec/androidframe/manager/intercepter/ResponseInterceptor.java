
package com.xwtec.androidframe.manager.intercepter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ayy on 2018/5/29.
 * Describe:统一对结果进行处理
 */

public class ResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        int code = response.code();
        switch (code) {
            case 300:
                break;
            default:
                break;
        }
        return response;
    }
}
