package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ayy on 2018/5/28.
 * Describe:网络接口
 */

public interface Service {
    @FormUrlEncoded
    @POST("login")
    Observable<BaseResponse> shopLogin(@FieldMap Map<String, String> map);
}
