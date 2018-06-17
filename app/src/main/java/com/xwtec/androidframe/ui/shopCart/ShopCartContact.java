package com.xwtec.androidframe.ui.shopCart;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public interface ShopCartContact {
    interface ShopCartView extends BaseView {
        void fetchShopCartSuccess(List<ShopCartBean> shopCartBeanList);

        void updateShopCartSuccess(ShopCartBean shopCartBean,int goodsNum,int position);

        void deleteFromShopCartSuccess();
    }

    interface ShopCartPresenter {
        /**
         * 查询购物车列表
         *
         * @param map
         */
        void fetchShopCartData(HashMap<String, Object> map);

        /**
         * 添加
         */
        void addShopCart(HashMap<String, Object> map);

        /**
         * 修改数量
         */
        void updateShopCart(HashMap<String, Object> map,ShopCartBean shopCartBean,int position);

        /**
         * 删除
         */
        void deleteShopCart(HashMap<String, Object> map);


    }
}
