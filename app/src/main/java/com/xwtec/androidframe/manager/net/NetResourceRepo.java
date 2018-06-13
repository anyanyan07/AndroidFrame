package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.home.BannerBean;
import com.xwtec.androidframe.ui.home.GoodListBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ayy on 2018/5/28.
 * Describe:所有接口声明
 */

public interface NetResourceRepo {
    /**
     *首页Banner
     */
    Observable<BaseResponse<List<BannerBean>>> getHomeBanner(int showNumber);

    /**
     *首页商品列表
     */
    Observable<BaseResponse<List<GoodListBean>>> getGoodList(HashMap map);
}
