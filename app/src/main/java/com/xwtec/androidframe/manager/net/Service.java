package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.address.Address;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.TabBean;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.myOrders.bean.Order;
import com.xwtec.androidframe.ui.register.RegisterResponseBean;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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
    Observable<BaseResponse> deleteFromShopCart(@Query("ids") String ids, @Query("token") String token);

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

    /**
     * 发送短信验证码
     */
    @FormUrlEncoded
    @POST("codes/sendCode")
    Observable<BaseResponse> sendVerifyCode(@FieldMap HashMap<String, Object> map);

    /**
     * 登录
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<UserBean>> login(@FieldMap HashMap<String, Object> map);

    /**
     * 重置密码
     */
    @FormUrlEncoded
    @POST("user/findPassWord")
    Observable<BaseResponse> resetPassword(@FieldMap HashMap<String, Object> map);

    /**
     * 意见反馈
     */
    @FormUrlEncoded
    @POST("feedBacks/save")
    Observable<BaseResponse> feedback(@FieldMap HashMap<String, Object> map);

    /**
     * 退出登录
     */
    @FormUrlEncoded
    @POST("user/exit")
    Observable<BaseResponse> logout(@Field("token") String token);

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("user/updatePassWord")
    Observable<BaseResponse> updatePassword(@FieldMap HashMap<String, Object> map);

    /**
     * 修改个人信息
     */
    @PUT("user/update")
    Observable<BaseResponse> updatePersonalInfo(@Body RequestBody requestBody);

    /**
     * 查询收货地址
     */
    @GET("receiveAddresses/getReceiveAddresses")
    Observable<BaseResponse<List<Address>>> queryAddress(@Query("token") String token);

    /**
     * 添加收货地址
     */
    @POST("receiveAddresses/save")
    Observable<BaseResponse> addAddress(@Body RequestBody body);

    /**
     * 修改收货地址
     */
    @PUT("receiveAddresses/update")
    Observable<BaseResponse> updateAddress(@Body RequestBody body);

    /**
     * 删除收货地址
     */
    @DELETE("receiveAddresses/delete")
    Observable<BaseResponse> deleteAddress(@QueryMap HashMap<String, Object> map);

    /**
     * 上传头像
     */
    @Multipart
    @POST("user/upHeadImg")
    Observable<BaseResponse> uploadHeader(@PartMap HashMap<String, Object> map, @Part MultipartBody.Part file);

    /**
     * 我的订单列表
     */
    @GET("orders/getOrder")
    Observable<BaseResponse<List<Order>>> fetchOrders(@QueryMap HashMap<String, Object> map);
}
