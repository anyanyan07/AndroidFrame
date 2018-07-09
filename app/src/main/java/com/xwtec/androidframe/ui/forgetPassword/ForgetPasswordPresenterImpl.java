package com.xwtec.androidframe.ui.forgetPassword;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.HashMap;

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

    @Override
    public void resetPassword(HashMap<String, Object> map) {
        netResourceRepo.resetPassword(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.resetSuccess(baseResponse.getMsg());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void sendVerifyCode(HashMap<String, Object> map) {
        netResourceRepo.sendVerifyCode(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.sendCodeSuccess(baseResponse.getMsg());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
