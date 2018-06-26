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
}
