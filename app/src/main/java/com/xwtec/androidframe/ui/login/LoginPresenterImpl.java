package com.xwtec.androidframe.ui.login;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.HashMap;

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

    @Override
    public void sendVerifyCode(HashMap<String, Object> map) {
        netResourceRepo.sendVerifyCode(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.sendCodeSuccess();
                            } else {
                                view.sendCodeFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.sendCodeFail(e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void login(HashMap<String, Object> map) {
        netResourceRepo.login(map)
                .subscribe(new ResponseObserver<BaseResponse<UserBean>>(this) {
                    @Override
                    public void onNext(BaseResponse<UserBean> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.loginSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.showLoadFail(e.getMessage());
                        }
                    }
                });
    }
}
