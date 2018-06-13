package com.xwtec.androidframe.ui.home;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class HomePresenterImpl extends BasePresenter<HomeContact.HomeView> implements HomeContact.HomePresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public HomePresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }


}
