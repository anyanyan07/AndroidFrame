package com.xwtec.androidframe.ui.main;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class MainPresenter extends BasePresenter<MainConcat.MainView> implements MainConcat.MainPresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public MainPresenter( NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }

    @Override
    public void login(Map<String, String> map) {
        mNetResourceRepo.login(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            view.loginSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.loginFail();
                        }
                    }
                });
    }
}
