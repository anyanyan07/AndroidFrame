package com.xwtec.androidframe.ui.shopCart;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class ShopCartPresenterImpl extends BasePresenter<ShopCartContact.ShopCartView> implements ShopCartContact.ShopCartPresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public ShopCartPresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }


}
