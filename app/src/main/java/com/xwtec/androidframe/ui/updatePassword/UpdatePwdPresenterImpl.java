package com.xwtec.androidframe.ui.updatePassword;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/22.
 * Describe:xxx
 */

public class UpdatePwdPresenterImpl extends BasePresenter<UpdatePasswordContact.UpdatePasswordView> implements UpdatePasswordContact.UpdatePasswordPresenter {

    private NetResourceRepo netResourceRepo;

    @Inject
    public UpdatePwdPresenterImpl(NetResourceRepo netResourceRepo) {
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
                                view.sendCodeSuccess(baseResponse.getMsg());
                            } else {
                                view.sendCodeFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (view != null) {
                            view.sendCodeFail(e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void updatePassword(HashMap<String, Object> map) {
        netResourceRepo.updatePassword(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.updateSuccess(baseResponse.getMsg());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (view != null) {
                            view.showLoadFail(e.getMessage());
                        }
                    }
                });
    }
}
