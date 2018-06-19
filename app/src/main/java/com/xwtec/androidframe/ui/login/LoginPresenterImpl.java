package com.xwtec.androidframe.ui.login;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/19.
 * Describe:xxx
 */

public class LoginPresenterImpl extends BasePresenter<LoginContact.LoginView> implements LoginContact.LoginPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public LoginPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }
}
