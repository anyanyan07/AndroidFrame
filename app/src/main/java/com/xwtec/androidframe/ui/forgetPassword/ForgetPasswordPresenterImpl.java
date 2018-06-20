package com.xwtec.androidframe.ui.forgetPassword;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/20.
 * Describe:xxx
 */

public class ForgetPasswordPresenterImpl extends BasePresenter<ForgetPasswordContact.ForgetPasswordView> implements ForgetPasswordContact.ForgetPasswordPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public ForgetPasswordPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }
}
