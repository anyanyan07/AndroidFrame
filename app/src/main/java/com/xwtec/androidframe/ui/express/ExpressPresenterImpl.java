package com.xwtec.androidframe.ui.express;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/7/1.
 * Describe:xxx
 */

public class ExpressPresenterImpl extends BasePresenter<ExpressContact.ExpressView> implements ExpressContact.ExpressPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public ExpressPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void fetchExpressList() {
        netResourceRepo.fetchExpressList()
                .subscribe(new ResponseObserver<BaseResponse<List<Express>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<Express>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchExpressSuccess(baseResponse.getContent());
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
}
