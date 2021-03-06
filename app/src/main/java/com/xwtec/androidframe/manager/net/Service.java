package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.address.bean.Address;
import com.xwtec.androidframe.ui.affirmOrder.bean.AffirmResponse;
import com.xwtec.androidframe.ui.affirmOrder.bean.SubmitOrderBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;
import com.xwtec.androidframe.ui.express.Express;
import com.xwtec.androidframe.ui.expressInfo.ExpressInfo;
import com.xwtec.androidframe.ui.goodDetail.bean.CommentInfo;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.TabBean;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.myOrders.bean.Order;
import com.xwtec.androidframe.ui.orderDetail.bean.CanceledInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.FinishedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.ReceivedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SendedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SureReceivedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitPayInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitSendInfo;
import com.xwtec.androidframe.ui.pay.PayBean;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturnedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturningInfo;
import com.xwtec.androidframe.ui.register.RegisterResponseBean;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Observable<BaseResponse<String>> uploadHeader(@Part("token") RequestBody token, @Part MultipartBody.Part file);

    /**
     * 获取个人信息
     */
    @GET("user/getUser")
    Observable<BaseResponse<UserBean>> fetchUserInfo(@Query("token") String token);

    /**
     * 我的订单列表
     */
    @GET("orders/getOrder")
    Observable<BaseResponse<List<Order>>> fetchOrders(@QueryMap HashMap<String, Object> map);

    /**
     * 确认订单
     */
    @POST("orders/settlement")
    Observable<BaseResponse<AffirmResponse>> affirmOrder(@Body RequestBody body);

    /**
     * 提交订单
     */
    @POST("orders/saveOrder")
    Observable<BaseResponse<SubmitOrderBean>> submitOrder(@Body RequestBody body);

    /**
     * 待付款订单详情
     */
    @GET("orders/getPendingPaymentOrderDetail")
    Observable<BaseResponse<WaitPayInfo>> fetchWaitPayInfo(@Query("orderId") long orderId, @Query("token") String token);

    /**
     * 取消订单
     */
    @FormUrlEncoded
    @PUT("orders/cancelOrder")
    Observable<BaseResponse> cancelOrder(@Field("orderId") long orderId, @Field("token") String token);

    /**
     * 删除订单
     */
    @DELETE("orders/deleteOrder")
    Observable<BaseResponse> deleteOrder(@Query("orderIds") String orderIds, @Query("token") String token);

    /**
     * 确认收货
     */
    @FormUrlEncoded
    @PUT("orders/confirmCollectGoods")
    Observable<BaseResponse> sureReceive(@Field("orderId") String orderId, @Field("token") String token);

    /**
     * 获得已完成订单详情
     */
    @GET("orders/getCompletedOrderDetail")
    Observable<BaseResponse<FinishedInfo>> fetchFinishedInfo(@Query("orderId") long orderId, @Query("token") String token);

    /**
     * 已取消订单详情
     */
    @GET("orders/getCancelledOrderDetail")
    Observable<BaseResponse<CanceledInfo>> fetchCanceledInfo(@Query("orderId") long orderId, @Query("token") String token);

    /**
     * 已发货订单详情
     *
     * @param orderId
     * @param token
     * @return
     */
    @GET("orders/getAlreadyShippedOrderDetail")
    Observable<BaseResponse<SendedInfo>> fetchSendedInfo(@Query("orderId") long orderId, @Query("token") String token);

    /**
     * 已收货订单详情
     */
    @GET("orders/getReceivedGoodsOrderDetail")
    Observable<BaseResponse<ReceivedInfo>> fetchReceivedInfo(@Query("orderId") long orderId, @Query("token") String token);

    /**
     * 待发货订单详情
     */
    @GET("orders/getPendingDeliveryOrderDetail")
    Observable<BaseResponse<WaitSendInfo>> fetchWaitSendInfo(@Query("orderId") long orderId, @Query("token") String token);

    /**
     * 退款中详情
     */
    @GET("orders/getRefundingOrderDetail")
    Observable<BaseResponse<RefundingInfo>> fetchRefundingInfo(@Query("orderId") long orderId, @Query("token") String token);

    /**
     * 已退款详情
     */
    @GET("orders/getRefundedOrderDetail")
    Observable<BaseResponse<RefundedInfo>> fetchRefundedInfo(@Query("orderId") long orderId, @Query("token") String token);

    /**
     * 已退货详情
     */
    @GET("orders/getReturnedGoodsOrderDetail")
    Observable<BaseResponse<SalesReturnedInfo>> fetchSaleRefundedInfo(@Query("orderId") long orderId, @Query("token") String token);
    /**
     * 退货中详情
     */
    @GET("orders/getReturningGoodsOrderDetail")
    Observable<BaseResponse<SalesReturningInfo>> fetchSaleReturningInfo(@Query("orderId") long orderId, @Query("token") String token);
    /**
     * 已确认收货详情
     */
    @GET("orders/getConfirmationOrderDetail")
    Observable<BaseResponse<SureReceivedInfo>> fetchSureReceivedInfo(@Query("orderId") long orderId, @Query("token") String token);
    /**
     * 退货
     */
    @POST("orders/returnGoods")
    Observable<BaseResponse> salesReturn(@Body RequestBody body);

    /**
     * 退款
     */
    @FormUrlEncoded
    @PUT("orders/refund")
    Observable<BaseResponse> moneyReturn(@FieldMap HashMap<String, Object> map);

    /**
     * 物流公司列表
     */
    @GET("expressCompanys/getExpressCompanys")
    Observable<BaseResponse<List<Express>>> fetchExpressList();

    /**
     * 评论列表
     */
    @GET("comments/selectGoodsComment")
    Observable<BaseResponse<List<CommentInfo>>> fetchGoodComment(@Query("goodsId") long goodId,
                                                                 @Query("commentLevel") int commentLevel,
                                                                 @Query("startIndex") int startIndex,
                                                                 @Query("showNumber") int showNumber);
    @GET("express/getExpressInfo")
    Observable<BaseResponse<List<ExpressInfo>>> fetchExpressInfo(@Query("orderNumber") String orderNum,
                                                                 @Query("token") String token);

    @POST("orders/orderPay")
    Observable<BaseResponse<PayBean>> pay(@Body RequestBody body);

//    @POST("comments/saveGoodsComment")
//    Observable<BaseResponse> submitComment(@Body MultipartBody multipartBody);

    @Multipart
    @POST("comments/saveGoodsComment")
    Observable<BaseResponse> submitComment(@PartMap Map<String, RequestBody > map, @Part List<MultipartBody.Part> parts);

}
