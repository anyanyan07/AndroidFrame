package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ayy on 2018/5/28.
 * Describe:网络接口调用
 */

public class NetResourceRepoSupport implements NetResourceRepo{
    private Service service;

    @Inject
    public NetResourceRepoSupport(Service service) {
        this.service = service;
    }

    //登录
    @Override
    public Observable<BaseResponse> login(Map<String, String> map) {
        return service.shopLogin(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }
}
