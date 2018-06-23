package com.xwtec.androidframe.ui.address;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public class AddressPresenterImpl extends BasePresenter<AddressContact.AddressView> implements AddressContact.AddressPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public AddressPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void queryAddress(String token) {
        netResourceRepo.queryAddress(token)
                .subscribe(new ResponseObserver<BaseResponse<List<Address>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<Address>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.querySuccess(baseResponse.getContent());
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