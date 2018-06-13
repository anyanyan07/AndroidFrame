package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.home.BannerBean;
import com.xwtec.androidframe.ui.home.GoodListBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ayy on 2018/5/28.
 * Describe:网络接口
 */

public interface Service {

    /**
     * 首页Banner
     * @param showNumber
     * @return
     */
    @GET("farmImgs/getHomeSlide")
    Observable<BaseResponse<List<BannerBean>>> getHomeBanner(@Query("showNumber") int showNumber);

    /**
     * 首页商品列表
     * @param map
     * @return
     */
    @GET("goods/getGoods")
    Observable<BaseResponse<List<GoodListBean>>> getGoodList(@QueryMap HashMap map);


}
