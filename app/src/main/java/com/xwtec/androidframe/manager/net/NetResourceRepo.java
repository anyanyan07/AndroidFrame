package com.xwtec.androidframe.manager.net;

import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

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
}
