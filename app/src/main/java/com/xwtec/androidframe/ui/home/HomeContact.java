package com.xwtec.androidframe.ui.home;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;

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
    }

    interface HomePresenter {
        /**
         * 请求首页Banner
         */
        void getHomeBanner();

        /**
         * 请求商品列表
         */
        void getGoodList(HashMap<String,Object> map);
    }
}
