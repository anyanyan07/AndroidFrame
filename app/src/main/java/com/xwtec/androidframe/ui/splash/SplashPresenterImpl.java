package com.xwtec.androidframe.ui.splash;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/29.
 * Describe:xxx
 */

public class SplashPresenterImpl extends BasePresenter<SplashContact.SplashView> {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public SplashPresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }
}
