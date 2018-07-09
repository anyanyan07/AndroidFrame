package com.xwtec.androidframe.ui.home;

import android.widget.ImageView;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.TabBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public interface HomeContact {
    interface HomeView extends BaseView {
        void bannerSuccess(List<BannerBean> bannerBeanList);

        void goodListSuccess(List<GoodListBean> goodListBeanList);

        void goodListFail(String msg);

        void fetchGoodDefinesSuccess(List<TabBean> tabBeanList);

        void addShopSuccess(ImageView startView);
    }

    interface HomePresenter {
        /**
         * 请求首页Banner
         */
        void getHomeBanner();

        /**
         * 请求商品列表
         */
        void getGoodList(HashMap<String, Object> map);

        /**
         * 请求商品分类
         */
        void fetchGoodDefines();

        /**
         * 加入购物车
         */
        void addShopCart(HashMap<String, Object> map, ImageView startView);
    }
}
