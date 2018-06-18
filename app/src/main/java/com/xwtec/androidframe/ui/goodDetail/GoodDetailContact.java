package com.xwtec.androidframe.ui.goodDetail;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;

import java.util.HashMap;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */

public interface GoodDetailContact {
    interface GoodDetailView extends BaseView {
        void fetchGoodDetailSuccess(GoodDetailResponse goodDetailResponse);

        void addShopCartSuccess();
    }

    interface GoodDetailPresenter {
        void fetchGoodDetail(long goodId);

        /**
         * 加入购物车
         */
        void addShopCart(HashMap<String, Object> map);
    }
}
