package com.xwtec.androidframe.ui.shopCart;


import android.support.v4.app.Fragment;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends BaseFragment {

    @Inject
    public ShopCartFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_cart;
    }

}
