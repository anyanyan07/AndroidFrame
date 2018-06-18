package com.xwtec.androidframe.ui.register;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/18.
 * Describe:注册
 */

public class RegisterPresenterImpl extends BasePresenter<RegisterContact.RegisterView> implements RegisterContact.RegisterPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public RegisterPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void register(HashMap<String, Object> map) {
        netResourceRepo.register(map)
                .subscribe(new ResponseObserver<BaseResponse<RegisterResponseBean>>(this) {
                    @Override
                    public void onNext(BaseResponse<RegisterResponseBean> baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0) {
                                view.registerSuccess(baseResponse.getContent());
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
