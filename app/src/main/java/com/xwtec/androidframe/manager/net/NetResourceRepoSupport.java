package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.address.bean.Address;
import com.xwtec.androidframe.ui.affirmOrder.bean.AffirmResponse;
import com.xwtec.androidframe.ui.affirmOrder.bean.SubmitOrderBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;
import com.xwtec.androidframe.ui.express.Express;
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
import com.xwtec.androidframe.ui.refundDetail.bean.RefundedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturnedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturningInfo;
import com.xwtec.androidframe.ui.register.RegisterResponseBean;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/5/28.
 * Describe:网络接口调用
 */

public class NetResourceRepoSupport implements NetResourceRepo {
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
    public Observable<BaseResponse<List<GoodListBean>>> getGoodList(HashMap<String, Object> map) {
        return service.getGoodList(map)
                .compose(new ObservableTransformer<BaseResponse<List<GoodListBean>>, BaseResponse<List<GoodListBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<GoodListBean>>> apply(Observable<BaseResponse<List<GoodListBean>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<List<TabBean>>> fetchGoodDefines() {
        return service.fetchGoodDefines()
                .compose(new ObservableTransformer<BaseResponse<List<TabBean>>, BaseResponse<List<TabBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<TabBean>>> apply(Observable<BaseResponse<List<TabBean>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<List<CategoryBean>>> fetchCategories() {
        return service.fetchCategories()
                .compose(new ObservableTransformer<BaseResponse<List<CategoryBean>>, BaseResponse<List<CategoryBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<CategoryBean>>> apply(Observable<BaseResponse<List<CategoryBean>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<List<CategoryContentBean>>> fetchCategoryContent(HashMap<String, Object> map) {
        return service.fetchCategoryContent(map)
                .compose(new ObservableTransformer<BaseResponse<List<CategoryContentBean>>, BaseResponse<List<CategoryContentBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<CategoryContentBean>>> apply(Observable<BaseResponse<List<CategoryContentBean>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<List<ShopCartBean>>> fetchShopCartData(HashMap<String, Object> map) {
        return service.fetchShopCartData(map)
                .compose(new ObservableTransformer<BaseResponse<List<ShopCartBean>>, BaseResponse<List<ShopCartBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<ShopCartBean>>> apply(Observable<BaseResponse<List<ShopCartBean>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> updateShopCart(HashMap<String, Object> map) {
        return service.updateShopCart(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> addShopCart(HashMap<String, Object> map) {
        return service.addShopCart(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> deleteFromShopCart(String ids, String token) {
        return service.deleteFromShopCart(ids, token)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<RegisterResponseBean>> register(HashMap<String, Object> map) {
        return service.register(map)
                .compose(new ObservableTransformer<BaseResponse<RegisterResponseBean>, BaseResponse<RegisterResponseBean>>() {
                    @Override
                    public ObservableSource<BaseResponse<RegisterResponseBean>> apply(Observable<BaseResponse<RegisterResponseBean>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<GoodDetailResponse>> fetchGoodDetail(long goodId) {
        return service.fetchGoodDetail(goodId)
                .compose(new ObservableTransformer<BaseResponse<GoodDetailResponse>, BaseResponse<GoodDetailResponse>>() {
                    @Override
                    public ObservableSource<BaseResponse<GoodDetailResponse>> apply(Observable<BaseResponse<GoodDetailResponse>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> sendVerifyCode(HashMap<String, Object> map) {
        return service.sendVerifyCode(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<UserBean>> login(HashMap<String, Object> map) {
        return service.login(map)
                .compose(new ObservableTransformer<BaseResponse<UserBean>, BaseResponse<UserBean>>() {
                    @Override
                    public ObservableSource<BaseResponse<UserBean>> apply(Observable<BaseResponse<UserBean>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> resetPassword(HashMap<String, Object> map) {
        return service.resetPassword(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> feedback(HashMap<String, Object> map) {
        return service.feedback(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> logout(String token) {
        return service.logout(token)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> updatePassword(HashMap<String, Object> map) {
        return service.updatePassword(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> updatePersonalInfo(RequestBody body) {
        return service.updatePersonalInfo(body)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<List<Address>>> queryAddress(String token) {
        return service.queryAddress(token)
                .compose(new ObservableTransformer<BaseResponse<List<Address>>, BaseResponse<List<Address>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<Address>>> apply(Observable<BaseResponse<List<Address>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> updateAddress(RequestBody requestBody) {
        return service.updateAddress(requestBody)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> addAddress(RequestBody requestBody) {
        return service.addAddress(requestBody)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> deleteAddress(HashMap<String, Object> map) {
        return service.deleteAddress(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> uploadHeader(HashMap<String, Object> map, MultipartBody.Part file) {
        return service.uploadHeader(map, file)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<List<Order>>> fetchOrders(HashMap<String, Object> map) {
        return service.fetchOrders(map)
                .compose(new ObservableTransformer<BaseResponse<List<Order>>, BaseResponse<List<Order>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<Order>>> apply(Observable<BaseResponse<List<Order>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<AffirmResponse>> affirmOrder(RequestBody body) {
        return service.affirmOrder(body)
                .compose(new ObservableTransformer<BaseResponse<AffirmResponse>, BaseResponse<AffirmResponse>>() {
                    @Override
                    public ObservableSource<BaseResponse<AffirmResponse>> apply(Observable<BaseResponse<AffirmResponse>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<SubmitOrderBean>> submitOrder(RequestBody body) {
        return service.submitOrder(body)
                .compose(new ObservableTransformer<BaseResponse<SubmitOrderBean>, BaseResponse<SubmitOrderBean>>() {
                    @Override
                    public ObservableSource<BaseResponse<SubmitOrderBean>> apply(Observable<BaseResponse<SubmitOrderBean>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<WaitPayInfo>> fetchWaitPayInfo(long orderId, String token) {
        return service.fetchWaitPayInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<WaitPayInfo>, BaseResponse<WaitPayInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<WaitPayInfo>> apply(Observable<BaseResponse<WaitPayInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> cancelOrder(long orderId, String token) {
        return service.cancelOrder(orderId, token)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<FinishedInfo>> fetchFinishedInfo(long orderId, String token) {
        return service.fetchFinishedInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<FinishedInfo>, BaseResponse<FinishedInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<FinishedInfo>> apply(Observable<BaseResponse<FinishedInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }


    @Override
    public Observable<BaseResponse> deleteOrder(String orderIds, String token) {
        return service.deleteOrder(orderIds, token)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<CanceledInfo>> fetchCanceledInfo(long orderId, String token) {
        return service.fetchCanceledInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<CanceledInfo>, BaseResponse<CanceledInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<CanceledInfo>> apply(Observable<BaseResponse<CanceledInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<SendedInfo>> fetchSendedInfo(long orderId, String token) {
        return service.fetchSendedInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<SendedInfo>, BaseResponse<SendedInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<SendedInfo>> apply(Observable<BaseResponse<SendedInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> sureReceive(String orderId, String token) {
        return service.sureReceive(orderId, token)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<RefundingInfo>> fetchRefundingInfo(long orderId, String token) {
        return service.fetchRefundingInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<RefundingInfo>, BaseResponse<RefundingInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<RefundingInfo>> apply(Observable<BaseResponse<RefundingInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<UserBean>> fetchUserInfo(String token) {
        return service.fetchUserInfo(token)
                .compose(new ObservableTransformer<BaseResponse<UserBean>, BaseResponse<UserBean>>() {
                    @Override
                    public ObservableSource<BaseResponse<UserBean>> apply(Observable<BaseResponse<UserBean>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> salesReturn(RequestBody body) {
        return service.salesReturn(body)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<ReceivedInfo>> fetchReceivedInfo(long orderId, String token) {
        return service.fetchReceivedInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<ReceivedInfo>, BaseResponse<ReceivedInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<ReceivedInfo>> apply(Observable<BaseResponse<ReceivedInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<WaitSendInfo>> fetchWaitSendInfo(long orderId, String token) {
        return service.fetchWaitSendInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<WaitSendInfo>, BaseResponse<WaitSendInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<WaitSendInfo>> apply(Observable<BaseResponse<WaitSendInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<List<Express>>> fetchExpressList() {
        return service.fetchExpressList()
                .compose(new ObservableTransformer<BaseResponse<List<Express>>, BaseResponse<List<Express>>>() {
                    @Override
                    public ObservableSource<BaseResponse<List<Express>>> apply(Observable<BaseResponse<List<Express>>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> moneyReturn(HashMap<String, Object> map) {
        return service.moneyReturn(map)
                .compose(new ObservableTransformer<BaseResponse, BaseResponse>() {
                    @Override
                    public ObservableSource<BaseResponse> apply(Observable<BaseResponse> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<RefundedInfo>> fetchRefundedInfo(long orderId, String token) {
        return service.fetchRefundedInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<RefundedInfo>, BaseResponse<RefundedInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<RefundedInfo>> apply(Observable<BaseResponse<RefundedInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<SalesReturnedInfo>> fetchSaleReturnedInfo(long orderId, String token) {
        return service.fetchSaleRefundedInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<SalesReturnedInfo>, BaseResponse<SalesReturnedInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<SalesReturnedInfo>> apply(Observable<BaseResponse<SalesReturnedInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<SalesReturningInfo>> fetchSaleReturningInfo(long orderId, String token) {
        return service.fetchSaleReturningInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<SalesReturningInfo>, BaseResponse<SalesReturningInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<SalesReturningInfo>> apply(Observable<BaseResponse<SalesReturningInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<BaseResponse<SureReceivedInfo>> fetchSureReceivedInfo(long orderId, String token) {
        return service.fetchSureReceivedInfo(orderId, token)
                .compose(new ObservableTransformer<BaseResponse<SureReceivedInfo>, BaseResponse<SureReceivedInfo>>() {
                    @Override
                    public ObservableSource<BaseResponse<SureReceivedInfo>> apply(Observable<BaseResponse<SureReceivedInfo>> upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }
}
