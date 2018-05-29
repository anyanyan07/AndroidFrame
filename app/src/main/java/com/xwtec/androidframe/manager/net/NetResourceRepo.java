package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public interface NetResourceRepo {
    Observable<BaseResponse> login(Map<String,String> map);
}
