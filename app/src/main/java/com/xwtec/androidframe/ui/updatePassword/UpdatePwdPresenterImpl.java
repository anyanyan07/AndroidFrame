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
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

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
                            view.updateSuccess(baseResponse.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
