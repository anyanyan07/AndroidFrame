package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.TabBean;
import com.xwtec.androidframe.ui.register.RegisterResponseBean;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ayy on 2018/5/28.
 * Describe:网络接口
 */

public interface Service {

    /**
     * 首页Banner
     *
     * @param showNumber
     * @return
     */
    @GET("farmImgs/getHomeSlide")
    Observable<BaseResponse<List<BannerBean>>> getHomeBanner(@Query("showNumber") int showNumber);

    /**
     * 首页商品列表
     *
     * @param map
     * @return
     */
    @GET("goods/getGoods")
    Observable<BaseResponse<List<GoodListBean>>> getGoodList(@QueryMap HashMap<String, Object> map);

    /**
     * 获取首页商品定义分类
     *
     * @return
     */
    @GET("defines/getDefines")
    Observable<BaseResponse<List<TabBean>>> fetchGoodDefines();

    /**
     * 获取分类种类列表数据
     */
    @GET("categors/getCategors")
    Observable<BaseResponse<List<CategoryBean>>> fetchCategories();

    /**
     * 回去某一种类下的二级分类
     */
    @GET("categors/getGoods")
    Observable<BaseResponse<List<CategoryContentBean>>> fetchCategoryContent(@QueryMap HashMap<String, Object> map);

    /**
     * 查询购物车
     */
    @GET("shoppingCarts/getShoppingCarts")
    Observable<BaseResponse<List<ShopCartBean>>> fetchShopCartData(@QueryMap HashMap<String, Object> map);

    /**
     * 添加到购物车
     */
    @FormUrlEncoded
    @POST("shoppingCarts/add")
    Observable<BaseResponse> addShopCart(@FieldMap HashMap<String, Object> map);

    /**
     * 从购物车删除
     */
    @DELETE("shoppingCarts/delete")
    Observable<BaseResponse> deleteFromShopCart(@Query("ids") String ids);

    /**
     * 修改购物车数量
     */
    @FormUrlEncoded
    @PUT("shoppingCarts/update")
    Observable<BaseResponse> updateShopCart(@FieldMap HashMap<String, Object> map);

    /**
     * 注册
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResponse<RegisterResponseBean>> register(@FieldMap HashMap<String, Object> map);

    /**
     * 商品详情
     */
    @GET("goods/getGoodsDetail")
    Observable<BaseResponse<GoodDetailResponse>> fetchGoodDetail(@Query("goodsId") long goodId);
}
