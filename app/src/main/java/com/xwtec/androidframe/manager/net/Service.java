package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;

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
    Observable<BaseResponse<List<GoodListBean>>> getGoodList(@QueryMap HashMap<String,Object> map);

    /**
     * 获取分类种类列表数据
     */
    @GET("categors/getCategors")
    Observable<BaseResponse<List<CategoryBean>>> fetchCategories();

    /**
     * 回去某一种类下的二级分类
     */
    @GET("categors/getGoods")
    Observable<BaseResponse<List<CategoryContentBean>>> fetchCategoryContent(@QueryMap HashMap<String,Object> map);
}
