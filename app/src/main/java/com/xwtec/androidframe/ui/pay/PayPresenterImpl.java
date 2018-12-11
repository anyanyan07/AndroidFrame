package com.xwtec.androidframe.ui.pay;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * @Author ayy
 * @Date 2018/10/22.
 * Describe:xxx
 */

public class PayPresenterImpl extends BasePresenter<PayContact.PayView> implements PayContact.PayPresenter {

    private NetResourceRepo netResourceRepo;

    @Inject
    public PayPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void pay(RequestBody requestBody) {
        netResourceRepo.pay(requestBody)
                .subscribe(new ResponseObserver<BaseResponse<PayBean>>(this) {
                    @Override
                    public void onNext(BaseResponse<PayBean> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()){
                                view.paySuccess(baseResponse.getContent());
                            }
                        }
                    }
                });
    }
}
