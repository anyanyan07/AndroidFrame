package com.xwtec.androidframe.ui.setting;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/22.
 * Describe:xxx
 */

public class SettingPresenterImpl extends BasePresenter<SettingContact.SettingView> implements SettingContact.SettingPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public SettingPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void logout(String token) {
        netResourceRepo.logout(token)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            view.logoutSuccess(baseResponse.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }
}
