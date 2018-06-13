package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.home.BannerBean;
import com.xwtec.androidframe.ui.home.GoodListBean;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public Observable<BaseResponse<List<BannerBean>>> getHomeBanner(int showNumber) {
        return service.getHomeBanner(showNumber)
                .compose(new ObservableTransformer<BaseResponse<List<BannerBean>>, BaseResponse<List<BannerBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<BannerBean>>> apply(Observable<BaseResponse<List<BannerBean>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<List<GoodListBean>>> getGoodList(HashMap map) {
        return service.getGoodList(map)
                .compose(new ObservableTransformer<BaseResponse<List<GoodListBean>>, BaseResponse<List<GoodListBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<GoodListBean>>> apply(Observable<BaseResponse<List<GoodListBean>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }
}
