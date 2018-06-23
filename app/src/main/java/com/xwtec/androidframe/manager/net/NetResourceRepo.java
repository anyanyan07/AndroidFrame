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
import com.xwtec.androidframe.ui.register.RegisterResponseBean;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
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
    Observable<BaseResponse> deleteFromShopCart(String ids);

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

}
