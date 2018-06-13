package com.xwtec.androidframe.ui.main;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class MainPresenterImpl extends BasePresenter<MainContact.MainView> implements MainContact.MainPresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public MainPresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }
}
