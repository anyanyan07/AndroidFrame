package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.address.Address;
import com.xwtec.androidframe.ui.affirmOrder.bean.AffirmResponse;
import com.xwtec.androidframe.ui.affirmOrder.bean.SubmitOrderBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.TabBean;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.myOrders.bean.Order;
import com.xwtec.androidframe.ui.orderDetail.bean.CanceledInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.FinishedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SendedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitPayInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;
import com.xwtec.androidframe.ui.register.RegisterResponseBean;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/5/28.
 * Describe:所有接口声明
 */

public interface NetResourceRepo {
    /**
     * 首页Banner
     */
    Observable<BaseResponse<List<BannerBean>>> getHomeBanner(int showNumber);

    /**
     * 首页商品列表
     */
    Observable<BaseResponse<List<GoodListBean>>> getGoodList(HashMap<String, Object> map);

    /**
     * 首页商品定义分类
     */
    Observable<BaseResponse<List<TabBean>>> fetchGoodDefines();

    /**
     * 分类-左侧
     */
    Observable<BaseResponse<List<CategoryBean>>> fetchCategories();

    /**
     * 分类-右侧
     */
    Observable<BaseResponse<List<CategoryContentBean>>> fetchCategoryContent(HashMap<String, Object> map);

    /**
     * 查询购物车数量
     */
    Observable<BaseResponse<List<ShopCartBean>>> fetchShopCartData(HashMap<String, Object> map);

    /**
     * 修改购物车数量
     */
    Observable<BaseResponse> updateShopCart(HashMap<String, Object> map);

    /**
     * 添加到购物车
     */
    Observable<BaseResponse> addShopCart(HashMap<String, Object> map);

    /**
     * 从购物车中删除
     */
    Observable<BaseResponse> deleteFromShopCart(String ids, String token);

    /**
     * 注册
     */
    Observable<BaseResponse<RegisterResponseBean>> register(HashMap<String, Object> map);

    /**
     * 商品详情
     */
    Observable<BaseResponse<GoodDetailResponse>> fetchGoodDetail(long goodId);

    /**
     * 发送验证码
     */
    Observable<BaseResponse> sendVerifyCode(HashMap<String, Object> map);

    /**
     * 登录
     */
    Observable<BaseResponse<UserBean>> login(HashMap<String, Object> map);

    /**
     * 重置密码
     */
    Observable<BaseResponse> resetPassword(HashMap<String, Object> map);

    /**
     * 意见反馈
     */
    Observable<BaseResponse> feedback(HashMap<String, Object> map);

    /**
     * 退出登录
     */
    Observable<BaseResponse> logout(String token);

    /**
     * 修改密码
     */
    Observable<BaseResponse> updatePassword(HashMap<String, Object> map);

    /**
     * 修改个人信息
     */
    Observable<BaseResponse> updatePersonalInfo(RequestBody requestBody);

    /**
     * 查询收货地址
     */
    Observable<BaseResponse<List<Address>>> queryAddress(String token);

    /**
     * 修改收货地址
     */
    Observable<BaseResponse> updateAddress(RequestBody requestBody);

    /**
     * 添加收货地址
     */
    Observable<BaseResponse> addAddress(RequestBody requestBody);

    /**
     * 删除收货地址
     */
    Observable<BaseResponse> deleteAddress(HashMap<String, Object> map);

    /**
     * 上传头像
     */
    Observable<BaseResponse> uploadHeader(HashMap<String, Object> map, MultipartBody.Part file);

    /**
     * 我的订单列表
     */
    Observable<BaseResponse<List<Order>>> fetchOrders(HashMap<String, Object> map);

    /**
     * 确认订单
     */
    Observable<BaseResponse<AffirmResponse>> affirmOrder(RequestBody body);

    /**
     * 提交订单
     */
    Observable<BaseResponse<SubmitOrderBean>> submitOrder(RequestBody body);

    /**
     * 待支付订单详情
     */
    Observable<BaseResponse<WaitPayInfo>> fetchWaitPayInfo(long orderId, String token);

    /**
     * 取消订单
     */
    Observable<BaseResponse> cancelOrder(long orderId, String token);

    /**
     * 删除订单
     */
    Observable<BaseResponse> deleteOrder(String orderIds, String token);

    /**
     * 确认收货
     */
    Observable<BaseResponse> sureReceive(String orderId, String token);

    /**
     * 已完成订单详情
     */
    Observable<BaseResponse<FinishedInfo>> fetchFinishedInfo(long orderId, String token);

    /**
     * 已取消订单详情
     */
    Observable<BaseResponse<CanceledInfo>> fetchCanceledInfo(long orderId, String token);

    /**
     * 已发货订单详情
     */
    Observable<BaseResponse<SendedInfo>> fetchSendedInfo(long orderId, String token);

    /**
     * 退款中详情
     */
    Observable<BaseResponse<RefundingInfo>> fetchRefundingInfo(long orderId, String token);

    /**
     * 获取个人信息
     */
    Observable<BaseResponse<UserBean>> fetchUserInfo(String token);

    /**
     * 退货
     */
    Observable<BaseResponse> salesReturn(HashMap<String, Object> map);
}
