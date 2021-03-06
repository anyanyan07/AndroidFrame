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
    Observable<BaseResponse<String>> uploadHeader(RequestBody token, MultipartBody.Part file);

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
    Observable<BaseResponse> salesReturn(RequestBody requestBody);

    /**
     * 已收货详情
     */
    Observable<BaseResponse<ReceivedInfo>> fetchReceivedInfo(long orderId, String token);

    /**
     * 待发货详情
     */
    Observable<BaseResponse<WaitSendInfo>> fetchWaitSendInfo(long orderId, String token);

    /**
     * 物流公司列表
     */
    Observable<BaseResponse<List<Express>>> fetchExpressList();

    /**
     * 退款
     */
    Observable<BaseResponse> moneyReturn(HashMap<String, Object> map);

    /**
     * 已退款详情
     */
    Observable<BaseResponse<RefundedInfo>> fetchRefundedInfo(long orderId, String token);

    /**
     * 已退货详情
     */
    Observable<BaseResponse<SalesReturnedInfo>> fetchSaleReturnedInfo(long orderId, String token);

    /**
     * 退货中详情
     */
    Observable<BaseResponse<SalesReturningInfo>> fetchSaleReturningInfo(long orderId, String token);

    /**
     * 已确认收货详情
     */
    Observable<BaseResponse<SureReceivedInfo>> fetchSureReceivedInfo(long orderId, String token);

    /**
     * 评论列表
     */
    Observable<BaseResponse<List<CommentInfo>>> fetchGoodComment(long goodsId, int commentLevel, int startIndex, int showNumber);

    /**
     * 物流信息
     */
    Observable<BaseResponse<List<ExpressInfo>>> fetchExpressInfo(String orderNum,String token);
    /**
     * 支付
     */
    Observable<BaseResponse<PayBean>> pay(RequestBody requestBody);

    /**
     * 提交评论
     */
    Observable<BaseResponse> submitComment(Map<String, RequestBody> map, List<MultipartBody.Part> parts);
}
